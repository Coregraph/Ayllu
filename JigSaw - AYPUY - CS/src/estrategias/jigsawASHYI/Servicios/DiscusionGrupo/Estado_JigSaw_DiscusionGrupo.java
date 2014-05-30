/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.Servicios.DiscusionGrupo;

import co.edu.javeriana.ayllu.agents.communitymanageragent.CommunityManagerAgentState;
import estrategias.jigsawASHYI.Servicios.Discusion.DatosDiscusion;

/**
 *
 * @author yolima
 */
public class Estado_JigSaw_DiscusionGrupo extends CommunityManagerAgentState{
         
    private DatosDiscusion datos;
    private DatosEstudiarOvaGrupo datosEstudio;
    private int numEstudiantesPregListas;    
    
    public Estado_JigSaw_DiscusionGrupo(DatosDiscusion data, int numEstudiantes) {
        super(null, null);
        datos = data;     
        this.setCourseID("SIST_INT");
        numEstudiantesPregListas = 0;
        datosEstudio = new DatosEstudiarOvaGrupo(numEstudiantes);
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

    public DatosEstudiarOvaGrupo getDatosEstudio() {
        return datosEstudio;
    }

    public void setDatosEstudio(DatosEstudiarOvaGrupo datosEstudio) {
        this.datosEstudio = datosEstudio;
    }
    
    
}
