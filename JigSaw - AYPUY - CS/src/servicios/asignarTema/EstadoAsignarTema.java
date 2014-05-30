/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.asignarTema;

import co.edu.javeriana.ayllu.agents.communitymanageragent.CommunityManagerAgentState;

/**
 *
 * @author AylluAdmin
 */
public class EstadoAsignarTema extends CommunityManagerAgentState {

    private DatosAsignarTema misDatos;

    public EstadoAsignarTema(DatosAsignarTema misDatos) {
        super(null,null);
        this.misDatos = misDatos;
        this.setCourseID("SIST_INT");
    }

    public DatosAsignarTema getMisDatos() {
        return misDatos;
    }

    /**
     * @param misDatos the misDatos to set
     */
    public void setMisDatos(DatosAsignarTema misDatos) {
        this.misDatos = misDatos;
    }

    public EstadoAsignarTema clonar() {
        EstadoAsignarTema estadoAsignarTema = new EstadoAsignarTema(misDatos.clonar());
        return estadoAsignarTema;
    }
}
