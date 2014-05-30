/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptation.profilemanager;

import BESA.Kernel.Agent.StateBESA;
import adaptation.common.ProfileAES;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AYLLU
 */
public class ProfileManagerStateAES extends StateBESA {
    private String category=null;
    private List<ProfileAES> newContext;

    public ProfileManagerStateAES() {
        super();
        newContext = new ArrayList<ProfileAES>();
    }
    public ProfileManagerStateAES(String category) {
        super();
        this.category=category;
        newContext = new ArrayList<ProfileAES>();
    }

    public List<ProfileAES> getNewContext() {
        return newContext;
    }

    public void setNewContext(List<ProfileAES> newContext) {
        this.newContext = newContext;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
}
