package com.lueinfo.tractorapp.GeowinePack;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.lueinfo.tractorapp.Gallery;
import com.lueinfo.tractorapp.R;
import com.lueinfo.tractorapp.Utils.PromotionModel;
import com.lueinfo.tractorapp.Utils.SaveUserId;
import com.lueinfo.tractorapp.Utils.Urls;
import com.lueinfo.tractorapp.Utils.UserSessionManager;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeoGallery extends AppCompatActivity {
    ImageView mimg1,mimg2,mimg3,mimg4,mimg5,mimg6,mgallery;
    Button msbmtbtn;
    EditText captiontxt;

    private int PICK_IMAGE_REQUEST = 1;
    private int CAMERA_REQUEST = 2;
    UserSessionManager session;
    private static final int PICK_CROPIMAGE = 4;
    Uri imageUri;
    String phoneno;
    String monname = null;
    AlertDialog dialog;
    String strtim;
    TextView meditbtn;
    Spinner mspinner,mcityspinner;
    String mobno;
    private ProgressDialog pDialog;
    String a[]   = new String[10];;
    private Bitmap bitmap;
    private static int RESULT_LOAD_IMAGE = 1;

    List<ImageView> promo = new ArrayList<>();
    String newurl ="";
    String upltxt ="";
    List<PromotionModel> promo123 = new ArrayList<>();
    List<String> promo12345 = new ArrayList<>();

    List<TextView> uptxtx = new ArrayList<>();

    List<String> addtxt = new ArrayList<>();

    TextView muptxt1, muptxt2, muptxt3, muptxt4, muptxt5, muptxt6;
    LinearLayout mlin1,mlin2,mli3,mli4,mli5,mli6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_gallery);
        mimg1 = findViewById(R.id.img1);
        mimg2 = findViewById(R.id.img2);
        mimg3 = findViewById(R.id.img3);
        mimg4 = findViewById(R.id.img4);
        mimg5 = findViewById(R.id.img5);
        mimg6 = findViewById(R.id.img6);

        promo.add(mimg1);
        promo.add(mimg2);
        promo.add(mimg3);
        promo.add(mimg4);
        promo.add(mimg5);
        promo.add(mimg6);


        muptxt1 =  findViewById(R.id.uptxt1);
        muptxt2 =  findViewById(R.id.uptxt2);
        muptxt3 =  findViewById(R.id.uptxt3);
        muptxt4 =  findViewById(R.id.uptxt4);
        muptxt5 =  findViewById(R.id.uptxt5);
        muptxt6 =  findViewById(R.id.uptxt6);

        uptxtx.add(muptxt1);
        uptxtx.add(muptxt2);
        uptxtx.add(muptxt3);
        uptxtx.add(muptxt4);
        uptxtx.add(muptxt5);
        uptxtx.add(muptxt6);

        mlin1 = findViewById(R.id.lin1);
        mlin2 = findViewById(R.id.lin2);
        mli3 = findViewById(R.id.lin3);
        mli4 = findViewById(R.id.lin4);
        mli5 = findViewById(R.id.lin5);
        mli6 = findViewById(R.id.lin6);

        mlin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadPhoto(mimg1,100,100,muptxt1.getText().toString());
            }
        });
        mlin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadPhoto(mimg2,50,50,muptxt2.getText().toString());
            }
        });
        mli3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadPhoto(mimg3,50,50,muptxt3.getText().toString());
            }
        });
        mli4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadPhoto(mimg4,50,50,muptxt4.getText().toString());
            }
        });
        mli5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadPhoto(mimg5,50,50,muptxt4.getText().toString());
            }
        });
        mli6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadPhoto(mimg6,50,50,muptxt5.getText().toString());
            }
        });

        ShowPic();

        mgallery = findViewById(R.id.camera);
        msbmtbtn = findViewById(R.id.submit);
        msbmtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubmitPic();
            }
        });
        captiontxt = findViewById(R.id.captiontxt);

        mgallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
    }

    private void loadPhoto(ImageView imageView, int width, int height,String text) {

        ImageView tempImageView = imageView;


        AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.zoomview,
                (ViewGroup) findViewById(R.id.layout_root));
        ImageView image = (ImageView) layout.findViewById(R.id.fullimage);
        TextView textView = (TextView) layout.findViewById(R.id.custom_fullimage_placename);
        textView.setText("Uploaded By "+text);
        image.setImageDrawable(tempImageView.getDrawable());
        imageDialog.setView(layout);
        imageDialog.setPositiveButton(getResources().getString(R.string.ok_button), new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });


        imageDialog.create();
        imageDialog.show();
    }

    private void showFileChooser() {
        try {
            if (android.os.Build.VERSION.SDK_INT >= 23) {

                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);


            } else {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);

            }
        }catch (Exception e){

            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }

    }

    public String getStringImage(Bitmap bmp)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if(bmp == null){
        }else {
            bmp.compress(Bitmap.CompressFormat.JPEG, 30, baos);
        }
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void SubmitPic() {

        String user = captiontxt.getText().toString().trim();

        String userid = SaveUserId.getInstance(GeoGallery.this).getUserId();
        final String image = getStringImage(bitmap);

        if (TextUtils.isEmpty(user)) {
            captiontxt.requestFocus();
            captiontxt.setError("This Field Is Mandatory");
        }  else {

            pDialog = new ProgressDialog(GeoGallery.this);
            // Showing progress dialog before making http request
            pDialog.setMessage("Loading...");
            pDialog.show();

            Map<String, String> postParam = new HashMap<String, String>();
            // postParam.put("session_id", sessionid);
            postParam.put("rest_id", "2");
            postParam.put("user_id", userid);
            postParam.put("caption", user);
            postParam.put("pic", image);

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                    Urls.uploadgal, new JSONObject(postParam),
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject objone) {
                            pDialog.dismiss();
                            Log.d("tag", objone.toString());
                            try {

                                boolean check = objone.getBoolean("error");


                                if (!check) {

                                    String newobj = objone.getString("message");

                                    AlertDialog.Builder builder = new AlertDialog.Builder(GeoGallery.this);
                                    builder.setMessage(newobj)
                                            .setNegativeButton("ok", null)
                                            .create()
                                            .show();


                                }
                                else {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(GeoGallery.this);
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
            RequestQueue queue = Volley.newRequestQueue(GeoGallery.this);
            queue.add(jsonObjReq);

        }
    }

    public void ShowPic() {

        //  String user = captiontxt.getText().toString().trim();

        String userid = SaveUserId.getInstance(GeoGallery.this).getUserId();
        final String image = getStringImage(bitmap);

        pDialog = new ProgressDialog(GeoGallery.this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        Map<String, String> postParam = new HashMap<String, String>();
        // postParam.put("session_id", sessionid);
        postParam.put("rest_id", "2");


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Urls.showgal, new JSONObject(postParam),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject objone) {
                        pDialog.dismiss();
                        Log.d("tag", objone.toString());
                        try {

                            boolean check = objone.getBoolean("error");

                            if (!check) {

                                JSONArray jsonArray = objone.getJSONArray("message");
                                Log.d("mnmvbfj",""+jsonArray.length());
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    promo12345.add(obj.getString("photo"));
                                    addtxt.add(obj.getString("username"));

                                }

                                for(int ut = 0; ut < addtxt.size(); ut++)
                                {
                                    upltxt =  addtxt.get(ut);
                                    TextView tt =  uptxtx.get(ut);
                                    tt.setText(upltxt);
                                }

                                for(int ijk = 0; ijk < promo12345.size();ijk++)
                                {
                                    newurl =  promo12345.get(ijk);
                                    ImageView v =  promo.get(ijk);
                                    Picasso.with(GeoGallery.this).load(newurl).into(v);

                                }

                            }
                            else {

                                AlertDialog.Builder builder = new AlertDialog.Builder(GeoGallery.this);
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
        RequestQueue queue = Volley.newRequestQueue(GeoGallery.this);
        queue.add(jsonObjReq);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();


                try {
                    bitmap = getBitmapFromUri(selectedImage);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                mgallery.setImageBitmap(bitmap);

            }
        }


    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }
}
