/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.communication.contractnet;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communitymanageragent.CommunityManagerAgent;
import co.edu.javeriana.ayllu.communication.contractnet.community.ContractNetInitiatorState;
import co.edu.javeriana.ayllu.communication.contractnet.community.ContractNetProposal;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AYLLU@
 */
public class CMA_ContractNet extends CommunityManagerAgent {

    public CMA_ContractNet(String alias, StateBESA state, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, passwd);
    }

    @Override
    public void cmaInitService() {
        CMA_ContractNetState myState = (CMA_ContractNetState) this.getState();
        AgHandlerBESA smaInitiator = myState.getContractNetInitiatior();
        AgHandlerBESA caInitiator = null;
        Ayllu_Data_Message startData = null;
        try {
            AgHandlerBESA myHandler = this.getAdmLocal().getHandlerByAid(this.getAid());
            List<AgHandlerBESA> participants = myState.getContractNetParticipants();

            for (AgHandlerBESA participantSMA : participants) {
                List<Class> participantGuards = new ArrayList<Class>();
                participantGuards.add(ReceiveContractNetProposalGuard.class);
                this.createCommunityAgent("Participant", participantSMA, participantGuards);
            }

            List<Class> initiatorGuards = new ArrayList<Class>();
            initiatorGuards.add(ContractNetCallForPurposalsGuard.class);
            initiatorGuards.add(ContractNetWaitForProposalsGuard.class);
            ContractNetInitiatorState inistate = new ContractNetInitiatorState(participants.size());
            caInitiator = this.createCommunityAgent("Initiator", smaInitiator, initiatorGuards, inistate);



            ContractNetProposal proposal = new ContractNetProposal(myState.getProposal(), myState.getTimeout());
            startData = new Ayllu_Data_Message(Ayllu_EventTypes.START_COOP, myHandler, proposal);

            EventBESA startEvent = new EventBESA(ContractNetCallForPurposalsGuard.class.getName(), startData);

            caInitiator.sendEvent(startEvent);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(CMA_ContractNet.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventBESA startTimeoutEvent = new EventBESA(ContractNetWaitForProposalsGuard.class.getName(), startData);
        try {
            caInitiator.sendEvent(startTimeoutEvent);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(CMA_ContractNet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
