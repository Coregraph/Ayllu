/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.Profesor;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import java.util.logging.Level;
import java.util.logging.Logger;
import servicios.generarInformeTema.GuardaRecibirEvaluacionInforme;

/**
 *
 * @author Yolima
 */
public class GuardaRecibirSMEvaluarInformeTema extends GuardBESA {

    @Override
    public synchronized void funcExecGuard(EventBESA ebesa) {
        try {
            System.out.println("En GuardaRecibirSMEvaluarInformeTema");
            
            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAlias(this.getAgent().getAlias()); 
            EstadoProfesor miEstado = (EstadoProfesor) this.getAgent().getState();
            
            Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();           
            
            DatosRetroNota retroalimentacion = (DatosRetroNota)theData.getMessage(); 
            
            miEstado.getMisDatos().getOvaTema().add(new DatosEvaluacionInforme(retroalimentacion.getNota(), retroalimentacion.getRetroalimentacion(), retroalimentacion.getAliasCma()));
            
            AgHandlerBESA senderHandler = this.getAgent().getAdmLocal().getHandlerByAlias(retroalimentacion.getAliasCma());//CMA_GenerarInformeTema
           
            Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REPLY, myHandler, retroalimentacion);
            EventBESA event = new EventBESA(GuardaRecibirEvaluacionInforme.class.getName(),data );
            senderHandler.sendEvent(event);
            
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRecibirSMEvaluarInformeTema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}