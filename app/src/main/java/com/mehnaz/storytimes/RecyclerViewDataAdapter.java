package com.mehnaz.storytimes;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewDataAdapter  extends RecyclerView.Adapter<RecyclerViewDataAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<FruitModel> imageModelArrayList;

    public RecyclerViewDataAdapter(Context ctx, ArrayList<FruitModel> imageModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.imageModelArrayList = imageModelArrayList;
    }

    @Override
    public RecyclerViewDataAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_single_card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewDataAdapter.MyViewHolder holder, int position) {

        holder.iv.setImageResource(imageModelArrayList.get(position).getImage_drawable());
        holder.time.setText(imageModelArrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView time;
        CircleImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);

            time = (TextView) itemView.findViewById(R.id.tv);
            iv = (CircleImageView) itemView.findViewById(R.id.iv);
        }

    }
}