package com.lueinfo.tractorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SongRequestPay extends AppCompatActivity {
    Button buy_credits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_request_pay);
        buy_credits=(Button)findViewById(R.id.buy_credits);
        buy_credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PaymentMethod.class));
            }
        });
    }
}
