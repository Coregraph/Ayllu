/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.generarInformeTema.secretario;

import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communityagent.AgentRole;
import co.edu.javeriana.ayllu.agents.communityagent.CommunityAgentState;
import java.util.List;

/**
 *
 * @author AylluAdmin
 */
public class EstadoSecretarioGIT extends CommunityAgentState {

    private DatosSecretarioGIT misDatos;

    
    public EstadoSecretarioGIT(List<AgentRole> sameVolatileGroupHandlers, AgHandlerBESA sessionManagerHandler, AgHandlerBESA communityManagerHandler) {
        super(sameVolatileGroupHandlers, sessionManagerHandler, communityManagerHandler);
    }

    public EstadoSecretarioGIT(DatosSecretarioGIT misDatos, List<AgentRole> sameVolatileGroupHandlers, AgHandlerBESA sessionManagerHandler, AgHandlerBESA communityManagerHandler) {
        super(sameVolatileGroupHandlers, sessionManagerHandler, communityManagerHandler);
        this.misDatos = misDatos;
    }

    

    public DatosSecretarioGIT getMisDatos() {
        return misDatos;
    }

    /**
     * @param misDatos the misDatos to set
     */
    public void setMisDatos(DatosSecretarioGIT misDatos) {
        this.misDatos = misDatos;
    }
}
