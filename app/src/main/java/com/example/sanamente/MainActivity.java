package com.example.sanamente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static List<MensajeModel> mensajes = null;
    MensajeAdapter mensajesAdapter = new MensajeAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mensajes = new ArrayList<MensajeModel>();
        List<String> parametros = new ArrayList<>();
        parametros.add("SABADO");
        parametros.add("11hs");

        MensajeModel modelo = new MensajeModel();
        modelo.setParametros(parametros);
        modelo.setMensaje("Hola, buenos días, nos comunicamos de De Sanamente Estetica \uD83C\uDF38 para recordarte tu turno del día "+modelo.getParametros().get(0)+" a las "+modelo.getParametros().get(1)+" para Depilación definitiva.\n" +
                "\n" +
                "✨Recorda rasurarte y traer tu toallon.\n" +
                "\n" +
                "\uD83C\uDFE0Te esperamos en luzuriaga 263, entre el rosedal y piedrabuena, Llavallol. \n" +
                "\n" +
                "POR FAVOR CONFIRMAR ASISTENCIA \n" +
                "Muchas Gracias \uD83D\uDE18");



        mensajes.add(modelo);//"+5491163033100"
        mensajes.add(new MensajeModel("Buenos días! Nos comunicamos de De Sanamente Estetica\uD83C\uDF3A para informarte que tu pedido ya está en camino\uD83D\uDCEB\uD83D\uDE97\n" +
                "\n" +
                "Llegará el día de hoy a partir de las 15 hs\uD83D\uDD52\n" +
                "\n" +
                "Te pedimos por favor que nos envies un mensajito cuando lo tengas en tus manos para verificar que haya llegado correctamente\uD83D\uDCE6\uD83D\uDE4B\uD83C\uDFFB\u200D♀️\n" +
                "\n" +
                "Cualquier consulta siempre podes escribirnos a este número\uD83D\uDCF2\n" +
                "\n" +
                "Que lo disfrutes y muchas gracias por confiar en nosotras! \uD83D\uDC95✨"));// "+5491123086894"

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
    public void modificar(Integer indice){
        Log.d("seguimiento",mensajes.get(indice).getMensaje());
        DialogGenerico dialog = new DialogGenerico(mensajes.get(indice).getMensaje(),"Titulo",true,mensajes,indice,this);
        dialog.show(getSupportFragmentManager(),"etiqueta");
    }
    public void notificarCambios(Integer indice){
        this.mensajesAdapter.notifyItemChanged(indice);
    }

    public void controlWpp(String boton,Integer indice){
        switch (boton){
            case "1":
                this.enviaMensajeWhatsApp(mensajes.get(indice).getMensaje());
                break;
            case "2":
               //
                this.enviaMensajeWhatsApp2(mensajes.get(indice).getMensaje(), "+5491123086894");
                break;
            case "3":
                this.enviaMensajeWhatsApp3(mensajes.get(indice).getMensaje(), "+5491123086894");

                break;
            case "4":
                this.enviaMensajeWhatsApp4(mensajes.get(indice).getMensaje(), "+5491123086894");

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

        if(item.getItemId()==R.id.op1){
           Intent intent = new Intent(this, AgregarActivity.class);
           startActivity(intent);
            Log.d("Click","Se hizo Click en la opt1");
        }else if(item.getItemId()==R.id.op2){
            //DialogGenerico dialog = new DialogGenerico("Mensaje","Titulo",false);
            //dialog.show(getSupportFragmentManager(),"etiqueta");
            Log.d("Click","Se hizo Click en la opt2");

        }else if(item.getItemId()==R.id.op3){
           // DialogGenerico dialog = new DialogGenerico("Mensaje","Titulo",true);
          //  dialog.show(getSupportFragmentManager(),"etiqueta");
        }
        else if(item.getItemId()== android.R.id.home){

            super.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}