/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.sessionmanageragent;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Log.ReportBESA;
import co.edu.javeriana.ayllu.agents.representativeagent.RA_ReceiveSMQuestionGuard;
import co.edu.javeriana.ayllu.data.Ayllu_CARegisterData;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;

/**
 *  Handles the request of a CMA
 * @author AYLLU
 */
public class SMA_ReceiveCMARequestGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        if (ebesa.getData() instanceof Ayllu_Data_Message) {
            Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
            try {
                askRA(theData);
            } catch (Exception ex) {
                ReportBESA.error("[" + this.getClass().getName() + ":funcExecGuard]->" + ex.toString());
            }
        }else if (ebesa.getData() instanceof Ayllu_CARegisterData){
            SessionManagerAgentState myState = (SessionManagerAgentState)this.getAgent().getState();
            myState.addCommunityAgent(((Ayllu_CARegisterData)ebesa.getData()).getTheCAtoregister());
        }
    }

    private void askRA(Ayllu_Data_Message theData) {
        ReportBESA.info("RECIBI PETICION DE SERVICIO y SOY: " + this.getAgent().getAlias());
        try {
            SessionManagerAgentState state = (SessionManagerAgentState) this.getAgent().getState();
            Ayllu_Data_Message newData = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid()), theData.getMessage());
            state.addSender(newData.getMessageId(), theData.getSenderHandler());
            EventBESA event = new EventBESA(RA_ReceiveSMQuestionGuard.class.getName(), newData);

            state.getRepresentativeAgentHandler().sendEvent(event);
        } catch (Exception ex) {
            ReportBESA.error("[" + this.getClass().getName() + "] => Error calling the RA" + ex.toString());
        }
    }
}
