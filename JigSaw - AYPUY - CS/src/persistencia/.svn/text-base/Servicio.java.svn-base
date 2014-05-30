/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Francisco
 */
public class Servicio implements Serializable{
    private String idServicio;
    private String descripcion;
    private List<Rol> rolesPorServicio;

    public Servicio() {
        rolesPorServicio = new ArrayList<Rol>();
    }

    public Servicio(String idServicio, String descripcion, List<Rol> rolesPorServicio) {
        this.idServicio = idServicio;
        this.descripcion = descripcion;
        this.rolesPorServicio = rolesPorServicio;
    }

    

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }

    public List<Rol> getRolesPorServicio() {
        return rolesPorServicio;
    }

    public void setRolesPorServicio(List<Rol> rolesPorServicio) {
        this.rolesPorServicio = rolesPorServicio;
    }
    
    
}
