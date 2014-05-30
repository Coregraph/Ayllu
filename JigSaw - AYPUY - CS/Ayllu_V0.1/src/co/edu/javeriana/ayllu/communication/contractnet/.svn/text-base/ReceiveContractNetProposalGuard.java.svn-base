/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.communication.contractnet;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import co.edu.javeriana.ayllu.agents.communityagent.CommunityAgentState;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SMA_ReceiveCARequestGuard;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AylluAdmin
 */
public class ReceiveContractNetProposalGuard extends GuardBESA{
    
    @Override
    public void funcExecGuard(EventBESA ebesa) {
        
        Ayllu_Data_Message proposal = (Ayllu_Data_Message)ebesa.getData();
        proposal.setEventType(Ayllu_EventTypes.QUEST_REQUEST);
        
        EventBESA questionEvent = new EventBESA(SMA_ReceiveCARequestGuard.class.getName(),proposal);
        CommunityAgentState myState = (CommunityAgentState)this.getAgent().getState();
        try {
            myState.getSessionManagerHandler().sendEvent(questionEvent);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(ReceiveContractNetProposalGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
