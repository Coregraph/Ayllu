/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Francisco
 */
public class Grupo implements Serializable{
    private String idGrupo;
    private List<Persona> participantes;

    public Grupo() {
    }

    public Grupo(String idGrupo, List<Persona> participantes) {
        this.idGrupo = idGrupo;
        this.participantes = participantes;
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public List<Persona> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Persona> participantes) {
        this.participantes = participantes;
    }
    
    
    
}
