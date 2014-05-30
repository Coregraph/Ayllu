/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adaptation.common;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author AYLLU
 */
public abstract class ProfileAES implements Serializable{
    private static final long serialVersionUID = -372498732592312L;

    private String ProfileId;
    private String category;   
    public ProfileAES(String categoria) {
        UUID uniqueId = UUID.randomUUID();
        this.ProfileId=uniqueId.toString();
        this.category=categoria;
    }
   
    public String getCategory() {
        return category;
    }
   
    public String getProfileId() {
        return ProfileId;
    }

    public void setId(String id) {
        this.ProfileId = id;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    } 
}