package com.example.mobilprogramlamafinalsinavi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MesajAdaptor extends RecyclerView.Adapter<MesajAdaptor.MyViewHolder> {

    Context context;
    ArrayList<MesajModel> list;

    public MesajAdaptor(Context context, ArrayList<MesajModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        MesajModel mesajModel = list.get(position);
        holder.mesajAdi.setText(mesajModel.getMesajAdi());
        holder.mesajIcerik.setText(mesajModel.getMesajIcerik());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mesajAdi, mesajIcerik;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mesajAdi = itemView.findViewById(R.id.adii);
            mesajIcerik = itemView.findViewById(R.id.govdei);
        }
    }
}
