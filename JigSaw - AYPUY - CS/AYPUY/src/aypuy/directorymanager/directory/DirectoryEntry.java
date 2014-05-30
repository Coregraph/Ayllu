/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.directorymanager.directory;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * This class represents an entry in the directory, it contains one bitSet and a List of AgentIDs that can address that bitSet information
 * @author Ayllu
 */
public class DirectoryEntry implements Comparable<DirectoryEntry> {

    private BitSet bitset; //CUIDADO CON BITSET! Es un apuntador (misma referencia en memoria, similar a hacer = con 2 apuntadores en c++), es por eso que se usa clone()
    private List<String> logicalAgentIDs;
    private int elements;
    private int nextAgent;
    
    /**
     * Parametrized constructor that receives an entry and the first AgentId
     * @param bitset The bitset that defines the entry
     * @param agentId The first AgentID from the list of agents
     */
    public DirectoryEntry(BitSet bitset, String agentId) {
        this.bitset = (BitSet) bitset.clone();
        this.logicalAgentIDs = new ArrayList<String>();
        this.logicalAgentIDs.add(agentId);
        this.elements = this.logicalAgentIDs.size();
        this.nextAgent = 0;
    }

    /**
     * Returns the bitset of the directory entry
     * @return The bitset that represents the entry
     */
    public BitSet getBitset() {
        return (BitSet) this.bitset.clone();
    }

    /**
     * This method returns a bitset with the bits that were met using xor, if all bits are met the bitset will be equal to zero
     * @param bs The bitset to check with this entry
     * @return A bitset with ones in the places where the bits were not met
     */
    public BitSet bitsMet(BitSet bs) {
        BitSet answ = this.getBitset();
        answ.xor(bs);
        return answ;
    }

    /**
     * Adds an aditional AgentID that handles the bitset declared in the entry
     * @param agentId The agent ID to register
     */
    public void addIDToEntry(String agentId) {
        this.logicalAgentIDs.add(agentId);
        this.elements = this.logicalAgentIDs.size();
    }

    @Override
    public int compareTo(DirectoryEntry o) {
        return o.getElements() - this.elements;
    }

    private int getElements() {
        return this.elements;
    }

    @Override
    public String toString() {
        return this.bitset + "[" + this.elements + "]->" + this.bitset.cardinality() + this.logicalAgentIDs;
    }

    /**
     * 
     * @return
     */
    public String getAvailableLogicalAgent() {
        String resp = this.logicalAgentIDs.get(this.nextAgent++);
        if (this.nextAgent >= this.logicalAgentIDs.size()) {
            this.nextAgent = 0;
        }
        return resp;
    }

    /**
     * 
     * @return
     */
    public List<String> getLogicalAgentIDs() {
        return logicalAgentIDs;
    }

    /**
     * 
     * @param bitSet
     * @return
     */
    public int getValueFromBits(BitSet bitSet) {
        int result = 0;
        for (int i = 0; i < bitSet.length(); i++) {
            if (bitSet.get(i)) {
                result |= (1 << i);
            }
        }
        result &= Integer.MAX_VALUE;
        return result;
    }
}
