/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.workspacemgr.workspace;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ayllu
 */
public class WorkSpaceAYPUY {

    /**
     * 
     */
    public static final int CREATE = 0;
    /**
     * 
     */
    public static final int RETRIEVE = 1;
    /**
     * 
     */
    public static final int UPDATE = 2;
    /**
     * 
     */
    public static final int DELETE = 3;
    private String workspaceName;
    private WorkSpaceAYPUY predecessor;
    private Map<String, boolean[]> permissions;

    /**
     * 
     * @param workspaceName
     */
    public WorkSpaceAYPUY(String workspaceName) {

        this.workspaceName = workspaceName;
        this.predecessor = null;
        this.permissions = new HashMap<String, boolean[]>();

    }

    /**
     * 
     * @param workspaceName
     * @param predecessor
     * @throws Exception
     */
    public WorkSpaceAYPUY(String workspaceName, WorkSpaceAYPUY predecessor) throws Exception {
        if (predecessor == null) {
            throw new Exception("Every Workspace MUST have a predecesssor");
        }
        this.workspaceName = workspaceName;
        this.predecessor = predecessor;
        this.permissions = new HashMap<String, boolean[]>();
        //this.predecessors.add(predecessor);
    }

    /**
     * 
     * @param role
     * @param permission
     * @throws Exception
     */
    public void removePermission(String role, int permission) throws Exception {
        if (!this.permissions.containsKey(role)) {
            throw new Exception("Role " + role + " is not defined in this workspace");
        } else {
            boolean perms[] = this.permissions.get(role);
            perms[permission] = false;
            this.permissions.put(role, perms);
        }
    }

    /**
     * 
     * @param role
     * @param permission
     * @throws Exception
     */
    public void removePermission(String role, int permission[]) throws Exception {
        if (!this.permissions.containsKey(role)) {
            throw new Exception("Role " + role + " is not defined in this workspace");
        } else {

            boolean perms[] = this.permissions.get(role);
            for (int pos : permission) {
                perms[pos] = false;
            }
            this.permissions.put(role, perms);
        }
    }

    /**
     * 
     * @param role
     * @throws Exception
     */
    public void removeAllPermission(String role) throws Exception {
        if (!this.permissions.containsKey(role)) {
            throw new Exception("Role " + role + " is not defined in this workspace");
        } else {
            boolean perms[] = {false, false, false, false};
            this.permissions.put(role, perms);
        }

    }

    /**
     * 
     * @param role
     * @param permission
     * @throws Exception
     */
    public void addPermission(String role, int permission) throws Exception {
        if (!this.permissions.containsKey(role)) {
            throw new Exception("Role " + role + " is not defined in this workspace");
        } else {
            boolean perms[] = this.permissions.get(role);
            perms[permission] = true;
            this.permissions.put(role, perms);
        }
    }

    /**
     * 
     * @param role
     * @param permission
     * @throws Exception
     */
    public void addPermission(String role, int permission[]) throws Exception {
        if (!this.permissions.containsKey(role)) {
            throw new Exception("Role " + role + " is not defined in this workspace");
        } else {
            boolean perms[] = this.permissions.get(role);
            for (int pos : permission) {
                perms[pos] = true;
            }
            this.permissions.put(role, perms);
        }
    }

    /**
     * 
     * @param role
     * @throws Exception
     */
    public void addAllPermissions(String role) throws Exception {
        if (!this.permissions.containsKey(role)) {
            throw new Exception("Role " + role + " is not defined in this workspace");
        } else {
            boolean perms[] = {true, true, true, true};
            this.permissions.put(role, perms);
        }

    }
    /*
     * ==============METODOS QUE NO LANZAN EXCEPCIÓN============= public void
     * addPermission(String role, int permission) { boolean perms[]; if
     * (!this.permissions.containsKey(role)) { perms = new boolean[]{false,
     * false, false, false}; } else { perms = this.permissions.get(role); }
     * perms[permission] = true; this.permissions.put(role, perms); }
     *
     * public void addPermission(String role, int permission[]) { boolean
     * perms[]; if (!this.permissions.containsKey(role)) { perms = new
     * boolean[]{false, false, false, false}; } else { perms =
     * this.permissions.get(role); } for (int pos : permission) { perms[pos] =
     * true; } this.permissions.put(role, perms); }
     *
     * public void addAllPermissions(String role) { boolean perms[] = {true,
     * true, true, true}; this.permissions.put(role, perms); }
     */

    /**
     * 
     * @param role
     * @throws Exception
     */
    public void addRole(String role) throws Exception {
        if (this.permissions.containsKey(role)) {
            throw new Exception("Role " + role + " is already defined in this workspace");
        } else {
            boolean perm[] = {false, false, false, false};
            this.permissions.put(role, perm);
        }
    }

    /**
     * 
     * @param role
     * @param permission
     * @return
     */
    public boolean hasPermission(String role, int permission) {
        if (!this.permissions.containsKey(role)) {
            boolean ans = false;
            if (this.predecessor != null) {

                ans = (ans || predecessor.hasPermission(role, permission));

            }
            return ans;
        } else {
            boolean perms[] = this.permissions.get(role);
            return perms[permission];
        }
    }

    /**
     * 
     * @param role
     * @param permission
     * @return
     */
    public boolean[] hasPermissions(String role, int permission[]) {
        boolean answ[] = new boolean[4];
        for (int b = 0; b < permission.length; b++) {
            answ[permission[b]] = hasPermission(role, permission[b]);
        }
        return answ;
    }

    /**
     * 
     * @return
     */
    public String getWorkspaceName() {
        return workspaceName;
    }

    @Override
    public String toString() {
        String answ = "\n==========================================\n";
        answ += "WorkSpace_Name: " + this.workspaceName;
        answ += "\nParent:\n";

        answ += "\t" + predecessor.getWorkspaceName() + "\n";

        answ += "Permissions:\n";
        for (String key : this.permissions.keySet()) {
            answ += "\tRole:" + key + "\n";
            boolean perms[] = this.permissions.get(key);
            if (perms[CREATE]) {
                answ += "\t\tCREATE\n";
            }
            if (perms[RETRIEVE]) {
                answ += "\t\tRETRIEVE\n";
            }
            if (perms[UPDATE]) {
                answ += "\t\tUPDATE\n";
            }
            if (perms[DELETE]) {
                answ += "\t\tDELETE\n";
            }
        }
        answ += "==========================================\n";
        return answ;
    }

    /**
     * 
     * @return
     */
    public WorkSpaceAYPUY getPredecessor() {
        return predecessor;
    }
    
}
