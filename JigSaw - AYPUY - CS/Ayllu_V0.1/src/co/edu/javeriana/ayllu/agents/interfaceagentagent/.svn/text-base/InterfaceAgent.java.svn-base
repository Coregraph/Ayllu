/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.interfaceagentagent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Log.ReportBESA;
import java.util.List;


/**
 * Abstract Agent that must be implemented in order to show an interface to the user
 * @author AYLLU
 */
public abstract class InterfaceAgent extends AgentBESA {

    private static StructBESA myStruct = null;

    /**
     * Constructor, must be called after prepareInstance has been called
     * @param alias the Agent Alias
     * @param state The Agent state (must be instace or chind of InterfaceAgentState)
     * @param passwd  The container's password
     */
    public InterfaceAgent(String alias, StateBESA state, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, myStruct, passwd);
        
    }
    /**
     * Constructor, must be called after prepareInstance has been called
     * @param alias the Agent Alias
     * @param state The Agent state (must be instace or chind of InterfaceAgentState)
     * @param passwd  The container's password
     */
    public InterfaceAgent(String alias, StateBESA state, double passwd, boolean startNow) throws KernellAgentExceptionBESA {
        super(alias, state, myStruct, passwd);

        if (startNow){
            this.start();
        }
    }

    @Override
    public final void start() {
        super.start();
        ((InterfaceAgentState)state).setAgentID(this.getAid());
    }
    
    /**
     * Method to prepate the Interface Agents Instance
     */
    public static void prepareIntance() {
        myStruct = new StructBESA();
        try {
            myStruct.addBehavior("IA_Behavior");

            myStruct.bindGuard("IA_Behavior", IA_ReceiveAARequestGuard.class);
            myStruct.bindGuard("IA_Behavior", IA_ReceiveRAQuestionGuard.class);
            myStruct.bindGuard("IA_Behavior", IA_ReceiveSMRequestGuard.class);
            //myStruct.bindGuard("IA_Behavior", IA_ReceiveLoginNotification.class);
            
            /*myStruct.addBehavior("BehaviorServiceProvider");
            myStruct.bindGuard("BehaviorServiceProvider", GuardServiceProviderSuscribe.class);
            myStruct.bindGuard("BehaviorServiceProvider", GuardServiceProviderRequest.class);*/
        } catch (ExceptionBESA ex) {
            ReportBESA.error(ex);
        }
    }
    /**
     * MEthod to prepate the Interface Agents Instance
     */
    public static void prepareIntance(List<Class> guards) {
        myStruct = new StructBESA();
        try {
            prepareIntance();
            for(Class guardBESA: guards){
                myStruct.bindGuard("IA_Behavior",guardBESA);
            }
            /*myStruct.addBehavior("BehaviorServiceProvider");
            myStruct.bindGuard("BehaviorServiceProvider", GuardServiceProviderSuscribe.class);
            myStruct.bindGuard("BehaviorServiceProvider", GuardServiceProviderRequest.class);*/
        } catch (ExceptionBESA ex) {
            ReportBESA.error(ex);
        }
    }
    @Override
    public void setupAgent() {
        this.getAdmLocal().addService("InterfaceAgent");
        this.getAdmLocal().bindService(this.getAid(), "InterfaceAgent");

    }

    @Override
    public void shutdownAgent() {
        this.getAdmLocal().unbindService(this.getAid(), "InterfaceAgent");
        //this.getAdmLocal().removeService("CommunityAgent");
    }
   
}
