package com.lueinfo.tractorapp.Adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.lueinfo.tractorapp.NewsBuletin;
import com.lueinfo.tractorapp.R;


import java.util.List;


public class NewsBuletinListRecyclerViewAdapter extends RecyclerView.Adapter<NewsBuletinListRecyclerViewAdapter.ViewHolder> {

    private final List<NewsBuletinListDetails> mValues;
    private final NewsBuletinListFragment.OnListFragmentInteractionListener mListener;

    public NewsBuletinListRecyclerViewAdapter(List<NewsBuletinListDetails> items, NewsBuletinListFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_newsbuletin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.mTitleView.setText(mValues.get(position).getTitle());
       // holder.imgBuletin.setText(mValues.get(position).content);
        getImage(mValues.get(position).getImage(),holder.imgBuletin);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final ImageView imgBuletin;
        public NewsBuletinListDetails mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.title);
            imgBuletin = (ImageView) view.findViewById(R.id.img_list);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }
    }

    void getImage(String url, final ImageView imageView)
    {
        RequestQueue requestAdministrator = null;
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {

                    @Override
                    public void onResponse(Bitmap bitmap) {
                        if(bitmap!=null) imageView.setBackground(new
                                BitmapDrawable(NewsBuletin.context.getResources(),bitmap));

                    }
                },0,0,null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        MySingleton.getInstance(NewsBuletin.context).addToRequestQueue(request);
    }
}

