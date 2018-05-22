package com.lueinfo.tractorapp.GeowinePack;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.lueinfo.tractorapp.QRActivity;
import com.lueinfo.tractorapp.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class GeoQRActivity extends AppCompatActivity {
    TextView mintronametxt, mintrophonetxt;
    ImageView mqrimg;
    private ProgressDialog pDialog;
    Button mbackbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_qr);
        mqrimg = (ImageView) findViewById(R.id.imageQR);

        mintronametxt = (TextView) findViewById(R.id.introducerName);

        mintrophonetxt = (TextView) findViewById(R.id.introducerContact);

        mbackbtn = findViewById(R.id.back_btn);
        mbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GeoQRActivity.this.finish();
            }
        });

//        String intr = getIntent().getStringExtra("name");
//        String intrphone = getIntent().getStringExtra("phone");
//        String qrimg = getIntent().getStringExtra("qrcode");
//
//
//        mintronametxt.setText(intr);
//        mintrophonetxt.setText(intrphone);

        populatedata();
    }

    public void populatedata() {

        pDialog = new ProgressDialog(GeoQRActivity.this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        SharedPreferences preferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String userid = preferences.getString("id", "");

        final String url = "http://condoassist2u.com/tractorapp/api/getQrCode/"+userid;

        StringRequest movieReq = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("rr000", response.toString());
                        hidePDialog();

                        try {
                            JSONObject objone = new JSONObject(response);
                            boolean check  = objone.getBoolean("error");
                            if(check) {

                                AlertDialog.Builder builder = new AlertDialog.Builder(GeoQRActivity.this);
                                builder.setMessage("Please Try Again")
                                        .setNegativeButton("ok", null)
                                        .create()
                                        .show();
                            }else{

                                JSONObject jobj  = objone.getJSONObject("message");

                                mintronametxt.setText(jobj.getString("introducer_name"));
                                mintrophonetxt.setText(jobj.getString("introducer_phone"));
                                Picasso.with(getApplicationContext()).load(jobj.getString("qr_image")).into(mqrimg);
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
                    Toast.makeText(GeoQRActivity.this, "You Have Some Connectivity Issue..", Toast.LENGTH_LONG).show();
                }
                hidePDialog();

            }
        });
        {

            RequestQueue requestQueue = Volley.newRequestQueue(GeoQRActivity.this);
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
