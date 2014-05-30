package aypuy.directorymanager.agent;

import BESA.Kernel.Agent.StateBESA;
import BESA.Log.ReportBESA;
import aypuy.directorymanager.directory.DirectoryEntry;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * DirectoryStateAypuy stores all of the Directory Entries that contain information 
 * about the Logical Agents available
 * @author Ayllu
 */
public class DirectoryStateAYPUY extends StateBESA {

    private List<DirectoryEntry> directory;

    /**
     * Default constructor
     */
    public DirectoryStateAYPUY() {
        this.directory = new ArrayList();
    }

    /**
     * This method returns the ID of the next available logical agent 
     * stored in the indicated position in the directory
     * @param posInDir The position to read
     * @return A String with the ID of the agent
     */
    public String getIDofEntry(int posInDir) {
        return ((DirectoryEntry) this.directory.get(posInDir)).getAvailableLogicalAgent();
    }

    /**
     * This method returns the ID of all logical agents
     * stored in the indicated position in the directory
     * @param posInDir The position to read
     * @return A List  with the ID of every agent
     */
    public List<String> getAgentsIDsofEntry(int posInDir) {
        return ((DirectoryEntry) this.directory.get(posInDir)).getLogicalAgentIDs();
    }

    /**
     * This method registers a logical agent with its correspondent BitSet to the Directory, 
     * if the bitset already exists in the Directory, the agent ID will be aded as an aditional agent for that bitset,
     * otherwise it will create a new entry
     * @param bitset The bitset that reprersents the files that the logical agent manages
     * @param agentId The ID of the logical agent
     */
    public void registerInDirectory(BitSet bitset, String agentId) {
        int inDirectory = getPosInDirectory(bitset);
        if (inDirectory == -1) {
            this.directory.add(new DirectoryEntry(bitset, agentId));
        } else {
            ((DirectoryEntry) this.directory.get(inDirectory)).addIDToEntry(agentId);
        }
        Collections.sort(this.directory);
    }

    /**
     * Method used to check wether a determined bitset is stored in the directory
     * @param bitset The BitSet to check
     * @return true if the bitset was found, flase otherwise
     */
    public boolean isInDirectory(BitSet bitset) {
        return getPosInDirectory(bitset) != -1;
    }

    /**
     * Obtains the position inside the directory in which a BitSet is stored
     * @param bitset The BitSet to check
     * @return The position in the directory, -1 if the bitset is not found
     */
    public int getPosInDirectory(BitSet bitset) {
        for (int i = 0; i < this.directory.size(); i++) {
            if (((DirectoryEntry) this.directory.get(i)).getBitset().equals(bitset)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        String ans = "";
        for (DirectoryEntry directoryEntry : this.directory) {
            ans = ans + directoryEntry + "\n";
        }
        return ans;
    }

    /**
     * Takes a BitSet as input and seturns a list with the possible AgentIds that could manage resources
     * @param identificador The BitSet to handle
     * @return A list with the agent Ids that could manage such resource
     */
    public List<String> locatePosssibleSources(BitSet identificador) {
        List<String> preResp = new ArrayList();
        List<String> resp = new ArrayList();
        Collections.sort(this.directory);
        BitSet identificador2 = null;
        for (DirectoryEntry directoryEntry : this.directory) {
            identificador2 = directoryEntry.bitsMet(identificador);
            if (identificador2.cardinality() < identificador.cardinality()) {
                preResp.addAll(directoryEntry.getLogicalAgentIDs());
                identificador = (BitSet) identificador2.clone();
            }
        }
        if (identificador2 != null) {
            if (!identificador2.isEmpty()) {
                ReportBESA.error("The directory cannot satisfy the query for the identifier: " + identificador2);
            }
        }
        HashMap hashDup = new HashMap();
        for (String string : preResp) {
            if (!hashDup.containsKey(string)) {
                hashDup.put(string, Integer.valueOf(0));
            }
            int count = ((Integer) hashDup.get(string)).intValue() + 1;
            hashDup.put(string, Integer.valueOf(count));
            if (count == 2) {
                resp.add(string);
            }
        }
        return resp;
    }
}