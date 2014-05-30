/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import co.edu.javeriana.ayllu.agents.communitymanageragent.CommunityManagerAgentState;

/**
 *
 * @author Yolima
 */
public class EstadoAgenteRecursos extends CommunityManagerAgentState {

    private DatosAgenteRecursos misDatos;
   
    public EstadoAgenteRecursos() {
        super(null,null);
        this.misDatos = new DatosAgenteRecursos("");
        this.setCourseID("SIST_INT");        
    }
    
    public EstadoAgenteRecursos(DatosAgenteRecursos misDatos) {
        super(null,null);
        this.misDatos = misDatos;
        this.setCourseID("SIST_INT");        
    }

    public DatosAgenteRecursos getMisDatos() {
        return misDatos;
    }

    /**
     * @param misDatos the misDatos to set
     */
    public void setMisDatos(DatosAgenteRecursos misDatos) {
        this.misDatos = misDatos;
    }
}
