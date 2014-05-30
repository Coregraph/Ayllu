/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.recuperarInformacion;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import servicios.recuperarInformacion.aprendiz.EstadoAprendizRI;
import servicios.recuperarInformacion.aprendiz.GuardaNotificacionLecturasAwarnes;

/**
 *
 * @author AylluAdmin
 */
public class GuardaRecibirRespuestasRI extends GuardBESA {
    

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        try {
            System.out.println("En la guarda que recibe la respuesta del CMA_RI");
            
            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAlias(this.getAgent().getAlias());

            Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
            AgHandlerBESA senderHandler = theData.getSenderHandler();

            EstadoRecuperarInformacion miEstado = (EstadoRecuperarInformacion) this.getAgent().getState();
            List<AgHandlerBESA> agentesComunidadAprendiz = ((EstadoRecuperarInformacion)this.getAgent().getState()).getMisDatos().getAgentesComunidadAprendiz();
            
            
            List<String> aprendicesConTemaLeido = miEstado.getMisDatos().getIdAprendicesConTemaLeido();
            if(!aprendicesConTemaLeido.contains(senderHandler.getAlias()))
            {
                aprendicesConTemaLeido.add(senderHandler.getAlias());
            }


            //TODO Reporte de los temas leidos a todos los aprendices. AWAERENES
            AgHandlerBESA agenteComunidadAprendiz;
            for (int i = 0; i < agentesComunidadAprendiz.size(); i++) {
                try {
                agenteComunidadAprendiz = agentesComunidadAprendiz.get(i);

                    EstadoAprendizRI estado = (EstadoAprendizRI)agenteComunidadAprendiz.getAg().getState();
                    estado.getMisDatos().setEstudiantesConOALeido(aprendicesConTemaLeido);
                    Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.START_COOP, myHandler, estado);
                    EventBESA event = new EventBESA(GuardaNotificacionLecturasAwarnes.class.getName(), data);
                    agenteComunidadAprendiz.sendEvent(event);
                } catch (ExceptionBESA ex) {
                    Logger.getLogger(GuardaRecibirRespuestasRI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            
            //Se finaliza el servicioa, debido a que todos los estudiantes han leido el tema
            if(aprendicesConTemaLeido.size()==agentesComunidadAprendiz.size())
            {
                miEstado = (EstadoRecuperarInformacion) this.getAgent().getState();


                AgHandlerBESA handlerCMA = this.getAgent().getAdmLocal().getHandlerByAlias("CMA_RI");

                Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.START_COOP, handlerCMA, miEstado.getMisDatos().getIdAprendicesConTemaLeido());
                EventBESA event = new EventBESA(GuardaFinServicio.class.getName(), data);
                handlerCMA.sendEvent(event);

                //Supuestamente para finalizar el CMA_AT y todo lo que el tiene relacionado
                //TODO verificar como se debe finalizar este agente, el alias tiene un numero adicional. Como está no lo debe encontrar
//                event = new EventBESA(CMA_EndVolatileGroupGuard.class.getName(), null);
//                handlerCMA.sendEvent(event);
            }
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRecibirRespuestasRI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
