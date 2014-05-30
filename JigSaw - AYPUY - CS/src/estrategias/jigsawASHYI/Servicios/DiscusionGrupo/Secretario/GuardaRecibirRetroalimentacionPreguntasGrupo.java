/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.Servicios.DiscusionGrupo.Secretario;

import service.Profesor.*;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communityagent.CommunityAgentState;
import co.edu.javeriana.ayllu.agents.communitymanageragent.CMA_SendMessageToCAsGuard;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yolima
 */
public class GuardaRecibirRetroalimentacionPreguntasGrupo extends GuardBESA {

    @Override
    public synchronized void funcExecGuard(EventBESA ebesa) {
        try {
                System.out.println("En GuardaRecibirRetroalimentacionPreguntasGrupo");

                AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
                CommunityAgentState miEstado = (CommunityAgentState) this.getAgent().getState();

                Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
                theData.setEventType(Ayllu_EventTypes.DISPLAY);
                DatosRetroalimentacionGrupo datosLlegan = (DatosRetroalimentacionGrupo)theData.getMessage();
                System.out.println("Preguntas recibidas: " + datosLlegan.getRetroalimentacionPreguntas().size()+" "+miEstado.getCommunityManagerHandler().getAlias());
                datosLlegan.setAliasCMA(miEstado.getCommunityManagerHandler().getAlias());
                datosLlegan.setAliasSecretario(this.getAgent().getAlias());
                theData.setMessage(datosLlegan);                
                //Mostrar por chat la retroalimentacion
                //
                System.out.println(datosLlegan.getRetroalimentacionPreguntas());
                
                //Enviar a CMA par aque lo envie a todos los CA
                AgHandlerBESA handlerCMA = miEstado.getCommunityManagerHandler();
                EventBESA evento = new EventBESA(CMA_SendMessageToCAsGuard.class.getName(), theData);
                handlerCMA.sendEvent(evento);
                
                
//                try {
//                    AgHandlerBESA handler = miEstado.getCommunityManagerHandler();
//                    EventBESA event = new EventBESA(CMA_EndVolatileGroupGuard.class.getName(), null);
//                    handler.sendEvent(event);
//                } catch (ExceptionBESA ex) {
//                    Logger.getLogger(GuardaRecibirRetroalimentacionPreguntasGrupo.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }
        catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRecibirRetroalimentacionPreguntasGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
}
