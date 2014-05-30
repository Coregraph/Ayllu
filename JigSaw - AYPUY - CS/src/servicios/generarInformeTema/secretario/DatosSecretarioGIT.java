/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.generarInformeTema.secretario;

import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communityagent.AgentRole;
import co.edu.javeriana.ayllu.agents.communityagent.CommunityAgentState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import persistencia.Tema;

/**
 *
 * @author AylluAdmin
 */
public class DatosSecretarioGIT extends CommunityAgentState{
    
    private ArrayList<String> grupo;
    private HashMap<String, Object> OAPorAprendiz;
    private String idSecretario;
    private long horaInicioServicio;
    private Tema temaManejado;
    private String aliasr;

    
    public DatosSecretarioGIT(List<AgentRole> sameVolatileGroupHandlers, AgHandlerBESA sessionManagerHandler, AgHandlerBESA communityManagerHandler) {
        super(sameVolatileGroupHandlers, sessionManagerHandler, communityManagerHandler);
        grupo = new ArrayList<String>();
        OAPorAprendiz = new HashMap<String, Object>();
        aliasr="";
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
     * @return the idSecretario
     */
    public String getIdSecretario() {
        return idSecretario;
    }

    /**
     * @param idSecretario the idSecretario to set
     */
    public void setIdSecretario(String idSecretario) {
        this.idSecretario = idSecretario;
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
     * @return the OAPorAprendiz
     */
    public HashMap<String, Object> getOAPorAprendiz() {
        return OAPorAprendiz;
    }

    /**
     * @param OAPorAprendiz the OAPorAprendiz to set
     */
    public void setOAPorAprendiz(HashMap<String, Object> OAPorAprendiz) {
        this.OAPorAprendiz = OAPorAprendiz;
    }

    public String getAliasr() {
        return aliasr;
    }

    public void setAliasr(String aliasr) {
        this.aliasr = aliasr;
    }

     

}