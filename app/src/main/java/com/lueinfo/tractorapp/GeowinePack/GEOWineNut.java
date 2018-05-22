package com.lueinfo.tractorapp.GeowinePack;

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
import com.lueinfo.tractorapp.CalendarActivity;
import com.lueinfo.tractorapp.CommentAndSugg;
import com.lueinfo.tractorapp.GeowinePack.GeoWIneFrag;
import com.lueinfo.tractorapp.R;
import com.lueinfo.tractorapp.SettingActivity;

public class GEOWineNut extends AppCompatActivity {
    ImageView logout;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.navigation_homeqwert:
                    addHomeFragment();
                    return true;
                case R.id.navigation_setting:
                 //   startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                    return true;
                case R.id.navigation_comment:
                  //  startActivity(new Intent(getApplicationContext(), CommentAndSugg.class));
                    // mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_calender:
                  //  startActivity(new Intent(getApplicationContext(), CalendarActivity.class));
                    return true;
            }
            return false;
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geowine_nut);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        addHomeFragment();
    }
    private void addHomeFragment()
    {
        Fragment fragment=new GeoWIneFrag();
        Bundle bundle=new Bundle();
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content,fragment);
        fragmentTransaction.commit();
    }
}
