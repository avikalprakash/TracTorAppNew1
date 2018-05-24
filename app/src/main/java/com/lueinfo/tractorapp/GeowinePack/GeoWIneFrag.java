package com.lueinfo.tractorapp.GeowinePack;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.lueinfo.tractorapp.Gallery;
import com.lueinfo.tractorapp.IntroducerActivity;
import com.lueinfo.tractorapp.MenuActivity;
import com.lueinfo.tractorapp.NewsBuletin;
import com.lueinfo.tractorapp.ProfileActivity;
import com.lueinfo.tractorapp.QRActivity;
import com.lueinfo.tractorapp.R;
import com.lueinfo.tractorapp.SongRequest;
import com.lueinfo.tractorapp.SpecialPromotion;
import com.lueinfo.tractorapp.TableReservation;
import com.lueinfo.tractorapp.Transaction;

/**
 * A simple {@link Fragment} subclass.
 */
public class GeoWIneFrag extends Fragment implements View.OnClickListener{

    Button introducer, news, promotion, paidItem,spinBtn, tableReservation, profile,mqrcodebtn,mmenubtn,
            song_request, gallery,ratesinger,live_stream,transaction;

    public GeoWIneFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_geo_wine, container, false);

        introducer=(Button)view.findViewById(R.id.introducer);
        news=(Button)view.findViewById(R.id.news);
        promotion=(Button)view.findViewById(R.id.promotion);
        paidItem=(Button)view.findViewById(R.id.paidItem);
        transaction=(Button)view.findViewById(R.id.transaction);
        tableReservation=(Button)view.findViewById(R.id.tableReservation);
        profile=(Button)view.findViewById(R.id.profile);
        mqrcodebtn = (Button)view.findViewById(R.id.qrcodeimg);
        mmenubtn = (Button)view.findViewById(R.id.menu);
        song_request = (Button)view.findViewById(R.id.song_request);
        gallery = (Button)view.findViewById(R.id.gallery);
        ratesinger = (Button)view.findViewById(R.id.rate_singer);
        live_stream = (Button)view.findViewById(R.id.live_stream);
        mmenubtn.setOnClickListener(this);
        mqrcodebtn.setOnClickListener(this);
        profile.setOnClickListener(this);
        introducer.setOnClickListener(this);
        news.setOnClickListener(this);
        promotion.setOnClickListener(this);
        paidItem.setOnClickListener(this);
        transaction.setOnClickListener(this);
        tableReservation.setOnClickListener(this);
        song_request.setOnClickListener(this);
        gallery.setOnClickListener(this);
        ratesinger.setOnClickListener(this);
        live_stream.setOnClickListener(this);

        return  view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.introducer:
                startActivity(new Intent(getActivity().getApplicationContext(), GeoIntroducerActivity.class));
                break;
            case R.id.news:
                startActivity(new Intent(getActivity().getApplicationContext(), GeoNewsBuletin.class));
                break;
            case R.id.promotion:
             //   startActivity(new Intent(getActivity().getApplicationContext(), GeoSpecialPromotion.class));
                break;
            case R.id.paidItem:
                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                break;
            case R.id.transaction:
                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                break;

            case R.id.tableReservation:
                startActivity(new Intent(getActivity().getApplicationContext(), GeoTableReservation.class));
                break;
            case R.id.profile:
            //    startActivity(new Intent(getActivity().getApplicationContext(), GeoProfileActivity.class));
                break;
            case R.id.qrcodeimg:
             //   startActivity(new Intent(getActivity().getApplicationContext(), GeoQRActivity.class));
                break;
            case R.id.menu:
            //    startActivity(new Intent(getActivity().getApplicationContext(), GeoMenuActivity.class));
                break;
            case R.id.song_request:
                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                break;
            case R.id.gallery:
            //    startActivity(new Intent(getActivity().getApplicationContext(), GeoGallery.class));
                break;

            case R.id.live_stream:
                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                break;

            case R.id.cofday:
                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                break;

            case R.id.rate_singer:
                Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
