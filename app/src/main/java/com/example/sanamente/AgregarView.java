package com.example.sanamente;

import android.widget.Button;
import android.widget.EditText;

public class AgregarView {

    AgregarActivity activity;
    MensajeModel modelo;


    public AgregarView(AgregarActivity activity, MensajeModel modelo){

        this.activity = activity;
        this.modelo = modelo;
    }
    public  void cargarModelo(){
        EditText mensaje = this.activity.findViewById(R.id.texto);

        modelo.setMensaje(mensaje.getText().toString());


    }


    public void onClickListenerGuardar(){
        OnClickListener listener = new OnClickListener(this,this.modelo,this.activity);
        Button btn_guardar =  this.activity.findViewById(R.id.guardar);
        btn_guardar.setOnClickListener(listener);


    }

}
