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
public class DatosInstrucciones extends DataBESA{
    
    private String instrucciones;
    private String aliasP;

    public DatosInstrucciones(String instruccionesP ) {
       instrucciones = instruccionesP;
       aliasP = "";
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getAliasP() {
        return aliasP;
    }

    public void setAliasP(String aliasP) {
        this.aliasP = aliasP;
    }

   

}
