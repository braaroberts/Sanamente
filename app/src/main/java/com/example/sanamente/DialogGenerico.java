package com.example.sanamente;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.List;

public class DialogGenerico extends DialogFragment  implements AdapterView.OnItemSelectedListener {



    List<MensajeModel> mensajes;
    Integer indice;
    MainActivity mainActivity;

   public DialogGenerico( List<MensajeModel> mensajes, Integer indice, MainActivity mainActivity){

       this.mensajes = mensajes;
       this.mainActivity = mainActivity;
       this.indice = indice;

   }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Modificar");
        View dialogPersonalizado = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_personalizado,null);
            builder.setView(dialogPersonalizado);
            EditText ETnombre = dialogPersonalizado.findViewById(R.id.textoedit1);
            ETnombre.setText(this.mensajes.get(indice).getMensaje());
        EditText ETnombrePost = dialogPersonalizado.findViewById(R.id.textoedit2);
        ETnombrePost.setText(this.mensajes.get(indice).getMensajePost());

            Spinner spinnerDias = dialogPersonalizado.findViewById(R.id.spinnerDias);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),R.array.dias,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerDias.setAdapter(adapter);
            spinnerDias.setOnItemSelectedListener(this);

            Spinner spinnerHoras = dialogPersonalizado.findViewById(R.id.spinnerHoras);
            ArrayAdapter<CharSequence> adapterHoras = ArrayAdapter.createFromResource(this.getContext(),R.array.horas,android.R.layout.simple_spinner_item);
            adapterHoras.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerHoras.setAdapter(adapterHoras);
            spinnerHoras.setOnItemSelectedListener(this);


            clickDialogGeneric clickDialog = new clickDialogGeneric(this.mensajes,  this.indice,ETnombre,ETnombrePost,this.mainActivity,spinnerHoras,spinnerHoras);
            //  builder.setNegativeButton("negative",clickDialog);
            builder.setPositiveButton("Aceptar",clickDialog);


        return builder.create();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       String horas;
       String dias ;
    String texto = parent.getItemAtPosition(position).toString();
       if(parent.getId()==R.id.spinnerDias){
           dias = texto;
           this.mensajes.get(this.indice).setVariableDia(dias);
        }
        if(parent.getId()==R.id.spinnerHoras){
            horas = texto;
            this.mensajes.get(this.indice).setVariableHora(horas);
        }
        this.mainActivity.notificarCambios(this.indice);


        Toast.makeText(parent.getContext(),texto,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
