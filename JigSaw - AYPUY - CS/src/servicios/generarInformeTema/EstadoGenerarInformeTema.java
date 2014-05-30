/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.generarInformeTema;

import co.edu.javeriana.ayllu.agents.communitymanageragent.CommunityManagerAgentState;

/**
 *
 * @author AylluAdmin
 */
public class EstadoGenerarInformeTema extends CommunityManagerAgentState {

    private DatosGenerarInformeTema misDatos;

    public EstadoGenerarInformeTema(DatosGenerarInformeTema misDatos) {
        super(null,null);
        this.misDatos = misDatos;
        this.setCourseID("SIST_INT");
    }

    public DatosGenerarInformeTema getMisDatos() {
        return misDatos;
    }

    /**
     * @param misDatos the misDatos to set
     */
    public void setMisDatos(DatosGenerarInformeTema misDatos) {
        this.misDatos = misDatos;
    }    


}
