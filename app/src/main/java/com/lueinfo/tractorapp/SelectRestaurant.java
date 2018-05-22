package com.lueinfo.tractorapp;

import android.*;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lueinfo.tractorapp.GeowinePack.GEOWineNut;
import com.lueinfo.tractorapp.Utils.AbsRuntimePermission;
import com.lueinfo.tractorapp.Utils.UserSessionManager;

public class SelectRestaurant extends AbsRuntimePermission {

    RelativeLayout relativeLayout1, relativeLayout2, relativeLayout3;
    UserSessionManager session;
    ImageView logout;
    private static final int REQUEST_PERMISSION = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_restaurant);

        session = new UserSessionManager(getApplicationContext());

        relativeLayout1=(RelativeLayout)findViewById(R.id.relative1);
        relativeLayout2=(RelativeLayout)findViewById(R.id.relative2);
        relativeLayout3=(RelativeLayout)findViewById(R.id.relative3);
        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(getApplicationContext(), Cloud9Home.class));
            }
        });
        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), GEOWineNut.class));
            }
        });
        relativeLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  startActivity(new Intent(getApplicationContext(), Epicssoul.class));
            }
        });

        logout=(ImageView) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceedMessage();
            }
        });

        if (session.checkLogin()) {
            finish();
        }

        requestAppPermissions(new String[]{

                        android.Manifest.permission.CAMERA,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE},

                R.string.msg, REQUEST_PERMISSION);


    }

    @Override
    public void onPermissionsGranted(int requestCode) {

    }

    public void proceedMessage()
    {
        final Dialog dialog = new Dialog(SelectRestaurant.this);
        LayoutInflater inflater = LayoutInflater.from(SelectRestaurant.this);
        View subView = inflater.inflate(R.layout.alert_logout, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(subView);
        // dialog.setTitle("Title...");
        Button yes = (Button) dialog.findViewById(R.id.yes);
        Button no = (Button) dialog.findViewById(R.id.no);
        //final MyEditText otp=(MyEditText)dialog.findViewById(R.id.otp);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                session.logoutUser();
                SelectRestaurant.this.finish();

            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        dialog.show();
    }


}
