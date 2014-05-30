/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adaptation.profilemanager.persistence;

import adaptation.common.ProfileAES;
import java.util.List;

/**
 *
 * @author Ayllu
 */
public class XMLDAOFactory extends DAOFactory{

    public XMLDAOFactory() {
    }

    @Override
    public ProfileAES persist(ProfileAES profile) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ProfileAES> getProfileList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ProfileAES findProfileById(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
