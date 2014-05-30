/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.generarInformeGrupo.secretario;

import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communityagent.AgentRole;
import co.edu.javeriana.ayllu.agents.communityagent.CommunityAgentState;
import java.util.HashMap;
import java.util.List;
import persistencia.Tema;

/**
 *
 * @author AylluAdmin
 */
public class DatosSecretarioGIG extends CommunityAgentState{
    
    private HashMap<Tema, Object> OAResumenPorTema;
    private int grupoManejado;
    private boolean oasLeidos;
    private Tema temaManejado;
    private String aliasr;

    public DatosSecretarioGIG(List<AgentRole> sameVolatileGroupHandlers, AgHandlerBESA sessionManagerHandler, AgHandlerBESA communityManagerHandler) {
        super(sameVolatileGroupHandlers, sessionManagerHandler, communityManagerHandler);
        OAResumenPorTema = new HashMap<Tema, Object>();
        grupoManejado = -1;
        temaManejado = new Tema();
        aliasr = "";
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
     * @return the oasLeidos
     */
    public boolean isOasLeidos() {
        return oasLeidos;
    }

    /**
     * @param oasLeidos the oasLeidos to set
     */
    public void setOasLeidos(boolean oasLeidos) {
        this.oasLeidos = oasLeidos;
    }

    /**
     * @return the grupoManejado
     */
    public int getGrupoManejado() {
        return grupoManejado;
    }

    /**
     * @param grupoManejado the grupoManejado to set
     */
    public void setGrupoManejado(int grupoManejado) {
        this.grupoManejado = grupoManejado;
    }

    public Tema getTemaManejado() {
        return temaManejado;
    }

    public void setTemaManejado(Tema temaManejado) {
        this.temaManejado = temaManejado;
    }

    public String getAliasr() {
        return aliasr;
    }

    public void setAliasr(String aliasr) {
        this.aliasr = aliasr;
    }

    
}
