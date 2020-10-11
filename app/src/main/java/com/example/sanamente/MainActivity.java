package com.example.sanamente;

import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static List<MensajeModel> mensajes = null;
    MensajeAdapter mensajesAdapter = new MensajeAdapter(this);
    String cacheDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cacheDir =   getCacheDir().getAbsolutePath()+"/MiCache";

        mensajes = new ArrayList<MensajeModel>();
        IniciarFromCache();

        //Toast.makeText(getBaseContext(), fromCache(getCacheDir().getAbsolutePath()+"/MiCache"),Toast.LENGTH_SHORT).show();

        RecyclerView rc = super.findViewById(R.id.recicler);

        mensajesAdapter.setMensajes(mensajes);
        rc.setAdapter(mensajesAdapter);

        LinearLayoutManager linearlayaoutManager =  new LinearLayoutManager(this);
        linearlayaoutManager.setOrientation(RecyclerView.VERTICAL);
        rc.setLayoutManager(linearlayaoutManager);

        ActionBar ab  = super.getSupportActionBar();
        ab.setTitle("Mensajes");
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStop() {
        toCache(cacheDir,toJson(mensajes));
        super.onStop();
    }


    public void borrar(Integer indice) {
        Log.d("Seguimiento","Intentando borrar el indice "+indice);

        mensajes.remove(mensajes.get(indice));
        toCache(cacheDir,toJson(mensajes));
        this.IniciarFromCache();
       this.mensajesAdapter.notifyDataSetChanged();

    }
    public void modificar(Integer indice){
        //Log.d("seguimiento",mensajes.get(indice).getMensaje());
        DialogGenerico dialog = new DialogGenerico(mensajes,indice,this);
        dialog.show(getSupportFragmentManager(),"etiqueta");
    }
    public void notificarCambios(Integer indice){
        this.mensajesAdapter.notifyItemChanged(indice);
    }

    public void controlWpp(String boton,Integer indice){
        switch (boton){
            case "1":
                this.enviaMensajeWhatsApp(mensajes.get(indice).getMensajeCompleto());
                break;
            case "2":
                this.enviaMensajeWhatsApp(mensajes.get(indice).getMensajeCompleto());
                break;
            case "3":
                this.enviaMensajeWhatsApp3(mensajes.get(indice).getMensajeCompleto(), "+5491123086894");

                break;
            case "4":
                this.enviaMensajeWhatsApp4(mensajes.get(indice).getMensajeCompleto(), "+5491123086894");

                break;
        }

        this.mensajesAdapter.notifyItemChanged(indice);
    }

    public void enviaMensajeWhatsApp(String msj) {
        PackageManager pm=getPackageManager();
        try {
            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            waIntent.setPackage("com.whatsapp");
            waIntent.putExtra(Intent.EXTRA_TEXT, msj);
            startActivity(Intent.createChooser(waIntent, "Compartir con:"));
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(getApplicationContext(), "WhatsApp no esta instalado!", Toast.LENGTH_SHORT)
                    .show();
        }
    }
    public void enviaMensajeWhatsApp2(String msj) {
        PackageManager pm=getPackageManager();
        try {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, msj);
            sendIntent.setType("text/plain");
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "WhatsApp no esta instalado!", Toast.LENGTH_SHORT)
                    .show();
        }
    }
    public void enviaMensajeWhatsApp2(String msj,String numero) {

        try {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        String uri = "whatsapp://send?phone=" + numero + "&text=" + msj;
        intent.setData(Uri.parse(uri));
        startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "WhatsApp no esta instalado!", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    public void enviaMensajeWhatsApp3(String msj,String numeroTelefono) {
         // Aquí va el número de teléfono, no olvidar el código de pais al inicio
        try {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setComponent(new ComponentName("com.whatsapp.w4b", "com.whatsapp.ContactPicker"));
        sendIntent.setType("text/plain");
        sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(numeroTelefono) + "@s.whatsapp.net");
        sendIntent.putExtra(Intent.EXTRA_TEXT, msj);
        startActivity(sendIntent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "WhatsApp no esta instalado!", Toast.LENGTH_SHORT)
                    .show();
        }
    }
    public void enviaMensajeWhatsApp4(String msj,String numeroTelefono) {
        try{
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        File file = new File("drawable/diablito.png");
        Uri uri = Uri.fromFile(file);
        sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.ContactPicker"));
        sendIntent.setType("image");
        sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
        sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(numeroTelefono) + "@s.whatsapp.net");
        sendIntent.putExtra(Intent.EXTRA_TEXT, msj);
        startActivity(sendIntent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "WhatsApp no esta instalado!", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option,menu);

       // MenuItem menuItem = menu.findItem(R.id.campo_buscar);
    //    SearchView searchView = (SearchView) menuItem.getActionView();
       //searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.op1) {
            Intent intent = new Intent(this, AgregarActivity.class);
            startActivity(intent);
            Log.d("Click", "Se hizo Click en la opt1");
        }
        else if(item.getItemId()== android.R.id.home){

            super.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void IniciarFromCache(){
        MensajeModel[] lista = fromJson(fromCache(cacheDir));
        if(lista!=null){
            mensajes.clear();
            for (MensajeModel msj :
                    lista) {
                mensajes.add(msj);
            }
        }

    }
    public String toJson(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
    public MensajeModel[] fromJson(String json){
        Gson gson = new Gson();
        MensajeModel[] data = gson.fromJson(json, MensajeModel[].class);
        return data;
    }
    public void toCache(String url, String content) {
        File dir = getApplicationContext().getCacheDir();
        File tempFile = new File(dir.getPath() + "/" + url.replace("/", "_"));
        FileWriter writer;
        try {
            if (tempFile.exists()) {
                tempFile.delete();
                try {
                    tempFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            writer = new FileWriter(tempFile);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String fromCache(String url) {
        String content = "";
        File dir = getApplicationContext().getCacheDir();
        File tempFile = new File(dir.getPath() + "/" + url.replace("/", "_"));
        FileReader fReader;
        try {
            fReader = new FileReader(tempFile);
            BufferedReader bReader = new BufferedReader(fReader);
            String strLine = "";
            StringBuilder text = new StringBuilder();
            while ((strLine = bReader.readLine()) != null) {
                text.append(strLine + "\n");
            }
            fReader.close();
            content = text.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    public boolean inCache(String url) {
        File dir = getApplicationContext().getCacheDir();
        File tempFile = new File(dir.getPath() + "/" + url.replace("/", "_"));
        if (tempFile.exists()) {
            return true;
        }
        return false;
    }


}