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
public class Curso implements Serializable{
    private String idCurso;
    private String nombre;
    private Persona profesor;
    private List<Tema> temas;
    private List<Persona> estudiantes;
    private List<TrabajoGrupal> trabajosGrupales;

    public Curso(String idCurso, String nombre, Persona profesor, List<Tema> temas, List<Persona> estudiantes) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.profesor = profesor;
        this.temas = temas;
        this.estudiantes = estudiantes;
        
        temas = new ArrayList<Tema>();
        estudiantes = new ArrayList<Persona>();
        trabajosGrupales = new ArrayList<TrabajoGrupal>();
    }

    

    


    public Curso() {
        temas = new ArrayList<Tema>();
        estudiantes = new ArrayList<Persona>();
        trabajosGrupales = new ArrayList<TrabajoGrupal>();
    }
    
    public List<TrabajoGrupal> getTrabajosGrupales() {
        return trabajosGrupales;
    }

    public void setTrabajosGrupales(List<TrabajoGrupal> trabajosGrupales) {
        this.trabajosGrupales = trabajosGrupales;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the estudiantes
     */
    public List<Persona> getEstudiantes() {
        return estudiantes;
    }

    /**
     * @param estudiantes the estudiantes to set
     */
    public void setEstudiantes(List<Persona> estudiantes) {
        this.estudiantes = estudiantes;
    }

    /**
     * @return the profesor
     */
    public Persona getProfesor() {
        return profesor;
    }

    /**
     * @param profesor the profesor to set
     */
    public void setProfesor(Persona profesor) {
        this.profesor = profesor;
    }

    /**
     * @return the temas
     */
    public List<Tema> getTemas() {
        return temas;
    }

    /**
     * @param temas the temas to set
     */
    public void setTemas(List<Tema> temas) {
        this.temas = temas;
    }

}
