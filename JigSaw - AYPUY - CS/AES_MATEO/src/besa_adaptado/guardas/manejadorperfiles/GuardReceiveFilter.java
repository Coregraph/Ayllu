package besa_adaptado.guardas.manejadorperfiles;

import adaptation.common.ProfileAES;
import adaptation.common.filter.FilterAES;
import adaptation.profilemanager.GuardRecieveFilterQuery;
import adaptation.profilemanager.ProfileManagerStateAES;
import adaptation.profilemanager.persistence.FacadeProfileManagerPersistence;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GuardReceiveFilter extends GuardRecieveFilterQuery {

    @Override
    public List<ProfileAES> obtainProfiles(FilterAES filter) {
        List resp = new ArrayList();
        FacadeProfileManagerPersistence facade = new FacadeProfileManagerPersistence();
        ProfileManagerStateAES state = (ProfileManagerStateAES) getAgent().getState();
        for (ProfileAES profile : state.getNewContext()) {
            List<ProfileAES> profileList = facade.getProfileList();
            for (ProfileAES stored : profileList) {
                if ((stored.getCategory().equals(filter.getCategory())) && (profile.getCategory().equals(filter.getCategory()))) {
                    Field[] fields = profile.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        try {
                            field.setAccessible(true);
                            if (field.get(stored).equals(field.get(profile))) {
                                resp.add(stored);

                                if (!stored.getCategory().equals("team")) {
                                    if (!stored.getCategory().equals("user"));
                                }

                            }

                        } catch (IllegalArgumentException ex) {
                            Logger.getLogger(GuardReceiveFilter.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(GuardReceiveFilter.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            System.out.println("AQUI!");
                        }
                    }
                }
            }
        }
        return resp;
    }
}
