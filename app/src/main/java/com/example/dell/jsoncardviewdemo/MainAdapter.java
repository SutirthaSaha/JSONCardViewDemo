package com.example.dell.jsoncardviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by DELL on 24-Jan-18.
 */

class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyHolder> {

    private ArrayList<String> titleDataSet,imageDataset;
    Context context;

    public MainAdapter(Context context,ArrayList<String> titleDataSet, ArrayList<String> imageDataset) {
        this.titleDataSet = titleDataSet;
        this.imageDataset = imageDataset;
        this.context=context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.title.setText(titleDataSet.get(position));
        Picasso.with(context).load(imageDataset.get(position)).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return titleDataSet.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;
        //public ImageView image;
        public MyHolder(View itemView) {
            super(itemView);
            title=(TextView) itemView.findViewById(R.id.title);
            image=(ImageView)itemView.findViewById(R.id.image);
        }
    }
}
