/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servicios.generarInformeTema;

import BESA.Kernel.Agent.Event.DataBESA;
import java.util.ArrayList;
import java.util.HashMap;
import persistencia.Tema;

/**
 *
 * @author Francisco
 */
public class DatosGenerarInformeTema extends DataBESA{

    
    private ArrayList<Tema> temas;
    private ArrayList<String> estudiantes;
    private ArrayList<String> rolesEstudiantes;
    private HashMap<Tema, Object> OAResumenPorTema;
    private Tema temaManejado;
    private int numeroTotalGruposTema;
    private int numeroOAenviado;
    private int estConfirmanRetro;
    private String aliasRecurso;
    private int numeroGrupo;
    private String idRecurso;

    public DatosGenerarInformeTema(int numGrupo) {
        numeroTotalGruposTema = 0;
        numeroOAenviado = 0;
        estConfirmanRetro = 0;
        temas = new ArrayList<Tema>();
        estudiantes = new ArrayList<String>();   
        rolesEstudiantes = new ArrayList<String>();   
        OAResumenPorTema = new HashMap<Tema, Object>();
        aliasRecurso = "";
        numeroGrupo = numGrupo;
        idRecurso = "";
    }
   
    /**
     * @return the temaManejado
     */
    public Tema getTemaManejado() {
        return temaManejado;
    }

    /**
     * @param temaManejado the temaManejado to set
     */
    public void setTemaManejado(Tema temaManejado) {
        this.temaManejado = temaManejado;
    }
    
    /**
     * @return the temas
     */
    public ArrayList<Tema> getTemas() {
        return temas;
    }

    /**
     * @param temas the temas to set
     */
    public void setTemas(ArrayList<Tema> temas) {
        this.temas = temas;
    }

    /**
     * @return the OAResumenPorTema
     */
    public HashMap<Tema, Object> getOAResumenPorTema() {
        return OAResumenPorTema;
    }

    /**
     * @param OAResumenPorTema the OAResumenPorTema to set
     */
    public void setOAResumenPorTema(HashMap<Tema, Object> OAResumenPorTema) {
        this.OAResumenPorTema = OAResumenPorTema;
    }

    /**
     * @return the numeroTotalGruposTema
     */
    public int getNumeroTotalGruposTema() {
        return numeroTotalGruposTema;
    }

    /**
     * @param numeroTotalGruposTema the numeroTotalGruposTema to set
     */
    public void setNumeroTotalGruposTema(int numeroTotalGruposTema) {
        this.numeroTotalGruposTema = numeroTotalGruposTema;
    }

    public ArrayList<String> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<String> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public int getNumeroOAenviado() {
        return numeroOAenviado;
    }

    public void setNumeroOAenviado(int numeroOAenviado) {
        this.numeroOAenviado = numeroOAenviado;
    }

    public ArrayList<String> getRolesEstudiantes() {
        return rolesEstudiantes;
    }

    public void setRolesEstudiantes(ArrayList<String> rolesEstudiantes) {
        this.rolesEstudiantes = rolesEstudiantes;
    }

    public int getEstConfirmanRetro() {
        return estConfirmanRetro;
    }

    public void setEstConfirmanRetro(int estConfirmanRetro) {
        this.estConfirmanRetro = estConfirmanRetro;
    }

    public String getAliasRecurso() {
        return aliasRecurso;
    }

    public void setAliasRecurso(String aliasRecurso) {
        this.aliasRecurso = aliasRecurso;
    }

    public int getNumeroGrupo() {
        return numeroGrupo;
    }

    public void setNumeroGrupo(int numeroGrupo) {
        this.numeroGrupo = numeroGrupo;
    }

    public String getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(String idRecurso) {
        this.idRecurso = idRecurso;
    }


}
