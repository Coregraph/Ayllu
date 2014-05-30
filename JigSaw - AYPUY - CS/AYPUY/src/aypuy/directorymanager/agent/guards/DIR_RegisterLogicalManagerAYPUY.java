/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.directorymanager.agent.guards;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import aypuy.directorymanager.agent.DirectoryStateAYPUY;
import aypuy.directorymanager.agent.data.DIRDirectoryManagementData;



/**
 *
 * @author Ayllu
 */
public class DIR_RegisterLogicalManagerAYPUY extends GuardBESA{

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        DIRDirectoryManagementData dirmgm = (DIRDirectoryManagementData)ebesa.getData();
        DirectoryStateAYPUY myState = (DirectoryStateAYPUY)this.getAgent().getState();
        myState.registerInDirectory(dirmgm.getIdentifier(),dirmgm.getAgentID());
    }
    
    
}
