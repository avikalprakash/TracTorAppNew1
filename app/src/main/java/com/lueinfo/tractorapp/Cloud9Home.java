package com.lueinfo.tractorapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.lueinfo.tractorapp.Adapter.BottomNavigationViewHelper;
import com.lueinfo.tractorapp.Fragment.HomeFragment;

public class Cloud9Home extends AppCompatActivity {
    ImageView logout;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_homeqwert:

                    addHomeFragment();

                    return true;
                case R.id.navigation_setting:
                    startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                    return true;

                case R.id.navigation_comment:
                    startActivity(new Intent(getApplicationContext(), CommentAndSugg.class));
                    // mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_calender:
                    startActivity(new Intent(getApplicationContext(), CalendarActivity.class));
                    return true;
            }
            return false;
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud9_home);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        addHomeFragment();
//        logout=(ImageView) findViewById(R.id.logout);
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                proceedMessage();
//            }
//        });
    }

//    public void proceedMessage(){
//        final Dialog dialog = new Dialog(Cloud9Home.this);
//        LayoutInflater inflater = LayoutInflater.from(Cloud9Home.this);
//        View subView = inflater.inflate(R.layout.alert_logout, null);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(subView);
//        // dialog.setTitle("Title...");
//        Button yes = (Button) dialog.findViewById(R.id.yes);
//        Button no = (Button) dialog.findViewById(R.id.no);
//        //final MyEditText otp=(MyEditText)dialog.findViewById(R.id.otp);
//        yes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//
//            }
//        });
//        no.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//
//            }
//        });
//
//        dialog.show();
//    }



    private void addHomeFragment()
    {
        Fragment fragment=new HomeFragment();
        Bundle bundle=new Bundle();
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content,fragment);
        fragmentTransaction.commit();
    }
}
