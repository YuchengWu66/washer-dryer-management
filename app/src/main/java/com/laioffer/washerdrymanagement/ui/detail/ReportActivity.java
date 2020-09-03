package com.laioffer.washerdrymanagement.ui.detail;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.laioffer.washerdrymanagement.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ReportActivity extends AppCompatActivity {
    String ID;
    ImageView machineImage;
    private EditText editTextMachineID;
    private EditText editTexterror;
    private String REPORT_URL; // add later
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_report);
        ID = getIntent().getStringExtra("ID");
        String type = getIntent().getStringExtra("type");
        editTextMachineID = findViewById(R.id.report_machineID);
        editTexterror = findViewById(R.id.report_error);
        editTextMachineID.setText(ID);

        machineImage = findViewById(R.id.icon_report);
        switch (type){
            case ("Washer"):
                machineImage.setImageAlpha(R.drawable.ic_washer_available);
            case ("Dryer"):
                machineImage.setImageAlpha(R.drawable.ic_dryer_available);
            default:
                machineImage.setImageAlpha(R.drawable.ic_xiyiji);
        }
//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        setContentView(R.layout.fragment_report);
    }

    public void sendReport(View V){
        String title = editTextMachineID.getText().toString();
        String errorMessage = editTexterror.getText().toString();
        RequestParams params = new RequestParams();
        params.put("item_id", title);
        params.put("issueType", 1); // figure later
        params.put("issue", errorMessage);
        sendRequest(params);
        //send api
    }

    private void sendRequest(RequestParams params){
        AsyncHttpClient client = new AsyncHttpClient();

        client.post(REPORT_URL, params, new JsonHttpResponseHandler(){
           @Override
           public void onSuccess(int statusCode, Header[] headers, JSONObject response){
               Log.d("Report", "Success sending Report!" + response.toString());
           }

           @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response){
               Log.e("Report","fail" + e.toString());
               Log.d("Report", "Status code" + statusCode);
               Toast.makeText(ReportActivity.this, "Send Report Failed", Toast.LENGTH_SHORT).show();
           }

        });
    }
}
