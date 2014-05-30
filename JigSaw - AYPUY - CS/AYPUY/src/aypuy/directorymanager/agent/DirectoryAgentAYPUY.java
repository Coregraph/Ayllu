/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.directorymanager.agent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Log.ReportBESA;
import aypuy.directorymanager.agent.guards.DIR_InsertResourceAYPUY;
import aypuy.directorymanager.agent.guards.DIR_RegisterLogicalManagerAYPUY;
import aypuy.directorymanager.agent.guards.DIR_RequestResourceAYPUY;

/**
 *
 * @author Ayllu
 */
public class DirectoryAgentAYPUY extends AgentBESA {

    /**
     * Constructor of a Directory Agent, It receives an alias and a passsword
     * @param alias The alias of the agent
     * @param passwd The password to validate it's creation
     * @throws KernellAgentExceptionBESA Thrown in case the agent fails to be created
     */
    public DirectoryAgentAYPUY(String alias, double passwd) throws KernellAgentExceptionBESA {
        super(alias, new DirectoryStateAYPUY(), getStruct(), passwd);
    }

    private static StructBESA getStruct() {
        StructBESA str = new StructBESA();
        str.addBehavior("DIRAYPUY_Behavior");
        try {
            str.bindGuard("DIRAYPUY_Behavior", DIR_RequestResourceAYPUY.class);//guarda de gestión
            str.bindGuard("DIRAYPUY_Behavior", DIR_RegisterLogicalManagerAYPUY.class);//guarda de gestión
            str.bindGuard("DIRAYPUY_Behavior", DIR_InsertResourceAYPUY.class);
        } catch (ExceptionBESA ex) {
            ReportBESA.error(ex);
        }
        return str;

    }

    @Override
    public void setupAgent() {
    }

    @Override
    public void shutdownAgent() {
    }
}
