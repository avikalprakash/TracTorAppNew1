package com.lueinfo.tractorapp.GeowinePack;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lueinfo.tractorapp.EditProfile;
import com.lueinfo.tractorapp.ProfileActivity;
import com.lueinfo.tractorapp.R;
import com.lueinfo.tractorapp.Utils.SaveUserId;
import com.lueinfo.tractorapp.Utils.Urls;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class GeoProfileActivity extends AppCompatActivity implements View.OnClickListener{
    Button profile;
    TextView mnametxt,memailtxt,mhometxt,mmobiletxt,mgendertxt,msatattxt;
    ImageView mlogo;

    private ProgressDialog pDialog;
    String photo,email,name,mobile,home,gender,stat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_profile);
        profile=(Button)findViewById(R.id.profileBtn);
        profile.setOnClickListener(this);

        mlogo = (ImageView) findViewById(R.id.logo);

        mlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadPhoto(mlogo,50,50);

            }
        });

        mnametxt = (TextView) findViewById(R.id.usernme_txt);
        memailtxt = (TextView) findViewById(R.id.email_txt);
        mhometxt = (TextView) findViewById(R.id.home_txt);
        mmobiletxt = (TextView) findViewById(R.id.mobile_txt);
        mgendertxt = (TextView) findViewById(R.id.gender_txt);
        msatattxt = (TextView) findViewById(R.id.state_txt);

        populatedata();
    }

    private void loadPhoto(ImageView imageView, int width, int height) {

        ImageView tempImageView = imageView;


        AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.zoomview,
                (ViewGroup) findViewById(R.id.layout_root));
        ImageView image = (ImageView) layout.findViewById(R.id.fullimage);
        image.setImageDrawable(tempImageView.getDrawable());
        imageDialog.setView(layout);
        imageDialog.setPositiveButton(getResources().getString(R.string.ok_button), new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });


        imageDialog.create();
        imageDialog.show();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.profileBtn:

                Intent intent = new Intent(GeoProfileActivity.this,GeoEditProfile.class);
                intent.putExtra("photo",photo);
                intent.putExtra("name",name);
                intent.putExtra("email",email);
                intent.putExtra("mobile",mobile);
                intent.putExtra("home",home);
                intent.putExtra("gender",gender);
                intent.putExtra("state",stat);
                startActivity(intent);
                finish();
                break;
        }
    }

    public void populatedata(){

        pDialog = new ProgressDialog(GeoProfileActivity.this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        String userid = SaveUserId.getInstance(GeoProfileActivity.this).getUserId();

        final String url = Urls.fetchprofiledetail+userid;

        StringRequest movieReq = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("rr000", response.toString());
                        hidePDialog();

                        // Parsing json
                        try {

                            JSONObject obj = new JSONObject(response);

                            JSONObject jobj  = obj.getJSONObject("message");

                            photo = jobj.getString("photo");
                            email = jobj.getString("email");
                            name = jobj.getString("user_name");
                            mobile = jobj.getString("mobile");
                            home = jobj.getString("home_phone");
                            gender = jobj.getString("gender");
                            stat = jobj.getString("state");


                            Picasso.with(getApplicationContext()).load(photo).into(mlogo);
                            mnametxt.setText(name);
                            memailtxt.setText(email);
                            mmobiletxt.setText(mobile);
                            if(home.equals("null")){
                                mhometxt.setText("Home");
                            }else{

                                mhometxt.setText(home);
                            }
                            if(gender.equals("null")){

                                mgendertxt.setText("Gender");

                            }else {

                                mgendertxt.setText(gender);
                            }
                            if(stat.equals("null")){
                                msatattxt.setText("State");
                            }else {

                                msatattxt.setText(stat);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    // notifying list adapter about data changes
                    // so that it renders the list view with updated data

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(GeoProfileActivity.this,"You Have Some Connectivity Issue..", Toast.LENGTH_LONG).show();
                }
                hidePDialog();

            }
        });{

            RequestQueue requestQueue = Volley.newRequestQueue(GeoProfileActivity.this);
            requestQueue.add(movieReq);
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
