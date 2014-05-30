/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.communitymanageragent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import co.edu.javeriana.ayllu.agents.communityagent.AgentRole;
import co.edu.javeriana.ayllu.agents.communityagent.CA_DisplayMessageGuard;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class CMA_SendMessageToCAsGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA event) {
        CommunityManagerAgentState myState = (CommunityManagerAgentState) this.getAgent().getState();
        
        EventBESA evsendmsjToGui = new EventBESA(CA_DisplayMessageGuard.class.getName(), event.getData());
        List<AgentRole> caHandlers = myState.getCommunityAgentsHandlers();
        for (int i = 0; i < caHandlers.size(); i++) {
            try {
                caHandlers.get(i).getCommunityAgentHandler().sendEvent(evsendmsjToGui);
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(CMA_SendMessageToCAsGuard.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExceptionBESA ex) {
                Logger.getLogger(CMA_SendMessageToCAsGuard.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        //myState.setFree(true);

    }


    
}
