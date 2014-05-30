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
public abstract class DAOFactory {

    public static final int BINARY_OBJECTS = 1;
    public static final int XML = 2;
    protected static final String dataDir = "data";

    public abstract ProfileAES persist(ProfileAES profile);
    public abstract List<ProfileAES> getProfileList();
    public abstract ProfileAES findProfileById(String id);

    public static DAOFactory getDAOFactory(int whichFactory) {

        switch (whichFactory) {
            case BINARY_OBJECTS:
                return new BinaryDAOFactory();
            case XML:
                return new XMLDAOFactory();
            default:
                return null;
        }
    }
}
