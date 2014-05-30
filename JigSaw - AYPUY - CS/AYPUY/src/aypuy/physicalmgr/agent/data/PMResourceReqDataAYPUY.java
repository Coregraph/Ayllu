/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.physicalmgr.agent.data;

import BESA.Kernel.Agent.Event.DataBESA;

/**
 *
 * @author Ayllu
 */
public class PMResourceReqDataAYPUY extends DataBESA{
    String agentToReply;
    String guardToReply;
    String fileName;

    /**
     * 
     * @param agentToReply
     * @param guardToReply
     * @param completePath
     */
    public PMResourceReqDataAYPUY(String agentToReply, String guardToReply, String completePath) {
        this.agentToReply = agentToReply;
        this.guardToReply = guardToReply;
        this.fileName = completePath;
    }

    /**
     * 
     * @return
     */
    public String getAgentToReply() {
        return agentToReply;
    }

    /**
     * 
     * @return
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 
     * @return
     */
    public String getGuardToReply() {
        return guardToReply;
    }
    
    
}
