package aypuy.workspacemgr.agent.guards;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Log.ReportBESA;
import aypuy.directorymanager.agent.data.DIRInsertResourceDataAYPUY;
import aypuy.directorymanager.agent.data.DIRRequestResourceDataAYPUY;
import aypuy.directorymanager.agent.guards.DIR_InsertResourceAYPUY;
import aypuy.directorymanager.agent.guards.DIR_RequestResourceAYPUY;
import aypuy.workspacemgr.agent.WorkSpaceManagerStateAYPUY;
import aypuy.workspacemgr.agent.data.WSManageResourceDataAYPUY;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author luis
 */
public class WSM_ResourceREQGuardAYPUY extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        WorkSpaceManagerStateAYPUY myState = (WorkSpaceManagerStateAYPUY) getAgent().getState();
        WSManageResourceDataAYPUY req = (WSManageResourceDataAYPUY) ebesa.getData();
        int theAction = req.getActionToPerform();
        try {
            if (myState.hasPermission(req.getWorkspacename(), req.getRoleName(), theAction)) {
                ReportBESA.info("Los permisos fueron exitosos para el rol " + req.getRoleName());
                switch (theAction) {
                    case 1: //retrieve
                        makeResourceRequest(req);
                        break;
                    case 0: //create
                    case 2: //update
                        makeResourceInsert(req);
                }
            } else {
                ReportBESA.error("El rol " + req.getRoleName() + " no tiene los permisos para realizar la operacion solicitada");
            }
        } catch (Exception ex) {
            Logger.getLogger(WSM_ResourceREQGuardAYPUY.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void makeResourceRequest(WSManageResourceDataAYPUY req) throws ExceptionBESA {
        DIRRequestResourceDataAYPUY dirReq = new DIRRequestResourceDataAYPUY(req.getGuardToSendReply(), req.getAgentToSendReply(), req.getRequestBitSet(), req.getQuery());
        EventBESA dirEvent = new EventBESA(DIR_RequestResourceAYPUY.class.getName(), dirReq);
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("aypuyConfig.properties"));
            getAgent().getAdmLocal().getHandlerByAlias(props.getProperty("directoryalias")).sendEvent(dirEvent);
        } catch (IOException ioex) {
            Logger.getLogger(WSM_ResourceREQGuardAYPUY.class.getName()).log(Level.SEVERE, null, ioex);
        }
    }

    private void makeResourceInsert(WSManageResourceDataAYPUY req) throws ExceptionBESA {
        DIRInsertResourceDataAYPUY dirIns = new DIRInsertResourceDataAYPUY(req.getRequestBitSet(), req.getQuery());
        EventBESA dirEvent = new EventBESA(DIR_InsertResourceAYPUY.class.getName(), dirIns);
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("aypuyConfig.properties"));
            getAgent().getAdmLocal().getHandlerByAlias(props.getProperty("directoryalias")).sendEvent(dirEvent);
        } catch (IOException ioex) {
            Logger.getLogger(WSM_ResourceREQGuardAYPUY.class.getName()).log(Level.SEVERE, null, ioex);
        }
    }
}