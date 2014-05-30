/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.logicalmgr.agent.logicalregistry;

/**
 *
 * @author Ayllu
 */
public class FileDescriptionAYPUY {
    private String name;    //nombre del OVA (Ej: Presentacion FuzzyLogic)
    private String filename; //Nombre del archivo (EJ: Pres01_c03.ppt)

    /**
     * 
     * @param name
     * @param filename
     */
    public FileDescriptionAYPUY(String name, String filename) {
        this.name = name;
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
    public String getName() {
        return name;
    }
    
    
}
