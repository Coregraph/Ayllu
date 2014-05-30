/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.communication.contractnet;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.communication.contractnet.community.ContractNetInitiatorState;
import co.edu.javeriana.ayllu.communication.contractnet.community.ContractNetProposal;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AylluAdmin
 */
public class ContractNetCallForPurposalsGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {

        ContractNetInitiatorState myState = (ContractNetInitiatorState) this.getAgent().getState();
        List<AgHandlerBESA> participants = myState.getAgentHandlersByRole("Participant");
        try {
            for (AgHandlerBESA agHandler : participants) {
                Ayllu_Data_Message message = ((Ayllu_Data_Message) ebesa.getData());
                ContractNetProposal cp = (ContractNetProposal) message.getMessage();
                Ayllu_Data_Message newmessage = new Ayllu_Data_Message(message.getEventType(), this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid()), cp.getProposal());
                newmessage.setGuardToActivate(ContractNetWaitForProposalsGuard.class);
                EventBESA sendProposal = new EventBESA(ReceiveContractNetProposalGuard.class.getName(), newmessage);

                agHandler.sendEvent(sendProposal);
            }
        } catch (ExceptionBESA ex) {
            Logger.getLogger(ContractNetCallForPurposalsGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
