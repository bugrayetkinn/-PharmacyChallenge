package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

/**
 * Code with ❤
 * ╔════════════════════════════╗
 * ║  Created by Buğra Yetkin  ║
 * ╠════════════════════════════╣
 * ║ bugrayetkinn@gmail.com ║
 * ╠════════════════════════════╣
 * ║     21/03/2020 - 00:51     ║
 * ╚════════════════════════════╝
 */
public class EczaneAdapter extends RecyclerView.Adapter<EczaneAdapter.EczaneHolder> {
    private List<String> eczaneModelList;
    private Context mContext;

    public EczaneAdapter(List<String> eczaneModelList, Context mContext) {
        this.eczaneModelList = eczaneModelList;
        this.mContext = mContext;
    }

    public List<String> getEczaneModelList() {
        return eczaneModelList;
    }

    public void setEczaneModelList(List<String> eczaneModelList) {
        this.eczaneModelList = eczaneModelList;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public EczaneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eczane_model, parent, false);
        return new EczaneHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EczaneHolder holder, int position) {

        holder.textViewEczaneAd.setText(eczaneModelList.get(position));

    }

    @Override
    public int getItemCount() {
        return eczaneModelList.size();
    }

    public class EczaneHolder extends RecyclerView.ViewHolder {
        TextView textViewEczaneAd;

        public EczaneHolder(@NonNull View itemView) {
            super(itemView);

            textViewEczaneAd = itemView.findViewById(R.id.textViewEczaneAd);
        }
    }
}
