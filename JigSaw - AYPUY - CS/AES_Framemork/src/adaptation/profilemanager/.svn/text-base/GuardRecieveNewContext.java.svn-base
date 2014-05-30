/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adaptation.profilemanager;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import adaptation.adapter.data.DataContextToProfileManager;

/**
 *
 * @author Ayllu
 */
public class GuardRecieveNewContext extends GuardBESA{


    @Override
    public void funcExecGuard(EventBESA ebesa) {
        DataContextToProfileManager data = (DataContextToProfileManager)ebesa.getData();
        ((ProfileManagerStateAES)this.getAgent().getState()).setNewContext(data.getNewcontext());
    }

}
