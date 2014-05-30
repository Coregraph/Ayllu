/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.communitymanageragent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SMA_ReceiveCARequestGuard;
import co.edu.javeriana.ayllu.data.Ayllu_AwarenessMessageRequestData;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class CMA_SendAwarenessMessageGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA event) {
        if (event.getData() instanceof Ayllu_AwarenessMessageRequestData) {
            CommunityManagerAgentState myState = (CommunityManagerAgentState) this.getAgent().getState();
            Ayllu_AwarenessMessageRequestData theData = (Ayllu_AwarenessMessageRequestData) event.getData();
            switch (theData.getMyType()) {
                case REQUEST:
                    String alias;
                    try {
                        alias = this.getAgent().getAdmLocal().getHandlerByAid(theData.getSenderId()).getAlias();
                        myState.addMessageEntry(alias.split("_")[0], theData.getSenderId(), theData.getGuardToRespond());
                        sendMessageToSMA(theData);
                    } catch (ExceptionBESA ex) {
                        Logger.getLogger(CMA_SendAwarenessMessageGuard.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case REPLY:
                    String id = theData.getSmaDestinatioId();//aqui es el username
                    //id = id.substring(id.lastIndexOf(">"), id.length());
                    try {
                        String respondToAg = myState.getSenderID(id);
                        String respondToGuard = myState.getResponseGuard(id);
                        sendMessageToCa(respondToAg, respondToGuard, theData);
                    } catch (Exception e) {
                        String errorMessage = "No se encontró mensaje de " + id + "En el servicio cooperativo" + this.getAgent().getAlias();
                        Logger.getLogger(CMA_SendAwarenessMessageGuard.class.getName()).log(Level.WARNING, errorMessage, e);
                    }
                    break;
            }
        }
    }

    private void sendMessageToSMA(Ayllu_AwarenessMessageRequestData theData) {
        EventBESA evrequest = new EventBESA(SMA_ReceiveCARequestGuard.class.getName(), theData);
        try {
            this.getAgent().getAdmLocal().getHandlerByAid(theData.getSmaDestinatioId()).sendEvent(evrequest);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(CMA_SendAwarenessMessageGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendMessageToCa(String respondToAg, String respondToGuard, Ayllu_AwarenessMessageRequestData theData) {
        try {
            AgHandlerBESA handler = this.getAgent().getAdmLocal().getHandlerByAid(respondToAg);
            Ayllu_Data_Message resp = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REPLY, handler, theData.getReqForm().getSelectedAnswersFromQuestion(0));
            EventBESA respAwa = new EventBESA(respondToGuard, resp);
            handler.sendEvent(respAwa);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(CMA_SendAwarenessMessageGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
