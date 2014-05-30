/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.sessionmanageragent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import co.edu.javeriana.ayllu.agents.communitymanageragent.CMA_SendMessageToCAsGuard;
import co.edu.javeriana.ayllu.agents.factoryagent.FA_CMACreationGuard;
import co.edu.javeriana.ayllu.data.Ayllu_Agent_Creation_Message;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_WallMessageData;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  Handles the request of an IA
 * @author AYLLU
 */
public class SMA_ReceiveIARequestGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        AgHandlerBESA handler_RA = ((SessionManagerAgentState) this.getAgent().getState()).getRepresentativeAgentHandler();
        //TODO_SMA Handler que le pregunta al RA
        try {
            handleIAMessage(ebesa.getData());
        } catch (Exception ex) {
            ReportBESA.error("[" + this.getClass().getName() + ":funcExecGuard]->" + ex.toString());
        }
    }

    private void handleIAMessage(DataBESA theData) {
        if (theData instanceof Ayllu_WallMessageData) {
            Ayllu_WallMessageData msjData = (Ayllu_WallMessageData) theData;
            AgHandlerBESA cmaAgent = null;
            try {
                cmaAgent = this.agent.getAdmLocal().getHandlerByAid(msjData.getCooperativeServiceId());
            } catch (ExceptionBESA ex) {
                Logger.getLogger(SMA_ReceiveIARequestGuard.class.getName()).log(Level.SEVERE, null, ex);
            }
            EventBESA evsendMessagetoCa = new EventBESA(CMA_SendMessageToCAsGuard.class.getName(), msjData);
            try {
                cmaAgent.sendEvent(evsendMessagetoCa);
            } catch (ExceptionBESA ex) {
                Logger.getLogger(SMA_ReceiveIARequestGuard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(theData instanceof Ayllu_Agent_Creation_Message){
           
            Ayllu_Agent_Creation_Message creamsj = (Ayllu_Agent_Creation_Message)theData;
            Iterator it = this.agent.getAdmLocal().searchAidByService("FactoryAgent");
            EventBESA evcreate = new EventBESA(FA_CMACreationGuard.class.getName(), creamsj);
            try {
                ((AgHandlerBESA)it.next()).sendEvent(evcreate);
            } catch (ExceptionBESA ex) {
                Logger.getLogger(SMA_ReceiveIARequestGuard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(theData instanceof Ayllu_Data_Message){
            Ayllu_Data_Message dataMessage = (Ayllu_Data_Message)theData;
            AgHandlerBESA agentToSend = dataMessage.getReceiverHandler();
            EventBESA evsendMessagetoCa = new EventBESA(dataMessage.getGuardToActivate().getName(),theData);
            try {
                agentToSend.sendEvent(evsendMessagetoCa);
            } catch (ExceptionBESA ex) {
                Logger.getLogger(SMA_ReceiveIARequestGuard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /*SessionManagerAgentState state = (SessionManagerAgentState)this.getAgent().getState();
        if(theData.getEventType()==Ayllu_EventTypes.STATE_REPORT){
        theData = state.handleCAMessage(theData);//Enviar un mensaje al CA seleccionado
        EventBESA event = new EventBESA(theData.getGuardToActivate().getName(), theData);
        try {
        theData.getReceiverHandler().sendEvent(event);
        } catch (ExceptionBESA ex) {
        ReportBESA.error("["+this.getClass().getName() +":funcExecGuard]->"+ex.toString());
        }
        }else if(theData.getEventType()==Ayllu_EventTypes.MESSAGE){
        
        theData = state.handleIAMessage(theData);
        EventBESA event = new EventBESA(theData.getGuardToActivate().getName(), theData);
        try {
        theData.getReceiverHandler().sendEvent(event);
        } catch (ExceptionBESA ex) {
        ReportBESA.error("["+this.getClass().getName() +":funcExecGuard]->"+ex.toString());
        }
        }else if(theData.getEventType()==Ayllu_EventTypes.DISPLAY){
        AgHandlerBESA caToRespond= theData.getReceiverHandler();
        EventBESA event = new EventBESA(theData.getGuardToActivate().getName(),theData);
        try {
        caToRespond.sendEvent(event);
        } catch (ExceptionBESA ex) {
        Logger.getLogger(SMA_ReceiveIARequestGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
        }*/

    }
}
