/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.generarInformeGrupo;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communitymanageragent.CMA_SendMessageToCAsGuard;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import estrategias.jigsawASHYI.General.Estado_JigsawASHYI;
import estrategias.jigsawASHYI.General.GuardaRespuestaOAGenerarInformeGrupo;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.Profesor.DatosRetroNota;

/**
 *
 * @author Yolima
 */
public class GuardaRecibirEvaluacionInformeGrupo extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        try {
            System.out.println("En la guarda GuardaRecibirEvaluacionInformeGrupo");
            
            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
            EstadoGenerarInformeGrupo miEstado = (EstadoGenerarInformeGrupo) this.getAgent().getState();
            
            Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
            DatosRetroNota nota = (DatosRetroNota) theData.getMessage();
            //enviar nota a los ca
                
            Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.DISPLAY, myHandler, nota);
            EventBESA event = new EventBESA(CMA_SendMessageToCAsGuard.class.getName(),data );
            myHandler.sendEvent(event); 
            
            //Control a JS
            AgHandlerBESA handler = this.getAgent().getAdmLocal().getHandlerByAlias("CMA_JS");
            Estado_JigsawASHYI estadoJS = new Estado_JigsawASHYI();
            data = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, myHandler, estadoJS);
            event = new EventBESA(GuardaRespuestaOAGenerarInformeGrupo.class.getName(), data);
            handler.sendEvent(event);
                
            
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRecibirEvaluacionInformeGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
