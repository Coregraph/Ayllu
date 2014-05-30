/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.logicalmgr.agent.data;

import BESA.Kernel.Agent.Event.DataBESA;

/**
 *
 * @author Ayllu
 */
public class LMInsertResourceData extends DataBESA{
    private Object insertData;
    /**
     * 
     * @param insertData
     */
    public LMInsertResourceData(Object insertData) {
        this.insertData = insertData;
    }

    /**
     * 
     * @return
     */
    public Object getInsertData() {
        return insertData;
    }
    
}
