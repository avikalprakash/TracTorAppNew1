package com.lueinfo.tractorapp.FCM;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.lueinfo.tractorapp.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;




/**
 * Created by Fujitsu on 15/06/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    NotificationManager notificationManager;
    Intent intent;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Map<String, String> params = remoteMessage.getData();
        Log.d(TAG, "Notification" + params);
        JSONObject jsonObject = new JSONObject(params);

//        try {
//            String keyword = jsonObject.getString("msgtype");
//            if(keyword.equals("rejectedarticle")){
//
//                String imageurl = jsonObject.getString("articleid");
//                String NewImgUrl = Url.imgurl+"article/"+imageurl+".png";
//                intent = new Intent(this, RejectedNewsArticleDetail.class);
//                intent.putExtra("title", jsonObject.getString("title"));
//                intent.putExtra("content", jsonObject.getString("description"));
//                intent.putExtra("id", jsonObject.getString("articleid"));
//                intent.putExtra("image", NewImgUrl);
//                intent.putExtra("comment", jsonObject.getString("comments"));
//                intent.putExtra("imageavail", jsonObject.getString("image"));
//                String imgchk = jsonObject.getString("image");
//                intent.putExtra("classsid", jsonObject.getString("classid"));
//                intent.putExtra("classstitle", jsonObject.getString("classtitle"));
//                intent.putExtra("section", jsonObject.getString("section"));
//                intent.putExtra("stream", jsonObject.getString("subjectstream"));
//                intent.putExtra("rollno", jsonObject.getString("studrollno"));
//                intent.putExtra("fname", jsonObject.getString("fname"));
//                intent.putExtra("lname", jsonObject.getString("lname"));
//
//                if(imgchk.equals("available")){
//
//                    soundNotificationImage("Your Article Rejected", NewImgUrl);
//
//                }else {
//                    soundNotification("Your Article Rejected");
//                }
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
////
//        try {
//            String keyword = jsonObject.getString("msgtype");
//            if(keyword.equals("approvedarticle")){
//
//                String imageurl = jsonObject.getString("articleid");
//                String NewImgUrl = Url.imgurl+"article/"+imageurl+".png";
//                intent = new Intent(this, DetailArticle.class);
//                intent.putExtra("title", jsonObject.getString("title"));
//                intent.putExtra("content", jsonObject.getString("description"));
//                intent.putExtra("id", jsonObject.getString("articleid"));
//                intent.putExtra("image", NewImgUrl);
//                intent.putExtra("imageavail", jsonObject.getString("image"));
//                String imgchk = jsonObject.getString("image");
//
//
//                // Log.d("fvsfvsvvssf852","0000000852"+jsonObject.getString("id") + NewImgUrl);
//                if(imgchk.equals("available")){
//
//                    soundNotificationImage("Your Article Approved", NewImgUrl);
//
//                }else {
//                    soundNotification("Your Article Approved");
//                }
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            String keyword = jsonObject.getString("msgtype");
//            String faccess = jsonObject.getString("facultyaccess");
//            if(keyword.equals("newfacultynews")||keyword.equals("newnews") && faccess.equals("true")){
//
//                String imageurl = jsonObject.getString("newsid");
//                String NewImgUrl = Url.imgurl+"news/"+imageurl+".png";
//                intent = new Intent(this, FNewsDetail.class);
//                intent.putExtra("title", jsonObject.getString("title"));
//                intent.putExtra("content", jsonObject.getString("description"));
//                intent.putExtra("id", jsonObject.getString("newsid"));
//                intent.putExtra("image", NewImgUrl);
//                intent.putExtra("imageavail", jsonObject.getString("image"));
//                String imgchk = jsonObject.getString("image");
//
//
//                // Log.d("fvsfvsvvssf852","0000000852"+jsonObject.getString("id") + NewImgUrl);
//                if(imgchk.equals("available")){
//
//                    soundNotificationImage("New News Came", NewImgUrl);
//
//                }else {
//                    soundNotification("New News Came");
//                }
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            String keyword = jsonObject.getString("msgtype");
//            String gaccess = jsonObject.getString("guestaccess");
//            if(keyword.equals("newnews") && gaccess.equals("true")){
//
//                String imageurl = jsonObject.getString("newsid");
//                String NewImgUrl = Url.imgurl+"news/"+imageurl+".png";
//                intent = new Intent(this, NewsDetail.class);
//                intent.putExtra("title", jsonObject.getString("title"));
//                intent.putExtra("content", jsonObject.getString("description"));
//                intent.putExtra("id", jsonObject.getString("newsid"));
//                intent.putExtra("image", NewImgUrl);
//                intent.putExtra("imageavail", jsonObject.getString("image"));
//                String imgchk = jsonObject.getString("image");
//
//
//                 Log.d("fvsfvsvvssf852","0000000852"+jsonObject.getString("newsid") + NewImgUrl);
//                if(imgchk.equals("available")){
//
//                    soundNotificationImage("New News Came", NewImgUrl);
//
//                }else {
//                    soundNotification("New News Came");
//                }
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        try {
//
//            String keyword = jsonObject.getString("msgtype");
//            if(keyword.equals("rejectednews")){
//
//
//                String imageurl = jsonObject.getString("newsid");
//                String NewImgUrl = Url.imgurl+"news/"+imageurl+".png";
//                intent = new Intent(this, RejectedFNewsDetail.class);
//                intent.putExtra("title", jsonObject.getString("title"));
//                intent.putExtra("content", jsonObject.getString("description"));
//                intent.putExtra("id", jsonObject.getString("newsid"));
//                intent.putExtra("image", NewImgUrl);
//                intent.putExtra("imageavail", jsonObject.getString("image"));
//                intent.putExtra("comment", jsonObject.getString("comments"));
//                intent.putExtra("staccess", jsonObject.getString("studentaccess"));
//                intent.putExtra("gtaccess", jsonObject.getString("guestaccess"));
//                intent.putExtra("ftaccess", jsonObject.getString("facultyaccess"));
//                String imgchk = jsonObject.getString("image");
//
//                if(imgchk.equals("available")){
//
//                    soundNotificationImage("Your News Is Rejected", NewImgUrl);
//
//                }else {
//                    soundNotification("Your News Is Rejected");
//                }
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            String keyword = jsonObject.getString("msgtype");
//            if(keyword.equals("forwardedcomplaint")){
//
//
//                intent = new Intent(this, ComplainDetails.class);
//                intent.putExtra("stfullnme", jsonObject.getString("studentfullname"));
//                intent.putExtra("strollno", jsonObject.getString("studentrollno"));
//                intent.putExtra("subject", jsonObject.getString("subject"));
//                intent.putExtra("parentmobno", jsonObject.getString("parentmobno"));
//                intent.putExtra("class", jsonObject.getString("class"));
//                intent.putExtra("complainid", jsonObject.getString("complaintid"));
//                intent.putExtra("desc", jsonObject.getString("description"));
//                intent.putExtra("fwby", jsonObject.getString("forwardedby"));
//
//                    soundNotification("New Complain Came");
//
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            String keyword = jsonObject.getString("msgtype");
//            if(keyword.equals("attendance")){
//
//
//                intent = new Intent(this, GetOverallAttandance.class);
//                intent.putExtra("classid", jsonObject.getString("classid"));
//                intent.putExtra("rollno", jsonObject.getString("rollno"));
//                intent.putExtra("detail", jsonObject.getString("detail"));
//                intent.putExtra("acttype","notif");
//
//                soundNotification("New Attandance Report Came");
//
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            String keyword = jsonObject.getString("msgtype");
//            if(keyword.equals("circular")){
//
//
//                intent = new Intent(this, CircularDetail.class);
//                intent.putExtra("desc", jsonObject.getString("msg"));
//                intent.putExtra("upby", jsonObject.getString("uploadedby"));
//
//                soundNotification("New Circular Came");
//
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        try {
//            String keyword = jsonObject.getString("msgtype");
//            if(keyword.equals("newpoll")){
//
//                String imageurl = jsonObject.getString("pollid");
//                String NewImgUrl = Url.imgurl+"poll/"+imageurl+".png";
//                Log.d("sdkvbhiadvbb",""+NewImgUrl);
//                intent = new Intent(this, PollNotification.class);
//                intent.putExtra("title", jsonObject.getString("title"));
//                intent.putExtra("description", jsonObject.getString("description"));
//                intent.putExtra("image", NewImgUrl);
//                intent.putExtra("option1", jsonObject.getString("option1"));
//                intent.putExtra("option2", jsonObject.getString("option2"));
//                intent.putExtra("option3", jsonObject.getString("option3"));
//                intent.putExtra("option4", jsonObject.getString("option4"));
//                String imgchk = jsonObject.getString("image");
//
//
//                if(imgchk.equals("available")){
//
//                    soundNotificationImage("New Poll Came", NewImgUrl);
//
//                }else {
//                    soundNotification("New Poll Came");
//                }
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            String keyword = jsonObject.getString("msgtype");
//            if(keyword.equals("newremark")){
//
////                remarkid = getIntent().getStringExtra("id");
////                remarkimg = getIntent().getStringExtra("img");
//
//                String imageurl = jsonObject.getString("remarkid");
//                String NewImgUrl = Url.imgurl+"remark/"+imageurl+".png";
//                intent = new Intent(this, RemarkDetailFaculty.class);
//                intent.putExtra("id", jsonObject.getString("remarkid"));
//                intent.putExtra("img", NewImgUrl);
//                String imgchk = jsonObject.getString("image");
//                intent.putExtra("activity","stremark");
//                if(imgchk.equals("available")){
//
//                    soundNotificationImage("New Remark Came", NewImgUrl);
//
//                }else {
//                    soundNotification("New Remark Came");
//                }
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            String keyword = jsonObject.getString("msgtype");
//            if(keyword.equals("newhomework")){
//
//                String imageurl = jsonObject.getString("homeworkid");
//                String NewImgUrl = Url.imgurl+"homework/"+imageurl+".png";
//                intent = new Intent(this, HomeworkDetail.class);
//                intent.putExtra("id", jsonObject.getString("homeworkid"));
//                intent.putExtra("img", NewImgUrl);
//                String imgchk = jsonObject.getString("image");
//                intent.putExtra("classnme",jsonObject.getString("classtitle"));
//                intent.putExtra("htitle",jsonObject.getString("homeworktitle"));
//                intent.putExtra("desc",jsonObject.getString("description"));
//                intent.putExtra("sec",jsonObject.getString("section"));
//                intent.putExtra("stream",jsonObject.getString("stream"));
//                if(imgchk.equals("available")){
//
//                    soundNotificationImage("New Homework Came", NewImgUrl);
//
//                }else {
//                    soundNotification("New Homework Came");
//                }
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            String keyword = jsonObject.getString("msgtype");
//            if(keyword.equals("newhomework")){
//
//                String imageurl = jsonObject.getString("homeworkid");
//                String NewImgUrl = Url.imgurl+"homework/"+imageurl+".png";
//                intent = new Intent(this, HomeworkDetail.class);
//                intent.putExtra("id", jsonObject.getString("homeworkid"));
//                intent.putExtra("img", NewImgUrl);
//                String imgchk = jsonObject.getString("image");
//                intent.putExtra("classnme",jsonObject.getString("classtitle"));
//                intent.putExtra("htitle",jsonObject.getString("homeworktitle"));
//                intent.putExtra("desc",jsonObject.getString("description"));
//                intent.putExtra("sec",jsonObject.getString("section"));
//                intent.putExtra("stream",jsonObject.getString("stream"));
//                if(imgchk.equals("available")){
//
//                    soundNotificationImage("New Homework Came", NewImgUrl);
//
//                }else {
//                    soundNotification("New Homework Came");
//                }
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            String keyword = jsonObject.getString("msgtype");
//            if(keyword.equals("newenquiry")){
//
//
//                intent = new Intent(this, EnquiryDetail.class);
//                intent.putExtra("id", jsonObject.getString("enquiryid"));
//                intent.putExtra("contpernme",jsonObject.getString("contactperson"));
//                intent.putExtra("address",jsonObject.getString("address"));
//                intent.putExtra("altcontno",jsonObject.getString("alternatecontactno"));
//                intent.putExtra("stnme",jsonObject.getString("studentname"));
//                intent.putExtra("emailid",jsonObject.getString("emailid"));
//                intent.putExtra("class",jsonObject.getString("class"));
//                intent.putExtra("contactno",jsonObject.getString("contactno"));
//                intent.putExtra("hdby",jsonObject.getString("forwardedby"));
//
//                    soundNotification("New Enquiry Came");
//
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


    }

    private void soundNotification(String msg) {

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon_logo)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(msg)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            notificationBuilder.setSmallIcon(R.drawable.rrrrrrrrr);
//        } else {
//            notificationBuilder.setSmallIcon(R.mipmap.ic_dainik);
//        }

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationBuilder.setPriority(Notification.PRIORITY_HIGH);
        if (Build.VERSION.SDK_INT >= 21) notificationBuilder.setVibrate(new long[0]);
        notificationManager.notify(0, notificationBuilder.build());
    }

    private void soundNotificationImage(String msg, String url) {

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
        bigPictureStyle.setBigContentTitle(getString(R.string.app_name));
        bigPictureStyle.setSummaryText(Html.fromHtml(msg).toString());
        bigPictureStyle.bigPicture(getBitmapFromURL(url));

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext());
        try {
            notificationBuilder
                    .setSmallIcon(R.drawable.icon_logo)
                    .setLargeIcon(Picasso.with(getApplicationContext()).load(url).get())
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText(msg)
                    .setStyle(bigPictureStyle)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationBuilder.setPriority(Notification.PRIORITY_HIGH);
        if (Build.VERSION.SDK_INT >= 21) notificationBuilder.setVibrate(new long[0]);
        notificationManager.notify(0, notificationBuilder.build());
    }

    private Bitmap getBitmapFromURL(String strURL) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
