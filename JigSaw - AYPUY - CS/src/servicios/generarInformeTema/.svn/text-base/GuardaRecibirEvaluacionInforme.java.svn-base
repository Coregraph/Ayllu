/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.generarInformeTema;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communitymanageragent.CMA_SendMessageToCAsGuard;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.Profesor.DatosRetroNota;

/**
 *
 * @author Yolima
 */
public class GuardaRecibirEvaluacionInforme extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        try {
            System.out.println("En la guarda GuardaRecibirEvaluacionInforme");
            
            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
            EstadoGenerarInformeTema miEstado = (EstadoGenerarInformeTema) this.getAgent().getState();
            System.out.println("2222222222222222222: "+myHandler.getAlias());
            
            Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
            DatosRetroNota nota = (DatosRetroNota) theData.getMessage();
            nota.setAliasCma(this.getAgent().getAlias());
            
            //enviar nota a los ca           
            Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.DISPLAY, myHandler, nota);
            EventBESA event = new EventBESA(CMA_SendMessageToCAsGuard.class.getName(),data );
            myHandler.sendEvent(event); 
            
            
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRecibirEvaluacionInforme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
