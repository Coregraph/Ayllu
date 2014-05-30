/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.Profesor;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import aypuy.workspacemgr.agent.data.WSManageResourceDataAYPUY;
import aypuy.workspacemgr.agent.guards.WSM_ResourceREQGuardAYPUY;
import aypuy.workspacemgr.workspace.WorkSpaceAYPUY;
import co.edu.javeriana.ayllu.agents.interfaceagentagent.InterfaceAgent;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SMA_ReceiveCARequestGuard;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SessionManagerAgentState;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import estrategias.jigsawASHYI.Interface.Agents.IA_SynchCommInterface;
import estrategias.jigsawASHYI.Interface.Agents.IA_SynchCommInterfaceState;
import estrategias.jigsawASHYI.Interface.MessageGUI;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import recursos.SMA_ReceiveHandlerAYPUY;

/**
 *
 * @author Yolima
 */
public class GuardaEvaluarInformeTema extends GuardBESA {

    @Override
    public synchronized void funcExecGuard(EventBESA ebesa) {
        try {
            System.out.println("En GuardaEvaluarInformeTema");   
            
            
            AgHandlerBESA handlerProfe = this.getAgent().getAdmLocal().getHandlerByAlias(this.getAgent().getAlias()); 
            EstadoProfesor miEstado = (EstadoProfesor) this.getAgent().getState();
            
            Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
            AgHandlerBESA senderHandler = theData.getSenderHandler();//CMA_GenerarInformeTema
            
            DatosEvaluacionInforme datos = (DatosEvaluacionInforme)theData.getMessage();
            miEstado.getMisDatos().getOvaTema().add(datos);
            String aliasS = datos.getAliasCMA();
            datos.setAliasCMA(senderHandler.getAlias());
             
             /**
             * |||||||||Solicitud Recurso||||||||||||
             */
            BitSet elbitBitSet = new BitSet();
            elbitBitSet.set(0);
            elbitBitSet.set(1);
            
            Properties props = new Properties();
            props.load(new FileInputStream("aypuyConfig.properties"));
            AgHandlerBESA hand = this.getAgent().getAdmLocal().getHandlerByAlias(props.getProperty("wsmanageralias"));
            WSManageResourceDataAYPUY dtaReq = new WSManageResourceDataAYPUY(SMA_ReceiveHandlerAYPUY.class.getName(),this.getAgent().getAid(), WorkSpaceAYPUY.RETRIEVE, elbitBitSet, "Profesor", "Tematico", datos.getOva());
            EventBESA evreq = new EventBESA(WSM_ResourceREQGuardAYPUY.class.getName(), dtaReq);
            hand.sendEvent(evreq);
            Thread.sleep(1000);
            Logger.getLogger(GuardaEvaluarInformeTema.class.getName()).log(Level.INFO,"Fin Solicitud Recurso!");
            
                        
            AgHandlerBESA handlerProfesor = this.getAgent().getAdmLocal().getHandlerByAlias("SMA_Profesor");
            SessionManagerAgentState smaState = (SessionManagerAgentState) handlerProfesor.getAg().getState();
            //crear IA
            InterfaceAgent.prepareIntance();
            IA_SynchCommInterfaceState estadoIA = new IA_SynchCommInterfaceState(handlerProfesor);
            IA_SynchCommInterface iface = new IA_SynchCommInterface("IA_Profesor_"+aliasS, estadoIA, miEstado.getVolatileGroupPassword());
            MessageGUI gui = new MessageGUI(iface, "Profesor: "+datos.getTema());
            estadoIA.setTheGUI(gui);
            iface.start();
            smaState.addInterfaceAgentHandler(this.getAgent().getAdmLocal().getHandlerByAid(iface.getAid()));
                        
            //mostrar OVA - enviar a SMA
            List<AgHandlerBESA> guis = ((SessionManagerAgentState)miEstado.getSessionManagerHandler().getAg().getState()).getInterfaceAgentsID();
            for(int i = 0 ;i < guis.size(); i++)
            {   
                if(guis.get(i).getAlias().contains(aliasS))
                {
                    Ayllu_Data_Message dataProfesor = new Ayllu_Data_Message(Ayllu_EventTypes.DISPLAYESPECIFIC, null,datos );
                    dataProfesor.setReceiverHandler(guis.get(i));
                    EventBESA eventProfesor = new EventBESA(SMA_ReceiveCARequestGuard.class.getName(),dataProfesor );
                    miEstado.getSessionManagerHandler().sendEvent(eventProfesor);
                }
            }
            
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(GuardaEvaluarInformeTema.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GuardaEvaluarInformeTema.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaEvaluarInformeTema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
