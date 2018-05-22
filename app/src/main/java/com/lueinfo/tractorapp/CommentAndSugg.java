package com.lueinfo.tractorapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.lueinfo.tractorapp.Utils.AudioRecorder;
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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class CommentAndSugg extends AppCompatActivity implements View.OnTouchListener {

    Button msubmitbtn;
    ImageView soundButton;

    private static final String TAG="CommentAndSuggestion";

    //components
    private Button mBtnStartRecording;
    private Button mBtnStopRecording;
    private Chronometer mChronometer;
    RatingBar mratingbar;
    EditText mratnme,mratemail,mratsuggestion;

    private String audioFileName="";
    final AudioRecorder recorder = new AudioRecorder();

    String getrat;
    String adb64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_and_sugg);

        mratnme = (EditText) findViewById(R.id.ratnme_edtxt);
        mratemail  = (EditText) findViewById(R.id.ratemail_edtxt);
        mratsuggestion = (EditText) findViewById(R.id.ratsuggestion_edtxt);

        msubmitbtn = (Button) findViewById(R.id.submit_btn);
        msubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new CommentSugg().execute();

            }
        });



        soundButton = (ImageView) findViewById(R.id.soundBtn);
        mChronometer=(Chronometer)findViewById(R.id.chrono);
        soundButton.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch(motionEvent.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                try {
                    startRecording();
                    soundButton.setBackgroundResource(R.drawable.recordcom_green);
                }catch (Exception e){
                }
                break;
            case MotionEvent.ACTION_UP:
                try {
                    stopRecording();
                    soundButton.setBackgroundResource(R.drawable.recordcom);
                    soundButton.setEnabled(false);
                }catch (Exception e){
                }
                break;
        }
        return true;
    }

    public  void checkaudio(String selectedPath){

    }

    private void startRecording(){
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();

        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);

        //  otp = String.valueOf(n);

        try{
            String myRecording="Comment&Suggestion-" + n ;
            Log.i(TAG, "Start Recording");
            recorder.startRecording(myRecording);

        }catch(IOException e){
            Log.e(TAG,"IOException error");
            e.printStackTrace();
        }

    }

    private void stopRecording(){

        mChronometer.stop();

        String adfile = recorder.a1;
        Log.e("adfffff","IOE"+adfile);

        adb64 = encodeAudio(adfile);
        Log.e("321021","85264"+adb64);


        try{
            recorder.stop();
        }catch(IOException e){
            e.printStackTrace();
        }

    }


    public String encodeAudio(String selectedPath) {

        byte[] audioBytes;
        String AudioString = null;
        try {

            // Just to check file size.. Its is correct i-e; Not Zero
            File audioFile = new File(selectedPath);
            long fileSize = audioFile.length();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileInputStream fis = new FileInputStream(new File(selectedPath));
            byte[] buf = new byte[1024];
            int n;
            while (-1 != (n = fis.read(buf)))
                baos.write(buf, 0, n);
            audioBytes = baos.toByteArray();

            // Here goes the Base64 string
            AudioString = Base64.encodeToString(audioBytes, Base64.DEFAULT);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return AudioString;
    }

    class CommentSugg extends AsyncTask<String, Void, String> {

        private ProgressDialog pDialog;

        String name = mratnme.getText().toString().trim();
        String email = mratemail.getText().toString().trim();
        String comsug = mratsuggestion.getText().toString().trim();


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CommentAndSugg.this);
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
                HttpPost httpPost = new HttpPost("http://condoassist2u.com/tractorapp/api/addCommentSuggestion");
                httpPost.setHeader("Content-type", "application/json");
                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("name", name);
                jsonObject.accumulate("email", email);
                jsonObject.accumulate("comment_suggestion", comsug);
                jsonObject.accumulate("voice", adb64);
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

                    String msg=objone.getString("message");
                    AlertDialog.Builder builder = new AlertDialog.Builder(CommentAndSugg.this);
                    builder.setMessage(msg)
                            .setNegativeButton("ok", null)
                            .create()
                            .show();
                }else{

                    String msg=objone.getString("message");
                    AlertDialog.Builder builder = new AlertDialog.Builder(CommentAndSugg.this);
                    builder.setMessage(msg)
                            .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    CommentAndSugg.this.finish();
                                }
                            })
                            .create()
                            .show();

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
            while ((line = bufferedReader.readLine()) != null)
            {
                sb.append(line);
            }
            return_text = sb.toString();
            Log.d("return1230", "" + return_text);
        } catch (Exception e) {}
        return return_text;
    }

}
