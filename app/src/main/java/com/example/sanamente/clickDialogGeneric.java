package com.example.sanamente;

import android.content.DialogInterface;
import android.util.Log;
import android.widget.EditText;

import java.util.List;

public class clickDialogGeneric implements DialogInterface.OnClickListener {
    List<MensajeModel> mensajes;
    Integer indice;
    EditText edit;
    MainActivity mainActivity;

    public clickDialogGeneric(){}
    public clickDialogGeneric(List<MensajeModel> mensajes, Integer indice, EditText edit, MainActivity mainActivity) {
        this.indice = indice;
        this.mensajes = mensajes;
        this.edit = edit;
        this.mainActivity = mainActivity;
    }
    @Override
    public void onClick(DialogInterface dialog, int which) {
        Log.d("DialogBUtton","Se hizo click en "+which);
        this.mensajes.get(indice).setMensaje(edit.getText().toString());
        this.mainActivity.notificarCambios(this.indice);

    }
}
