/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.Profesor;

import BESA.Kernel.Agent.Event.DataBESA;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import persistencia.Tema;

/**
 *
 * @author Yolima
 */
public class DatosProfesor extends DataBESA{
    
    private List<Tema> temas;
    private HashMap<String, String> evaluacionPorEstudiante;
    private List<String> estudiantes;
    private List<DatosEvaluacionInforme> ovaTema;//ova enviados por tema
    private List<DatosEvaluacionInforme> ovaGrupo;//ova enviados por grupo
    private List<DatosEvaluacionInforme> notasTema;

    public DatosProfesor(List<String> estudiantes) {
        evaluacionPorEstudiante = new HashMap<String, String>();
        temas = new ArrayList<Tema>();
       this.estudiantes = estudiantes;
        notasTema = new ArrayList<DatosEvaluacionInforme>();
        ovaTema = new ArrayList<DatosEvaluacionInforme>();
        ovaGrupo = new ArrayList<DatosEvaluacionInforme>();
    }

    
    /**
     * @return the estudiantesConTemaAceptado
     */
    public HashMap<String, String> getEvaluacionPorEstudiante() {
        return evaluacionPorEstudiante;
    }

    /**
     * @param estudiantesConTemaAceptado the estudiantesConTemaAceptado to set
     */
    public void setEvaluacionPorEstudiante(HashMap<String, String> estudiantes) {
        this.evaluacionPorEstudiante = estudiantes;
    }

    /**
     * @return the temaSeleccionado
     */
    public List<Tema> getTemaSeleccionado() {
        return temas;
    }

    /**
     * @param temaSeleccionado the temaSeleccionado to set
     */
    public void setTemaSeleccionado(List<Tema> tema) {
        this.temas = tema;
    }

    public List<String> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<String> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public List<Tema> getTemas() {
        return temas;
    }

    public void setTemas(List<Tema> temas) {
        this.temas = temas;
    }

    public List<DatosEvaluacionInforme> getNotasTema() {
        return notasTema;
    }

    public void setNotasTema(List<DatosEvaluacionInforme> datos) {
        this.notasTema = datos;
    }

    public List<DatosEvaluacionInforme> getOvaTema() {
        return ovaTema;
    }

    public void setOvaTema(List<DatosEvaluacionInforme> ovaTema) {
        this.ovaTema = ovaTema;
    }

    public List<DatosEvaluacionInforme> getOvaGrupo() {
        return ovaGrupo;
    }

    public void setOvaGrupo(List<DatosEvaluacionInforme> ovaGrupo) {
        this.ovaGrupo = ovaGrupo;
    }

    

}
