package com.lueinfo.tractorapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Transaction extends AppCompatActivity implements View.OnClickListener {
    ImageView detailsDownImageView, detailsUpImageView;
    RelativeLayout detailsLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        detailsDownImageView=(ImageView)findViewById(R.id.detailsDownImageView);
        detailsUpImageView=(ImageView)findViewById(R.id.detailsUpImageView);
        detailsLayout=(RelativeLayout) findViewById(R.id.detailsLayout);
        detailsDownImageView.setOnClickListener(this);
        detailsUpImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.detailsDownImageView:
                detailsLayout.setVisibility(View.VISIBLE);
                detailsDownImageView.setVisibility(View.INVISIBLE);
                detailsUpImageView.setVisibility(View.VISIBLE);
                break;
            case R.id.detailsUpImageView:
                detailsDownImageView.setVisibility(View.VISIBLE);
                detailsUpImageView.setVisibility(View.INVISIBLE);
                detailsLayout.setVisibility(View.GONE);
                break;
        }
    }
}
