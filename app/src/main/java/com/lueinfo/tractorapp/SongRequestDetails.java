package com.lueinfo.tractorapp;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class SongRequestDetails extends AppCompatActivity implements View.OnClickListener {
   Button normalSong, requestSong, paidSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_request_details);
        normalSong=(Button)findViewById(R.id.normalSong);
        requestSong=(Button)findViewById(R.id.requestSong);
        paidSong=(Button)findViewById(R.id.paidSong);
        normalSong.setOnClickListener(this);
        requestSong.setOnClickListener(this);
        paidSong.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.normalSong:
                proceedMessage1();
                break;
            case R.id.requestSong:
                proceedMessage2();
                break;
            case R.id.paidSong:
                proceedMessage3();
                break;
        }
    }


    public void proceedMessage1(){
        final Dialog dialog = new Dialog(SongRequestDetails.this);
        LayoutInflater inflater = LayoutInflater.from(SongRequestDetails.this);
        View subView = inflater.inflate(R.layout.alert_normal_song_request, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(subView);
        // dialog.setTitle("Title...");
        Button dialogButton = (Button) dialog.findViewById(R.id.ok);
        //final MyEditText otp=(MyEditText)dialog.findViewById(R.id.otp);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void proceedMessage2(){
        final Dialog dialog = new Dialog(SongRequestDetails.this);
        LayoutInflater inflater = LayoutInflater.from(SongRequestDetails.this);
        View subView = inflater.inflate(R.layout.alert_request_song, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(subView);
        // dialog.setTitle("Title...");
        Button dialogButton = (Button) dialog.findViewById(R.id.ok);
        //final MyEditText otp=(MyEditText)dialog.findViewById(R.id.otp);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(getApplicationContext(), SongRequestPay.class));
            }
        });

        dialog.show();
    }


    public void proceedMessage3(){
        final Dialog dialog = new Dialog(SongRequestDetails.this);
        LayoutInflater inflater = LayoutInflater.from(SongRequestDetails.this);
        View subView = inflater.inflate(R.layout.alert_paid_song_request, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(subView);
        // dialog.setTitle("Title...");
        Button dialogButton = (Button) dialog.findViewById(R.id.ok);
        //final MyEditText otp=(MyEditText)dialog.findViewById(R.id.otp);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(getApplicationContext(), SongRequestPay.class));
            }
        });

        dialog.show();
    }

}
