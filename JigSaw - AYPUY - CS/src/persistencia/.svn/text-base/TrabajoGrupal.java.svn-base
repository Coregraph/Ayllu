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
public class TrabajoGrupal implements Serializable{
    private String idTrabajoGrupal;
    private Estrategia estrategiaAsignada;
    private List<Grupo> grupos;

    public TrabajoGrupal() {
        grupos = new ArrayList<Grupo>();
    }

    public TrabajoGrupal(String idTrabajoGrupal, Estrategia estrategiaAsignada, List<Grupo> grupos) {
        this.idTrabajoGrupal = idTrabajoGrupal;
        this.estrategiaAsignada = estrategiaAsignada;
        this.grupos = grupos;
    }

    public Estrategia getEstrategiaAsignada() {
        return estrategiaAsignada;
    }

    public void setEstrategiaAsignada(Estrategia estrategiaAsignada) {
        this.estrategiaAsignada = estrategiaAsignada;
    }

    public String getIdTrabajoGrupal() {
        return idTrabajoGrupal;
    }

    public void setIdTrabajoGrupal(String idTrabajoGrupal) {
        this.idTrabajoGrupal = idTrabajoGrupal;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }
    
    
    
}
