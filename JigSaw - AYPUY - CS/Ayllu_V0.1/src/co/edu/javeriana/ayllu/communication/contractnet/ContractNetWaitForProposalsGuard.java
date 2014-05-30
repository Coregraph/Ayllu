/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.communication.contractnet;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.TimeOutGuardBESA;
import BESA.Log.ReportBESA;
import co.edu.javeriana.ayllu.communication.contractnet.community.ContractNetProposal;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;

/**
 *
 * @author AylluAdmin
 */
public class ContractNetWaitForProposalsGuard extends TimeOutGuardBESA{



    @Override
    public void funcNormalExecGuard(EventBESA ebesa) {
        Ayllu_Data_Message theData = (Ayllu_Data_Message)ebesa.getData();
        if(theData.getEventType()==Ayllu_EventTypes.START_COOP){
            ContractNetProposal cnp = (ContractNetProposal) theData.getMessage();
            this.startTimeOut(cnp.getTimeout());
        }else{
            String resp = String.valueOf(theData.getMessage());
            ReportBESA.info("LA RTA DE "+theData.getSenderHandler().getAlias()+" ES: "+resp);
        }
    
    }
        

   @Override
    public void funcTimeOutExecGuard(EventBESA ebesa) {
        ReportBESA.error("TimeOut Alcanzado!");
        this.stopTimeOut();
    }
}
