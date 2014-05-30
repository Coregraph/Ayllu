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
public class Persona implements Serializable{
    
    private String login;
    private String pass;
    private Rol rolBasico;
    private List<Curso> misCursosDirigidos; //Solo los profesores dirigen cursos
    private List<Curso> misCursosAsistidos; //Todos los roles pueden asistir a un curso

    public Persona() {
        misCursosDirigidos = new ArrayList<Curso>();
        misCursosAsistidos = new ArrayList<Curso>();
    }

    public Persona(String login, String pass, Rol rolBasico, List<Curso> misCursosDirigidos, List<Curso> misCursosAsistidos) {
        this.login = login;
        this.pass = pass;
        this.rolBasico = rolBasico;
        this.misCursosDirigidos = misCursosDirigidos;
        this.misCursosAsistidos = misCursosAsistidos;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Rol getRolBasico() {
        return rolBasico;
    }

    public void setRolBasico(Rol rolBasico) {
        this.rolBasico = rolBasico;
    }

    /**
     * @return the misCursosAsistidos
     */
    public List<Curso> getMisCursosAsistidos() {
        return misCursosAsistidos;
    }

    /**
     * @param misCursosAsistidos the misCursosAsistidos to set
     */
    public void setMisCursosAsistidos(List<Curso> misCursosAsistidos) {
        this.misCursosAsistidos = misCursosAsistidos;
    }

    /**
     * @return the misCursosDirigidos
     */
    public List<Curso> getMisCursosDirigidos() {
        return misCursosDirigidos;
    }

    /**
     * @param misCursosDirigidos the misCursosDirigidos to set
     */
    public void setMisCursosDirigidos(List<Curso> misCursosDirigidos) {
        this.misCursosDirigidos = misCursosDirigidos;
    }

    
}
