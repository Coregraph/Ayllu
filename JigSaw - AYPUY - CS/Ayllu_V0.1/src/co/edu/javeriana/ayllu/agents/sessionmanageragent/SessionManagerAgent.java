/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.sessionmanageragent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.Agent.StructBESA;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The session manager agent represents the session of one person, it is used to link the user woth its CA
 * @author AYLLU
 */
public class SessionManagerAgent extends AgentBESA {

    private static StructBESA myStruct;

    public static void prepareInstance() {

        try {
            myStruct = new StructBESA();
            myStruct.addBehavior("SMA_Behavior");
            myStruct.bindGuard("SMA_Behavior", SMA_ReceiveCARequestGuard.class);
            myStruct.bindGuard("SMA_Behavior", SMA_ReceiveCMARequestGuard.class);
            myStruct.bindGuard("SMA_Behavior", SMA_ReceiveIARequestGuard.class);
            myStruct.bindGuard("SMA_Behavior", SMA_ReceiveRAReplyGuard.class);
            myStruct.bindGuard("SMA_Behavior", SMA_RegisterIAGuard.class);
            myStruct.bindGuard("SMA_Behavior", SMA_CreateCooperativeServiceGuard.class);
            myStruct.bindGuard("SMA_Behavior", SMA_ValidateUserLogin.class);
            myStruct.bindGuard("SMA_Behavior", SMA_RegisterCooperativeServiceGuard.class);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(SessionManagerAgent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*private static StructBESA getStruct() {
    StructBESA my_Struct = null;
    try {
    my_Struct = new StructBESA();
    my_Struct.addBehavior("SMA_Behavior");
    my_Struct.bindGuard("SMA_Behavior", SMA_ReceiveCARequestGuard.class);
    my_Struct.bindGuard("SMA_Behavior", SMA_ReceiveCMARequestGuard.class);
    my_Struct.bindGuard("SMA_Behavior", SMA_ReceiveIARequestGuard.class);
    my_Struct.bindGuard("SMA_Behavior", SMA_ReceiveRAReplyGuard.class);
    my_Struct.bindGuard("SMA_Behavior", SMA_CreateCooperativeServiceGuard.class);
    my_Struct.bindGuard("SMA_Behavior", SMA_ValidateUserLogin.class);
    my_Struct.bindGuard("SMA_Behavior", SMA_RegisterCooperativeServiceGuard.class);
    
    } catch (ExceptionBESA ex) {
    Logger.getLogger(SessionManagerAgent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return my_Struct;
    
    }
     */
    public static void prepareInstance(List<Class> newGuards) {

        try {
            prepareInstance();
            for (Class guard : newGuards) {
                myStruct.bindGuard("SMA_Behavior", guard);
            }

        } catch (ExceptionBESA ex) {
            Logger.getLogger(SessionManagerAgent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public SessionManagerAgent(String alias, StateBESA state, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, myStruct, passwd);

    }

    public SessionManagerAgent(String alias, StateBESA state, double passwd, boolean startNow) throws KernellAgentExceptionBESA {
        super(alias, state, myStruct, passwd);
        if (startNow) {
            this.start();
        }
    }

    @Override
    public void setupAgent() {
        this.getAdmLocal().addService("SessionManagerAgent");
        this.getAdmLocal().bindService(this.getAid(), "SessionManagerAgent");

    }

    @Override
    public void shutdownAgent() {
        this.getAdmLocal().unbindService(this.getAid(), "SessionManagerAgent");
        //this.getAdmLocal().removeService("CommunityAgent");
    }
}
