package com.lueinfo.tractorapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.FacebookSdk;
import com.lueinfo.tractorapp.FCM.TokenSave;
import com.lueinfo.tractorapp.Utils.SaveCredentials;
import com.lueinfo.tractorapp.Utils.SaveUserId;
import com.lueinfo.tractorapp.Utils.Urls;
import com.lueinfo.tractorapp.Utils.UserSessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
     Spinner language;
    RelativeLayout relativeLang;
    TextView signup,mforgot_txt;
    Button login;
    private ProgressDialog pDialog;
    EditText musenmelgin_edtxt,mpasslgin;
    CheckBox hidePass;
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signup=(TextView)findViewById(R.id.signup);
        login=(Button)findViewById(R.id.loginbtn);

        mforgot_txt = findViewById(R.id.forgot);

        hidePass = (CheckBox)findViewById(R.id.hidePass);

        mforgot_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPassword.class));
            }
        });

        session = new UserSessionManager(getApplicationContext());

        musenmelgin_edtxt = findViewById(R.id.usernmelgin_edttxt);
        mpasslgin = findViewById(R.id.passwordlgin_edttxt);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Login();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), SignUp.class));

            }
        });

        hidePass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked)
                {
                    mpasslgin.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else
                {
                    mpasslgin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }

            }
        });

    }

    public void Login() {

        String user = musenmelgin_edtxt.getText().toString().trim();
        String pass = mpasslgin.getText().toString().trim();
        final String tokenid = TokenSave.getInstance(LoginActivity.this).getDeviceToken();

        if (TextUtils.isEmpty(user)) {
            musenmelgin_edtxt.requestFocus();
            musenmelgin_edtxt.setError("This Field Is Mandatory");
        } else if (TextUtils.isEmpty(pass)) {
            mpasslgin.requestFocus();
            mpasslgin.setError("This Field Is Mandatory");
        }  else {

            pDialog = new ProgressDialog(LoginActivity.this);
            // Showing progress dialog before making http request
            pDialog.setMessage("Loading...");
            pDialog.show();

            Map<String, String> postParam = new HashMap<String, String>();
            // postParam.put("session_id", sessionid);
            postParam.put("username", user);
            postParam.put("password", pass);
            postParam.put("reg_token", tokenid);


            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                    Urls.login, new JSONObject(postParam),
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject objone) {
                            pDialog.dismiss();
                            Log.d("tag", objone.toString());
                            try {
                                // JSONObject objone = new JSONObject(response);
                                boolean check = objone.getBoolean("error");
                                JSONObject newobj = objone.getJSONObject("message");

                                if (!check) {

                                    String userid = newobj.getString("user_id");
                                    String usernme = newobj.getString("user_name");
                                    String mobile = newobj.getString("mobile");
                                    String email = newobj.getString("email");
                                    String c9id = newobj.getString("c9");
                                    String geo_wineid = newobj.getString("geo_wine");
                                    String epicsoulid = newobj.getString("epicsoul");

                                    session.createUserLoginSession(userid);
                                    SaveUserId.getInstance(getApplicationContext()).saveuserId(userid);

                                    SaveCredentials.getInstance(getApplicationContext()).saveserveraddress(c9id,geo_wineid,epicsoulid);


                                    Intent showadintnt = new Intent(LoginActivity.this, SelectRestaurant.class);

                                    showadintnt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    showadintnt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                    startActivity(showadintnt);
                                    finish();

                                }
                                else {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
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
            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            queue.add(jsonObjReq);

        }
    }

}
