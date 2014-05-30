/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.generarInformeGrupo.aprendiz;

import BESA.Kernel.Agent.Event.DataBESA;
import java.util.ArrayList;
import java.util.HashMap;
import persistencia.GrupoPreguntas;
import persistencia.Tema;

/**
 *
 * @author AylluAdmin
 */
public class DatosAprendizGIG extends DataBESA{
    
    private ArrayList<String> grupo;
    private String aliasSecretario;
    private Tema tema;
    private GrupoPreguntas gruposPreguntasPorEstudiante;
    private HashMap<String, String> temasPorEstudiante;
    private long horaInicioServicio;

    public DatosAprendizGIG() {
        grupo = new ArrayList<String>();
        temasPorEstudiante = new HashMap<String, String>();
        gruposPreguntasPorEstudiante = new GrupoPreguntas();
        tema = new Tema();
    }

    /**
     * @return the grupo
     */
    public ArrayList<String> getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(ArrayList<String> grupo) {
        this.grupo = grupo;
    }


    /**
     * @return the horaInicioServicio
     */
    public long getHoraInicioServicio() {
        return horaInicioServicio;
    }

    /**
     * @param horaInicioServicio the horaInicioServicio to set
     */
    public void setHoraInicioServicio(long horaInicioServicio) {
        this.horaInicioServicio = horaInicioServicio;
    }

    /**
     * @return the aliasSecretario
     */
    public String getAliasSecretario() {
        return aliasSecretario;
    }

    /**
     * @param aliasSecretario the aliasSecretario to set
     */
    public void setAliasSecretario(String aliasSecretario) {
        this.aliasSecretario = aliasSecretario;
    }


    /**
     * @return the temasPorEstudiante
     */
    public HashMap<String, String> getTemasPorEstudiante() {
        return temasPorEstudiante;
    }

    /**
     * @param temasPorEstudiante the temasPorEstudiante to set
     */
    public void setTemasPorEstudiante(HashMap<String, String> temasPorEstudiante) {
        this.temasPorEstudiante = temasPorEstudiante;
    }

    /**
     * @return the gruposPreguntasPorEstudiante
     */
    public GrupoPreguntas getGruposPreguntasPorEstudiante() {
        return gruposPreguntasPorEstudiante;
    }

    /**
     * @param gruposPreguntasPorEstudiante the gruposPreguntasPorEstudiante to set
     */
    public void setGruposPreguntasPorEstudiante(GrupoPreguntas gruposPreguntasPorEstudiante) {
        this.gruposPreguntasPorEstudiante = gruposPreguntasPorEstudiante;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }
}