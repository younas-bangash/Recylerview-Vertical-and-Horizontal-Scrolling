package com.uowd.sport.nestdrecylerviews_vert_horizontal;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by belladunovska on 17/07/15.
 */
public class MyHorizontalAdapter extends RecyclerView.Adapter<MyHorizontalAdapter.ViewHolder> {
    private ArrayList<String> mImagesName;
    private Context context;
    private  MyClickListener myClickListener;
    private int mClickedPosition=0;
    public MyHorizontalAdapter(Context context, ArrayList<String> dataset) {
        this.context = context;
        this.mImagesName = dataset;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_item_small, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.pBar.setVisibility(View.VISIBLE);
        holder.pBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(context, R.color.ColorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
        /*if(position==mClickedPosition)
            holder.myBackground.setBackgroundResource(R.drawable.selector_row);
        else
            holder.myBackground.setBackgroundResource(0);*/

       String mCollectionType="2";

        Log.d("ResponseImageLink","https://gizbo.ae/GizboApp/GizboImages/catalogues/1155/"+mCollectionType+"/thumbnail/" + mImagesName.get(position));
        Picasso.with(context.getApplicationContext())
                .load("https://gizbo.ae/GizboApp/GizboImages/catalogues/1155/"+mCollectionType+"/thumbnail/" + mImagesName.get(position))
                .into(holder.randomImageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        holder.pBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        //holder.pBar.setVisibility(View.GONE);
                    }
                });


    }

    @Override
    public int getItemCount() {
        return mImagesName.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private ImageView randomImageView;
        private ProgressBar pBar;
        private FrameLayout myBackground;
        public ViewHolder(View v) {
            super(v);
            randomImageView = (ImageView) v.findViewById(R.id.random_image_view);
            myBackground= (FrameLayout) v.findViewById(R.id.backgound);
            pBar= (ProgressBar) v.findViewById(R.id.progressBarSmall);
            v.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getLayoutPosition(), v);
            //mClickedPosition=getLayoutPosition();
           // notifyDataSetChanged();
           // Log.d("Click", "Click");

        }
    }
    public interface MyClickListener {
        void onItemClick(int position, View v);
    }
    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;

    }


}