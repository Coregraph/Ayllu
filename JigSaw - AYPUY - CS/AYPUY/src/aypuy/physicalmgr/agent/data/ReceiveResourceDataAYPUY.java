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
public class ReceiveResourceDataAYPUY extends DataBESA{
    private byte[] theFile;
    private String filename;

    /**
     * 
     * @param theFile
     * @param filename
     */
    public ReceiveResourceDataAYPUY(byte[] theFile, String filename) {
        this.theFile = theFile;
        this.filename = filename;
    }

    /**
     * 
     * @return
     */
    public String getFilename() {
        return filename;
    }
    

    /**
     * 
     * @return
     */
    public byte[] getTheFile() {
        return theFile;
    }
    
    
    
}
