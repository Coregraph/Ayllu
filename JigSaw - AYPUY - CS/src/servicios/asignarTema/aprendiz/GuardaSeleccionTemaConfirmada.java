/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.asignarTema.aprendiz;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Tema;
import servicios.asignarTema.EstadoAsignarTema;
import servicios.asignarTema.GuardaFinServicio;

/**
 *
 * @author AylluAdmin
 */
public class GuardaSeleccionTemaConfirmada extends GuardBESA{



    @Override
    public void funcExecGuard(EventBESA ebesa) {
        try {
            System.out.println("En GuardaSeleccionTemaConfirmada "+this.getAgent().getAlias());
            
            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
            
            Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
            AgHandlerBESA senderHandler = theData.getSenderHandler();

            //TODO En este punto se debe reportar al IA del estudiante la confirmacion de la seleccion.
            Tema temaConfirmado = (Tema)theData.getMessage();
            
            
            System.out.println("temasConfirmado->"+temaConfirmado);
            
            ((EstadoAsignarTema) senderHandler.getAg().getState()).getMisDatos().getTemasAsignar().add(temaConfirmado);
            
            EstadoAprendizAT estadoAprendizAT = (EstadoAprendizAT)this.getAgent().getState();
            estadoAprendizAT.getMisDatos().setTemaSeleccionado(temaConfirmado);
            
             Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.COOP_REQUEST, myHandler, temaConfirmado);
             EventBESA evento = new EventBESA(GuardaFinServicio.class.getName(),data);
             estadoAprendizAT.getCommunityManagerHandler().sendEvent(evento);

        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaSeleccionTemaConfirmada.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
}
