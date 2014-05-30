/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.workspacemgr.agent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Log.ReportBESA;
import aypuy.workspacemgr.agent.guards.WSM_ManagementGuardAYPUY;
import aypuy.workspacemgr.agent.guards.WSM_ResourceREQGuardAYPUY;


/**
 *
 * @author Ayllu
 */
public class WorkSpaceManagerAgentAYPUY extends AgentBESA {

   

    private static StructBESA getStruct() {
        StructBESA myStruct;
        myStruct = new StructBESA();
        myStruct.addBehavior("WSmgr_AYPUY");
        try {
            myStruct.bindGuard("WSmgr_AYPUY", WSM_ManagementGuardAYPUY.class);
            myStruct.bindGuard("WSmgr_AYPUY", WSM_ResourceREQGuardAYPUY.class);
        } catch (ExceptionBESA ex) {
            ReportBESA.error(ex.getStackTrace());
        }
        return myStruct;
    }

    public WorkSpaceManagerAgentAYPUY(String alias, double passwd) throws KernellAgentExceptionBESA {
        super(alias, new WorkSpaceManagerStateAYPUY(), getStruct(), passwd);
    }

    @Override
    public void setupAgent() {
    }

    @Override
    public void shutdownAgent() {
       
    }
}
