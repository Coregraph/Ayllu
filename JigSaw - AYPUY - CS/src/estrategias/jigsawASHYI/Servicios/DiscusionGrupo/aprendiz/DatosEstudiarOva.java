/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.Servicios.DiscusionGrupo.aprendiz;

import BESA.Kernel.Agent.Event.DataBESA;
/**
 *
 * @author Yolima
 */
public class DatosEstudiarOva extends DataBESA{
    
    private Object OA;
    private boolean OALeido;
    private String aliasCMA;

    public DatosEstudiarOva() {
         OA = new Object();
         OALeido = false;
         aliasCMA = "";
    }

    /**
     * @return the OA
     */
    public Object getOA() {
        return OA;
    }

    /**
     * @param OA the OA to set
     */
    public void setOA(Object OA) {
        this.OA = OA;
    }

    

    /**
     * @return the OALeido
     */
    public boolean isOALeido() {
        return OALeido;
    }

    /**
     * @param OALeido the OALeido to set
     */
    public void setOALeido(boolean OALeido) {
        this.OALeido = OALeido;
    }

    public String getAliasCMA() {
        return aliasCMA;
    }

    public void setAliasCMA(String aliasCA) {
        this.aliasCMA = aliasCA;
    }

}
