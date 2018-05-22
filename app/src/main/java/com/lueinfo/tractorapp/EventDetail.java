package com.lueinfo.tractorapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
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
import com.lueinfo.tractorapp.Utils.CalenderEvensEntity;
import com.lueinfo.tractorapp.Utils.SaveUserId;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EventDetail extends AppCompatActivity {

    ImageView image_notice;
    TextView tital_notification,by,description,timing3;
    View view1;
    CalenderEvensEntity calenderEvensEntity;
    private ProgressDialog pDialog;
    String from="",s1,s2,eventid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        view1=findViewById( R.id. view1 );
        tital_notification=(TextView) view1.findViewById( R.id.tital_notification );
        by=(TextView) view1.findViewById( R.id.by );
        description=(TextView) view1.findViewById( R.id.description );
        timing3=(TextView) view1.findViewById( R.id.timing3 );
        image_notice=(ImageView)findViewById( R.id.image_notice );


        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG )
                        .setAction( "Action", null ).show();*/
                finish();
            }
        } );
        //  tital_notification.setText( ""+calenderEvensEntity.getTitle() );
        Intent intent=getIntent();
        calenderEvensEntity=(CalenderEvensEntity) intent.getSerializableExtra(  "eventid" );

        if(eventid.equals("")) {

            try {
                eventid = calenderEvensEntity.getId();
            }catch (Exception e){

                eventid = getIntent().getStringExtra("event_id");
            }
        }else{
            eventid = getIntent().getStringExtra("event_id");
        }
      //  populatedata();

      new MakeIntro().execute();

    }

    class MakeIntro extends AsyncTask<String, Void, String> {

        private ProgressDialog pDialog;

        String userid = SaveUserId.getInstance(EventDetail.this).getUserId();


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(EventDetail.this);
            pDialog.setMessage("Please Wait ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... args) {
            String s = "";


            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://condoassist2u.com/tractorapp/api/event/getEventDetails");
                httpPost.setHeader("Content-type", "application/json");
                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("rest_id", "1");
                jsonObject.accumulate("event_id", eventid);

                StringEntity stringEntity = new StringEntity(jsonObject.toString());
                httpPost.setEntity(stringEntity);
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                s = readadsResponse(httpResponse);
                Log.d("tag1", " " + s);
            } catch (Exception exception) {
                exception.printStackTrace();

                Log.d("espone",exception.toString());

            }

            return s;

        }
        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            pDialog.dismiss();
            try {
                JSONObject objone = new JSONObject(json);
                boolean check = objone.getBoolean("error");
                JSONArray jsonArray = objone.getJSONArray("message");
                if (check) {


                } else {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Log.d("111sizeeeem", "kk1111 " + jsonArray.length());
                        JSONObject jobject = jsonArray.getJSONObject(i);
                        String title  = jobject.getString("title");
                        String descr = jobject.getString("description");
                        String image = jobject.getString("image");
                        String ftime = jobject.getString("from_time");
                        String ttime = jobject.getString("to_time");
                        String postedby = jobject.getString("posted_by");
                        String eventdate = jobject.getString("event_date");

                        tital_notification.setText(title);

                        String alphaOnly = descr.replaceAll("&#39;","'");
                        String alphaO = alphaOnly.replaceAll("&nbsp;"," ");
                        String alpha = alphaO.replaceAll("&amp;","&");
                        String alpha12345 = alpha.replaceAll("&eacute;","e");

                        String alph = alpha12345.replaceAll("&euml;","&");

                        description.setText( Html.fromHtml(alph));
                        timing3.setText( "(Timing) From:- "+ftime+" To:- "+ttime);
                        by.setText( ""+ postedby+"\n Date:"+eventdate);
                        Picasso.with(EventDetail.this)
                                .load(image)
                                .placeholder(R.drawable.newc9lg)   // optional
                                .error(R.drawable.newc9lg)     // optional
                                .into(image_notice);
                    }


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String readadsResponse(HttpResponse httpResponse) {

        InputStream is = null;
        String return_text = "";
        try {
            is = httpResponse.getEntity().getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return_text = sb.toString();
            Log.d("return1230", "" + return_text);
        } catch (Exception e) {

        }
        return return_text;
    }





//    public void populatedata() {
//
//        pDialog = new ProgressDialog(EventDetail.this);
//        // Showing progress dialog before making http request
//        pDialog.setMessage("Loading...");
//        pDialog.show();
//
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        String userid = preferences.getString("id", "");
//
//        final String url = "http://cloud9.condoassist2u.com/api/event/getEventDetails/"+eventid;
//
//        StringRequest movieReq = new StringRequest(url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d("rr000", response.toString());
//                        hidePDialog();
//
//                        try {
//                            JSONObject objone = new JSONObject(response);
//                            boolean check = objone.getBoolean("error");
//                            JSONArray jsonArray = objone.getJSONArray("message");
//                            if (check) {
//
//
//                            } else {
//                                for (int i = 0; i < jsonArray.length(); i++) {
//                                    Log.d("111sizeeeem", "kk1111 " + jsonArray.length());
//                                    JSONObject jobject = jsonArray.getJSONObject(i);
//                                    String title  = jobject.getString("title");
//                                    String descr = jobject.getString("description");
//                                    String image = jobject.getString("image");
//                                    String ftime = jobject.getString("from_time");
//                                    String ttime = jobject.getString("to_time");
//                                    String postedby = jobject.getString("posted_by");
//                                    String eventdate = jobject.getString("event_date");
//
//                                    tital_notification.setText(title);
//                                    description.setText( Html.fromHtml(descr));
//                                    timing3.setText( "(Timing) From:- "+ftime+" To:- "+ttime);
//                                    by.setText( ""+ postedby+"\n Date:"+eventdate);
//                                    Picasso.with(EventDetail.this)
//                                            .load(image)
//                                            .placeholder(R.drawable.newc9lg)   // optional
//                                            .error(R.drawable.newc9lg)     // optional
//                                            .into(image_notice);
//                                }
//
//
//                            }
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }      // notifying list adapter about data changes
//                    // so that it renders the list view with updated data
//
//                },  new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//                    Toast.makeText(EventDetail.this, "You Have Some Connectivity Issue..", Toast.LENGTH_LONG).show();
//                }
//                hidePDialog();
//
//            }
//        });
//        {
//
//            RequestQueue requestQueue = Volley.newRequestQueue(EventDetail.this);
//            requestQueue.add(movieReq);
//        }
//
//
//    }
//
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        hidePDialog();
//    }
//
//    private void hidePDialog() {
//        if (pDialog != null) {
//            pDialog.dismiss();
//            pDialog = null;
//        }
//    }


}
