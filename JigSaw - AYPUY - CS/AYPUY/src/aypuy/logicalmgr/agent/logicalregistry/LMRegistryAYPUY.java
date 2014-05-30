/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.logicalmgr.agent.logicalregistry;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ayllu
 */
public class LMRegistryAYPUY {

    private String phAgentId;
    //private String generalPath;
    private List<FileDescriptionAYPUY> objectList;

    /**
     * 
     * @param phAgentId
     * @param firstObject
     */
    public LMRegistryAYPUY(String phAgentId,/* String generalPath,*/ FileDescriptionAYPUY firstObject) {
        this.phAgentId = phAgentId;
        //this.generalPath = generalPath;
        this.objectList = new ArrayList<FileDescriptionAYPUY>();
        this.objectList.add(firstObject);
    }
    /**
     * 
     * @param phAgentId
     */
    public LMRegistryAYPUY(String phAgentId) {
        this.phAgentId = phAgentId;
        //this.generalPath = generalPath;
        this.objectList = new ArrayList<FileDescriptionAYPUY>();
    }

    /*public String getGeneralPath() {
        return generalPath;
    }*/

    /**
     * 
     * @return
     */
    public List<FileDescriptionAYPUY> getObjectList() {
        return objectList;
    }

    /**
     * 
     * @param anobject
     */
    public void addObject(FileDescriptionAYPUY anobject) {
        if (!isNameInRegistry(anobject.getName())) {
            objectList.add(anobject);
        }
    }

    /**
     * 
     * @return
     */
    public String getPhAgentId() {
        return phAgentId;
    }

    /**
     * 
     * @param name
     * @return
     */
    public boolean isNameInRegistry(String name) {
        for (FileDescriptionAYPUY adesc : objectList) {
            if (adesc.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     */
    public void clearObjectList() {
        this.objectList.clear();
    }

    /**
     * 
     * @param resName
     * @return
     */
    public boolean hasFileName(String resName) {
        for (FileDescriptionAYPUY fileDescriptionAYPUY : objectList) {
            if(fileDescriptionAYPUY.getName().equals(resName)){
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param phAgentId
     */
    public void setPhAgentId(String phAgentId) {
        this.phAgentId = phAgentId;
    }
    
    
}
