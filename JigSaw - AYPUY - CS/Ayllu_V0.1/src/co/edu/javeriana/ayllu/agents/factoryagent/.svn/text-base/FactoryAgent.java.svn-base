/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.javeriana.ayllu.agents.factoryagent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import co.edu.javeriana.ayllu.data.Ayllu_Agent_Creation_Message;
import java.util.List;

/**
 * The factory Agent is an abstract agent that handles the creation of a communityManagerAgent
 * @author AYLLU
 */
public abstract class FactoryAgent extends AgentBESA{
    private static StructBESA myStruct;
    /*public static FactoryAgent createInstance (String alias, StateBESA state, double passwd){
        StructBESA myStruct = new StructBESA();
        myStruct.addBehavior(FA_Behavior.class.getName());
        myStruct.bindGuard(FA_CMACreationGuard.class.getName(), FA_Behavior.class.getName());
        FactoryAgent fa = new FactoryAgent(alias, state, myStruct, passwd);
        return fa;
    }*/
    /**
     * Constructor of a Factory Agent
     * @param alias The name of the Agent
     * @param state The state of an agent (must be an instance or child of FactoryAgentState)
     * @param struct The struct of the agent
     * @param passwd The password
     */
    public FactoryAgent(String alias, StateBESA state, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, myStruct, passwd);
        //this.start();
    }
    public FactoryAgent(String alias, StateBESA state, double passwd,boolean startNow) throws KernellAgentExceptionBESA {
        super(alias, state, myStruct, passwd);
        if(startNow){
            this.start();
        }
    }
    public static void prepareInstance(List<Class> guards) {
        myStruct = new StructBESA();
        try {
            myStruct.addBehavior("FA_Behavior");
            myStruct.bindGuard("FA_Behavior", FA_CMACreationGuard.class);
            myStruct.bindGuard("FA_Behavior", FA_CMAEndNestedServiceGuard.class);
            for (Class guardBESA : guards) {
                myStruct.bindGuard("FA_Behavior", guardBESA);
            }
        } catch (ExceptionBESA ex) {
            ReportBESA.error(ex);
        }
    }

    /**
     * This method creates a StructBESA with default BESA guards
     */
    public static void prepareInstance() {
        myStruct = new StructBESA();
        try {
            myStruct.addBehavior("FA_Behavior");
            myStruct.bindGuard("FA_Behavior", FA_CMACreationGuard.class);
            myStruct.bindGuard("FA_Behavior", FA_CMAEndNestedServiceGuard.class);
        } catch (ExceptionBESA ex) {
            ReportBESA.error(ex);
        }
    }
    @Override
    public void setupAgent() {
        this.getAdmLocal().addService("FactoryAgent");
        this.getAdmLocal().bindService(this.getAid(), "FactoryAgent");

    }
    @Override
    public void shutdownAgent() {
        this.getAdmLocal().unbindService(this.getAid(), "FactoryAgent");
        //this.getAdmLocal().removeService("FactoryAgent");
    }
    public abstract void createCMAAgent(String agentAlias, Ayllu_Agent_Creation_Message theData, AgHandlerBESA replyHandler, Class guardToReply);

}
