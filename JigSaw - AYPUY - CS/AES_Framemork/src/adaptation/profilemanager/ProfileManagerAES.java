/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptation.profilemanager;

import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.Agent.StructBESA;

/**
 *
 * @author AYLLU
 */
public class ProfileManagerAES extends AgentBESA {

    public ProfileManagerAES(String alias, StateBESA state, StructBESA structAgent, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, structAgent, passwd);
    }

    @Override
    public void setupAgent() {
        ProfileManagerStateAES agState = (ProfileManagerStateAES)this.getState();
        if(agState.getCategory()!=null){
            this.getAdmLocal().addService(agState.getCategory());
            this.getAdmLocal().bindService(this.getAid(), agState.getCategory());
        }
    }

    @Override
    public void shutdownAgent() {
        ProfileManagerStateAES agState = (ProfileManagerStateAES)this.getState();
         if(agState.getCategory()!=null){
            this.getAdmLocal().unbindService(this.getAid(), agState.getCategory());
            this.getAdmLocal().removeService(agState.getCategory());
        }
        
    }
    
}
