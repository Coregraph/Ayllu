/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.physicalmgr.agent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StructBESA;
import aypuy.physicalmgr.agent.guards.PM_DownloadReqAYPUY;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ayllu
 */
public class PhysicalManagerAgentAYPUY extends AgentBESA{

    /**
     * 
     * @param alias
     * @param storagePath
     * @param passwd
     * @throws KernellAgentExceptionBESA
     */
    public PhysicalManagerAgentAYPUY(String alias,String storagePath, double passwd) throws KernellAgentExceptionBESA {
        super(alias, new PhysicalManagerStateAYPUY(storagePath), getStruct(), passwd);
    }
    
    @Override
    public void setupAgent() {
        
    }

    @Override
    public void shutdownAgent() {
        
    }
    private static StructBESA getStruct(){
        StructBESA aStruct = null;
        try {
            aStruct = new StructBESA();
            aStruct.addBehavior("PMAYPUY_Behavior");
            aStruct.bindGuard("PMAYPUY_Behavior", PM_DownloadReqAYPUY.class);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(PhysicalManagerAgentAYPUY.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aStruct;
    }
}
