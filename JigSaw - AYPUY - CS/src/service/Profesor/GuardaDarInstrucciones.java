/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.Profesor;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SMA_ReceiveCARequestGuard;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SessionManagerAgentState;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AylluAdmin
 */
public class GuardaDarInstrucciones extends GuardBESA {

    @Override
    public synchronized void funcExecGuard(EventBESA ebesa) {
        try {
            System.out.println("En GuardaDarInstrucciones");

            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
            
            EstadoProfesor miEstado = (EstadoProfesor) this.getAgent().getState();
            Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
            DatosInstrucciones instrucciones = (DatosInstrucciones)theData.getMessage();
            instrucciones.setAliasP(this.getAgent().getAlias());
            //Pedir instrucciones
            Ayllu_Data_Message dataProfesor = new Ayllu_Data_Message(Ayllu_EventTypes.DISPLAY, null,instrucciones );
            SessionManagerAgentState estadoSMA = (SessionManagerAgentState) miEstado.getSessionManagerHandler().getAg().getState();
            dataProfesor.setReceiverHandler(estadoSMA.getInterfaceAgentsID().get(0));
            EventBESA eventProfesor = new EventBESA(SMA_ReceiveCARequestGuard.class.getName(),dataProfesor );
            miEstado.getSessionManagerHandler().sendEvent(eventProfesor);
            // enviar a la GUI InterfaceAgent
            
//            DatosInstrucciones instrucciones = new DatosInstrucciones(new ArrayList<String>());
//            
//            for (int i = 0; i < cmas.size(); i++) {
//                String idCMA = cmas.get(i);
//                AgHandlerBESA handlerC = this.getAgent().getAdmLocal().getHandlerByAlias(idCMA);
//                Ayllu_Data_Message misDatos = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REPLY, myHandler, instrucciones);
//                EventBESA evI = new EventBESA(GuardaRecibirInstrucciones.class.getName(), misDatos);
//                handlerC.sendEvent(evI);
//            }
           
            }
        catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaDarInstrucciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
}
