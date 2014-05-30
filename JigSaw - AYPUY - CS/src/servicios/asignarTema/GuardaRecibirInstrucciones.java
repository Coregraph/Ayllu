/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.asignarTema;

import service.Profesor.*;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communitymanageragent.CMA_SendMessageToCAsGuard;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yolima
 */
public class GuardaRecibirInstrucciones extends GuardBESA {

    @Override
    public synchronized void funcExecGuard(EventBESA ebesa) {
        try {
            System.out.println("En GuardaRecibirInstrucciones");

            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
            
            Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
            DatosInstrucciones instrucciones = (DatosInstrucciones)theData.getMessage();
            instrucciones.setAliasP(this.getAgent().getAlias());
                        
            //enviar a todos los CA
            Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.DISPLAY, null, instrucciones);
            EventBESA event = new EventBESA(CMA_SendMessageToCAsGuard.class.getName(),data );
            myHandler.sendEvent(event); 
            }
        catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRecibirInstrucciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
}