/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.asignarTema.aprendiz;

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
public class DatosAprendizAT extends CommunityAgentState{
    
    private Tema temaSeleccionado;
    private boolean tieneTema;
    private HashMap<String, String> temaSeleccionadoPorEstudiante;//Variable de awarennes

    public DatosAprendizAT(List<AgentRole> sameVolatileGroupHandlers, AgHandlerBESA sessionManagerHandler, AgHandlerBESA communityManagerHandler) {
        super(sameVolatileGroupHandlers, sessionManagerHandler, communityManagerHandler);
        temaSeleccionadoPorEstudiante = new HashMap<String, String>();
        tieneTema = false;
    }

    
    /**
     * @return the estudiantesConTemaAceptado
     */
    public HashMap<String, String> getEstudiantesConTemaAceptado() {
        return temaSeleccionadoPorEstudiante;
    }

    /**
     * @param estudiantesConTemaAceptado the estudiantesConTemaAceptado to set
     */
    public void setEstudiantesConTemaAceptado(HashMap<String, String> estudiantesConTemaAceptado) {
        this.temaSeleccionadoPorEstudiante = estudiantesConTemaAceptado;
    }

    /**
     * @return the temaSeleccionado
     */
    public Tema getTemaSeleccionado() {
        return temaSeleccionado;
    }

    /**
     * @param temaSeleccionado the temaSeleccionado to set
     */
    public void setTemaSeleccionado(Tema temaSeleccionado) {
        this.temaSeleccionado = temaSeleccionado;
    }

    /**
     * @return the tieneTema
     */
    public boolean getTieneTema() {
        return tieneTema;
    }

    /**
     * @param tieneTema the tieneTema to set
     */
    public void setTieneTema(boolean tieneTema) {
        this.tieneTema = tieneTema;
    }

    

}
