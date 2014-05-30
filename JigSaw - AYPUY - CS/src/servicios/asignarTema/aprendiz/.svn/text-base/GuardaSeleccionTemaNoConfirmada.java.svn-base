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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Tema;
import servicios.asignarTema.GuardaRecibirSeleccionTemas;

/**
 *
 * @author AylluAdmin
 */
public class GuardaSeleccionTemaNoConfirmada extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {

        try {

            System.out.println("En GuardaSeleccionTemaNoConfirmada " + this.getAgent().getAlias());



            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());



            Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();

            AgHandlerBESA senderHandler = theData.getSenderHandler();

            List<Tema> temasDisponibles = (List<Tema>) theData.getMessage();

            switch (theData.getEventType()) {

                case QUEST_REQUEST:

                    manejarPeticion();//Metodo que maneja la petición al IA
                    // manejarRespuesta(temasDisponibles, myHandler, senderHandler);//Esto es para continuar probando el protocolo

                    break;

                case QUEST_REPLY:

                    manejarRespuesta(temasDisponibles, myHandler, senderHandler);  //Metodo que maneja la respuesta del IA

                    break;

            }



        } catch (ExceptionBESA ex) {

            Logger.getLogger(GuardaSeleccionTemaNoConfirmada.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    private void manejarRespuesta(List<Tema> temasDisponibles, AgHandlerBESA myHandler, AgHandlerBESA senderHandler) throws ExceptionBESA {

        //TODO Se debe esperar respuesta de la selecciona de un tema.

        if (!temasDisponibles.isEmpty()) {

            Tema tema = temasDisponibles.get(0);



            System.out.println("Tema: " + tema + " seleccionado por el aprendiz: " + this.getAgent().getAlias());



            Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.START_COOP, myHandler, tema);

            EventBESA event = new EventBESA(GuardaRecibirSeleccionTemas.class.getName(), data);

            senderHandler.sendEvent(event);

        }

        return;

    }

    private void manejarPeticion() {

        //TODO En este punto se debe reportar al IA del estudiante un mensaje de no seleccion de tema
        
        System.out.println("El tema seleccionado anteriormente no esta disponible");
        

    }
}
