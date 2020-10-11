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

   private String mensaje;
   private String titulo;
   private Boolean personalizado = false;

    List<MensajeModel> mensajes;
    Integer indice;
    MainActivity mainActivity;

   public DialogGenerico(String mensaje, String titulo, Boolean personalizado, List<MensajeModel> mensajes, Integer indice, MainActivity mainActivity){
       this.mensaje = mensaje;
       this.titulo = titulo;
       this.personalizado = personalizado;
       this.mensajes = mensajes;
       this.mainActivity = mainActivity;
       this.indice = indice;

   }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        if(personalizado)
        {
            View dialogPersonalizado = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_personalizado,null);

            builder.setView(dialogPersonalizado);

            EditText ETnombre = dialogPersonalizado.findViewById(R.id.texto);
            Spinner spinnerDias = dialogPersonalizado.findViewById(R.id.spinner1);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),R.array.dias,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ETnombre.setText(this.mensajes.get(indice).getMensaje());
            spinnerDias.setAdapter(adapter);

            spinnerDias.setOnItemSelectedListener(this);


            clickDialogGeneric clickDialog = new clickDialogGeneric(this.mensajes,  this.indice,ETnombre,this.mainActivity);
            //  builder.setNegativeButton("negative",clickDialog);
            builder.setPositiveButton("positivo",clickDialog);
        }else{
            builder.setMessage(this.mensaje);

            clickDialogGeneric clickDialog = new clickDialogGeneric();
          //  builder.setNegativeButton("negative",clickDialog);
            builder.setPositiveButton("positivo",clickDialog);
         //   builder.setNeutralButton("neutral",clickDialog);

        }

        return builder.create();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
