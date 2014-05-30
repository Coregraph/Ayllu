/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.asignarTema.aprendiz;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communityagent.CommunityAgentState;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Tema;
import servicios.asignarTema.GuardaRecibirSeleccionTemas;

/**
 *
 * @author AylluAdmin
 */
public class GuardaReporteTemasDisponibles extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        try {
            System.out.println("En GuardaReporteTemasDisponibles " + this.getAgent().getAlias());

            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());

            Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
            AgHandlerBESA senderHandler = theData.getSenderHandler();
             Tema temaDisponible = (Tema) theData.getMessage();
             manejarRespuesta(temaDisponible, myHandler, senderHandler);  //Metodo que maneja la respuesta del IA
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaReporteTemasDisponibles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void manejarRespuesta(Tema tema, AgHandlerBESA myHandler, AgHandlerBESA senderHandler) throws ExceptionBESA {

        System.out.println("Tema: " + tema + " seleccionado para el aprendiz: " + this.getAgent().getAlias());

        Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.START_COOP, myHandler, tema);
        EventBESA event = new EventBESA(GuardaRecibirSeleccionTemas.class.getName(), data);
        senderHandler.sendEvent(event);

        return;

    }
    

    private void manejarPeticion(List<Tema> temasDisponibles) {

        //XXX OK En este punto se debe reportar al IA del estudiante, los temas que estan disponibles. 
        CommunityAgentState myState = (CommunityAgentState) this.getAgent().getState();
        String coopserID = myState.getCommunityManagerHandler().getAgId();
        String destinationId = myState.getSessionManagerHandler().getAgId();
        //Cambiar por solicitud al CMA de manejo del envío/recepción
        ((EstadoAprendizAT) this.getAgent().getState()).sendAwarenessMessageToGUI(this.getAgent().getAid(), this.getClass().getName(), destinationId, coopserID, temasDisponibles);
        System.out.println("temasDisponibles->" + temasDisponibles);

    }
}
