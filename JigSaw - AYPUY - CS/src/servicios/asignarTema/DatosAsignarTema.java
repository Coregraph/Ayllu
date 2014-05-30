/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servicios.asignarTema;

import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import java.util.ArrayList;
import java.util.List;
import persistencia.Tema;

/**
 *
 * @author Francisco
 */
public class DatosAsignarTema extends DataBESA{

    
    private ArrayList<ArrayList<String>> grupo;//grupo temático
    private ArrayList<ArrayList<String>> rolesGrupo;//grupo temático - secretario - aprendices
    private ArrayList<Tema> temasDisponibles;
    private int estConfirmados;
    private int estConfirmaInstrucciones;
    private Tema tema;
    
    private List<Tema> temasAsignar;
    private List<AgHandlerBESA> listaAgentesComunidadParticipantes;

    public DatosAsignarTema() {
        temasDisponibles = new ArrayList<Tema>();
        grupo = new ArrayList<ArrayList<String>>();
        rolesGrupo = new ArrayList<ArrayList<String>>();
        temasAsignar = new ArrayList<Tema>();
        listaAgentesComunidadParticipantes = new ArrayList<AgHandlerBESA>();
        estConfirmados = 0;
        estConfirmaInstrucciones = 0;
    }
    

    public DatosAsignarTema(ArrayList<ArrayList<String>> grupos, ArrayList<ArrayList<String>> rolesPorGrupo, ArrayList<Tema> temas) {
        this.grupo = grupos;
        this.rolesGrupo = rolesPorGrupo;
        this.temasDisponibles = temas;
        temasAsignar = new ArrayList<Tema>();
        estConfirmados = 0;
        estConfirmaInstrucciones = 0;
    }

    /**
     * @return the grupos
     */
    public ArrayList<ArrayList<String>> getGrupo() {
        return grupo;
    }

    /**
     * @param grupos the grupos to set
     */
    public void setGrupo(ArrayList<ArrayList<String>> grupos) {
        this.grupo = grupos;
    }

    /**
     * @return the rolesPorGrupo
     */
    public ArrayList<ArrayList<String>> getRolesGrupo() {
        return rolesGrupo;
    }

    /**
     * @param rolesGrupo the rolesPorGrupo to set
     */
    public void setRolesPorGrupo(ArrayList<ArrayList<String>> rolesGrupo) {
        this.rolesGrupo = rolesGrupo;
    }

   

    public DatosAsignarTema clonar() {
        DatosAsignarTema datos = new DatosAsignarTema();
             
        datos.setGrupo(this.grupo);
        datos.setRolesPorGrupo(this.rolesGrupo);
        datos.getTemasDisponibles().addAll(this.temasDisponibles);
        
        return datos;
    }

    /**
     * @return the temasDisponibles
     */
    public ArrayList<Tema> getTemasDisponibles() {
        return temasDisponibles;
    }

    /**
     * @param temasDisponibles the temasDisponibles to set
     */
    public void setTemasDisponibles(ArrayList<Tema> temasDisponibles) {
        this.temasDisponibles = temasDisponibles;
    }

    /**
     * @return the temasAsignadosPorEstudiantePorGrupo
     */
    public List<Tema> getTemasAsignar() {
        return temasAsignar;
    }

    /**
     * @param temasAsignadosPorEstudiantePorGrupo the temasAsignadosPorEstudiantePorGrupo to set
     */
    public void setTemasAsignadr(ArrayList<Tema> temasAsignados) {
        this.temasAsignar = temasAsignados;
    }

    /**
     * @return the listaAgentesComunidadParticipantes
     */
    public List<AgHandlerBESA> getListaAgentesComunidadParticipantes() {
        return listaAgentesComunidadParticipantes;
    }

    /**
     * @param listaAgentesComunidadParticipantes the listaAgentesComunidadParticipantes to set
     */
    public void setListaAgentesComunidadParticipantes(List<AgHandlerBESA> listaAgentesComunidadParticipantes) {
        this.listaAgentesComunidadParticipantes = listaAgentesComunidadParticipantes;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public int getEstConfirmados() {
        return estConfirmados;
    }

    public void setEstConfirmados(int estConfirmados) {
        this.estConfirmados = estConfirmados;
    }

    public int getEstConfirmaInstrucciones() {
        return estConfirmaInstrucciones;
    }

    public void setEstConfirmaInstrucciones(int estConfirmaInstrucciones) {
        this.estConfirmaInstrucciones = estConfirmaInstrucciones;
    }
    
}