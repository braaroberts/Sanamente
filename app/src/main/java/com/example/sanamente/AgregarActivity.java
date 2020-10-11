package com.example.sanamente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class AgregarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        MensajeModel modelo = new MensajeModel();
        AgregarView vista = new AgregarView(this,modelo);
        vista.cargarSpinners();
        vista.onClickListenerGuardar();
         ActionBar ab  = super.getSupportActionBar();
        ab.setTitle("Agregar");
         ab.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_option,menu);

        // MenuItem menuItem = menu.findItem(R.id.campo_buscar);
        //    SearchView searchView = (SearchView) menuItem.getActionView();
        //searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

       if(item.getItemId()== android.R.id.home){

            super.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}