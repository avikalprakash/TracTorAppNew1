package com.lueinfo.tractorapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bluejamesbond.text.style.TextAlignment;
import com.lueinfo.tractorapp.FCM.TokenSave;
import com.lueinfo.tractorapp.Utils.PromotionModel;
import com.lueinfo.tractorapp.Utils.SaveCredentials;
import com.lueinfo.tractorapp.Utils.SaveUserId;
import com.lueinfo.tractorapp.Utils.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AboutUs extends AppCompatActivity {

    WebView para1,para2,para3,para5,para6,para7,para8,para9,para4;
    private ProgressDialog pDialog;

    String p1,p2,p3,p4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        para1 = (WebView) findViewById(R.id.para1);

        para2 = (WebView) findViewById(R.id.para2);

        para3 = (WebView) findViewById(R.id.para3);

        para4 = (WebView) findViewById(R.id.para4);

        para5 = (WebView) findViewById(R.id.para5);
        para6 = (WebView) findViewById(R.id.para6);
        para7 = (WebView) findViewById(R.id.para7);
        para8 = (WebView) findViewById(R.id.para8);
        para9 = (WebView) findViewById(R.id.para9);

//        String text;
//        text = "<html><body><p align=\"justify\">";
//        view.loadData(text, "text/html", "utf-8");

        String youtContentStr = String.valueOf(Html
                .fromHtml("<![CDATA[<body style=\"text-align:justify;color:#cda84d; background-color: #000000;\">"
                        + "For a truly fresh vision of fine dining with an amazing view \n" +
                        "of the bustling heart of the city, CLOUD 9, located on 9th Floor of Pavilion Elite,  Kuala Lumpur has the perfect culinary experience that you need to savour.\n"
                        + "</body>]]>"));

        String youtCo = String.valueOf(Html
                .fromHtml("<![CDATA[<body style=\"text-align:justify;color:#cda84d;background-color: #000000; \">"
                        + "Exclusively designedin the concept of fine dining, this elegant fine cuisine hotspot is deliciously and innovatively finessed by our Korean-born Executive Chef Ryan Lee, caters to a comfortable 36 persons seating"
                        + "</body>]]>"));

        String pp3 = String.valueOf(Html
                .fromHtml("<![CDATA[<body style=\"text-align:justify;color:#cda84d;background-color: #000000; \">"
                        + "A unique diversity to the white tablecloths concept of fine dining, CLOUD 9 provides a private space for those who seeks to have the highest quality of food in the allure of a fine dining culture with formal atmosphere and in the company of family and friends at the same table."
                        + "</body>]]>"));

        String pp4 = String.valueOf(Html
                .fromHtml("<![CDATA[<body style=\"text-align:justify;color:#cda84d; background-color: #000000;\">"
                        + "A fine choice for allinformal and formal gatherings alike, we are equipped with facilities to cater all conferencing requirements and infused karaoke entertainment with HD Projection and quality sound system to unwind in private."
                        + "</body>]]>"));

        String pp5 = String.valueOf(Html
                .fromHtml("<![CDATA[<body style=\"text-align:justify;color:#cda84d; background-color: #000000;\">"
                        + "Unwind at our Social Bar and enjoy the bustling view over Jalan Bukit Bintang while you wait to be seated to enjoy a fine dining experience or just chill to the fine selection of wines and liquors."
                        + "</body>]]>"));

        String pp6 = String.valueOf(Html
                .fromHtml("<![CDATA[<body style=\"text-align:justify;color:#cda84d;background-color: #000000; \">"
                        + "Ultra-modern, creative and sublime; Executive Chef Ryan Lee's culinary delights will enthral all food enthusiasts alike."
                        + "</body>]]>"));

        String pp7 = String.valueOf(Html
                .fromHtml("<![CDATA[<body style=\"text-align:justify;color:#cda84d;background-color: #000000; \">"
                        + "EXECUTIVE CHEF RYAN LEE\n" +
                        "Ryan Lee Sangpil, hailing from Republic of Korea at the age of 34 years has over 12 years of experience to his illustrious career in the culinary industry.\n"
                        + "</body>]]>"));

        String pp8 = String.valueOf(Html
                .fromHtml("<![CDATA[<body style=\"text-align:justify;color:#cda84d; background-color: #000000;\">"
                        + "Having won many medals in cooking competitions in Seoul during his early years of his culinary career, his career escalated him from his home country to work with International Hotels and Michelin-Starred Chefs in Qatar, Belgium, Singapore and now Malaysia.\n" +
                        "From a humble beginning at a French Restaurant in Millennium Seoul Hilton Hotel, he ascended in the world of fine cookery through plain hard work and his intense passion of culinary arts that has brought him to L’Air du Temps (Two MICHELIN Stars) in Belgium, Jung Sik Dang (Two MICHELIN Stars) in Seoul, Korea and in Singapore’s only Three MICHELIN Stars restaurant.\n"
                        + "</body>]]>"));

        String pp9 = String.valueOf(Html
                .fromHtml("<![CDATA[<body style=\"text-align:justify;color:#cda84d; background-color: #000000;\">"
                        + "His technical cooking style and theory is a sight to behold as he infuses classic dishes with a modern balance, sense and texture as well as his unique presentation of flavour, style and taste."
                        + "</body>]]>"));

        para1.loadData(youtContentStr, "text/html", "UTF-8");
        para2.loadData(youtCo, "text/html", "UTF-8");
        para3.loadData(pp3, "text/html", "UTF-8");
        para4.loadData(pp4, "text/html", "UTF-8");

        para5.loadData(pp5, "text/html", "UTF-8");
        para6.loadData(pp6, "text/html", "UTF-8");
        para7.loadData(pp7, "text/html", "UTF-8");
        para8.loadData(pp8, "text/html", "UTF-8");

        para9.loadData(pp9, "text/html", "UTF-8");

//        AboutUs();

    }

    public void AboutUs() {

            pDialog = new ProgressDialog(AboutUs.this);
            // Showing progress dialog before making http request
            pDialog.setMessage("Loading...");
            pDialog.show();

            Map<String, String> postParam = new HashMap<String, String>();
            // postParam.put("session_id", sessionid);
            postParam.put("rest_id", "1");


            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                   "http://condoassist2u.com/tractorapp/api/aboutus", new JSONObject(postParam),
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject objone) {
                            pDialog.dismiss();
                            Log.d("tag", objone.toString());
                            try {
                                // JSONObject objone = new JSONObject(response);
                                boolean check = objone.getBoolean("error");

                                if (!check)
                                {
                                    JSONArray newobj = objone.getJSONArray("message");
                                    for (int i = 0; i < newobj.length(); i++) {

                                        JSONObject obj = newobj.getJSONObject(i);

                                        p1 = obj.getString("para1");
                                        p2 = obj.getString("para2");
                                        p3 = obj.getString("para3");
                                        p4 = obj.getString("para4");

                                    }


                                    String youtContentStr = String.valueOf(Html
                                            .fromHtml("<![CDATA[<body style=\"text-align:justify;color:#cda84d; background-color: #000000;\">"
                                                    + p1
                                                    + "</body>]]>"));

                                    String youtCo = String.valueOf(Html
                                            .fromHtml("<![CDATA[<body style=\"text-align:justify;color:#cda84d;background-color: #000000; \">"
                                                    + p2
                                                    + "</body>]]>"));

                                    String pp3 = String.valueOf(Html
                                            .fromHtml("<![CDATA[<body style=\"text-align:justify;color:#cda84d;background-color: #000000; \">"
                                                    + p3
                                                    + "</body>]]>"));

                                    String pp4 = String.valueOf(Html
                                            .fromHtml("<![CDATA[<body style=\"text-align:justify;color:#cda84d; background-color: #000000;\">"
                                                    + p4
                                                    + "</body>]]>"));

                                    para1.loadData(youtContentStr, "text/html", "UTF-8");
                                    para2.loadData(youtCo, "text/html", "UTF-8");
                                    para3.loadData(pp3, "text/html", "UTF-8");
                                    para4.loadData(pp4, "text/html", "UTF-8");
                                }
                                else {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(AboutUs.this);
                                    builder.setMessage("Please Retry")
                                            .setNegativeButton("ok", null)
                                            .create()
                                            .show();


                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                  //  pDialog.dismiss();
                    VolleyLog.d("tag", "Error: " + error.getMessage());
                    //  hideProgressDialog();
                }
            }) {

                /**
                 * Passing some request headers
                 */
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    return headers;
                }


            };

            jsonObjReq.setTag("tag");
            // Adding request to request queue
            RequestQueue queue = Volley.newRequestQueue(AboutUs.this);
            queue.add(jsonObjReq);

        }
    }

