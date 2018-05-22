package com.lueinfo.tractorapp;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lueinfo.tractorapp.Utils.SaveUserId;
import com.lueinfo.tractorapp.Utils.Urls;
import com.squareup.picasso.Picasso;

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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EditProfile extends AppCompatActivity implements View.OnClickListener {

    Button msavebtn;
    ImageView mfimage;
    String photo, email, mobile, home, gender, state, name;

    TextView mnametxt;
    EditText memailtxt, mmobiletxt, mhometxt, mgendertxt, mstatetxt;

    Uri imageUri;

    private int PICK_IMAGE_REQUEST = 1;
    private int CAMERA_REQUEST = 2;

    AlertDialog dialog;

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mfimage = (ImageView) findViewById(R.id.logomimf);

        mnametxt = (TextView) findViewById(R.id.nametxt);
        memailtxt = (EditText) findViewById(R.id.emai_edtxt);
        mhometxt = (EditText) findViewById(R.id.home_edtxt);
        mmobiletxt = (EditText) findViewById(R.id.mobile_rdtxt);

        mgendertxt = (EditText) findViewById(R.id.gender_edtxt);
        mstatetxt = (EditText) findViewById(R.id.state_edtxt);

        msavebtn = (Button) findViewById(R.id.save_btn);
        msavebtn.setOnClickListener(this);


        photo = getIntent().getStringExtra("photo");
        email = getIntent().getStringExtra("email");
        mobile = getIntent().getStringExtra("mobile");
        home = getIntent().getStringExtra("home");
        gender = getIntent().getStringExtra("gender");
        state = getIntent().getStringExtra("state");
        name = getIntent().getStringExtra("name");


        Picasso.with(getApplicationContext()).load(photo).into(mfimage);

        mnametxt.setText(name);
        memailtxt.setText(email);
        mmobiletxt.setText(mobile);

        if (home.equals("null")) {
            mhometxt.setText("Home");
        } else {

            mhometxt.setText(home);
        }
        if (gender.equals("null")) {
            mgendertxt.setText("Gender");
        } else {

            mgendertxt.setText(gender);
        }
        if (state.equals("null")) {
            mstatetxt.setText("State");
        } else {

            mstatetxt.setText(state);
        }

        mfimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mbuilder = new AlertDialog.Builder(EditProfile.this);
                View mview = getLayoutInflater().inflate(R.layout.chooseimage, null);
                Button mtakephoto = (Button) mview.findViewById(R.id.imagebycamera);
                mtakephoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ContentValues values = new ContentValues();
                        values.put(MediaStore.Images.Media.TITLE, "New Picture");
                        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                        imageUri = getContentResolver().insert(
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(intent, CAMERA_REQUEST);


                    }
                });

                Button mtakegallery = (Button) mview.findViewById(R.id.imagebygallery);
                mtakegallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        showFileChooser();

                    }
                });
                mbuilder.setView(mview);
                dialog = mbuilder.create();
                dialog.show();
            }

        });

    }

    private void showFileChooser() {
        try {
            if (android.os.Build.VERSION.SDK_INT >= 23) {

                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);


            } else {

                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, ""), PICK_IMAGE_REQUEST);

            }
        } catch (Exception e) {

            //   Toast.makeText(getContext(),e.toString(),Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View v) {

        new EditPro().execute();

    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(EditProfile.this, ProfileActivity.class));
        finish();

        super.onBackPressed();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode >= PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {


            Uri filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
                mfimage.setImageBitmap(bitmap);
                dialog.dismiss();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (requestCode == CAMERA_REQUEST) {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(
                        getContentResolver(), imageUri);
                mfimage.setImageBitmap(bitmap);
                dialog.dismiss();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if(bmp == null){

        }else {
            bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        }
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    class EditPro extends AsyncTask<String, Void, String> {

        private ProgressDialog pDialog;

        String userid = SaveUserId.getInstance(EditProfile.this).getUserId();
        //   String namestr = mnametxt.getText().toString().trim();
        String emailstr = memailtxt.getText().toString().trim();
        String hometxt = mhometxt.getText().toString().trim();
        String mobiletxt = mmobiletxt.getText().toString().trim();

        String gendertxt  = mgendertxt.getText().toString().trim();
        String stattxt = mstatetxt.getText().toString().trim();

        String image = getStringImage(bitmap);


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(EditProfile.this);
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
                HttpPost httpPost = new HttpPost(Urls.editprofile);
                httpPost.setHeader("Content-type", "application/json");
                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("user_id", userid);
                jsonObject.accumulate("email", emailstr);
                jsonObject.accumulate("mobile", mobiletxt);
                jsonObject.accumulate("home_phone", hometxt);
                jsonObject.accumulate("gender", gendertxt);
                jsonObject.accumulate("state", stattxt);
                jsonObject.accumulate("photo", image);

                Log.d("img000",""+image);

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

                    AlertDialog.Builder builder = new AlertDialog.Builder(EditProfile.this);
                    builder.setMessage("Please Try Again")
                            .setNegativeButton("ok", null)
                            .create()
                            .show();
                }else{

//                    Intent showadintnt = new Intent(EditProfile.this, SendingArrow.class);
//                    startActivity(showadintnt);
//                    finish();

                    AlertDialog.Builder builder = new AlertDialog.Builder(EditProfile.this);
                    builder.setMessage("Your Profile Is Updated")
                            .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(EditProfile.this, ProfileActivity.class));
                                    finish();
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
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return_text = sb.toString();
            Log.d("return1230", "" + return_text);
        } catch (Exception e) {

        }
        return return_text;
    }




}
