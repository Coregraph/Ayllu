/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guardasagentesesion;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import mensajesagentesesion.MensajeReply;

/**
 *
 * @author usuario
 */
public class InterfazGuardReply extends GuardBESA{

    @Override
    public void funcExecGuard(EventBESA event) {
        MensajeReply rep = (MensajeReply) event.getData();
        System.out.println("El usuario respondio: "+rep.getResp());
    }
    
}
