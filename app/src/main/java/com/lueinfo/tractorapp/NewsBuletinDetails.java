package com.lueinfo.tractorapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsBuletinDetails extends AppCompatActivity {
   String title="";
    String time="";
    String description="";
    String image="";
    TextView listDetails, titleName, timeText;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_buletin_details);
        title=getIntent().getStringExtra("title");
        time=getIntent().getStringExtra("time");
        description=getIntent().getStringExtra("description");
        image=getIntent().getStringExtra("image");
        imageView=(ImageView)findViewById(R.id.logout);
        Picasso.with(getApplicationContext()).load(String.valueOf(image)).into(imageView);
       // Picasso.with(getApplicationContext()).load(String.valueOf(image)).resize(312, 162).into(imageView);
        String htmlText = " %s ";

        String myData = "When the predecessor of international fast food restaurant chain Burger King (BK) first opened in 1953, its menu predominantly consisted of hamburgers, French fries, soft drinks, milkshakes, and desserts. After being acquired by its Miami, Florida franchisees and renamed in 1954, BK began expanding its menu by adding the Whopper sandwich in 1957, and has since added non-beef items such as chicken, fish, and vegetarian offerings, including salads and meatless sandwiches. Other additions include a breakfast menu and beverages such as Icees, juices, and bottled waters. As the company expanded both inside and outside the United States, it introduced localized versions of its products that conform to regional tastes and cultural or religious beliefs. To generate additional sales, BK occasionally introduces limited-time offers of special versions of its products, or brings out completely new products intended for either long- or short-term sales. Not all of these products and services have been successful; in 1992, Burger King introduced limited table service featuring special dinner platters, but this concept failed to generate interest and was discontinued";

        listDetails = (TextView) findViewById(R.id.textDetails);
        titleName = (TextView) findViewById(R.id.titleName);
        timeText = (TextView) findViewById(R.id.time);
        timeText.setText(time);
        titleName.setText(title);

        String alphaOnly = description.replaceAll("&#39;","'");
        String alphaO = alphaOnly.replaceAll("&nbsp;"," ");
        String alpha = alphaO.replaceAll("&amp;","&");

        String alpha123 = alpha.replaceAll("&eacute;","e");

        String alpha12345 = alpha123.replaceAll("&euml;","&");

        listDetails.setText(alpha12345);

    }

  /*  @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), NewsBuletin.class));
    }*/
}
