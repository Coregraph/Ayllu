/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.workspacemgr.agent.data;

import BESA.Kernel.Agent.Event.DataBESA;
import java.util.BitSet;

/**
 *
 * @author Ayllu
 */
public class WSManageResourceDataAYPUY extends DataBESA {
    private String guardToSendReply; //guarda respuesta
    private String agentToSendReply; //agente respuesta
    private int actionToPerform; // accion (CRUD recursos)
    private BitSet requestBitSet; //información del recurso (se saca del dominio?)
    private String roleName; //nombre del rol que quiere realizar la acción
    private String workspacename; //nombre del workspace en donde se encuentra el usuario
    private Object query; // información del Recurso

    /**
     * 
     * @param guardToSendReply
     * @param agentToSendReply
     * @param actionToPerform
     * @param requestBitSet
     * @param roleName
     * @param workspacename
     * @param query
     */
    public WSManageResourceDataAYPUY(String guardToSendReply, String agentToSendReply, int actionToPerform, BitSet requestBitSet, String roleName, String workspacename, Object query) {
        this.guardToSendReply = guardToSendReply;
        this.agentToSendReply = agentToSendReply;
        this.actionToPerform = actionToPerform;
        this.requestBitSet = (BitSet)requestBitSet.clone();
        this.roleName = roleName;
        this.workspacename = workspacename;
        this.query = query;
    }

    
    
    /**
     * 
     * @return
     */
    public String getWorkspacename() {
        return workspacename;
    }

    
    /**
     * 
     * @return
     */
    public String getRoleName() {
        return roleName;
    }
    
    /**
     * 
     * @return
     */
    public String getAgentToSendReply() {
        return agentToSendReply;
    }

    /**
     * 
     * @return
     */
    public String getGuardToSendReply() {
        return guardToSendReply;
    }

    /**
     * 
     * @return
     */
    public int getActionToPerform() {
        return actionToPerform;
    }

    /**
     * 
     * @return
     */
    public Object getQuery() {
        return query;
    }

    /**
     * 
     * @return
     */
    public BitSet getRequestBitSet() {
        return (BitSet)requestBitSet.clone();
    }
    
    
    
    
}
