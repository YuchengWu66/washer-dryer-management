package com.laioffer.washerdrymanagement.ui.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {
    String UserID;
    String machineID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    public void getMachineStart (View v){
        //start api with UserID and MachineID
    }
    public void gotoReport(View v){
        Intent intent = new Intent(this, ReportActivity.class);
        finish();
        startActivity(intent);
        //navigate to report with machineID
    }
    public void prepareMachine(View V){
        //api to reservation

    }
}