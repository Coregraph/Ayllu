/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adaptation.profilemanager.persistence;

import adaptation.common.ProfileAES;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Ayllu
 */
public class BinaryDAOFactory extends DAOFactory{

    public BinaryDAOFactory() {
    }

    @Override
    public ProfileAES persist(ProfileAES profile) {
        File outFile = newFile(profile.getProfileId());
        FileOutputStream flStream = null;
        ObjectOutputStream objStream = null;
        try {
            flStream = new java.io.FileOutputStream(outFile);
            objStream = new ObjectOutputStream(flStream);
            objStream.writeObject(profile);
            objStream.close();
            return findProfileById(outFile.getName());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FacadeProfileManagerPersistence.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(FacadeProfileManagerPersistence.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<ProfileAES> getProfileList() {
        List<ProfileAES> profiles = new  ArrayList<ProfileAES>();
        String[] children = fileList();
        for (int i = 0; i < children.length; i++) {
            String filename = children[i];
            if(!filename.equalsIgnoreCase(".svn")) {
                ProfileAES profile = findProfileById(filename);
                profiles.add(profile);
            }
        }
        return profiles;
    }

    private String[] fileList() {
        File dir = new File(dataDir);
        String[] children = dir.list();
        return children;
    }

    /*public HashMap<String, ProfileAES> getKeyProfileList()
    {
        HashMap<String, ProfileAES> profiles = new HashMap<String, ProfileAES>();
        String[] children = fileList();
        for (int i = 0; i < children.length; i++) {
            String filename = children[i];
            ProfileAES profile = findProfileById(filename);
            profiles.put(filename, profile);
        }
        return profiles;
    }*/

    private File newFile(String uniqueFileName) {
        try {
            File outFile = new java.io.File(dataDir + "/" + uniqueFileName + ".obj");
            return outFile;
        } catch (Exception ex) {
            Logger.getLogger(FacadeProfileManagerPersistence.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public ProfileAES findProfileById(String id) {
        File outFile = new java.io.File(dataDir + "/" + id);
        FileInputStream flStream = null;
        ObjectInputStream objStream = null;
        try {
            flStream = new java.io.FileInputStream(outFile);
            objStream = new ObjectInputStream(flStream);
            ProfileAES perfil = (ProfileAES) objStream.readObject();
            objStream.close();
            return perfil;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FacadeProfileManagerPersistence.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FacadeProfileManagerPersistence.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(FacadeProfileManagerPersistence.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
