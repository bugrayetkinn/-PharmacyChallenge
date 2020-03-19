package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.HaberModel;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Code with ❤
 * ╔════════════════════════════╗
 * ║  Created by Buğra Yetkin  ║
 * ╠════════════════════════════╣
 * ║ bugrayetkinn@gmail.com ║
 * ╠════════════════════════════╣
 * ║     19/03/2020 - 13:55     ║
 * ╚════════════════════════════╝
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {

    private ArrayList<HaberModel> haberModelArrayList = new ArrayList<>();
    private Context mContext;


    public RecyclerAdapter(ArrayList<HaberModel> haberModelArrayList, Context mContext) {
        this.haberModelArrayList = haberModelArrayList;
        this.mContext = mContext;
    }

    public ArrayList<HaberModel> getHaberModelArrayList() {
        return haberModelArrayList;
    }

    public void setHaberModelArrayList(ArrayList<HaberModel> haberModelArrayList) {
        this.haberModelArrayList = haberModelArrayList;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.haber_model, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        HaberModel haberModel = haberModelArrayList.get(position);
        holder.textBaslik.setText(haberModel.getBaslik());
        holder.textTarih.setText(haberModel.getTarih());
        Picasso.get().load(haberModel.getImgLink()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return haberModelArrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textBaslik, textTarih;

        public Holder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textBaslik = itemView.findViewById(R.id.textViewBaslik);
            textTarih = itemView.findViewById(R.id.textTarih);
        }
    }

}