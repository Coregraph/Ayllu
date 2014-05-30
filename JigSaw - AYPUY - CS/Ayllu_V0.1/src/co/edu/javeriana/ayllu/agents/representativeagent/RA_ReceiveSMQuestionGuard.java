/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.representativeagent;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import co.edu.javeriana.ayllu.agents.interfaceagentagent.IA_ReceiveRAQuestionGuard;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SMA_ReceiveRAReplyGuard;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Guard that asks the RA if it knows the answer, if it knows it, it will respond, if it doesn't a question to the user is sent
 * @author AYLLU
 */
public class RA_ReceiveSMQuestionGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {

        Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
        if (theData.getEventType() == Ayllu_EventTypes.QUEST_REQUEST) {
            try {
                handleRARequest(theData);
            } catch (Exception ex) {
                ReportBESA.error("[" + this.getClass().getName() + ":funcExecGuard]->" + ex.toString());
            }
        } else if (theData.getEventType() == Ayllu_EventTypes.MESSAGE) {
            try {
                handleRAMessage(theData);
            } catch (Exception ex) {
                ReportBESA.error("[" + this.getClass().getName() + ":funcExecGuard]->" + ex.toString());
            }
        }
    }

    private void handleRARequest(Ayllu_Data_Message theData) {
        String theKey = String.valueOf(theData.getMessage());
        RepresentativeAgentState state = (RepresentativeAgentState) this.getAgent().getState();
        Object responseObj = state.getAnswer(theKey);
        EventBESA event;
        AgHandlerBESA handler;
        try {
            if (responseObj == null) {
                theData.setSenderHandler(this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid()));
                event = new EventBESA(IA_ReceiveRAQuestionGuard.class.getName(), theData);
                handler = theData.getReceiverHandler();
            } else {
                theData.setEventType(Ayllu_EventTypes.QUEST_REPLY);
                theData.setMessage(responseObj);
                event = new EventBESA(SMA_ReceiveRAReplyGuard.class.getName(), theData);
                handler = theData.getSenderHandler();
            }
            handler.sendEvent(event);
        } catch (Exception ex) {
            Logger.getLogger(RA_ReceiveSMQuestionGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleRAMessage(Ayllu_Data_Message theData) {
        EventBESA event = new EventBESA(IA_ReceiveRAQuestionGuard.class.getName(), theData);
        AgHandlerBESA handler = theData.getReceiverHandler();
        try {
            handler.sendEvent(event);
        } catch (Exception ex) {
            Logger.getLogger(RA_ReceiveSMQuestionGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
