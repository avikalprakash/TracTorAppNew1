package com.lueinfo.tractorapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class PaymentMethod extends AppCompatActivity implements View.OnClickListener {
     TextView dateText;
    Calendar myCalendar;
    Button proceed;
    DatePickerDialog.OnDateSetListener date,date1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        dateText=(TextView)findViewById(R.id.date);
        proceed=(Button)findViewById(R.id.proceed);
        proceed.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.proceed:
                proceedMessage();
                break;
        }
    }
    public void proceedMessage(){
        final Dialog dialog = new Dialog(PaymentMethod.this);
        LayoutInflater inflater = LayoutInflater.from(PaymentMethod.this);
        View subView = inflater.inflate(R.layout.alert_payment, null);
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

}
