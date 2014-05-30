/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.directorymanager.agent.data;

import BESA.Kernel.Agent.Event.DataBESA;
import java.util.BitSet;

/**
 *
 * @author Ayllu
 */
public class DIRRequestResourceDataAYPUY extends DataBESA {
    private String guardToSendReply; //guarda respuesta
    private String agentToSendReply; //agente respuesta
    private BitSet requestBitSet; //información del recurso (se saca del dominio?)
    private Object query; // información del Recurso

    /**
     * The data that will make a request for a resource, it requires the guard and agent tho whom it must respond
     * @param guardToSendReply The guard that must be activated once the resource is obtained
     * @param agentToSendReply The agent ID of the agent that owns the guard
     * @param requestBitSet The BitSet that identifies the required resource
     * @param query The Object that contains the query
     */
    public DIRRequestResourceDataAYPUY(String guardToSendReply, String agentToSendReply, BitSet requestBitSet, Object query) {
        this.guardToSendReply = guardToSendReply;
        this.agentToSendReply = agentToSendReply;
        this.requestBitSet = (BitSet)requestBitSet.clone();
        this.query = query;
    }

    /**
     * Get the agent ID that will expect the answer, must contain the guard specified to receive the response 
     * @return The agent ID that will receive the answer
     */
    public String getAgentToSendReply() {
        return agentToSendReply;
    }

    /**
     * Gets the Guard to respond to when the resource is obtained, must be associated with the specified agent
     * @return The Guard to respond to when the resource is obtained
     */ 
    public String getGuardToSendReply() {
        return guardToSendReply;
    }

    /**
     * Returns the query
     * @return The query
     */
    public Object getQuery() {
        return query;
    }

    /**
     * Gets the bitset that identifies the request
     * @return The Bitset that identifies the request
     */ 
    public BitSet getRequestBitSet() {
        return (BitSet)requestBitSet.clone();
    }
    
 
}
