/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.Servicios.Discusion;

import co.edu.javeriana.ayllu.agents.communitymanageragent.CommunityManagerAgentState;

/**
 *
 * @author yolima
 */
public class Estado_JigSaw_Discusion extends CommunityManagerAgentState{
         
    private DatosDiscusion datos;
    private int numEstudiantesPregListas;
    
    
    public Estado_JigSaw_Discusion(DatosDiscusion data) {
        super(null, null);
        datos = data;     
        this.setCourseID("SIST_INT");
        numEstudiantesPregListas = 0;
    }

    public DatosDiscusion getDatos() {
        return datos;
    }

    public void setDatos(DatosDiscusion datos) {
        this.datos = datos;
    }
    
    public int getNumEstudiantesPregListas() {
        return numEstudiantesPregListas;
    }

    public void setNumEstudiantesPregListas(int numEstudiantesPregListas) {
        this.numEstudiantesPregListas = numEstudiantesPregListas;
    }
    
    public void aumentarNumEstudiantesPregListas() {
        this.numEstudiantesPregListas += 1;
    }    
}
