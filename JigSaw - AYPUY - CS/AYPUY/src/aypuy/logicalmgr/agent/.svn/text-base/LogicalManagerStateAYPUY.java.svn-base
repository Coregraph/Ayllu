/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.logicalmgr.agent;

import BESA.Kernel.Agent.StateBESA;
import aypuy.logicalmgr.agent.logicalregistry.FileDescriptionAYPUY;
import aypuy.logicalmgr.agent.logicalregistry.LMRegistryAYPUY;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 *
 * @author Ayllu
 */
public class LogicalManagerStateAYPUY extends StateBESA {

    private List<String> validHandlerIds;
    private List<LMRegistryAYPUY> registry;
    private BitSet myBitSetId;

    /*public LogicalManagerStateAYPUY() {
        this.registry = new ArrayList<LMRegistryAYPUY>();
        this.validHandlerIds = new ArrayList<String>();
    }*/

    LogicalManagerStateAYPUY(BitSet myBitSet) {
        this.myBitSetId = (BitSet) myBitSet.clone();
        this.registry = new ArrayList<LMRegistryAYPUY>();
        this.validHandlerIds = new ArrayList<String>();
    }

    public BitSet getMyBitSetId() {
        return (BitSet) myBitSetId.clone();
    }

    public void addNewAgentId(String sgId) {
        if (!agentIDInRegistry(sgId)) {
            this.registry.add(new LMRegistryAYPUY(sgId));
            reOrganizeRegistry();
        }
    }

    public void addNewEntry(FileDescriptionAYPUY aFile) {
        int posinReg = posInRegistryByName(aFile.getName());
        if (posinReg != -1) {
            this.registry.get(posinReg).addObject(aFile);
        }

    }

    public void addNewEntry(String name, String filename) {
        FileDescriptionAYPUY aFile = new FileDescriptionAYPUY(name, filename);
        int posinReg = posInRegistryByName(name);
        if (posinReg != -1) {
            this.registry.get(posinReg).addObject(aFile);
        }

    }

    /*
     * private int posInRegistryByID(String agId) { for(int
     * i=0;i<registry.size();i++){
     * if(registry.get(i).getPhAgentId().equals(agId)){ return i; } } return -1;
     * }
     */
    public int posInRegistryByName(String resourceName) {
        int minDiff = 0;
        int hashone = resourceName.hashCode();
        String nameArray[] = getListOfAgId();
        int posInArray = 0;
        if (nameArray.length == 0) {
            return -1;
        }
        for (int i = 0; i < nameArray.length; i++) {
            int hashtwo = nameArray[i].hashCode();
            int diff = Math.abs(Math.abs(Math.abs(hashone) - Math.abs(hashtwo)));
            if (i == 0) {
                minDiff = diff;
            } else {
                if (diff < minDiff) {
                    minDiff = diff;
                    posInArray = i;
                }
            }
        }
        return posInArray;
    }

    public LMRegistryAYPUY getFileDescription(int posInReg) {
        return registry.get(posInReg);
    }

    public String getFileNamefromName(String name) {
        int pos = posInRegistryByName(name);
        for (FileDescriptionAYPUY desc : this.registry.get(pos).getObjectList()) {
            if (desc.getName().equals(name)) {
                return desc.getFilename();
            }
        }
        return "";
    }

    public String[] getListOfAgId() {
        String[] resp = new String[this.registry.size()];
        for (int i = 0; i < this.registry.size(); i++) {
            resp[i] = this.registry.get(i).getPhAgentId();
        }
        return resp;
    }

    private boolean agentIDInRegistry(String agId) {
        for (LMRegistryAYPUY lMRegistryAYPUY : registry) {
            if (lMRegistryAYPUY.getPhAgentId().equals(agId)) {
                return true;
            }
        }
        return false;
    }

    private void reOrganizeRegistry() {
        List<FileDescriptionAYPUY> allFiles = new ArrayList<FileDescriptionAYPUY>();
        for (LMRegistryAYPUY aRegistry : registry) {
            allFiles.addAll(aRegistry.getObjectList());
            aRegistry.clearObjectList();
        }
        for (FileDescriptionAYPUY fileDescriptionAYPUY : allFiles) {
            this.addNewEntry(fileDescriptionAYPUY);
        }
    }

    public int locateInRegistryByName(String resName) {
        
        int pos = this.posInRegistryByName(resName);
        if (this.registry.get(pos).hasFileName(resName)) {
            return pos;
        }
        return -1;
    }

    public void addValidHandlerID(String handlerId) {
        this.validHandlerIds.add(handlerId);
    }
    
    public boolean isValidHandler(String handlerId) {
        for (String hndId : this.validHandlerIds) {
            if (hndId.equals(handlerId)) {
                return true;
            }
        }
        return false;
    }

    public void changeAgentId(String oldphisicalAgentID, String newphisicalAgentID) {
        //TODO
    }
}
