/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.factoryagent;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Log.ReportBESA;
import co.edu.javeriana.ayllu.data.Ayllu_Agent_Creation_Message;

/**
 * Guard of the Factory Agent that initiates the creation of a CMA
 * @author AYLLU
 */
public class FA_CMACreationGuard extends GuardBESA {

 

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        Ayllu_Agent_Creation_Message theData = (Ayllu_Agent_Creation_Message) ebesa.getData();
        try {
            createOneCMAAgent(theData);
        } catch (Exception ex) {
            ReportBESA.error("[" + this.getClass().getName() + ":funcExecGuard]->" + ex.toString());
        }
    }
    private void createOneCMAAgent(Ayllu_Agent_Creation_Message theData) {
        
        ((FactoryAgent)this.getAgent()).createCMAAgent(theData.getAgentAlias(), theData , theData.getReplyHandler(), theData.getGuardToReply());
    }
    
}
