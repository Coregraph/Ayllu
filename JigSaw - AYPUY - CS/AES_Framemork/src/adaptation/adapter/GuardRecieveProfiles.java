/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adaptation.adapter;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import adaptation.adapter.exceptions.FilterMismatchException;
import adaptation.adapter.exceptions.NotEnoughFieldsException;
import adaptation.common.filter.FilterAES;
import adaptation.common.filter.FilterResultAES;
import adaptation.profilemanager.data.DataProfileManagerToAdapter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ayllu
 */
public class GuardRecieveProfiles extends GuardBESA{

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        DataProfileManagerToAdapter data = (DataProfileManagerToAdapter) ebesa.getData();
        AdapterAgentStateAES agstate = (AdapterAgentStateAES)this.getAgent().getState();
        List<FilterAES> filterList = agstate.getSelectedAdaptationMechanism().getAssociateFilters();
        for (FilterAES filter: filterList){
            if(filter.getFilterId().equals(data.getFilterId())){
                FilterResultAES filterResult=null;
                try {
                    filterResult = filter.execFilter(data.getAssociateProfiles());
                } catch (NotEnoughFieldsException ex) {
                    Logger.getLogger(GuardRecieveProfiles.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FilterMismatchException ex){
                    Logger.getLogger(GuardRecieveProfiles.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(filterResult!=null){
                    agstate.addFilterResult(filterResult);
                    agstate.decrementFilterCount();
                }

                break;
            }
        }
        if(agstate.getFiltersToFill()==0){
            agstate.getSelectedAdaptationMechanism().enrichQuery(agstate.getTheQuery(),agstate.getFilterResults());
        }

    }

}
