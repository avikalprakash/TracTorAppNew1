package com.lueinfo.tractorapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.lueinfo.tractorapp.Utils.Urls;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TableReservation extends AppCompatActivity {
    Button reservetble_btn;

    EditText mdate,mtime,mnopeople,mname,memail,mmobile;
    Button msmokinbtn,mnonsmokingbtn;
    String smokevalue,nonsmokevalue;
    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_reservation);

        myCalendar = Calendar.getInstance();

        mdate = (EditText) findViewById(R.id.sdate);
        mtime = (EditText) findViewById(R.id.stime);
        mnopeople = (EditText) findViewById(R.id.nopeople_edtxt);
        mname = (EditText) findViewById(R.id.nopeople_edtxt);
        memail =  (EditText) findViewById(R.id.email_edtxt);
        mmobile =  (EditText) findViewById(R.id.mobile_edtxt);

        msmokinbtn = (Button) findViewById(R.id.smoke_btn);
        msmokinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                smokevalue = "smoking";
                mnonsmokingbtn.setBackgroundColor(getResources().getColor(R.color.colorwhite));
                mnonsmokingbtn.setHintTextColor(getResources().getColor(android.R.color.darker_gray));

                msmokinbtn.setBackgroundColor(getResources().getColor(R.color.colorgold));
                msmokinbtn.setHintTextColor(getResources().getColor(R.color.colorwhite));

            }
        });


        mnonsmokingbtn = (Button) findViewById(R.id.nonsmoke_btn);
        mnonsmokingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                smokevalue = "nonsmoking";
                mnonsmokingbtn.setBackgroundColor(getResources().getColor(R.color.colorgold));
                mnonsmokingbtn.setHintTextColor(getResources().getColor(R.color.colorwhite));

                msmokinbtn.setBackgroundColor(getResources().getColor(R.color.colorwhite));
                msmokinbtn.setHintTextColor(getResources().getColor(android.R.color.darker_gray));

            }
        });


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        mdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(TableReservation.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        mtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(TableReservation.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mtime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        reservetble_btn=(Button)findViewById(R.id.reservetble_btn);
        reservetble_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ReserveTable().execute();
            }
        });
    }


    private void updateLabel() {

        String myFormat = "dd-MM-yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
        mdate.setText(sdf.format(myCalendar.getTime()));

    }


    class ReserveTable extends AsyncTask<String, Void, String> {

        private ProgressDialog pDialog;

        String date = mdate.getText().toString().trim();
        String time = mtime.getText().toString().trim();
        String email = memail.getText().toString().trim();
        String noofp = mnopeople.getText().toString().trim();
        String name = mname.getText().toString().trim();
        String mobile = mmobile.getText().toString().trim();
        String value = smokevalue;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(TableReservation.this);
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
                HttpPost httpPost = new HttpPost(Urls.tablereserve);
                httpPost.setHeader("Content-type", "application/json");
                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("date", date);
                jsonObject.accumulate("time", time);
                jsonObject.accumulate("customer_type", smokevalue);
                jsonObject.accumulate("no_of_prople", noofp);
                jsonObject.accumulate("name", name);
                jsonObject.accumulate("email", email);
                jsonObject.accumulate("mobile", mobile);
                jsonObject.accumulate("rest_id", "1");

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
                boolean check  = objone.getBoolean("error");
                if(check) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(TableReservation.this);
                    builder.setMessage("Please Try Again")
                            .setNegativeButton("ok", null)
                            .create()
                            .show();
                }else{

                    proceedMessage();

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



    public void proceedMessage(){
        final Dialog dialog = new Dialog(TableReservation.this);
        LayoutInflater inflater = LayoutInflater.from(TableReservation.this);
        View subView = inflater.inflate(R.layout.alert_table_reservation, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(subView);
        // dialog.setTitle("Title...");
        Button dialogButton = (Button) dialog.findViewById(R.id.ok);
        //final MyEditText otp=(MyEditText)dialog.findViewById(R.id.otp);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TableReservation.this.finish();

            }
        });

        dialog.show();
    }
}
