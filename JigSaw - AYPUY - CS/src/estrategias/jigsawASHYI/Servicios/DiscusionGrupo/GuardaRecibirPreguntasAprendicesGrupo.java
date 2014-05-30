/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.Servicios.DiscusionGrupo;

import service.Profesor.*;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.interfaceagentagent.InterfaceAgent;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SMA_ReceiveCARequestGuard;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SessionManagerAgentState;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import estrategias.jigsawASHYI.Interface.Agents.IA_SynchCommInterface;
import estrategias.jigsawASHYI.Interface.Agents.IA_SynchCommInterfaceState;
import estrategias.jigsawASHYI.Interface.MessageGUI;
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.aprendiz.DatosDiscusionAprendizGrupo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yolima
 */
public class GuardaRecibirPreguntasAprendicesGrupo extends GuardBESA {

    @Override
    public synchronized void funcExecGuard(EventBESA ebesa) {
        try {
                System.out.println("En GuardaRecibirPreguntasAprendicesGrupo");

                AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
                Ayllu_Data_Message datosLlegan = (Ayllu_Data_Message) ebesa.getData();
                DatosDiscusionAprendizGrupo datos = (DatosDiscusionAprendizGrupo) datosLlegan.getMessage();
                Estado_JigSaw_DiscusionGrupo miEstado = (Estado_JigSaw_DiscusionGrupo) this.getAgent().getState();
                
                AgHandlerBESA handlerProfesor = this.getAgent().getAdmLocal().getHandlerByAlias("SMA_Profesor");
                SessionManagerAgentState smaState = (SessionManagerAgentState) handlerProfesor.getAg().getState();
                miEstado.getDatos().setPreguntas(datos.getPreguntas());
                //crear IA
                InterfaceAgent.prepareIntance();
                IA_SynchCommInterfaceState estadoIA = new IA_SynchCommInterfaceState(handlerProfesor);
                IA_SynchCommInterface iface = new IA_SynchCommInterface("IA_Profesor_"+datos.getAliasSecretario(), estadoIA, miEstado.getVolatileGroupPassword());
                MessageGUI gui = new MessageGUI(iface, "Profesor: "+datos.getTema());
                estadoIA.setTheGUI(gui);
                iface.start();
                smaState.addInterfaceAgentHandler(this.getAgent().getAdmLocal().getHandlerByAid(iface.getAid()));
                
                //Pedir retroalimetacion a profesor GUI por cada 
                DatosRetroalimentacionGrupo datosEnviar = new DatosRetroalimentacionGrupo(datos.getTema(),miEstado.getDatos().getPreguntas() );
                datosEnviar.setAliasSecretario(datos.getAliasSecretario());
                //enviar preguntas a IA específico del grupo respectivo
                Ayllu_Data_Message dataProfesor = new Ayllu_Data_Message(Ayllu_EventTypes.DISPLAYESPECIFIC, myHandler,datosEnviar );
                List<AgHandlerBESA> guis = smaState.getInterfaceAgentsID();
                for(int i = 0;i < guis.size();i++)
                {
                    if (guis.get(i).getAlias().contains(datos.getAliasSecretario()))
                    {
                        dataProfesor.setReceiverHandler(guis.get(i));
                        EventBESA eventProfesor = new EventBESA(SMA_ReceiveCARequestGuard.class.getName(),dataProfesor );
                        handlerProfesor.sendEvent(eventProfesor);
                    }
                }
                
            }
        catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRecibirPreguntasAprendicesGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
}
