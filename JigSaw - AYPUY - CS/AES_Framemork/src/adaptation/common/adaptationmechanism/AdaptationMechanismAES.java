/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adaptation.common.adaptationmechanism;

import adaptation.common.query.AdaptationQueryAES;
import adaptation.common.query.EnrichedAdaptationQueryAES;
import adaptation.common.filter.FilterAES;
import adaptation.common.filter.FilterResultAES;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AYLLU
 */

public abstract class AdaptationMechanismAES {
    private List<FilterAES> associateFilters;

    public AdaptationMechanismAES() {
        associateFilters = new ArrayList<FilterAES>();
    }


    public void putFilter(FilterAES filter){
        associateFilters.add(filter);
    }

    public List<FilterAES> getAssociateFilters() {
        return associateFilters;
    }

    public void setAssociateFilters(List<FilterAES> associateFilters) {
        this.associateFilters = associateFilters;
    }
    

 

    public abstract EnrichedAdaptationQueryAES enrichQuery(AdaptationQueryAES theQuery, List<FilterResultAES> filterResults);
}



