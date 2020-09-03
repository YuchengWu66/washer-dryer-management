package com.laioffer.washerdrymanagement.ui.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.laioffer.washerdrymanagement.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import javax.crypto.Mac;

import cz.msebera.android.httpclient.Header;

public class StartActivity extends AppCompatActivity {
   // String UserID;
    String ID;
    String type;
//    CheckBox checkbox;
    TextView MachineID;
    TextView MachineType;
    String StartURL;
    ImageView machineImage;
//    Button startbt;
//    Button reportbt;
//    Button waitbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_start);
        ID = getIntent().getStringExtra("ID");
        type = getIntent().getStringExtra("type");
        MachineID = findViewById(R.id.startmachine_id);
        MachineType = findViewById(R.id.start_type);
        MachineID.setText(ID);
        MachineType.setText(type);
        machineImage = findViewById(R.id.icon_start);
        switch (type){
            case ("Washer"):
                machineImage.setImageAlpha(R.drawable.ic_washer_available);
            case ("Dryer"):
                machineImage.setImageAlpha(R.drawable.ic_dryer_available);
            default:
                machineImage.setImageAlpha(R.drawable.ic_xiyiji);
        }
        setContentView(R.layout.fragment_start);




//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        checkbox = findViewById(R.id.checkBox_add);
//        startbt = findViewById(R.id.start_button);
//        reportbt = findViewById(R.id.report_button);
//        waitbt = findViewById(R.id.wait_button);
    }
    public void getMachineStart (View v){
        RequestParams params = new RequestParams();
        params.put("status", "reserve");
        params.put("item_id", ID);

        sendRequest(params);

        //start api with UserID and MachineID
    }
    public void gotoReport(View v){
        Intent intent = new Intent(StartActivity.this, ReportActivity.class);
        Bundle b = new Bundle();
        b.putString("ID", ID);
        intent.putExtras(b);
        startActivity(intent);
        //navigate to report with machineID
    }
    public void prepareMachine(View V){
        //api to send 15 min prepare time

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
                Toast.makeText(StartActivity.this, "Send Report Failed", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
