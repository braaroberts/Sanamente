package com.example.sanamente;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AgregarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        MensajeModel modelo = new MensajeModel();

        AgregarView vista = new AgregarView(this,modelo);
        vista.onClickListenerGuardar();
         ActionBar ab  = super.getSupportActionBar();
        ab.setTitle("Agregar");
         ab.setDisplayHomeAsUpEnabled(true);
    }
}