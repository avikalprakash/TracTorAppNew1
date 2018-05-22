package com.lueinfo.tractorapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Fujitsu on 01/09/2017.
 */

public class SaveCredentials {

    private static final String SHARED_PREF_NAME = "savecredentials";
    private static final String TAG_TOKEN = "saveserveraddress";

    private static final String TAG_TOKENIMGADDRESS = "saveserverimageaddress";

    private static final String TAG_TOKENANDROIDVERSION = "androidversion";

    private static SaveCredentials mInstance;
    private static Context mCtx;

    private SaveCredentials(Context context) {
        mCtx = context;
    }

    public static synchronized SaveCredentials getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SaveCredentials(context);
        }
        return mInstance;
    }

    //this method will save the device token to shared preferences
    public boolean saveserveraddress(String basicurl , String city, String imgurl){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("basicurl",basicurl);
        editor.putString("city",city);
        editor.putString("imgurl",imgurl);

        editor.apply();
        return true;
    }

    //this method will fetch the device token from shared preferences
    public String getc9ID(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString("basicurl", null);
    }

    public String getGeoWineId(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString("imgurl", null);
    }

    public boolean saveserveraddressimg(String id){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_TOKENIMGADDRESS, id);
        editor.apply();
        return true;
    }

    //this method will fetch the device token from shared preferences


    public boolean saveandroidversion(String id){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_TOKENANDROIDVERSION, id);
        editor.apply();
        return true;
    }

    //this method will fetch the device token from shared preferences
    public String getEpissoulId(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString("city", null);
    }

}
