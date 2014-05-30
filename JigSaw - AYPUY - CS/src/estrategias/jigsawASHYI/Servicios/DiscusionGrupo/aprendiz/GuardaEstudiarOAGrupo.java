/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.Servicios.DiscusionGrupo.aprendiz;

import servicios.recuperarInformacion.aprendiz.*;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SMA_ReceiveCARequestGuard;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yolima
 */
public class GuardaEstudiarOAGrupo extends GuardBESA{

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        
        try {
            System.out.println("En GuardaEstudiarOAGrupo");
            
            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAlias(this.getAgent().getAlias());

            Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
            AgHandlerBESA senderHandler = theData.getSenderHandler();
            
            EstadoAprendizRI miEstado = (EstadoAprendizRI) this.getAgent().getState();
            
            DatosEstudiarOva ova = (DatosEstudiarOva) theData.getMessage();
            ova.setAliasCMA(miEstado.getCommunityManagerHandler().getAlias());
            
             //mostrar OVA - enviar a SMA
            Ayllu_Data_Message dataProfesor = new Ayllu_Data_Message(Ayllu_EventTypes.DISPLAY, myHandler,ova );
            EventBESA evento = new EventBESA(SMA_ReceiveCARequestGuard.class.getName(),dataProfesor );
            miEstado.getSessionManagerHandler().sendEvent(evento);
            
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaEstudiarOAGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
    }
    
}
