/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.General;

import BESA.Kernel.Agent.Event.DataBESA;


/**
 *
 * @author Yolima
 */
public class DatosRespuestaMATEOGB extends DataBESA{
    
    private String tema;
    private Object ova;
    private String aliasCMA;

    public DatosRespuestaMATEOGB(String tem, Object oa, String aliasCma) {
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

    /**
     * @return the Direccion OVa
     */
    public Object getOva() {
        return ova;
    }

    /**
     * @param the Direccion OVa
     */
    public void setOva(Object ova) {
        this.ova = ova;
    }

    /**
     * @return the aliasCMA
     */
    public String getAliasCMA() {
        return aliasCMA;
    }

    /**
     * @param aliasCMA
     */
    public void setAliasCMA(String aliasCMA) {
        this.aliasCMA = aliasCMA;
    }
    
}
