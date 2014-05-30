/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.sessionmanageragent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import co.edu.javeriana.ayllu.agents.communityagent.CommunityAgent;
import co.edu.javeriana.ayllu.agents.communitymanageragent.CMA_EndVolatileGroupGuard;
import co.edu.javeriana.ayllu.agents.communitymanageragent.CommunityManagerAgent;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * GUard that receives a rsponse from a question made to a RA
 * @author AYLLU
 */
public class SMA_ReceiveRAReplyGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        ReportBESA.info("Recibi respuesta de mi RA y soy: " + this.getAgent().getAlias());
        Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
        AgHandlerBESA handler_RA = ((SessionManagerAgentState) this.getAgent().getState()).getRepresentativeAgentHandler();
        //Handler que le pregunta al RA
        try {
            handleRAMessage(theData);
        } catch (Exception ex) {
            ReportBESA.error("[" + this.getClass().getName() + ":funcExecGuard]->" + ex.toString());
        }
    }

    private void handleRAMessage(Ayllu_Data_Message theData) {
        SessionManagerAgentState state = (SessionManagerAgentState) this.getAgent().getState();
        if (theData.getEventType() == Ayllu_EventTypes.QUEST_REPLY) {
            handleRAReply(theData);
        }
    }

    public synchronized void handleRAReply(Ayllu_Data_Message theData) {
        SessionManagerAgentState state = (SessionManagerAgentState) this.getAgent().getState();
        AgHandlerBESA sender = state.getSenderOfMessage(theData.getMessageId());
        EventBESA event = null;
        try {
            ReportBESA.info(this.getAgent().getAlias()+": Mi Agente RA Respondio "+theData.getMessage());
            if (sender.getAg() instanceof CommunityManagerAgent && theData.getMessage().equals("OK")) {
                theData.setEventType(Ayllu_EventTypes.CREATE_AGENT);
                theData.setMessage(this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid()));
                event = new EventBESA(CMA_EndVolatileGroupGuard.class.getName(), theData);
            } else if (sender.getAg() instanceof CommunityAgent) {
                theData.setEventType(Ayllu_EventTypes.QUEST_REPLY);
                event = new EventBESA(theData.getGuardToActivate().getName(), theData);
            }
            if (event != null) {

                sender.sendEvent(event);
            }  
        }catch (ExceptionBESA ex) {
            Logger.getLogger(SessionManagerAgentState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

