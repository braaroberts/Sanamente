package com.example.sanamente;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AgregarView implements AdapterView.OnItemSelectedListener {

    AgregarActivity activity;
    MensajeModel modelo;


    public AgregarView(AgregarActivity activity, MensajeModel modelo){

        this.activity = activity;
        this.modelo = modelo;
    }
    public void cargarSpinners(){
        Spinner spinnerDias =  this.activity.findViewById(R.id.spinnerDias);
        Spinner spinnerHoras =  this.activity.findViewById(R.id.spinnerHoras);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity.getApplicationContext(),R.array.dias,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDias.setAdapter(adapter);
        spinnerDias.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> adapterHoras = ArrayAdapter.createFromResource(activity.getApplicationContext(),R.array.horas,android.R.layout.simple_spinner_item);
        adapterHoras.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHoras.setAdapter(adapterHoras);
        spinnerHoras.setOnItemSelectedListener(this);
    }
    public  void cargarModelo(){
        EditText mensaje = this.activity.findViewById(R.id.texto1);
        EditText mensaje2 = this.activity.findViewById(R.id.texto2);
        this.modelo.setMensaje(mensaje.getText().toString());
        this.modelo.setMensajePost(mensaje2.getText().toString());

    }


    public void onClickListenerGuardar(){
        OnClickListener listener = new OnClickListener(this,this.modelo,this.activity);
        Button btn_guardar =  this.activity.findViewById(R.id.guardar);
        btn_guardar.setOnClickListener(listener);


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String horas;
        String dias ;
        String texto = parent.getItemAtPosition(position).toString();
        if(parent.getId()==R.id.spinnerDias){
            dias = texto;
            this.modelo.setVariableDia(dias);
        }
        if(parent.getId()==R.id.spinnerHoras){
            horas = texto;
            this.modelo.setVariableHora(horas);
        }
       // this.ac.notificarCambios(this.indice);


        Toast.makeText(parent.getContext(),texto,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
