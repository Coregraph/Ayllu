/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.Profesor;

import BESA.Kernel.Agent.Event.DataBESA;
import java.util.ArrayList;
import persistencia.Tema;

/**
 *
 * @author Yolima
 */
public class DatosRetroalimentacion extends DataBESA{
    
    private String aliasSecretario;
    private String aliasCMA;
    private Tema tema;
    private ArrayList<String> retroalimentacionPreguntas;
    private ArrayList<String> preguntas;

    public DatosRetroalimentacion(Tema tem) {
        retroalimentacionPreguntas = new ArrayList<String>();
        tema = tem;
        preguntas = new ArrayList<String>();
        aliasSecretario = "";
        aliasCMA = "";
    }

    public DatosRetroalimentacion(Tema tem,  ArrayList<String> preg) {
        retroalimentacionPreguntas = new ArrayList<String>();
        tema = tem;
        preguntas = preg;
        aliasCMA = "";
    }
    /**
     * @return the estudiantesConTemaAceptado
     */
    public ArrayList<String> getRetroalimentacionPreguntas() {
        return retroalimentacionPreguntas;
    }

    /**
     * @param estudiantesConTemaAceptado the estudiantesConTemaAceptado to set
     */
    public void setRetroalimentacionPreguntas(ArrayList<String> estudiantes) {
        this.retroalimentacionPreguntas = estudiantes;
    }

    /**
     * @return the temaSeleccionado
     */
    public Tema getTema() {
        return tema;
    }

    /**
     * @param temaSeleccionado the temaSeleccionado to set
     */
    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public ArrayList<String> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<String> estudiantes) {
        this.preguntas = estudiantes;
    }

    public String getAliasSecretario() {
        return aliasSecretario;
    }

    public void setAliasSecretario(String aliasSecretario) {
        this.aliasSecretario = aliasSecretario;
    }

    public String getAliasCMA() {
        return aliasCMA;
    }

    public void setAliasCMA(String aliasCMA) {
        this.aliasCMA = aliasCMA;
    }

}