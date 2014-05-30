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
public class Tema implements Serializable{
    private String idTema;
    private String nombre;
    private String descripcion;
    private List<GrupoPreguntas> grupoPreguntas;
    private List<Curso> cursosRelacionados;

    public Tema() {
        cursosRelacionados = new ArrayList<Curso>();
        grupoPreguntas = new ArrayList<GrupoPreguntas>();
    }

    public Tema(String idTema, String nombre, String descripcion, List<GrupoPreguntas> gruporeguntas, List<Curso> cursosRelacionados) {
        this.idTema = idTema;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.grupoPreguntas = gruporeguntas;
        this.cursosRelacionados = cursosRelacionados;
    }

    

    /**
     * @return the idTema
     */
    public String getIdTema() {
        return idTema;
    }

    /**
     * @param idTema the idTema to set
     */
    public void setIdTema(String idTema) {
        this.idTema = idTema;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the cursosRelacionados
     */
    public List<Curso> getCursosRelacionados() {
        return cursosRelacionados;
    }

    /**
     * @param cursosRelacionados the cursosRelacionados to set
     */
    public void setCursosRelacionados(List<Curso> cursosRelacionados) {
        this.cursosRelacionados = cursosRelacionados;
    }

    /**
     * @return the grupoPreguntas
     */
    public List<GrupoPreguntas> getGrupoPreguntas() {
        return grupoPreguntas;
    }

    /**
     * @param grupoPreguntas the grupoPreguntas to set
     */
    public void setGrupoPreguntas(List<GrupoPreguntas> grupoPreguntas) {
        this.grupoPreguntas = grupoPreguntas;
    }

}
