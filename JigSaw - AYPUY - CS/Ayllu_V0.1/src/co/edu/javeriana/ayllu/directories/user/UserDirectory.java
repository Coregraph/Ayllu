/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.javeriana.ayllu.directories.user;

import BESA.Kernel.System.Directory.AgHandlerBESA;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A basic user directory
 * @author Ayllu
 */
public class UserDirectory implements AylluDataManagement{
    private Set<AgHandlerBESA> totalUsers = null;
    private Map<String,List<AgHandlerBESA>> userDirectory=null;
    private static UserDirectory instance=null;
    
    private UserDirectory() {
        userDirectory=new HashMap<String, List<AgHandlerBESA>>();
        totalUsers = new HashSet<AgHandlerBESA>();
    }
    public static UserDirectory getInstance(){
        if(instance==null){
            instance = new UserDirectory();
        }
        return instance;
    }

    @Override
    public List<AgHandlerBESA> getUsersbyHability(String hability){
        return this.userDirectory.get(hability);
    }

    @Override
    public boolean addUserToHability(String hability,AgHandlerBESA user){
        if(this.userDirectory.containsKey(hability)){
            this.userDirectory.get(hability).add(user);
            this.totalUsers.add(user);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean addHability(String hability, List<AgHandlerBESA> users){
        if(!this.userDirectory.containsKey(hability)){
            this.userDirectory.put(hability, users);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean addHability(String hability){
        if(!this.userDirectory.containsKey(hability)){
            this.userDirectory.put(hability, new ArrayList<AgHandlerBESA>());
            return true;
        }
        return false;
    }
    
    @Override
    public int getUsersNumber(){
        return this.totalUsers.size(); 
    }
    
    @Override
    public AgHandlerBESA getUserSMA(int pos){
        return (AgHandlerBESA)this.totalUsers.toArray()[pos];
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
    
}
