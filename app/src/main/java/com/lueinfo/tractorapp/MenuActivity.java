package com.lueinfo.tractorapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lueinfo.tractorapp.Utils.MenAdap;
import com.lueinfo.tractorapp.Utils.PromotionModel;
import com.lueinfo.tractorapp.Utils.Urls;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    GridView grid;
    ProgressDialog pDialog;
    RecyclerView recyclerView;
    FrameLayout searchFrame;
    MenAdap adapter;

    TextView title;
    List<PromotionModel> promotionlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        title = (TextView) findViewById(R.id.title);

        //   adapter = new CustomListAdapter(SpecialPromotion.this, web, imageId);
        grid=(GridView)findViewById(R.id.grid);

        populatedata();
    }


    public void populatedata(){

        pDialog = new ProgressDialog(MenuActivity.this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        Map<String, String> postParam= new HashMap<String, String>();
        // postParam.put("session_id", sessionid);
        postParam.put("rest_id", "1");


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                "http://condoassist2u.com/tractorapp/api/menu/getMenuItems", new JSONObject(postParam),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject objone)
                    {
                        pDialog.dismiss();
                        Log.d("tag", objone.toString());
                        try {
                           // JSONObject objone = new JSONObject(response);
                            boolean error = objone.getBoolean("error");

                            if(error){

                            }else{

                                JSONArray jsonArray = objone.getJSONArray("message");
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    PromotionModel movie = new PromotionModel();
                                    movie.setImage(obj.getString("image"));
                                    movie.setDetailtxt(obj.getString("item_name"));
                                   // movie.setTimetxt(obj.getString("created_at"));
                                    movie.setCaltxt(obj.getString("calories"));
                                    movie.setItemid(obj.getString("promotion_id"));
                                    movie.setPrice(obj.getString("price"));
                                    promotionlist.add(movie);

                                }
                                adapter = new MenAdap(MenuActivity.this,promotionlist);
                                grid.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }

                            //  adapter.notifyDataSetChanged();

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                VolleyLog.d("tag", "Error: " + error.getMessage());
                //  hideProgressDialog();
            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };

        jsonObjReq.setTag("tag");
        // Adding request to request queue
        RequestQueue queue = Volley.newRequestQueue(MenuActivity.this);
        queue.add(jsonObjReq);

    }


    
//    public void populatedata() {
//
//        pDialog = new ProgressDialog(MenuActivity.this);
//        // Showing progress dialog before making http request
//        pDialog.setMessage("Loading...");
//        pDialog.show();
//
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        String userid = preferences.getString("id", "");
//
//        final String url = "http://cloud9.condoassist2u.com/api/menu/getMenuItems";
//
//        final StringRequest movieReq = new StringRequest(url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d("rr000", response.toString());
//                        hidePDialog();
//                        try {
//                            JSONObject objone = new JSONObject(response);
//                            boolean error = objone.getBoolean("error");
//
//                            if(error){
//
//                            }else{
//
//                                JSONArray jsonArray = objone.getJSONArray("message");
//                                for (int i = 0; i < jsonArray.length(); i++) {
//
//                                    JSONObject obj = jsonArray.getJSONObject(i);
//                                    PromotionModel movie = new PromotionModel();
//                                    movie.setImage(obj.getString("image"));
//                                    movie.setDetailtxt(obj.getString("item_name"));
//                                   // movie.setTimetxt(obj.getString("created_at"));
//                                    movie.setCaltxt(obj.getString("calories"));
//                                    movie.setItemid(obj.getString("promotion_id"));
//                                    promotionlist.add(movie);
//
//                                }
//                                adapter = new MenAdap(MenuActivity.this,promotionlist);
//                                grid.setAdapter(adapter);
//                                adapter.notifyDataSetChanged();
//                            }
//
//                            //  adapter.notifyDataSetChanged();
//
//                        }catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//
//                    // notifying list adapter about data changes
//                    // so that it renders the list view with updated data
//
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//                    Toast.makeText(MenuActivity.this, "You Have Some Connectivity Issue....", Toast.LENGTH_LONG).show();
//                }
//                hidePDialog();
//
//            }
//        });
//        {
//            RequestQueue requestQueue = Volley.newRequestQueue(MenuActivity.this);
//            requestQueue.add(movieReq);
//        }
//
//    }
//    @Override
//    public void onDestroy () {
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

    @Override
    public void onClick(View v) {

        /*Intent intent = new Intent(MenuActivity.this,SpecialPromotionSearch.class);
        intent.putExtra("urlsearch","http://cloud9.condoassist2u.com/api/menu/searchForFood");
        intent.putExtra("title","Menu Detail");
        startActivity(intent);*/

        Intent intent = new Intent(MenuActivity.this,MenuDetails.class);
        startActivity(intent);

    }

}
