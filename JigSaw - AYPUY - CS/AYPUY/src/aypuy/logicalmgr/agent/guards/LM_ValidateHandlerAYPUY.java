/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.logicalmgr.agent.guards;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Log.ReportBESA;
import aypuy.logicalmgr.agent.LogicalManagerStateAYPUY;
import aypuy.logicalmgr.agent.data.LMValidateHandlerData;
import aypuy.physicalmgr.agent.data.PMResourceReqDataAYPUY;
import aypuy.physicalmgr.agent.guards.PM_DownloadReqAYPUY;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ayllu
 */
public class LM_ValidateHandlerAYPUY extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA event) {
        LMValidateHandlerData theData = (LMValidateHandlerData) event.getData();
        LogicalManagerStateAYPUY myState = (LogicalManagerStateAYPUY) this.getAgent().getState();
        if (myState.isValidHandler(theData.getHandlerId())) {
            PMResourceReqDataAYPUY resreq = new PMResourceReqDataAYPUY(theData.getAgToReceive(), theData.getGuardToReceive(), theData.getFileName());
            EventBESA evReqRes = new EventBESA(PM_DownloadReqAYPUY.class.getName(), resreq);
            try {
                this.getAgent().getAdmLocal().getHandlerByAid(theData.getAgToRequest()).sendEvent(evReqRes);
            } catch (ExceptionBESA ex) {
                Logger.getLogger(LM_ValidateHandlerAYPUY.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            ReportBESA.error("The ResourceHandler does not have a valid ID");
        }
    }
}
