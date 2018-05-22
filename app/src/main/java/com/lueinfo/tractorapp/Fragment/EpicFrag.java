package com.lueinfo.tractorapp.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lueinfo.tractorapp.Gallery;
import com.lueinfo.tractorapp.IntroducerActivity;
import com.lueinfo.tractorapp.MenuActivity;
import com.lueinfo.tractorapp.NewsBuletin;
import com.lueinfo.tractorapp.ProfileActivity;
import com.lueinfo.tractorapp.QRActivity;
import com.lueinfo.tractorapp.R;
import com.lueinfo.tractorapp.SongRequest;
import com.lueinfo.tractorapp.TableReservation;
import com.lueinfo.tractorapp.Transaction;


public class EpicFrag extends Fragment implements View.OnClickListener {

    Button introducer, news, promotion, paidItem, transaction, spinBtn, tableReservation, profile, mqrcodebtn,mmenubtn,
            song_request, gallery;

    public EpicFrag()
    {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_epic, container, false);

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

        return  view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.introducer:
                startActivity(new Intent(getActivity().getApplicationContext(), IntroducerActivity.class));
                break;
            case R.id.news:
                startActivity(new Intent(getActivity().getApplicationContext(), NewsBuletin.class));
                break;
            case R.id.promotion:

                break;
            case R.id.paidItem:

                break;
            case R.id.transaction:
                startActivity(new Intent(getActivity().getApplicationContext(), Transaction.class));
                break;

            case R.id.tableReservation:
                startActivity(new Intent(getActivity().getApplicationContext(), TableReservation.class));
                break;
            case R.id.profile:
                startActivity(new Intent(getActivity().getApplicationContext(), ProfileActivity.class));
                break;
            case R.id.qrcodeimg:
                startActivity(new Intent(getActivity().getApplicationContext(), QRActivity.class));
                break;
            case R.id.menu:
                startActivity(new Intent(getActivity().getApplicationContext(), MenuActivity.class));
                break;

            case R.id.song_request:
                startActivity(new Intent(getActivity().getApplicationContext(), SongRequest.class));
                break;
            case R.id.gallery:
                startActivity(new Intent(getActivity().getApplicationContext(), Gallery.class));
                break;
        }
    }

}
