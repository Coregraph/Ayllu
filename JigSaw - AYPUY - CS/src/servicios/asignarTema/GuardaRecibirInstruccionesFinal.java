/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.asignarTema;

import co.edu.javeriana.ayllu.agents.communityagent.AgentRole;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import estrategias.jigsawASHYI.General.Estado_JigsawASHYI;
import estrategias.jigsawASHYI.General.GuardaControlarJigsawASHYI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yolima
 */
public class GuardaRecibirInstruccionesFinal extends GuardBESA {

    @Override
    public synchronized void funcExecGuard(EventBESA ebesa) {
        try {
            System.out.println("En GuardaRecibirInstruccionesFinal");

            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
            EstadoAsignarTema estado = (EstadoAsignarTema)myHandler.getAg().getState();
            List<AgentRole> ca = estado.getCommunityAgentsHandlers();  
            
            if(estado.getMisDatos().getEstConfirmaInstrucciones()==estado.getMisDatos().getListaAgentesComunidadParticipantes().size()-1)
            {   
                //Enviar control a JS
                AgHandlerBESA handlerCJS = this.getAgent().getAdmLocal().getHandlerByAlias("CMA_JS"); 
                ((Estado_JigsawASHYI) handlerCJS.getAg().getState()).setEstadoEstrategia(Estado_JigsawASHYI.DISCUSION_TEMATICA);
                Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.START_COOP, myHandler, ((Estado_JigsawASHYI)handlerCJS.getAg().getState()));
                EventBESA evento = new EventBESA(GuardaControlarJigsawASHYI.class.getName(),data);
                handlerCJS.sendEvent(evento);
            }
            else
            {
                estado.getMisDatos().setEstConfirmaInstrucciones(estado.getMisDatos().getEstConfirmaInstrucciones()+1);
            }
        }
        catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRecibirInstruccionesFinal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
}
