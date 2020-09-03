package com.laioffer.washerdrymanagement.ui.detail;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.laioffer.washerdrymanagement.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;
import org.w3c.dom.Text;

import cz.msebera.android.httpclient.Header;

public class DetailActivity extends AppCompatActivity {
    String ID;
    String time;
    String condition;
    String type;
    String StartURL;
    ImageView conditionImage;
    TextView viewtitle;
    TextView MachineID;
    TextView timeLeft;
    TextView detailCondition;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail);
        ID = getIntent().getStringExtra("ID");
        type = getIntent().getStringExtra("type");
        time = getIntent().getStringExtra("end_time");
        condition = getIntent().getStringExtra("condition");
        String machineID = "Machine NO." + ID;
        //    UserID = bundle.getString("user");
        conditionImage = findViewById(R.id.icon_detail);
        viewtitle = findViewById(R.id.details_title_text_view);
//        MachineID = findViewById(R.id.details_machine_num);
        timeLeft = findViewById(R.id.details_time_left);

        if (type == "Washer") {
            switch (condition) {
                case ("reserve"):
                    conditionImage.setImageAlpha(R.drawable.ic_washer_using);
                case ("available"):
                    conditionImage.setImageAlpha(R.drawable.ic_washer_available);
                case ("finished"):
                    conditionImage.setImageAlpha(R.drawable.ic_washer_finished);
                case ("occupied"):
                    conditionImage.setImageAlpha(R.drawable.ic_washer_occupied);
            }
            if (time == "0"){
                conditionImage.setImageAlpha(R.drawable.ic_washer_finished);
            }
        }else if (type == "Dryer"){
            switch (condition) {
                case ("reserve"):
                    conditionImage.setImageAlpha(R.drawable.ic_dryer_using);
                case ("available"):
                    conditionImage.setImageAlpha(R.drawable.ic_dryer_available);
                case ("finished"):
                    conditionImage.setImageAlpha(R.drawable.ic_dryer_finished);
                case ("occupied"):
                    conditionImage.setImageAlpha(R.drawable.ic_dryer_occupied);
            }
            if (time == "0"){
                conditionImage.setImageAlpha(R.drawable.ic_dryer_occupied);
            }
        }else{
            conditionImage.setImageAlpha(R.drawable.ic_xiyiji);
        }
        viewtitle.setText("#"+ID+" Washing Machine");
//        MachineID.setText("NO."+machineID);
        timeLeft.setText(time + "mins left");
//        detailCondition.setText(condition);
        StartURL = ""; // Set URL


        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    public void terminateWasher(View V){
        RequestParams params = new RequestParams();
        params.put("status", "available");
        params.put("item_id", ID);

        sendRequest(params);
        // send terminate signal
    }
    public void GotoReport(View V){
        Intent intent = new Intent(this, ReportActivity.class);
        Bundle b = new Bundle();
        b.putString("ID", ID);
        intent.putExtras(b);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        //navigate to report with machineID
        //navigate to report fragment
    }
    private void sendRequest(RequestParams params){
        AsyncHttpClient client = new AsyncHttpClient();

        client.post(StartURL, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                Log.d("Start", "Success Start!" + response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response){
                Log.e("Start","fail" + e.toString());
                Log.d("Start", "Status code" + statusCode);
                Toast.makeText(DetailActivity.this, "Send Report Failed", Toast.LENGTH_SHORT).show();
            }

        });
    }

}
