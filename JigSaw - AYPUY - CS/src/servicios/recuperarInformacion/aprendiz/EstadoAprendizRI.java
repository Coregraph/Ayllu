/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.recuperarInformacion.aprendiz;

import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communityagent.AgentRole;
import co.edu.javeriana.ayllu.agents.communityagent.CommunityAgentState;
import java.util.List;

/**
 *
 * @author AylluAdmin
 */
public class EstadoAprendizRI extends CommunityAgentState {

    private DatosAprendizRI misDatos;

    public EstadoAprendizRI(List<AgentRole> sameVolatileGroupHandlers, AgHandlerBESA sessionManagerHandler, AgHandlerBESA communityManagerHandler) {
        super(sameVolatileGroupHandlers, sessionManagerHandler, communityManagerHandler);
    }

    public EstadoAprendizRI(DatosAprendizRI misDatos, List<AgentRole> sameVolatileGroupHandlers, AgHandlerBESA sessionManagerHandler, AgHandlerBESA communityManagerHandler) {
        super(sameVolatileGroupHandlers, sessionManagerHandler, communityManagerHandler);
        this.misDatos = misDatos;
    }

    

    

    public DatosAprendizRI getMisDatos() {
        return misDatos;
    }

    /**
     * @param misDatos the misDatos to set
     */
    public void setMisDatos(DatosAprendizRI misDatos) {
        this.misDatos = misDatos;
    }
}
