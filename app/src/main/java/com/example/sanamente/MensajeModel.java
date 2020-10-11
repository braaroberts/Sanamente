package com.example.sanamente;

import java.util.List;

public class MensajeModel {

    private String mensaje;
    private String mensajePost;

    private String variableHora;
    private String variableDia;

    public String getMensaje() {
        return mensaje;
    }

    public String getMensajePost() {
        return mensajePost;
    }

    public String getMensajeCompleto() {
        String salida = "";
        if(mensaje!= null){
            salida+=mensaje;
        }
        if(variableDia!= null){
            if(variableDia=="HOY"){
                salida+=" "+variableDia;
            }else{
                salida+=" el "+variableDia;
            }

        }else{salida+="";}
        if(variableHora!= null && variableHora!=""){
            salida+=" "+variableHora;
        }else{salida+="";}
        if(mensajePost!= null){
            salida+= "\n" +mensajePost;
        }else{salida+="";}

        return salida;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }


    public void setMensajePost(String mensajePost) {
        this.mensajePost = mensajePost;
    }


    public void setVariableHora(String variableHora) {
        this.variableHora = variableHora;
    }

    public void setVariableDia(String variableDia) {
        this.variableDia = variableDia;
    }
}
