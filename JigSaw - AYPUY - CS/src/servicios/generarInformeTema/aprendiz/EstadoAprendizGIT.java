/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.generarInformeTema.aprendiz;

import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communityagent.AgentRole;
import co.edu.javeriana.ayllu.agents.communityagent.CommunityAgentState;
import java.util.List;

/**
 *
 * @author AylluAdmin
 */
public class EstadoAprendizGIT extends CommunityAgentState {

    private DatosAprendizGIT misDatos;

    
    public EstadoAprendizGIT(List<AgentRole> sameVolatileGroupHandlers, AgHandlerBESA sessionManagerHandler, AgHandlerBESA communityManagerHandler) {
        super(sameVolatileGroupHandlers, sessionManagerHandler, communityManagerHandler);
    }

    public EstadoAprendizGIT(DatosAprendizGIT misDatos, List<AgentRole> sameVolatileGroupHandlers, AgHandlerBESA sessionManagerHandler, AgHandlerBESA communityManagerHandler) {
        super(sameVolatileGroupHandlers, sessionManagerHandler, communityManagerHandler);
        this.misDatos = misDatos;
    }

    

    public DatosAprendizGIT getMisDatos() {
        return misDatos;
    }

    /**
     * @param misDatos the misDatos to set
     */
    public void setMisDatos(DatosAprendizGIT misDatos) {
        this.misDatos = misDatos;
    }
}
