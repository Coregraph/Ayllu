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
import aypuy.directorymanager.agent.data.DIRInsertResourceDataAYPUY;
import aypuy.logicalmgr.agent.data.LMInsertResourceData;
import aypuy.logicalmgr.agent.guards.LM_InsertResourceAYPUY;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ayllu
 */
public class DIR_InsertResourceAYPUY extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        DirectoryStateAYPUY myState = (DirectoryStateAYPUY) this.getAgent().getState();
        DIRInsertResourceDataAYPUY theData = (DIRInsertResourceDataAYPUY) ebesa.getData();
        if (myState.isInDirectory(theData.getRequestBitSet())) {
            int posInDir = myState.getPosInDirectory(theData.getRequestBitSet());
            String agentID = myState.getIDofEntry(posInDir);
            LMInsertResourceData insData = new LMInsertResourceData(theData.getQuery());
            EventBESA evReqBesa = new EventBESA(LM_InsertResourceAYPUY.class.getName(), insData);
            try {
                AgHandlerBESA logMgrAYPUY = this.getAgent().getAdmLocal().getHandlerByAid(agentID);
                logMgrAYPUY.sendEvent(evReqBesa);
            } catch (ExceptionBESA ex) {
                Logger.getLogger(DIR_InsertResourceAYPUY.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            List<String> agentsToCall = myState.locatePosssibleSources(theData.getRequestBitSet());
            ReportBESA.info("VOY A LLAMAR (INS) A: " + agentsToCall);
            LMInsertResourceData insData = new LMInsertResourceData(theData.getQuery());
            EventBESA evInsBesa = new EventBESA(LM_InsertResourceAYPUY.class.getName(), insData);
            for (String agId : agentsToCall) {
                try {
                    AgHandlerBESA handler = this.getAgent().getAdmLocal().getHandlerByAid(agId);
                    handler.sendEvent(evInsBesa);
                } catch (ExceptionBESA exbesa) {
                    ReportBESA.error(exbesa);
                }

            }
        }
    }
}
