package com.example.sanamente;

import android.util.Log;
import android.view.View;

public class OnClickListenerGeneric implements View.OnClickListener {

    MainActivity main;
    MensajeViewHolder mensajeVH;
    public OnClickListenerGeneric(MainActivity main, MensajeViewHolder mensajeVH){
        this.main = main;
        this.mensajeVH = mensajeVH;
    }
    @Override
    public void onClick(View v) {
        Integer indice = this.mensajeVH.id;
        switch (v.getId()){

            case R.id.butonShare2:
                this.main.controlWpp("2",indice);
                break;
            case R.id.butonShare3:
                this.main.controlWpp("3",indice);
                break;

            case R.id.edit:
                this.main.modificar(indice);
                break;
            case R.id.delete:
                this.main.borrar(indice);
                break;
        }
        Log.d("Seguimiento","click en indice:"+indice);





    }
}
