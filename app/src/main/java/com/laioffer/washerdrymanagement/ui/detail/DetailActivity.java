package com.laioffer.washerdrymanagement.ui.detail;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    TextView MachineID;
    TextView timeLeft;
    TextView detailCondition;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            ID = bundle.getString("ID");
            time = bundle.getString("end_time");
            condition = bundle.getString("condition");
//            type = bundle.getString("type");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail);
        String machineID = "Machine NO." + ID;
        //    UserID = bundle.getString("user");
        MachineID = findViewById(R.id.details_machine_num);
        timeLeft = findViewById(R.id.details_time_left);
        detailCondition = findViewById(R.id.detail_condition);
        MachineID.setText(machineID);
        timeLeft.setText(time);
        detailCondition.setText(condition);
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
