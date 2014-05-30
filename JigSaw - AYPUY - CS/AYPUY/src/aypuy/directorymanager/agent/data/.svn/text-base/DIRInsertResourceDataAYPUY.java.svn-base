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
public class DIRInsertResourceDataAYPUY extends DataBESA {
    private BitSet requestBitSet; //información del recurso 
    private Object query; // información del Recurso

    /**
     * Constructor for the message that insert a resource in the directory
     * @param requestBitSet The BitSet that identifies the resource
     * @param query An Object that contains the resource
     */
    public DIRInsertResourceDataAYPUY(BitSet requestBitSet, Object query) {
        this.requestBitSet = (BitSet) requestBitSet.clone();
        this.query = query;
    }

    /**
     * Returns the query which will contain the resource to insert
     * @return An Object that contains the query
     */
    public Object getQuery() {
        return query;
    }

    /**
     * Gets the BitSet that identifies the resource
     * @return The BitSet that identifies the resource
     */
    public BitSet getRequestBitSet() {
        return (BitSet) requestBitSet.clone();
    }
    
    
}
