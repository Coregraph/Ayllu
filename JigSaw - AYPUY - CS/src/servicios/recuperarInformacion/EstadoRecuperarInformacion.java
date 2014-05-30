/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.recuperarInformacion;

import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communitymanageragent.CommunityManagerAgentState;
import java.util.List;

/**
 *
 * @author AylluAdmin
 */
public class EstadoRecuperarInformacion extends CommunityManagerAgentState {

    private DatosRecuperarInformacion misDatos;
    //XXX _NUEVO
    private int contadorUsuarios;
    private List<AgHandlerBESA> agentesComunidadAprendiz;
   //XXX _NUEVO
    public EstadoRecuperarInformacion(DatosRecuperarInformacion misDatos) {
        super(null,null);
        this.misDatos = misDatos;
        this.setCourseID("SIST_INT");
    }

    public DatosRecuperarInformacion getMisDatos() {
        return misDatos;
    }

    /**
     * @param misDatos the misDatos to set
     */
    public void setMisDatos(DatosRecuperarInformacion misDatos) {
        this.misDatos = misDatos;
    }
    //XXX _NUEVO
    public void initContadorUsuarios() {
        this.contadorUsuarios = 0;
    }
    
    public void aumentarContador(){
        this.contadorUsuarios++;
    }

    public int getContadorUsuarios() {
        return contadorUsuarios;
    }
    public int getNumeroUsuarios(){
        return this.getMisDatos().getTemasPorEstudiante().keySet().size();
    }

    public List<AgHandlerBESA> getAgentesComunidadAprendiz() {
        return agentesComunidadAprendiz;
    }
    //XXX _NUEVO

}
