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
public class Estrategia implements Serializable{
    
    private String idEstrategia;
    private String nombre;
    private List<Servicio> plan;
    private List estadoPlan;

    public String getIdEstrategia() {
        return idEstrategia;
    }

    public void setIdEstrategia(String idEstrategia) {
        this.idEstrategia = idEstrategia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Servicio> getPlan() {
        return plan;
    }

    public void setPlan(List<Servicio> plan) {
        this.plan = plan;
    }
    

    public Estrategia() {
        plan = new ArrayList<Servicio>();
        estadoPlan = new ArrayList();
    }

    public Estrategia(String idEstrategia, String nombre, List<Servicio> plan) {
        this.idEstrategia = idEstrategia;
        this.nombre = nombre;
        this.plan = plan;
    }

    /**
     * @return the estadoPlan
     */
    public List getEstadoPlan() {
        return estadoPlan;
    }

    /**
     * @param estadoPlan the estadoPlan to set
     */
    public void setEstadoPlan(List estadoPlan) {
        this.estadoPlan = estadoPlan;
    }
    
    
    
    
}
