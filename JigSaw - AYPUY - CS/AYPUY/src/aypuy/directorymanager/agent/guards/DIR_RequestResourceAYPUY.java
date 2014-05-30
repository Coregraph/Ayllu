/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.directorymanager.agent.guards;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import aypuy.directorymanager.agent.DirectoryStateAYPUY;
import aypuy.directorymanager.agent.data.DIRRequestResourceDataAYPUY;
import aypuy.logicalmgr.agent.data.LMRequestResourceData;
import aypuy.logicalmgr.agent.guards.LM_RequestResourceAYPUY;
import java.util.BitSet;
import java.util.List;


/**
 *
 * @author Ayllu
 */
public class DIR_RequestResourceAYPUY extends GuardBESA{

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        List<String> agentsToCall;
        DirectoryStateAYPUY myState = (DirectoryStateAYPUY) this.getAgent().getState();
        DIRRequestResourceDataAYPUY theData = (DIRRequestResourceDataAYPUY)ebesa.getData();
        BitSet identificador = theData.getRequestBitSet();
        if(myState.isInDirectory(identificador)){
            agentsToCall = myState.getAgentsIDsofEntry(myState.getPosInDirectory(identificador));
        }else{
            agentsToCall =myState.locatePosssibleSources(identificador);
        }
        ReportBESA.info("VOY A LLAMAR (REQ) A: "+agentsToCall);
        LMRequestResourceData req = new LMRequestResourceData(theData.getAgentToSendReply(),theData.getGuardToSendReply(),theData.getQuery());
        EventBESA reqAypuy = new EventBESA(LM_RequestResourceAYPUY.class.getName(), req);
        for (String agId : agentsToCall) {
            try{
                AgHandlerBESA handler = this.getAgent().getAdmLocal().getHandlerByAid(agId);
                handler.sendEvent(reqAypuy);
            }catch(ExceptionBESA exbesa){
                ReportBESA.error(exbesa);
            }
            
        }
    }

    
}
