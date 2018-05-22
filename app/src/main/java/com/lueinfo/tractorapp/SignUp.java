package com.lueinfo.tractorapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lueinfo.tractorapp.Utils.Urls;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    EditText musernme_edtxt,memail_edtxt,mmobileno_edtxt,mpass_edtxt,mspinchk_edtxt;
    Spinner mgender_spinnertxt;
    Button msignupbtn;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        musernme_edtxt = (EditText) findViewById(R.id.uername_edtxt);
        memail_edtxt = (EditText) findViewById(R.id.email_edtxt);
        mmobileno_edtxt = (EditText) findViewById(R.id.mobileno_edtxt);
        mpass_edtxt = (EditText) findViewById(R.id.pass_edtxt);
        mgender_spinnertxt = (Spinner) findViewById(R.id.spinner1);

        mspinchk_edtxt = (EditText) findViewById(R.id.spin_edtxt);

        msignupbtn  = (Button) findViewById(R.id.signup);

        msignupbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Addcart();
            }
        });

    }

    public void Addcart() {

        String user = musernme_edtxt.getText().toString().trim();
        String email = memail_edtxt.getText().toString().trim();
        String mobile = mmobileno_edtxt.getText().toString().trim();
        String pass = mpass_edtxt.getText().toString().trim();
        String gender = mgender_spinnertxt.getSelectedItem().toString().trim();

        //  String spinch = mpass_edtxt.getText().toString().trim();

        if (TextUtils.isEmpty(user)) {
            musernme_edtxt.requestFocus();
            musernme_edtxt.setError("This Field Is Mandatory");
        } else if (gender.equals("Gender")) {
            mspinchk_edtxt.requestFocus();
            mspinchk_edtxt.setError("This Field Is Mandatory");
        } else if (TextUtils.isEmpty(email)) {
            memail_edtxt.requestFocus();
            memail_edtxt.setError("This Field Is Mandatory");
        } else if (TextUtils.isEmpty(mobile)) {
            mmobileno_edtxt.requestFocus();
            mmobileno_edtxt.setError("This Field Is Mandatory");
        } else if (TextUtils.isEmpty(pass)) {
            mpass_edtxt.requestFocus();
            mpass_edtxt.setError("This Field Is Mandatory");
        } else {

            pDialog = new ProgressDialog(SignUp.this);
            // Showing progress dialog before making http request
            pDialog.setMessage("Loading...");
            pDialog.show();

            Map<String, String> postParam = new HashMap<String, String>();
            // postParam.put("session_id", sessionid);
            postParam.put("username", user);
            postParam.put("mobile", mobile);
            postParam.put("email", email);
            postParam.put("password", pass);
            postParam.put("gender", gender);

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                    Urls.register, new JSONObject(postParam),
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject objone) {
                            pDialog.dismiss();
                            Log.d("tag", objone.toString());
                            try {
                                // JSONObject objone = new JSONObject(response);
                                boolean check = objone.getBoolean("error");

                                if (!check) {

                                    String messg = objone.getString("message");
                                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                                    builder.setMessage(messg)
                                            .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    startActivity(new Intent(SignUp.this,Varification.class));
                                                }
                                            })
                                            .create()
                                            .show();
                                } else
                                    {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                                    builder.setMessage("Please Retry")
                                            .setNegativeButton("ok", null)
                                            .create()
                                            .show();
                                    }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    , new Response.ErrorListener()
                 {

                @Override
                public void onErrorResponse(VolleyError error)
                {
                    pDialog.dismiss();
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
            RequestQueue queue = Volley.newRequestQueue(SignUp.this);
            queue.add(jsonObjReq);

        }
    }

}
