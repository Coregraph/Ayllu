/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.logicalmgr.agent.guards;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import aypuy.logicalmgr.agent.LogicalManagerStateAYPUY;
import aypuy.logicalmgr.agent.data.LMInsertResourceData;
import aypuy.logicalmgr.agent.logicalregistry.FileDescriptionAYPUY;

/**
 *
 * @author Ayllu
 */
public class LM_InsertResourceAYPUY extends GuardBESA{

    @Override
    public void funcExecGuard(EventBESA event) {
       LMInsertResourceData insReq = (LMInsertResourceData)event.getData();
       LogicalManagerStateAYPUY myState = (LogicalManagerStateAYPUY) this.getAgent().getState();
       myState.addNewEntry((FileDescriptionAYPUY)insReq.getInsertData());
    }
    
    
    
}
