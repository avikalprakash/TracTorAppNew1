package com.lueinfo.tractorapp.FCM;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Fujitsu on 14/06/2017.
 */

public class MyFirebaseInstanceIDService  extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";
    public static final String TOKEN_BROADCAST = "myfcmtokenbroadcast";
    String refreshedToken;

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
       refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        storeToken(refreshedToken);

    }

    private void storeToken(String token) {
        //saving the token on shared preferences
        TokenSave.getInstance(getApplicationContext()).saveDeviceToken(token);
    }




}
