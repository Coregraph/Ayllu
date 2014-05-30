/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.workspacemgr.agent.guards;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Log.ReportBESA;
import aypuy.workspacemgr.agent.WorkSpaceManagerStateAYPUY;
import aypuy.workspacemgr.agent.data.RoleManagementDataAYPUY;
import aypuy.workspacemgr.agent.data.WSManagementDataAYPUY;
import aypuy.workspacemgr.workspace.WorkSpaceAYPUY;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Ayllu
 */
public class WSM_ManagementGuardAYPUY extends GuardBESA {
    @Override
    public void funcExecGuard(EventBESA ebesa) {
        WorkSpaceManagerStateAYPUY myState = (WorkSpaceManagerStateAYPUY) this.getAgent().getState();
        if (ebesa.getData() instanceof RoleManagementDataAYPUY) {
            RoleManagementDataAYPUY roleMgmt = (RoleManagementDataAYPUY) ebesa.getData();
            manageRoles(roleMgmt, myState);
        } else if (ebesa.getData() instanceof WSManagementDataAYPUY) {
            WSManagementDataAYPUY wsMgmt = (WSManagementDataAYPUY) ebesa.getData();
            manageWorkspaces(wsMgmt, myState);
        }
    }

    private void manageRoles(RoleManagementDataAYPUY roleMgmt, WorkSpaceManagerStateAYPUY myState) {
        String logInfo;
        switch (roleMgmt.getAction()) {
            case ADD_ROLE:
                try {
                    myState.addRoleToWorkspace(roleMgmt.getWorkspaceName(), roleMgmt.getRolename());
                } catch (Exception ex) {
                    Logger.getLogger(WSM_ManagementGuardAYPUY.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case ADD_PERM:
                try {
                    myState.addPermission(roleMgmt.getWorkspaceName(), roleMgmt.getRolename(), roleMgmt.getPermissions());
                } catch (Exception ex) {
                    Logger.getLogger(WSM_ManagementGuardAYPUY.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case ADALL_PERM:
                try {
                    myState.addAllPermissions(roleMgmt.getWorkspaceName(), roleMgmt.getRolename());
                } catch (Exception ex) {
                    Logger.getLogger(WSM_ManagementGuardAYPUY.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case RMV_PERM:
                try {
                    myState.removePermission(roleMgmt.getWorkspaceName(), roleMgmt.getRolename(), roleMgmt.getPermissions());

                } catch (Exception ex) {
                    Logger.getLogger(WSM_ManagementGuardAYPUY.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case RMVALL_PERM:
                try {
                    myState.removeAllPermissions(roleMgmt.getWorkspaceName(), roleMgmt.getRolename());
                } catch (Exception ex) {
                    Logger.getLogger(WSM_ManagementGuardAYPUY.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case HAS_PERM:
                try {
                    boolean perms[] = myState.hasPermissions(roleMgmt.getWorkspaceName(), roleMgmt.getRolename(), roleMgmt.getPermissions());
                    for (int b : roleMgmt.getPermissions()) {
                        switch (b) {
                            
                            case WorkSpaceAYPUY.CREATE:
                                logInfo="Permission for CREATE: " + perms[b];
                                Logger.getLogger(WSM_ManagementGuardAYPUY.class.getName()).log(Level.INFO,logInfo);
                                break;
                            case WorkSpaceAYPUY.RETRIEVE:
                                logInfo= "Permission for RETRIEVE: " + perms[b];
                                Logger.getLogger(WSM_ManagementGuardAYPUY.class.getName()).log(Level.INFO,logInfo);
                                break;
                            case WorkSpaceAYPUY.UPDATE:
                                logInfo= "Permission for UPDATE: " + perms[b];
                                Logger.getLogger(WSM_ManagementGuardAYPUY.class.getName()).log(Level.INFO,logInfo);
                                break;
                            case WorkSpaceAYPUY.DELETE:
                                logInfo= "Permission for DELETE: " + perms[b];
                                Logger.getLogger(WSM_ManagementGuardAYPUY.class.getName()).log(Level.INFO,logInfo);
                                break;
                        }
                    }

                } catch (Exception ex) {
                    Logger.getLogger(WSM_ManagementGuardAYPUY.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            default:
                ReportBESA.error("ROLE MANAGEMENT STATE NOT RECOGNIZED");
                break;
        }
    }

    private void manageWorkspaces(WSManagementDataAYPUY wsMgmt, WorkSpaceManagerStateAYPUY myState) {
        switch (wsMgmt.getAction()) {
            case CREATE_ROOTWS:
                myState.createRootWorkSpace(wsMgmt.getWorkspaceName());
                break;
            case CREATEWS:
                try {
                    myState.createWorkSpace(wsMgmt.getWorkspaceName(), wsMgmt.getFatherworkspaceName());
                } catch (Exception ex) {
                    Logger.getLogger(WSM_ManagementGuardAYPUY.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case PRINTWS:
                myState.printWorkspace(wsMgmt.getWorkspaceName());
                break;
            case DELETEWS:
                try {
                    myState.deleteWorkSpace(wsMgmt.getWorkspaceName());
                } catch (Exception ex) {
                    Logger.getLogger(WSM_ManagementGuardAYPUY.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
    }
}
