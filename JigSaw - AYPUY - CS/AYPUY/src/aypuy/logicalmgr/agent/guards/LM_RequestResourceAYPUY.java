/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.logicalmgr.agent.guards;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import aypuy.handler.ResourceHandlerAYPUY;
import aypuy.logicalmgr.agent.LogicalManagerStateAYPUY;
import aypuy.logicalmgr.agent.data.LMRequestResourceData;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ayllu
 */
public class LM_RequestResourceAYPUY extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        LogicalManagerStateAYPUY myState = (LogicalManagerStateAYPUY)this.getAgent().getState();
        LMRequestResourceData reqInfo = (LMRequestResourceData)ebesa.getData();
        String agIdtoReply = reqInfo.getAgentToSendReply();
        String guardToReply = reqInfo.getGuardToSendReply();
        int pos = myState.locateInRegistryByName(reqInfo.getQuery().toString());
        if(pos!=-1){
            try {
                String agToValidate = this.getAgent().getAid();
                String agToReq = myState.getFileDescription(pos).getPhAgentId();
                String fullpath = myState.getFileNamefromName(reqInfo.getQuery().toString());
                ResourceHandlerAYPUY hndreq = new ResourceHandlerAYPUY(fullpath, agToReq,agToValidate);
                String handlerId = UUID.randomUUID().toString();
                hndreq.setTheId(handlerId);
                myState.addValidHandlerID(handlerId);
                EventBESA evreturnHandler = new EventBESA(guardToReply, hndreq);
                this.agent.getAdmLocal().getHandlerByAid(agIdtoReply).sendEvent(evreturnHandler);
            } catch (ExceptionBESA ex) {
                Logger.getLogger(LM_RequestResourceAYPUY.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
