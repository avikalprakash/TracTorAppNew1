package com.lueinfo.tractorapp.GeowinePack;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lueinfo.tractorapp.Adapter.AdapterGeoNewsBuletin;
import com.lueinfo.tractorapp.Adapter.AndroidImageAdapternew;
import com.lueinfo.tractorapp.Adapter.BuletinSlideShow;
import com.lueinfo.tractorapp.Adapter.NewsBuletinListDetails;
import com.lueinfo.tractorapp.Adapter.NewsBuletinListFragment;
import com.lueinfo.tractorapp.NewsBuletin;
import com.lueinfo.tractorapp.NewsBuletinDetails;
import com.lueinfo.tractorapp.R;
import com.lueinfo.tractorapp.Utils.SaveUserId;
import com.lueinfo.tractorapp.Utils.Urls;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GeoNewsBuletin extends AppCompatActivity implements NewsBuletinListFragment.OnListFragmentInteractionListener {
    ViewPager mViewPager;
    private int delay = 3000;
    private int page = 0;
    Handler handler1;
    Runnable runnable;
    public static Context context;
    RecyclerView recycler_view;
    TextView timeText;
    ListView listCustomer;
    ArrayList<BuletinSlideShow> SliderImage = new ArrayList<BuletinSlideShow>();
    public static ArrayList<NewsBuletinListDetails> buletinListDetailses = new ArrayList<>();
    ProgressDialog pDialog;
    AndroidImageAdapternew adapterView;
    ArrayList<String> ImageList = new ArrayList<>();
    AdapterGeoNewsBuletin adapterGeoNewsBuletin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_news_buletin);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        listCustomer=(ListView)findViewById(R.id.listCustomer);
        handler1=new Handler();
        context=this;
        pDialog = new ProgressDialog(GeoNewsBuletin.this);
        timeText=(TextView)findViewById(R.id.timeText);
        //  DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        timeText.setText(date);
        listCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(GeoNewsBuletin.this, NewsBuletinDetails.class);
                i.putExtra("title",buletinListDetailses.get(position).getTitle());
                i.putExtra("time",buletinListDetailses.get(position).getCreated_at());
                i.putExtra("description", buletinListDetailses.get(position).getDescription());
                i.putExtra("image",buletinListDetailses.get(position).getImage());
                startActivity(i);
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                page = position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        new MakeIntro().execute();
        new NewsSlideShow().execute();
    }

    @Override
    public void onListFragmentInteraction(NewsBuletinListDetails item) {

    }



    class MakeIntro extends AsyncTask<String, Void, String> {

        private ProgressDialog pDialog;

        String userid = SaveUserId.getInstance(GeoNewsBuletin.this).getUserId();


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(GeoNewsBuletin.this);
            pDialog.setMessage("Please Wait ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
            buletinListDetailses=new ArrayList<>();
            buletinListDetailses.clear();
        }

        @Override
        protected String doInBackground(String... args) {
            String s = "";


            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://condoassist2u.com/tractorapp/api/getNewsAndBuletin");
                httpPost.setHeader("Content-type", "application/json");
                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("rest_id", "2");

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
                JSONObject jsonObject=new JSONObject(json);
                boolean error=jsonObject.getBoolean("error");

                if(!error) {
                    JSONArray jsonArray = jsonObject.getJSONArray("message");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        NewsBuletinListDetails newsBuletinListDetails = new NewsBuletinListDetails();
                        newsBuletinListDetails.setTitle(jsonObject1.getString("title"));
                        newsBuletinListDetails.setCreated_at(jsonObject1.getString("created_at"));
                        newsBuletinListDetails.setDescription(jsonObject1.getString("description"));
                        newsBuletinListDetails.setImage(jsonObject1.getString("image"));
                        buletinListDetailses.add(newsBuletinListDetails);
                        String tit = newsBuletinListDetails.getTitle();
                        Log.d("fffffff", tit);
                        adapterGeoNewsBuletin=new AdapterGeoNewsBuletin(GeoNewsBuletin.this, buletinListDetailses);
                        listCustomer.setAdapter(adapterGeoNewsBuletin);
                    }
                }
            } catch (JSONException e) {}
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


    @Override
    public void onPause() {
        super.onPause();
        handler1.removeCallbacks(runnable);
    }


    @Override
    public void onResume() {
        super.onResume();
        handler1.postDelayed(runnable, delay);
    }


    class NewsSlideShow extends AsyncTask<String, Void, String>
    {
        private ProgressDialog pDialog;

        String userid = SaveUserId.getInstance(GeoNewsBuletin.this).getUserId();


        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(GeoNewsBuletin.this);
            pDialog.setMessage("Please Wait ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
            buletinListDetailses=new ArrayList<>();
        }
        @Override
        protected String doInBackground(String... args) {
            String s = "";


            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(Urls.newsBuletinSlideshow);
                httpPost.setHeader("Content-type", "application/json");
                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("rest_id", "2");

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

                JSONObject jsonObject=new JSONObject(json);
                boolean error=jsonObject.getBoolean("error");
                if (!error)
                {
                    JSONArray slidShowArry=jsonObject.getJSONArray("message");
                    for (int j = 0; j < slidShowArry.length(); j++)
                    {
                        BuletinSlideShow buletinSlideShow = new BuletinSlideShow();
                        JSONObject jobject = slidShowArry.getJSONObject(j);
                        buletinSlideShow.setSliderImage(jobject.getString("image"));
                        SliderImage.add(buletinSlideShow);
                        ImageList.add(jobject.getString("image"));
                    }

                }

                if (ImageList.size() > 0)
                {
                    adapterView = new AndroidImageAdapternew(GeoNewsBuletin.this, ImageList);
                    mViewPager.setAdapter(adapterView);
                    runnable = new Runnable()
                    {
                        public void run()
                        {
                            if (adapterView.getCount() == page)
                            {
                                page = 0;
                            } else
                            {
                                page++;
                            }
                            mViewPager.setCurrentItem(page, true);
//                            handler1.postDelayed(this, delay);
                        }
                    };handler1.postDelayed(runnable, delay);
                }
                if (pDialog.isShowing())
                    pDialog.dismiss();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
