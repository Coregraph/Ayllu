/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptation.adapter;

import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.Agent.StructBESA;

/**
 *
 * @author AYLLU
 */
public class AdapterAgentAES extends AgentBESA {

    public AdapterAgentAES(String alias, StateBESA state, StructBESA structAgent, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, structAgent, passwd);
    }

    @Override
    public void shutdownAgent() {
        //TODO Que hacer en el adapter?
    }

    @Override
    public void setupAgent() {
        //TODO Que hacer en el adapter?
    }
}
