package com.example.sanamente;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MensajeAdapter extends RecyclerView.Adapter<MensajeViewHolder>  {
    private List<MensajeModel> mensajes;
    private MainActivity main;

    public MensajeAdapter( MainActivity main){
        this.main = main;
    }

    public void setMensajes(List<MensajeModel> mensajes) {
        this.mensajes = mensajes;

    }
    @NonNull
    @Override
    public MensajeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat,parent,false);
        MensajeViewHolder pvh = new MensajeViewHolder(view,main);
        view.setOnClickListener(pvh);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MensajeViewHolder holder, int position) {
        holder.id = position;
        holder.tvMensaje.setText(mensajes.get(position).getMensajeCompleto());

    }

    @Override
    public int getItemCount() {
        return this.mensajes.size();
    }
}
