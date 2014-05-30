/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.sessionmanageragent;

import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import co.edu.javeriana.ayllu.agents.interfaceagentagent.IA_ReceiveSMRequestGuard;
import co.edu.javeriana.ayllu.agents.representativeagent.RA_ReceiveSMQuestionGuard;
import co.edu.javeriana.ayllu.data.Ayllu_AwarenessMessageRequestData;
import co.edu.javeriana.ayllu.data.Ayllu_CoopServCreationMessage;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import co.edu.javeriana.ayllu.data.Ayllu_NumberOfStudensAwarenessData;
import co.edu.javeriana.ayllu.data.Ayllu_WallMessageData;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * GUard that receives a request from a CA
 * @author AYLLU
 */
public class SMA_ReceiveCARequestGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        try {
            handleCARequest(ebesa.getData());

        } catch (Exception ex) {
            ReportBESA.error("[" + this.getClass().getName() + ":funcExecGuard]->" + ex.toString());
        }
    }

    /**
     * Handles the request of a CA, if it is a Question it rill redirect it to a RA, is it is a message TODO _SMA and if if its a display event it will redirect it to the Active IAs
     * @param theData 
     */
    public void handleCARequest(DataBESA aData) {
        SessionManagerAgentState myState = (SessionManagerAgentState) this.getAgent().getState();
        if (aData instanceof Ayllu_Data_Message) {
            Ayllu_Data_Message theData = (Ayllu_Data_Message) aData;
            switch (theData.getEventType()) {
                case MESSAGE:
                    handleCAMessage(theData, myState);
                    break;
                case QUEST_REQUEST:
                    handleCAQuestion(theData, myState);
                    break;
                case DISPLAY:
                    handleDisplayEvent(theData, myState);
                    break;
                case DISPLAYESPECIFIC:
                    handleDisplayEspecificEvent(theData, myState);
                    break;    
            }
        } else if (aData instanceof Ayllu_WallMessageData) {
            Ayllu_WallMessageData theData = (Ayllu_WallMessageData) aData;
            handleDisplayEvent(theData, myState);
        } else if (aData instanceof Ayllu_CoopServCreationMessage) {
        } else if (aData instanceof Ayllu_NumberOfStudensAwarenessData) {
            Ayllu_NumberOfStudensAwarenessData theData = (Ayllu_NumberOfStudensAwarenessData) aData;
            handleNumberStudentsUpdate(myState, theData);
        } else if (aData instanceof Ayllu_AwarenessMessageRequestData) {
            Ayllu_AwarenessMessageRequestData theData = (Ayllu_AwarenessMessageRequestData) aData;
//            myState.addAwarenessMessage(theData.getCooperativeServiceId(),theData.getReqForm());
            reportToIa(myState, aData);

        }
    }

    private void handleNumberStudentsUpdate(SessionManagerAgentState myState, Ayllu_NumberOfStudensAwarenessData theData) {
//        myState.updateNumberOfStudents(theData.getCoopservId(), theData.getNumstudents());
        reportToIa(myState, theData);
    }

    private void handleCAQuestion(Ayllu_Data_Message theData, SessionManagerAgentState state) {
        ReportBESA.info("RECIBI PETICION DE SERVICIO y SOY: " + this.getAgent().getAlias());
        try {
            Ayllu_Data_Message newData = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid()), theData.getMessage());
            newData.setGuardToActivate(theData.getGuardToActivate());
            newData.setReceiverHandler(theData.getReceiverHandler());
            state.addSender(newData.getMessageId(), theData.getSenderHandler());
            EventBESA event = new EventBESA(RA_ReceiveSMQuestionGuard.class.getName(), newData);

            state.getRepresentativeAgentHandler().sendEvent(event);
        } catch (Exception ex) {
            ReportBESA.error("[" + this.getClass().getName() + "] => Error calling the RA" + ex.toString());
        }
    }

    private void handleCAMessage(Ayllu_Data_Message theData, SessionManagerAgentState state) {
        EventBESA event = new EventBESA(RA_ReceiveSMQuestionGuard.class.getName(), theData);
        try {
            state.getRepresentativeAgentHandler().sendEvent(event);
        } catch (Exception ex) {
            ReportBESA.error("[" + this.getClass().getName() + "] => Error calling the RA" + ex.toString());
        }
    }

    private void handleDisplayEvent(DataBESA theData, SessionManagerAgentState state) {
//        state.addMessageToWall(theData);
        reportToIa(state, theData);
    }
    
     private void handleDisplayEspecificEvent(DataBESA theData, SessionManagerAgentState state) {
//        state.addMessageToWall(theData);
        reportToOneIa(state, theData);
    }

    private void reportToIa(SessionManagerAgentState myState, DataBESA regmsj) {
        List<AgHandlerBESA> iaList = myState.getInterfaceAgentsID();
        for (AgHandlerBESA agHandler : iaList) {
            EventBESA iaEvent = new EventBESA(IA_ReceiveSMRequestGuard.class.getName(), regmsj);
            try {
                agHandler.sendEvent(iaEvent);
            } catch (Exception ex) {
                Logger.getLogger(SMA_ReceiveCARequestGuard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void reportToOneIa(SessionManagerAgentState myState, DataBESA regmsj) {
        Ayllu_Data_Message data = (Ayllu_Data_Message) regmsj;
        AgHandlerBESA agHandler = data.getReceiverHandler();
        EventBESA iaEvent = new EventBESA(IA_ReceiveSMRequestGuard.class.getName(), regmsj);
        try {
            agHandler.sendEvent(iaEvent);
        } catch (Exception ex) {
            Logger.getLogger(SMA_ReceiveCARequestGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}