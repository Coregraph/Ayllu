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
 * @author luis
 */
public class GrupoPreguntas implements Serializable{
    
    private String idGrupoPreguntas;
    private List<Pregunta> preguntas;
    private Tema temaRelacionado;

    public GrupoPreguntas() {
        preguntas = new ArrayList<Pregunta>();
    }

    
    public GrupoPreguntas(String idGrupoPreguntas, List<Pregunta> preguntas, Tema temaRelacionado) {
        this.idGrupoPreguntas = idGrupoPreguntas;
        this.preguntas = preguntas;
        this.temaRelacionado = temaRelacionado;
    }

    /**
     * @return the idGrupoPreguntas
     */
    public String getIdGrupoPreguntas() {
        return idGrupoPreguntas;
    }

    /**
     * @param idGrupoPreguntas the idGrupoPreguntas to set
     */
    public void setIdGrupoPreguntas(String idGrupoPreguntas) {
        this.idGrupoPreguntas = idGrupoPreguntas;
    }

    /**
     * @return the preguntas
     */
    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    /**
     * @param preguntas the preguntas to set
     */
    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    /**
     * @return the temaRelacionado
     */
    public Tema getTemaRelacionado() {
        return temaRelacionado;
    }

    /**
     * @param temaRelacionado the temaRelacionado to set
     */
    public void setTemaRelacionado(Tema temaRelacionado) {
        this.temaRelacionado = temaRelacionado;
    }

}
