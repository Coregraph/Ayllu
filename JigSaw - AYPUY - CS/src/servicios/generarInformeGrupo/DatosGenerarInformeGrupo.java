/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servicios.generarInformeGrupo;

import BESA.Kernel.Agent.Event.DataBESA;
import java.util.ArrayList;
import java.util.HashMap;
import persistencia.Tema;

/**
 *
 * @author Francisco
 */
public class DatosGenerarInformeGrupo extends DataBESA{


    private HashMap<Tema, Object> OAResumenPorTema;
    private ArrayList<String> grupos;
    private ArrayList<String> rolesPorGrupo;
    private int numeroOAenviado;
    private int numeroRetroalimentacionConfirmado;
    private Tema tema;
    private String aliasr;
    private int numeroGrupo;
    private String idRecurso;

    public DatosGenerarInformeGrupo(int numgrupo) {
        OAResumenPorTema = new HashMap<Tema, Object>();
        grupos = new ArrayList<String>();
        rolesPorGrupo = new ArrayList<String>();
        numeroOAenviado = 0;
        numeroRetroalimentacionConfirmado = 0;
        tema = new Tema();
        aliasr = "";
        numeroGrupo = numgrupo;
        idRecurso = "";
    }


    /**
     * @return the grupos
     */
    public ArrayList<String> getGrupos() {
        return grupos;
    }

    /**
     * @param grupos the grupos to set
     */
    public void setGrupos(ArrayList<String> grupos) {
        this.grupos = grupos;
    }

    /**
     * @return the rolesPorGrupo
     */
    public ArrayList<String> getRolesPorGrupo() {
        return rolesPorGrupo;
    }

    /**
     * @param rolesPorGrupo the rolesPorGrupo to set
     */
    public void setRolesPorGrupo(ArrayList<String> rolesPorGrupo) {
        this.rolesPorGrupo = rolesPorGrupo;
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

    public int getNumeroOAenviado() {
        return numeroOAenviado;
    }

    public void setNumeroOAenviado(int numeroOAenviado) {
        this.numeroOAenviado = numeroOAenviado;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public int getNumeroRetroalimentacionConfirmado() {
        return numeroRetroalimentacionConfirmado;
    }

    public void setNumeroRetroalimentacionConfirmado(int numeroRetroalimentacionConfirmado) {
        this.numeroRetroalimentacionConfirmado = numeroRetroalimentacionConfirmado;
    }

    public String getAliasr() {
        return aliasr;
    }

    public void setAliasr(String aliasr) {
        this.aliasr = aliasr;
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
