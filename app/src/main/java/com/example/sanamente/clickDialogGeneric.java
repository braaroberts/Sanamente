package com.example.sanamente;

import android.content.DialogInterface;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class clickDialogGeneric implements DialogInterface.OnClickListener {
    List<MensajeModel> mensajes;
    Integer indice;
    EditText edit;
    EditText editPost;
    MainActivity mainActivity;
    Spinner horas;
    Spinner dias;


    public clickDialogGeneric(List<MensajeModel> mensajes, Integer indice, EditText edit,EditText editPost, MainActivity mainActivity, Spinner horas, Spinner dias) {
        this.indice = indice;
        this.mensajes = mensajes;
        this.edit = edit;
        this.mainActivity = mainActivity;
        this.horas = horas;
        this.dias = dias;
        this.editPost = editPost;
    }
    @Override
    public void onClick(DialogInterface dialog, int which) {
        //MensajeModel modelo =  this.mensajes.get(indice);

       // modelo.setMensaje(edit.getText().toString());

        this.mensajes.get(indice).setMensajePost(editPost.getText().toString());
        this.mensajes.get(indice).setMensaje(edit.getText().toString());

        this.mainActivity.notificarCambios(this.indice);

    }
}
