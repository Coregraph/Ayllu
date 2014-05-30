/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.communitymanageragent;

import BESA.ExceptionBESA;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;

import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;

/**
 * This guard makes the CMA agent to end its volatilegroups (killing all the agents) and reporting that the work has ended to the agent that created it.
 * This guard is automatically added in the static CMA prepareInstance method.
 * @author AYLLU
 */
public class CMA_EndVolatileGroupGuard extends GuardBESA{

 


        
    @Override
    public void funcExecGuard(EventBESA ebesa) {
        try {
            Ayllu_Data_Message theData = (Ayllu_Data_Message)ebesa.getData();
            CommunityManagerAgentState state = (CommunityManagerAgentState) this.getAgent().getState();
        if (theData.getEventType()==Ayllu_EventTypes.STOP_TASK){
            
            //AgentBESA sender = this.getAgent().getAdmLocal().getHandlerByAid(ebesa.getSenderAgId()).getAgent();
            
                AgHandlerBESA replyTo = state.getAgentToSendEndHandler();
                EventBESA replyEvent = new EventBESA(state.getGuardToSendEndHandler().getName(), theData);
                replyTo.sendEvent(replyEvent); 
                
                
                
                
                ((CommunityManagerAgent)this.getAgent()).destroyAllCommunityAgents(theData);
                this.getAgent().getAdmLocal().killAgent(this.getAgent().getAid(),state.getVolatileGroupPassword());
            
        }
        } catch (ExceptionBESA ex) {
            ReportBESA.error("["+this.getClass().getName()+":funcExecGuard]->"+ex.toString());
        }
    }

    
    
}
