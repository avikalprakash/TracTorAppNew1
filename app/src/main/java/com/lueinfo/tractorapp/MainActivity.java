package com.lueinfo.tractorapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;



import java.util.Locale;



public class MainActivity extends AppCompatActivity {

    Button mclanguage,mskip;

    private static Locale myLocale;
    TextView mTitle;

    //Shared Preferences Variables
    private static final String Locale_Preference = "Locale Preference";
    private static final String Locale_KeyValue = "Saved Locale";
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    int i= 0;
    String appversion;
    ImageView mimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //   mclanguage = (Button) findViewById(R.id.lang_btn);
        mclanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLangDialog();
            }
        });

     //   mimg = (ImageView) findViewById(R.id.img_second);
     //   Picasso.with(getApplicationContext()).load(Url.serverAddresscore+"schoolfileservice?key=f1S1c3H5o7O9l0b8pl&opid=4&schooldetail="+ Url.schoolid).placeholder(R.drawable.download).into(mimg);

      //  mskip = (Button) findViewById(R.id.skip_btn);
        mskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(MainActivity.this,PublicDashboard.class));
                finish();
            }
        });
        sharedPreferences = getSharedPreferences(Locale_Preference, Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        loadLocale();

     //   appversion = SaveCredentials.getInstance(getApplicationContext()).getappversion();
        if(appversion.equals("1.0"))
        {

        }else
        {

            new AlertDialog.Builder(MainActivity.this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Reminder Dialogue")
                    .setMessage("New Update Came Pls Update App")
                    .setCancelable(false)
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            MainActivity.this.finish();

                        }

                    })
                    .setNegativeButton("Update", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id="+MainActivity.this.getPackageName()); // missing 'http://' will cause crashed
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .show();
        }

    }


    public void showChangeLangDialog() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.language_dialog, null);
        dialogBuilder.setView(dialogView);

        final Spinner spinner1 = (Spinner) dialogView.findViewById(R.id.spinner1);

        dialogBuilder.setTitle("Language");
        dialogBuilder.setMessage("Please select language");

        dialogBuilder.setPositiveButton("Change", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                String lang = "en";//Default Language
                int langpos = spinner1.getSelectedItemPosition();
                switch(langpos) {
                    case 0: //English
                        lang = "en";
                        changeLocale(lang);
                        return;
                    case 1: //Hindi
                        lang = "hi";
                        changeLocale(lang);
                        return;
                    case 2:
                        lang = "pa";
                        changeLocale(lang);
                        return;
                    case 3:
                        lang = "mr";
                        changeLocale(lang);
                        return;
                    case 4:
                        lang = "ur";
                        changeLocale(lang);
                        return;
                    default: //By default set to english
                        lang = "en";
                        changeLocale(lang);
                        return;
                }


            }


        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    public void changeLocale(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);//Set Selected Locale
        saveLocale(lang);//Save the selected locale
        Locale.setDefault(myLocale);//set new locale as default
        Configuration config = new Configuration();//get Configuration
        config.locale = myLocale;//set config locale as selected locale
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());//Update the config
        updateTexts();//Update texts according to locale
    }

    private void updateTexts() {

      /*  Intent i = new Intent(MainActivity.this, SplashScreen.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();*/
    }

    public void saveLocale(String lang) {
        editor.putString(Locale_KeyValue, lang);
        editor.commit();
    }

    public void loadLocale() {
        String language = sharedPreferences.getString(Locale_KeyValue, "");
        changeLocale2(language);
    }

    public void changeLocale2(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);//Set Selected Locale
        saveLocale(lang);//Save the selected locale
        Locale.setDefault(myLocale);//set new locale as default
        Configuration config = new Configuration();//get Configuration
        config.locale = myLocale;//set config locale as selected locale
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());//Update the config
        mclanguage.setText("");
        mskip.setText("");//Update texts according to locale
    }

}
