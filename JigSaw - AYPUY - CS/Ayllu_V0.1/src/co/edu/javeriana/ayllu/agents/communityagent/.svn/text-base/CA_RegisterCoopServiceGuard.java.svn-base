/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.communityagent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SMA_ReceiveCARequestGuard;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LG
 */
public class CA_RegisterCoopServiceGuard extends GuardBESA{

    @Override
    public void funcExecGuard(EventBESA event) {
        CommunityAgentState myState = (CommunityAgentState)this.getAgent().getState();
        try {
            myState.getSessionManagerHandler().sendEvent(new EventBESA(SMA_ReceiveCARequestGuard.class.getName(), event.getData()));
        } catch (ExceptionBESA ex) {
            Logger.getLogger(CA_RegisterCoopServiceGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
