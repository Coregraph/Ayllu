/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.directories.competence;

import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.Agent.StructBESA;

/**
 *
 * @author Ayllu
 */
public class Algo extends AgentBESA{

    public Algo(String alias, StateBESA state, StructBESA structAgent, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, structAgent, passwd);
    }
    
    @Override
    public void setupAgent() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void shutdownAgent() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
