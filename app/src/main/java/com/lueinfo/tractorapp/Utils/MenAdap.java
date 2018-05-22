package com.lueinfo.tractorapp.Utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.lueinfo.tractorapp.Adapter.CustomListAdapter;
import com.lueinfo.tractorapp.MenuDetails;
import com.lueinfo.tractorapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lue on 27-11-2017.
 */

public class MenAdap extends BaseAdapter {
    private Context activity;
    private LayoutInflater inflater;
    private List<PromotionModel> movieItems;

    String id ;


    public MenAdap(Context context, List<PromotionModel> movie) {

        this.activity = context;
        this.movieItems = movie;

    }


    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int location) {
        return movieItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    static class ViewHolder {

        ImageView mgridimage;
        TextView detailtxt;
        TextView price;
        TextView calrtxt;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            convertView = inflater.inflate(R.layout.menusubitemlayout, null);
            holder = new ViewHolder();

            holder.mgridimage = (ImageView) convertView
                    .findViewById(R.id.grid_image);

            holder.detailtxt = (TextView) convertView.findViewById(R.id.griddetail_text);
            holder.price = (TextView) convertView.findViewById(R.id.gridtime_text);
            holder.calrtxt = (TextView) convertView.findViewById(R.id.gridcal_text);
            convertView.setTag(holder);
        }
        else {

            holder = (ViewHolder) convertView.getTag();
        }


        PromotionModel m = movieItems.get(position);
        Log.d("kbbobo",""+movieItems.size());
        if(!URLUtil.isValidUrl(movieItems.get(position).getImage()))
        {
            holder.mgridimage.setVisibility(View.GONE);
        }
        else
        {
            holder.mgridimage.setVisibility(View.VISIBLE);//add this
            Picasso.with(activity).load(movieItems.get(position).getImage()).into(holder.mgridimage);
            String str = "rjbhai";

        }
        id = m.getItemid();
        Log.d("rj123",id);
        holder.detailtxt.setText(m.getDetailtxt());
        holder.price.setText("RM "+m.getPrice());

        holder.mgridimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String img = movieItems.get(position).getImage();
                String name= movieItems.get(position).getDetailtxt();
                String itmid= movieItems.get(position).getItemid();
                Intent i = new Intent(activity, MenuDetails.class);
                i.putExtra("id",itmid);
                activity.startActivity(i);

            }
        });



        return convertView;
    }


}
