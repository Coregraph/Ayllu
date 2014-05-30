/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.generarInformeGrupo.secretario;

import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communityagent.AgentRole;
import co.edu.javeriana.ayllu.agents.communityagent.CommunityAgentState;
import java.util.List;

/**
 *
 * @author AylluAdmin
 */
public class EstadoSecretarioGIG extends CommunityAgentState {

    private DatosSecretarioGIG misDatos;

    public EstadoSecretarioGIG(List<AgentRole> sameVolatileGroupHandlers, AgHandlerBESA sessionManagerHandler, AgHandlerBESA communityManagerHandler) {
        super(sameVolatileGroupHandlers, sessionManagerHandler, communityManagerHandler);
    }

    public EstadoSecretarioGIG(DatosSecretarioGIG misDatos, List<AgentRole> sameVolatileGroupHandlers, AgHandlerBESA sessionManagerHandler, AgHandlerBESA communityManagerHandler) {
        super(sameVolatileGroupHandlers, sessionManagerHandler, communityManagerHandler);
        this.misDatos = misDatos;
    }

    

    

    

    public DatosSecretarioGIG getMisDatos() {
        return misDatos;
    }

    /**
     * @param misDatos the misDatos to set
     */
    public void setMisDatos(DatosSecretarioGIG misDatos) {
        this.misDatos = misDatos;
    }
}
