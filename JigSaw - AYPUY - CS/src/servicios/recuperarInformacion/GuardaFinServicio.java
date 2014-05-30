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
import estrategias.jigsawASHYI.General.Estado_JigsawASHYI;
import estrategias.jigsawASHYI.General.GuardaRespuestaRecuperarInformacion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AylluAdmin
 */
public class GuardaFinServicio extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        try {
            System.out.println("En GuardaFinServicio");
            Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
            List<String> aprendicesTemaLeido = (List<String>)theData.getMessage();
            System.out.println("aprendicesTemaLeido: " + aprendicesTemaLeido);
            
            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAlias(this.getAgent().getAlias());
            
            AgHandlerBESA handlerJS = this.getAgent().getAdmLocal().getHandlerByAlias("CMA_JS");
                                    
            Estado_JigsawASHYI theMessage = (Estado_JigsawASHYI) handlerJS.getAg().getState();           

            Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.COOP_REQUEST, myHandler, theMessage);
            EventBESA evento = new EventBESA(GuardaRespuestaRecuperarInformacion.class.getName(),data);
            handlerJS.sendEvent(evento);
            System.out.println("Devuelve control a jigsaw");
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaFinServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
