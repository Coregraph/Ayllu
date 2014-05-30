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
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import estrategias.jigsawASHYI.General.GuardaRespuestaPreguntasGrupo;
import estrategias.jigsawASHYI.Servicios.Discusion.Estado_JigSaw_Discusion;
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.Estado_JigSaw_DiscusionGrupo;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yolima
 */
public class GuardaRecibirRtaRetroalimentacionGrupo extends GuardBESA {

    @Override
    public synchronized void funcExecGuard(EventBESA ebesa) {
        try {
                System.out.println("En GuardaRecibirRtaRetroalimentacion");

                AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
                CommunityAgentState miEstado = (CommunityAgentState) this.getAgent().getState();

                Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
                theData.setEventType(Ayllu_EventTypes.DISPLAY);
                DatosRetroalimentacionGrupo datosLlegan = (DatosRetroalimentacionGrupo)theData.getMessage();
                
                System.out.println("Retroalimentación recibida");
                
                //Enviar a CMA par aque lo envie a todos los CA
                AgHandlerBESA handlerCMA = miEstado.getCommunityManagerHandler();
                Estado_JigSaw_DiscusionGrupo estadoCMA = (Estado_JigSaw_DiscusionGrupo)handlerCMA.getAg().getState();
                estadoCMA.getDatos().setNumeroEstudiantesRta(estadoCMA.getDatos().getNumeroEstudiantesRta()+1);
                
                if (estadoCMA.getDatos().getNumeroEstudiantesRta() == estadoCMA.getDatos().getGrupo().size())
                {                    
                    AgHandlerBESA handlerJS = this.getAgent().getAdmLocal().getHandlerByAlias("CMA_JS");    
                    DatosRetroalimentacionGrupo datosEnviar = new DatosRetroalimentacionGrupo(null, null);
                    Ayllu_Data_Message terminar = new Ayllu_Data_Message(Ayllu_EventTypes.STOP_TASK, myHandler,datosEnviar );
                    EventBESA event = new EventBESA(GuardaRespuestaPreguntasGrupo.class.getName(), terminar);
                    handlerJS.sendEvent(event);        
                }
                       
            }
        catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRecibirRtaRetroalimentacionGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
}
