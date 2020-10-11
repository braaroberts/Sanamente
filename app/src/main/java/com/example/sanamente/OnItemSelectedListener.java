package com.example.sanamente;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class OnItemSelectedListener implements AdapterView.OnItemSelectedListener {

    MainActivity main;
    MensajeViewHolder mensajeViewHolder;


    public OnItemSelectedListener(MainActivity main, MensajeViewHolder mensajeViewHolder) {
        this.main= main;
        this.mensajeViewHolder = mensajeViewHolder;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("Seguimiento","onItemSelectedListener-onItemSelected"+parent.toString());
        String horas;
        String dias ;
        String texto = parent.getItemAtPosition(position).toString();
        Log.d("Seguimiento","onItemSelectedListener-onItemSelected;texto:"+texto+"id;"+this.mensajeViewHolder.id);

        //TODO falta cambiar los spiner del amin, falla al cambiarlo
        if(parent.getId()==R.id.ItemSpinnerDias){
            dias = texto;
            this.main.cambiarVariables("DIAS",dias,this.mensajeViewHolder.id);
            //this.main.getMensajes().get(this.id).setVariableDia(dias);
           // this.modelo.setVariableDia(dias);
        }
        if(parent.getId()==R.id.ItemSpinnerHoras){
            horas = texto;
          this.main.cambiarVariables("HORAS",horas,this.mensajeViewHolder.id);
            //this.main.getMensajes().get(this.id).setVariableHora(horas);
           // this.modelo.setVariableHora(horas);
        }
        // this.ac.notificarCambios(this.indice);


        //Toast.makeText(parent.getContext(),texto,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
