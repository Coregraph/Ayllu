/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.representativeagent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.Agent.StructBESA;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The representative Agent that will answer questions made to the user automatically when it knows a default answer
 * @author AYLLU
 */
public class RepresentativeAgent extends AgentBESA {
    private static StructBESA myStruct;
    public static void prepareInstance() {
        myStruct = new StructBESA();
        try {
            myStruct.addBehavior("RA_Behavior");
            myStruct.bindGuard("RA_Behavior", RA_ReceiveSMQuestionGuard.class);
            myStruct.bindGuard("RA_Behavior", RA_ReceiveIAReplyGuard.class);

          
        } catch (ExceptionBESA ex) {
            Logger.getLogger(RepresentativeAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    public RepresentativeAgent(String alias, StateBESA state, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, myStruct, passwd);
        //this.start();
    }
    public RepresentativeAgent(String alias, StateBESA state, StructBESA structAgent, double passwd, boolean startNow) throws KernellAgentExceptionBESA {
        super(alias, state, structAgent, passwd);
        if(startNow){
            this.start();
        }
    }

    @Override
    public void setupAgent() {
        this.getAdmLocal().addService("RepresentativeAgent");
        this.getAdmLocal().bindService(this.getAid(), "RepresentativeAgent");

    }

    @Override
    public void shutdownAgent() {
        this.getAdmLocal().unbindService(this.getAid(), "RepresentativeAgent");
        //this.getAdmLocal().removeService("CommunityAgent");
    }
}
