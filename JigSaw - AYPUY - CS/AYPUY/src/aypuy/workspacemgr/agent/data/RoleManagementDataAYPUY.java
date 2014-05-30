/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.workspacemgr.agent.data;

import BESA.Kernel.Agent.Event.DataBESA;

/**
 *
 * @author Ayllu
 */
public class RoleManagementDataAYPUY extends DataBESA{
    /**
     * 
     */
    public enum EnumRoleMgmtAYPUY{

        /**
         * 
         */
        ADD_ROLE,
        /**
         * 
         */
        ADD_PERM,
        /**
         * 
         */
        RMV_PERM,
        /**
         * 
         */
        ADALL_PERM,
        /**
         * 
         */
        RMVALL_PERM,
        /**
         * 
         */
        HAS_PERM;
    }
    private String rolename;
    private String workspaceName;
    private int permissions[];
    private EnumRoleMgmtAYPUY action;

    /**
     * 
     * @param rolename
     * @param workspaceName
     * @param action
     * @param permissions
     */
    public RoleManagementDataAYPUY(String rolename, String workspaceName, EnumRoleMgmtAYPUY action, int ... permissions) {
        this.rolename = rolename;
        this.workspaceName = workspaceName;
        this.permissions = permissions;
        this.action = action;
    }
    /**
     * 
     * @param rolename
     * @param workspaceName
     * @param action
     */
    public RoleManagementDataAYPUY(String rolename, String workspaceName, EnumRoleMgmtAYPUY action) {
        this.rolename = rolename;
        this.workspaceName = workspaceName;
        this.action = action;
    }

    /**
     * 
     * @return
     */
    public int[] getPermissions() {
        return permissions;
    }

    /**
     * 
     * @return
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * 
     * @return
     */
    public EnumRoleMgmtAYPUY getAction() {
        return action;
    }

    /**
     * 
     * @return
     */
    public String getWorkspaceName() {
        return workspaceName;
    }
    
}
