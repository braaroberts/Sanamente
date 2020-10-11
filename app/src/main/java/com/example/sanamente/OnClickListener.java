package com.example.sanamente;

import android.view.View;

public class OnClickListener implements View.OnClickListener {

    AgregarView view;
    MensajeModel model;
    AgregarActivity activity2;

    public OnClickListener(AgregarView view, MensajeModel model, AgregarActivity activity2){
      this.view = view;
      this.model = model;
      this.activity2 = activity2;

    }
    @Override
    public void onClick(View view) {

        this.view.cargarModelo();

        //this.view.cargarPantalla();


        MainActivity.mensajes.add(model);
        this.activity2.finish();




    }

}
