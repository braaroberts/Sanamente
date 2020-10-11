package com.example.sanamente;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MensajeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tvMensaje;
    public TextView tvMensaje2;
    public Integer id;
    public Spinner spinnerhora;
    public Spinner spinnerDia;
    public Button share2;
    public Button share3;
    public Button delete;
    public Button edit;

    public MensajeViewHolder(@NonNull View itemView, MainActivity main) {
        super(itemView);
        tvMensaje= this.itemView.findViewById(R.id.texto);
        share2 = this.itemView.findViewById(R.id.butonShare2);
        share3 = this.itemView.findViewById(R.id.butonShare3);
        delete = this.itemView.findViewById(R.id.delete);
        edit = this.itemView.findViewById(R.id.edit);

        spinnerDia = this.itemView.findViewById(R.id.ItemSpinnerDias);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(main.getApplicationContext(),R.array.dias,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDia.setAdapter(adapter);

        spinnerhora = this.itemView.findViewById(R.id.ItemSpinnerHoras);
        ArrayAdapter<CharSequence> adapterHoras = ArrayAdapter.createFromResource(main.getApplicationContext(),R.array.horas,android.R.layout.simple_spinner_item);
      //  adapterHoras.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerhora.setAdapter(adapterHoras);
       Log.d("Seguimiento","Donde se ejecuta esta mierda? -1 ");
       OnItemSelectedListener onItemSelected = new OnItemSelectedListener(main,this);
        Log.d("Seguimiento","Donde se ejecuta esta mierda? -2 ");
        spinnerDia.setOnItemSelectedListener(onItemSelected);
        Log.d("Seguimiento","Donde se ejecuta esta mierda? -3 ");
        spinnerhora.setOnItemSelectedListener(onItemSelected);
        //OnItemClickListener onItemSelected = new OnItemClickListener(main,this);
        //spinnerhora.setOnItemClickListener(new OnItemClickListener(main,this));


       // spinnerDia.setOnItemClickListener(onItemSelected);
      //  spinnerDia.setOnItemClickListener(new OnItemClickListener(main,this));

       // spinnerDia.setVisibility(View.INVISIBLE);
        //spinnerhora.setVisibility(View.INVISIBLE);

        OnClickListenerGeneric onclick = new OnClickListenerGeneric(main,this);

        delete.setOnClickListener(onclick);
        share2.setOnClickListener(onclick);
        share3.setOnClickListener(onclick);
        edit.setOnClickListener(onclick);


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Log.d("LLamando id = ", this.id + " - " +this.tvMensaje.getText().toString());
        //DialogGenerico dialog = new DialogGenerico(this.tvMensaje.getText().toString(),"Titulo",false);
        //dialog.show(main.getSupportFragmentManager(),"etiqueta");
    }
}
