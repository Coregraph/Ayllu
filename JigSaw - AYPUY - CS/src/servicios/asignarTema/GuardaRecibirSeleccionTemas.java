/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.asignarTema;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Tema;
import servicios.asignarTema.aprendiz.EstadoAprendizAT;
import servicios.asignarTema.aprendiz.GuardaSeleccionTemaConfirmada;

/**
 *
 * @author AylluAdmin
 */
public class GuardaRecibirSeleccionTemas extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {

        try {
            System.out.println("En GuardaRecibirSeleccionTemas " + this.getAgent().getAlias());

            Thread.sleep(50);

            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAlias(this.getAgent().getAlias());

            Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
            AgHandlerBESA senderHandler = theData.getSenderHandler();

            EstadoAsignarTema miEstado = (EstadoAsignarTema) this.getAgent().getState();

            Tema temaSeleccionado = (Tema) theData.getMessage();
            ArrayList<Tema> temasDisponibles = miEstado.getMisDatos().getTemasDisponibles();

            System.out.println("temasDisponibles->" + temasDisponibles);

                ((EstadoAprendizAT) senderHandler.getAg().getState()).getMisDatos().setTieneTema(true);
                Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.START_COOP, myHandler, temaSeleccionado);
                EventBESA event = new EventBESA(GuardaSeleccionTemaConfirmada.class.getName(), data);
                senderHandler.sendEvent(event);


        } catch (InterruptedException ex) {
            Logger.getLogger(GuardaRecibirSeleccionTemas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRecibirSeleccionTemas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
