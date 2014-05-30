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
public class DIRDirectoryManagementData extends DataBESA{
    private enum EnumDirMgmt{
        DIR_REGISTER, DIR_REQUEST;
    }
    private EnumDirMgmt action;
    private BitSet identifier;
    private String agentID;

    /**
     * Constructor for the message that will create a new entry for a logical agent in the directory
     * @param identifier The BitSet to use as identifier
     * @param agentID The agentID to be registered
     */
    public DIRDirectoryManagementData(BitSet identifier, String agentID) {
        this.identifier = (BitSet)identifier.clone();
        this.agentID = agentID;
    }
    
    /**
     * Gets the agent ID
     * @return The agent ID
     */
    public String getAgentID() {
        return agentID;
    }

    /**
     * Gets the identifier
     * @return The Identifier
     */
    public BitSet getIdentifier() {
        return (BitSet)identifier.clone();
    }
    
    
    
    
}
