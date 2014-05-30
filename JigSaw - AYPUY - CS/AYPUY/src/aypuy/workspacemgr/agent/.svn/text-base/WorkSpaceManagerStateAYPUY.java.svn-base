/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.workspacemgr.agent;

import BESA.Kernel.Agent.StateBESA;
import aypuy.workspacemgr.workspace.WorkSpaceAYPUY;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Ayllu
 */
public class WorkSpaceManagerStateAYPUY extends StateBESA {

    private Map<String, WorkSpaceAYPUY> workspaces;

    public WorkSpaceManagerStateAYPUY() {
        this.workspaces = new HashMap<String, WorkSpaceAYPUY>();
    }

    public void createRootWorkSpace(String name) {
        try {
            this.workspaces.put(name, new WorkSpaceAYPUY(name,new WorkSpaceAYPUY("AYPUY")));
        } catch (Exception ex) {
            Logger.getLogger(WorkSpaceManagerStateAYPUY.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createWorkSpace(String name, String fatherName) throws Exception {
        if (!this.workspaces.containsKey(fatherName)) {
            throw new Exception("The Workspace: " + fatherName + " is not registered as a Workspace");
        } else {
            this.workspaces.put(name, new WorkSpaceAYPUY(name, this.workspaces.get(fatherName)));
        }
    }

    public void addRoleToWorkspace(String wsName, String roleName) throws Exception {
        if (!this.workspaces.containsKey(wsName)) {
            throw new Exception("The Workspace: " + wsName + " is not registered as a Workspace");
        } else {
            this.workspaces.get(wsName).addRole(roleName);
        }
    }

    public void addPermission(String wsName, String roleName, int permission) throws Exception {
        if (!this.workspaces.containsKey(wsName)) {
            throw new Exception("The Workspace: " + wsName + " is not registered as a Workspace");
        } else {
            this.workspaces.get(wsName).addPermission(roleName, permission);
        }
    }

    public void addPermission(String wsName, String roleName, int permission[]) throws Exception {
        if (!this.workspaces.containsKey(wsName)) {
            throw new Exception("The Workspace: " + wsName + " is not registered as a Workspace");
        } else {
            this.workspaces.get(wsName).addPermission(roleName, permission);
        }
    }

    public void addAllPermissions(String wsName, String roleName) throws Exception {
        if (!this.workspaces.containsKey(wsName)) {
            throw new Exception("The Workspace: " + wsName + " is not registered as a Workspace");
        } else {
            this.workspaces.get(wsName).addAllPermissions(roleName);
        }
    }

    public void removePermission(String wsName, String roleName, int permission) throws Exception {
        if (!this.workspaces.containsKey(wsName)) {
            throw new Exception("The Workspace: " + wsName + " is not registered as a Workspace");
        } else {
            this.workspaces.get(wsName).removePermission(roleName, permission);
        }
    }

    public void removePermission(String wsName, String roleName, int permission[]) throws Exception {
        if (!this.workspaces.containsKey(wsName)) {
            throw new Exception("The Workspace: " + wsName + " is not registered as a Workspace");
        } else {
            this.workspaces.get(wsName).removePermission(roleName, permission);
        }
    }

    public void removeAllPermissions(String wsName, String roleName) throws Exception {
        if (!this.workspaces.containsKey(wsName)) {
            throw new Exception("The Workspace: " + wsName + " is not registered as a Workspace");
        } else {
            this.workspaces.get(wsName).removeAllPermission(roleName);
        }
    }

    public void printWorkspaces() {
        for (String key : this.workspaces.keySet()) {
            Logger.getLogger(WorkSpaceManagerStateAYPUY.class.getName()).log(Level.INFO,this.workspaces.get(key).toString());
        }
    }

    public void printWorkspace(String name) {
        Logger.getLogger(WorkSpaceManagerStateAYPUY.class.getName()).log(Level.INFO,this.workspaces.get(name).toString());
    }

    public boolean hasPermission(String wsName, String roleName, int permission) throws Exception {
        if (!this.workspaces.containsKey(wsName)) {
            throw new Exception("The Workspace: " + wsName + " is not registered as a Workspace");
        } else {
            return this.workspaces.get(wsName).hasPermission(roleName, permission);
        }
    }

    public boolean[] hasPermissions(String wsName, String roleName, int permission[]) throws Exception {
        if (!this.workspaces.containsKey(wsName)) {
            throw new Exception("The Workspace: " + wsName + " is not registered as a Workspace");
        } else {
            return this.workspaces.get(wsName).hasPermissions(roleName, permission);
        }
    }

    public void deleteWorkSpace(String name) throws Exception {
        if (!this.workspaces.containsKey(name)) {
            throw new Exception("The Workspace: " + name + " is not registered as a Workspace");
        } else {
            this.workspaces.remove(name);
            Set<String> keys = this.workspaces.keySet();
            for (String key : keys) {
                WorkSpaceAYPUY pred = this.workspaces.get(key).getPredecessor();
                if (pred!=null) {
                    String predeccesor = pred.getWorkspaceName();
                   if(predeccesor.equals(name)){
                       deleteWorkSpace(key);
                   }
                }
            }
        }
    }
}
