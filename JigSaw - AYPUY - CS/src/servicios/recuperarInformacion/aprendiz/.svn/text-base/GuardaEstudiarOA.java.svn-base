/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.recuperarInformacion.aprendiz;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Tema;
import servicios.recuperarInformacion.GuardaRecibirRespuestasRI;

/**
 *
 * @author AylluAdmin
 */
public class GuardaEstudiarOA extends GuardBESA{



    @Override
    public void funcExecGuard(EventBESA ebesa) {
        
        try {
            System.out.println("En GuardaEstudiarOA");
            
            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAlias(this.getAgent().getAlias());

            Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
            AgHandlerBESA senderHandler = theData.getSenderHandler();
            Tema tema = (Tema)theData.getMessage();
            
            EstadoAprendizRI miEstado = (EstadoAprendizRI) this.getAgent().getState();
            
            switch (theData.getEventType()) {

                case QUEST_REQUEST:
                    
                    manejarPeticion(tema);//Metodo que maneja la petición al IA
                    
                    manejarRespuesta(true, myHandler, senderHandler);//Esto es para efectos de prueba del protocolo

                    break;

                case QUEST_REPLY:
                    
                    boolean estudiado = (Boolean)theData.getMessage();

                    manejarRespuesta(estudiado, myHandler, senderHandler);  //Metodo que maneja la respuesta del IA

                    break;

            }
           
            
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaEstudiarOA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
    }
    
    private void manejarRespuesta(boolean estudiado, AgHandlerBESA myHandler, AgHandlerBESA senderHandler) throws ExceptionBESA {

        //TODO Se debe esperar respuesta de la lectura del tema.
        
        if (estudiado) {

            EstadoAprendizRI miEstado = (EstadoAprendizRI)myHandler.getAg().getState();
            miEstado.getMisDatos().setOALeido(true);
            Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.START_COOP, myHandler, null);
            EventBESA event = new EventBESA(GuardaRecibirRespuestasRI.class.getName(), data);
            senderHandler.sendEvent(event);
        }

        return;

    }

    private void manejarPeticion(Tema tema) {

        //TODO En este punto se debe reportar al IA del estudiante, el OA que fue seleccionado.
        System.out.println("OA Seleccionado->"+tema);

    }
    
}
