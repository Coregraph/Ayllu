/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptation.profilemanager;


import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;

import BESA.Kernel.System.Directory.AgHandlerBESA;
import adaptation.common.filter.FilterAES;
import adaptation.profilemanager.data.DataProfileManagerToAdapter;
import adaptation.adapter.GuardRecieveProfiles;
import adaptation.adapter.data.DataAdaptertoProfileManager;
import adaptation.common.ProfileAES;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ayllu
 */
public abstract class GuardRecieveFilterQuery extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        DataAdaptertoProfileManager data = (DataAdaptertoProfileManager) ebesa.getData();
        DataProfileManagerToAdapter profiles = new DataProfileManagerToAdapter();
        profiles.setAssociateProfiles(obtainProfiles(data.getFilter()));
        profiles.setFilterId(data.getFilter().getFilterId());
        //Obtener el handler del Agente
        try {
            AgHandlerBESA handler = this.getAgent().getAdmLocal().getHandlerByAlias("Adapter");
            //Enviando el evento
            handler.sendEvent(new EventBESA(GuardRecieveProfiles.class.getName(), profiles));
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardRecieveFilterQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public abstract List<ProfileAES> obtainProfiles(FilterAES filter);
}
