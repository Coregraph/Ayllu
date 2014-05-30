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
public class WSManagementDataAYPUY extends DataBESA {

    /**
     * 
     */
    public enum EnumWSMgmtAYPUY {

        /**
         * 
         */
        CREATE_ROOTWS,
        /**
         * 
         */
        DELETEWS,
        /**
         * 
         */
        CREATEWS,
        /**
         * 
         */
        PRINTWS;
    }
    private EnumWSMgmtAYPUY action;
    private String workspaceName;
    private String fatherworkspaceName;

    /**
     * 
     * @param workspaceName
     * @param fatherworkspaceName
     */
    public WSManagementDataAYPUY(String workspaceName, String fatherworkspaceName) {
        this.workspaceName = workspaceName;
        if (fatherworkspaceName != null) {
            this.action = EnumWSMgmtAYPUY.CREATEWS;
            this.fatherworkspaceName = fatherworkspaceName;
        } else {
            this.action = EnumWSMgmtAYPUY.CREATE_ROOTWS;
        }
    }

    /**
     * 
     * @param workspaceName
     * @param action
     */
    public WSManagementDataAYPUY(String workspaceName,EnumWSMgmtAYPUY action) {
        this.action = action;
        this.workspaceName = workspaceName;
    }

    /**
     * 
     * @return
     */
    public String getFatherworkspaceName() {
        return fatherworkspaceName;
    }

    /**
     * 
     * @return
     */
    public EnumWSMgmtAYPUY getAction() {
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
