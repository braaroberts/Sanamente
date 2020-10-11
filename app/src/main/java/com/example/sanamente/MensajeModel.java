package com.example.sanamente;

import java.util.List;

public class MensajeModel {

    private String mensaje;

    private List<String> parametros;
    public MensajeModel() {
    }
    public MensajeModel(String mensaje) {
        this.mensaje = mensaje;


    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }





    public List<String> getParametros() {
        return parametros;
    }

    public void setParametros(List<String> parametros) {
        this.parametros = parametros;
    }
}
