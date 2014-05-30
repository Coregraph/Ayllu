/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.communication.contractnet;

import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communitymanageragent.CommunityManagerAgentState;
import java.util.List;

/**
 *
 * @author AYLLU
 */
public class CMA_ContractNetState extends CommunityManagerAgentState{
    private String proposal;
    private long timeout;
    private List<AgHandlerBESA> contractNetParticipants;
    private AgHandlerBESA contractNetInitiatior;

    public CMA_ContractNetState(AgHandlerBESA endAgent, Class endGuard, long timeout, List<AgHandlerBESA> contractNetParticipants, AgHandlerBESA contractNetInitiatior, String proposal) {
        super(endAgent, endGuard);
        this.timeout = timeout;
        this.contractNetParticipants = contractNetParticipants;
        this.contractNetInitiatior = contractNetInitiatior;
        this.proposal=proposal;
    }

    public long getTimeout() {
        return timeout;
    }

    public AgHandlerBESA getContractNetInitiatior() {
        return contractNetInitiatior;
    }

    public List<AgHandlerBESA> getContractNetParticipants() {
        return contractNetParticipants;
    }

    public void setProposal(String proposal) {
        this.proposal = proposal;
    }
    
    String getProposal() {
        return proposal;
    }
    
    
}