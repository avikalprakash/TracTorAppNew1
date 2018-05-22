package com.lueinfo.tractorapp.GeowinePack;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lueinfo.tractorapp.MenuActivity;
import com.lueinfo.tractorapp.MenuDetails;
import com.lueinfo.tractorapp.R;
import com.lueinfo.tractorapp.Utils.MenAdap;
import com.lueinfo.tractorapp.Utils.PromotionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeoMenuActivity extends AppCompatActivity implements View.OnClickListener {
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
        setContentView(R.layout.activity_geo_menu);
        title = (TextView) findViewById(R.id.title);

        //   adapter = new CustomListAdapter(SpecialPromotion.this, web, imageId);
        grid=(GridView)findViewById(R.id.grid);

        populatedata();
    }

    public void populatedata(){

        pDialog = new ProgressDialog(GeoMenuActivity.this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        Map<String, String> postParam= new HashMap<String, String>();
        // postParam.put("session_id", sessionid);
        postParam.put("rest_id", "2");


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
                                adapter = new MenAdap(GeoMenuActivity.this,promotionlist);
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
        RequestQueue queue = Volley.newRequestQueue(GeoMenuActivity.this);
        queue.add(jsonObjReq);

    }

    @Override
    public void onClick(View v) {

        /*Intent intent = new Intent(MenuActivity.this,SpecialPromotionSearch.class);
        intent.putExtra("urlsearch","http://cloud9.condoassist2u.com/api/menu/searchForFood");
        intent.putExtra("title","Menu Detail");
        startActivity(intent);*/

        Intent intent = new Intent(GeoMenuActivity.this,MenuDetails.class);
        startActivity(intent);

    }
}
