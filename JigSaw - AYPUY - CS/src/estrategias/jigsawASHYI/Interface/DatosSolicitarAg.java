/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.Interface;

import BESA.Kernel.Agent.Event.DataBESA;


/**
 *
 * @author Yolima
 */
public class DatosSolicitarAg extends DataBESA{
    
    private String contenedor;
    private String iaAlias;

    public DatosSolicitarAg(String contenedor, String iaAlias) {
        this.contenedor = contenedor;
        this.iaAlias = iaAlias;
    }

    public String getContenedor() {
        return contenedor;
    }

    public void setContenedor(String contenedor) {
        this.contenedor = contenedor;
    }

    public String getIaAlias() {
        return iaAlias;
    }

    public void setIaAlias(String iaAlias) {
        this.iaAlias = iaAlias;
    }

   
}
