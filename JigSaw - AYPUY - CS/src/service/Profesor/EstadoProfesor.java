/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.Profesor;

import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communityagent.AgentRole;
import co.edu.javeriana.ayllu.agents.communityagent.CommunityAgentState;
import java.util.ArrayList;
import java.util.List;
import persistencia.Tema;

/**
 *
 * @author Yolima
 */
public class EstadoProfesor extends CommunityAgentState {

    private DatosProfesor datos;
    private List<String> cmas;// pasos

    public EstadoProfesor(List<AgentRole> sameVolatileGroupHandlers, AgHandlerBESA sessionManagerHandler, AgHandlerBESA communityManagerHandler) {
        super(sameVolatileGroupHandlers, sessionManagerHandler, communityManagerHandler);
         this.cmas = new ArrayList<String>();
    }

    public EstadoProfesor(DatosProfesor datos, List<AgentRole> sameVolatileGroupHandlers, AgHandlerBESA sessionManagerHandler, AgHandlerBESA communityManagerHandler) {
        super(sameVolatileGroupHandlers, sessionManagerHandler, communityManagerHandler);
        this.datos = datos;
        this.cmas = new ArrayList<String>();
    }
    
    public DatosProfesor getMisDatos() {
        return datos;
    }

    /**
     * @param misDatos the misDatos to set
     */
    public void setMisDatos(DatosProfesor misDatos) {
        this.datos = misDatos;
    }
    //XXX _nuevo!
    public void sendAwarenessMessageToGUI(String senderId, String guardname, String destination, String coopserID, List<Tema> temasDisponibles) {
       
    }

    void setCmas(List<String> temasDisponibles) {
        this.cmas=temasDisponibles;
    }

    public List<String> getCmas() {
        return cmas;
    }
    
}
