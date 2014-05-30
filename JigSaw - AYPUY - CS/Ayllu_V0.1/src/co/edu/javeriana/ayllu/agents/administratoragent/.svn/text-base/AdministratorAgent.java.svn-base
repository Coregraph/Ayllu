/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.administratoragent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.Agent.StructBESA;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AYLLU
 * Administrator Agent
 */
public class AdministratorAgent extends AgentBESA {

    public static AdministratorAgent createInstance(String alias, StateBESA state, double passwd) {
        StructBESA myStruct = new StructBESA();
        try {
            myStruct.addBehavior("AA_Behavior");
            myStruct.bindGuard("AA_Behavior", AA_ReceiveDirectoryManagementGuard.class);
            return new AdministratorAgent(alias, state, myStruct, passwd);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(AdministratorAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private AdministratorAgent(String alias, StateBESA state, StructBESA structAgent, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, structAgent, passwd);
    }
    private AdministratorAgent(String alias, StateBESA state, StructBESA structAgent, double passwd, boolean startNow) throws KernellAgentExceptionBESA {
        super(alias, state, structAgent, passwd);
        if (startNow){
            this.start();
        }
    }

    @Override
    public void setupAgent() {
        //TODO _AA Servicios de insercion en BD, etc.
        this.getAdmLocal().addService("AdministratorAgent");
        this.getAdmLocal().bindService(this.getAid(), "AdministratorAgent");

    }

    @Override
    public void shutdownAgent() {
        this.getAdmLocal().unbindService(this.getAid(), "AdministratorAgent");
        //this.getAdmLocal().removeService("AdministratorAgent");
    }
}
