/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptation.common.query;

import BESA.Kernel.Agent.Event.DataBESA;
import adaptation.common.ProfileAES;
import java.util.List;

/**
 *
 * @author AYLLU
 */
public abstract class AdaptationQueryAES extends DataBESA{
    String query;
    public List<ProfileAES> context;
    public List<ProfileAES> restrictions;

    public List<ProfileAES> getContext() {
        return context;
    }

    public void setContext(List<ProfileAES> context) {
        this.context = context;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<ProfileAES> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(List<ProfileAES> restrictions) {
        this.restrictions = restrictions;
    }


    

    public void putContextInformation(ProfileAES profile) {
        context.add(profile);
        
    }

    public void putRestrictionInformation(ProfileAES profile) {
        restrictions.add(profile);        
    }

    
}
