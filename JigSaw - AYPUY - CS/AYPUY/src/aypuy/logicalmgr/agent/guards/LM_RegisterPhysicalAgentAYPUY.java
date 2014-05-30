/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.logicalmgr.agent.guards;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import aypuy.logicalmgr.agent.LogicalManagerStateAYPUY;
import aypuy.logicalmgr.agent.data.LMRegisterPhysicalManagerData;

/**
 *
 * @author Ayllu
 */
public class LM_RegisterPhysicalAgentAYPUY extends GuardBESA{

    @Override
    public void funcExecGuard(EventBESA event) {
        LMRegisterPhysicalManagerData theData = (LMRegisterPhysicalManagerData)event.getData();
        LogicalManagerStateAYPUY myState = (LogicalManagerStateAYPUY)this.getAgent().getState();
        if(theData.getOldphisicalAgentID()==null){
            myState.addNewAgentId(theData.getNewphisicalAgentID());
        }else{
            myState.changeAgentId(theData.getOldphisicalAgentID(),theData.getNewphisicalAgentID());
        }
    }
    
}
