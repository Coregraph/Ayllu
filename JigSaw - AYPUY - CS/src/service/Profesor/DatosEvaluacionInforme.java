/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.Profesor;

import BESA.Kernel.Agent.Event.DataBESA;


/**
 *
 * @author Yolima
 */
public class DatosEvaluacionInforme extends DataBESA{
    
    private String tema;
    private Object ova;
    private String aliasCMA;

    public DatosEvaluacionInforme(String tem, Object oa, String aliasCma) {
        ova = oa;
        tema = tem;
        aliasCMA = aliasCma;
    }

    /**
     * @return the temaSeleccionado
     */
    public String getTema() {
        return tema;
    }

    /**
     * @param temaSeleccionado the temaSeleccionado to set
     */
    public void setTema(String tema) {
        this.tema = tema;
    }

    public Object getOva() {
        return ova;
    }

    public void setOva(Object ova) {
        this.ova = ova;
    }

    public String getAliasCMA() {
        return aliasCMA;
    }

    public void setAliasCMA(String aliasCMA) {
        this.aliasCMA = aliasCMA;
    }
    
}
