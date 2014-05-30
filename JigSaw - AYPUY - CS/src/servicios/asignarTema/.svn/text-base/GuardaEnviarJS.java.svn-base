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
import estrategias.jigsawASHYI.General.Estado_JigsawASHYI;
import estrategias.jigsawASHYI.General.GuardaRespuestaAsignarTemas;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Tema;

/**
 *
 * @author AylluAdmin
 */
public class GuardaEnviarJS extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        try {
            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
            EstadoAsignarTema miEstado = (EstadoAsignarTema) this.getAgent().getState();
            
            System.out.println("En GuardaFinServicio");
            Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
            AgHandlerBESA senderHandler = theData.getSenderHandler();
            Tema tema = (Tema) theData.getMessage();
            System.out.println("tema de estudiante confirmado: "+tema);
            
            if(miEstado.getMisDatos().getEstConfirmados()==miEstado.getMisDatos().getGrupo().size()-1)
            {   
                AgHandlerBESA handlerJS = this.getAgent().getAdmLocal().getHandlerByAlias("CMA_JS");
                                
                Estado_JigsawASHYI theMessage = (Estado_JigsawASHYI) handlerJS.getAg().getState();
                theMessage.getIdentificadoresAprendicesQueAceptaronTema().add(senderHandler.getAgId());             

                Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.COOP_REQUEST, myHandler, theMessage);
                EventBESA evento = new EventBESA(GuardaRespuestaAsignarTemas.class.getName(),data);
                handlerJS.sendEvent(evento);
                System.out.println("Devuelve control a jigsag");
            }
            else
            {                
                miEstado.getMisDatos().setEstConfirmados(miEstado.getMisDatos().getEstConfirmados()+1);
            }
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaEnviarJS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
