/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.generarInformeTema.aprendiz;

import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import co.edu.javeriana.ayllu.agents.communityagent.CommunityAgent;

/**
 *
 * @author AylluAdmin
 */
public class CA_AprendizGIT extends CommunityAgent {
    
    private static long MAX_TIME_SERVICE = 86400000;

    public CA_AprendizGIT(String alias, StateBESA state, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, passwd);
    }

    @Override
    public void init() throws KernellAgentExceptionBESA {
        EstadoAprendizGIT estadoAprendiz = (EstadoAprendizGIT) this.getState();
        estadoAprendiz.getMisDatos().setHoraInicioServicio(System.currentTimeMillis());
        System.out.println("Inicializando el aprendiz para la generación de informes tema "+this.getAlias());
    }
}
