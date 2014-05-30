
package adaptation.profilemanager.persistence;

import adaptation.common.ProfileAES;
import java.util.List;




public class FacadeProfileManagerPersistence {
    private DAOFactory daoFactory;

    public FacadeProfileManagerPersistence(){
        daoFactory =  DAOFactory.getDAOFactory(DAOFactory.BINARY_OBJECTS);
    }

    public ProfileAES persist(ProfileAES perfil){
        return daoFactory.persist(perfil);
    }

    public List<ProfileAES> getProfileList()
    {
        return daoFactory.getProfileList();
    }
    
    public ProfileAES findProfileById(String id){
         return daoFactory.findProfileById(id);
    }
}
