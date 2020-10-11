package com.example.sanamente;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MensajeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView tvMensaje;
    public Integer id;
    public Button share;
    public Button share2;
    public Button share3;
    public Button share4;
    public Button edit;
    MainActivity main;
    public MensajeViewHolder(@NonNull View itemView, MainActivity main) {
        super(itemView);
        tvMensaje= this.itemView.findViewById(R.id.texto);
        share = this.itemView.findViewById(R.id.butonShare);
        share2 = this.itemView.findViewById(R.id.butonShare2);
        share3 = this.itemView.findViewById(R.id.butonShare3);
        share4 = this.itemView.findViewById(R.id.butonShare4);
        edit = this.itemView.findViewById(R.id.edit);
        OnClickListenerGeneric onclick = new OnClickListenerGeneric(main,this);
        share.setOnClickListener(onclick);
        share2.setOnClickListener(onclick);
        share3.setOnClickListener(onclick);
        share4.setOnClickListener(onclick);
        edit.setOnClickListener(onclick);
        this.main = main;

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Log.d("LLamando id = ", this.id + " - " +this.tvMensaje.getText().toString());
        //DialogGenerico dialog = new DialogGenerico(this.tvMensaje.getText().toString(),"Titulo",false);
        //dialog.show(main.getSupportFragmentManager(),"etiqueta");
    }
}
