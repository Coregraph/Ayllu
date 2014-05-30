/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.Interface.Agents;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communityagent.CommunityAgent;
import co.edu.javeriana.ayllu.agents.communityagent.CommunityAgentState;
import co.edu.javeriana.ayllu.agents.communitymanageragent.CommunityManagerAgent;
import co.edu.javeriana.ayllu.agents.interfaceagentagent.InterfaceAgent;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SMA_ReceiveIARequestGuard;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import co.edu.javeriana.ayllu.data.Ayllu_WallMessageData;
import estrategias.jigsawASHYI.General.Estado_JigsawASHYI;
import estrategias.jigsawASHYI.Servicios.Discusion.Secretario.GuardaRecibirPreguntasAprendices;
import estrategias.jigsawASHYI.Servicios.Discusion.Secretario.GuardaRecibirRetroalimentacionPreguntas;
import estrategias.jigsawASHYI.Servicios.Discusion.Secretario.GuardaRecibirRtaRetroalimentacion;
import estrategias.jigsawASHYI.Servicios.Discusion.aprendiz.DatosDiscusionAprendiz;
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.Estado_JigSaw_DiscusionGrupo;
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.GuardaRecibirRespuestasEstudiarGrupo;
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.Secretario.GuardaRecibirPreguntasGrupo;
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.Secretario.GuardaRecibirRetroalimentacionPreguntasGrupo;
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.Secretario.GuardaRecibirRtaRetroalimentacionGrupo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Tema;
import service.Profesor.DatosInstrucciones;
import service.Profesor.DatosRetroNota;
import service.Profesor.DatosRetroalimentacion;
import service.Profesor.DatosRetroalimentacionGrupo;
import service.Profesor.EstadoProfesor;
import service.Profesor.GuardaRecibirSMEvaluarInformeGrupo;
import service.Profesor.GuardaRecibirSMEvaluarInformeTema;
import servicios.asignarTema.DatosAsignarTema;
import servicios.asignarTema.GuardaRecibirInstrucciones;
import servicios.asignarTema.GuardaRecibirInstruccionesFinal;
import servicios.generarInformeGrupo.GuardaRecibirRetroalimentacionFinalG;
import servicios.generarInformeTema.GuardaRecibirRetroalimentacionFinal;

/**
 * A basic infterface Agent with a basic chat interface
 * @author Yolima
 */
public class IA_SynchCommInterface extends InterfaceAgent implements Serializable{
    String name;
    public IA_SynchCommInterface(String alias, StateBESA state, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, passwd);
        name = alias;
    }

    public void enviarRetroalimentacion(String grade, String messageToSend, String aliasCma) {
        try {
            DatosRetroNota datosEnviar = new DatosRetroNota(grade, messageToSend, aliasCma);
            AgHandlerBESA myHandler = this.getAdmLocal().getHandlerByAlias(this.getAlias()); 
            Ayllu_Data_Message theData = new Ayllu_Data_Message(Ayllu_EventTypes.DISPLAY,myHandler, datosEnviar );
            if(aliasCma.contains("CMA_GIG"))
            {
                theData.setGuardToActivate(GuardaRecibirSMEvaluarInformeGrupo.class);
            }
            else
            {
                theData.setGuardToActivate(GuardaRecibirSMEvaluarInformeTema.class);
            }
            //encontrar handler profesor
            AgHandlerBESA handlerJS = this.getAdmLocal().getHandlerByAlias("CMA_JS"); 
            Estado_JigsawASHYI estadoSender = (Estado_JigsawASHYI)handlerJS.getAg().getState();            
            AgHandlerBESA handlerProfe = estadoSender.getCommunityAgentsHandlersbyRole("Profesor").get(0);
            theData.setReceiverHandler(handlerProfe);
            
            AgHandlerBESA smahandler = ((IA_SynchCommInterfaceState) state).getSessionManagerHandler();
            EventBESA event = new EventBESA(SMA_ReceiveIARequestGuard.class.getName(), theData);
            smahandler.sendEvent(event);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(IA_SynchCommInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarPreguntas(String pregunta1, String pregunta2, String aliasSecretario) {
        try {
            ArrayList<String> preguntas = new ArrayList<String>();
            preguntas.add(pregunta1);
            preguntas.add(pregunta2);
            DatosDiscusionAprendiz datosEnviar = new DatosDiscusionAprendiz(new Tema(), preguntas, aliasSecretario);
            AgHandlerBESA myHandler = this.getAdmLocal().getHandlerByAlias(this.getAlias()); 
            //datos a enviar
            Ayllu_Data_Message theData = new Ayllu_Data_Message(Ayllu_EventTypes.DISPLAY,myHandler, datosEnviar );            
            theData.setGuardToActivate(GuardaRecibirPreguntasAprendices.class);
            //encontrar handler profesor
            AgHandlerBESA handlerS = this.getAdmLocal().getHandlerByAlias(aliasSecretario);             
            theData.setReceiverHandler(handlerS);
            
            AgHandlerBESA smahandler = ((IA_SynchCommInterfaceState) state).getSessionManagerHandler();
            EventBESA event = new EventBESA(SMA_ReceiveIARequestGuard.class.getName(), theData);
            smahandler.sendEvent(event);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(IA_SynchCommInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarPreguntasGrupo(String pregunta1, String pregunta2, String aliasSecretario) {
        try {
            System.out.println("En enviar preguntas a secretario");
            ArrayList<String> preguntas = new ArrayList<String>();
            preguntas.add(pregunta1);
            preguntas.add(pregunta2);
            DatosDiscusionAprendiz datosEnviar = new DatosDiscusionAprendiz(new Tema(), preguntas, aliasSecretario);
            AgHandlerBESA myHandler = this.getAdmLocal().getHandlerByAlias(this.getAlias()); 
            //datos a enviar
            Ayllu_Data_Message theData = new Ayllu_Data_Message(Ayllu_EventTypes.DISPLAY,myHandler, datosEnviar );            
            theData.setGuardToActivate(GuardaRecibirPreguntasGrupo.class);
            //encontrar handler profesor
            AgHandlerBESA handlerS = this.getAdmLocal().getHandlerByAlias(aliasSecretario);             
            theData.setReceiverHandler(handlerS);
            
            AgHandlerBESA smahandler = ((IA_SynchCommInterfaceState) state).getSessionManagerHandler();
            EventBESA event = new EventBESA(SMA_ReceiveIARequestGuard.class.getName(), theData);
            smahandler.sendEvent(event);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(IA_SynchCommInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarRetroalimentacionPreguntas(String retroalimentacion, ArrayList<String> preguntas, Tema tema, String aliasSecretario) {
        try {
            DatosRetroalimentacion datosEnviar = new DatosRetroalimentacion(tema, preguntas);
            datosEnviar.getRetroalimentacionPreguntas().add(retroalimentacion);
            AgHandlerBESA myHandler = this.getAdmLocal().getHandlerByAlias(this.getAlias()); 
            Ayllu_Data_Message theData = new Ayllu_Data_Message(Ayllu_EventTypes.DISPLAY,myHandler, datosEnviar );
            
            theData.setGuardToActivate(GuardaRecibirRetroalimentacionPreguntas.class);
            //encontrar handler secretario
            AgHandlerBESA handlerS = this.getAdmLocal().getHandlerByAlias(aliasSecretario); 
            
            theData.setReceiverHandler(handlerS);
            
            AgHandlerBESA smahandler = ((IA_SynchCommInterfaceState) state).getSessionManagerHandler();
            EventBESA event = new EventBESA(SMA_ReceiveIARequestGuard.class.getName(), theData);
            smahandler.sendEvent(event);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(IA_SynchCommInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarRtaRetroalimentacionPreg(String aliasSecretario) {
        try {
            
            //volver el control a JS
            
            AgHandlerBESA myHandler = this.getAdmLocal().getHandlerByAlias(this.getAlias()); 
                        
            DatosRetroalimentacion datosEnviar = new DatosRetroalimentacion(new Tema(), new ArrayList<String>());
            Ayllu_Data_Message theData = new Ayllu_Data_Message(Ayllu_EventTypes.DISPLAY,myHandler, datosEnviar );
            
            //encontrar handler secretario
            AgHandlerBESA handlerS = this.getAdmLocal().getHandlerByAlias(aliasSecretario); 
            
            theData.setGuardToActivate(GuardaRecibirRtaRetroalimentacion.class);
            
            theData.setReceiverHandler(handlerS);
            
            AgHandlerBESA smahandler = ((IA_SynchCommInterfaceState) state).getSessionManagerHandler();
            EventBESA event = new EventBESA(SMA_ReceiveIARequestGuard.class.getName(), theData);
            smahandler.sendEvent(event);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(IA_SynchCommInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarRtaRetroalimentacionPregGrupo(String aliasSecretario) {
        try {
            
            //volver el control a JS
            
            AgHandlerBESA myHandler = this.getAdmLocal().getHandlerByAlias(this.getAlias()); 
                        
            DatosRetroalimentacionGrupo datosEnviar = new DatosRetroalimentacionGrupo(new Tema(), new ArrayList<String>());
            Ayllu_Data_Message theData = new Ayllu_Data_Message(Ayllu_EventTypes.DISPLAY,myHandler, datosEnviar );
            
            //encontrar handler secretario
            AgHandlerBESA handlerS = this.getAdmLocal().getHandlerByAlias(aliasSecretario); 
            
            theData.setGuardToActivate(GuardaRecibirRtaRetroalimentacionGrupo.class);
            
            theData.setReceiverHandler(handlerS);
            
            AgHandlerBESA smahandler = ((IA_SynchCommInterfaceState) state).getSessionManagerHandler();
            EventBESA event = new EventBESA(SMA_ReceiveIARequestGuard.class.getName(), theData);
            smahandler.sendEvent(event);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(IA_SynchCommInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarRetroalimentacionPreguntasGrupo(String retroalimentacion, ArrayList<String> preguntas, Tema tema,String aliasSecretario) {
        try {
            DatosRetroalimentacionGrupo datosEnviar = new DatosRetroalimentacionGrupo(tema, preguntas);
            datosEnviar.getRetroalimentacionPreguntas().add(retroalimentacion);
            AgHandlerBESA myHandler = this.getAdmLocal().getHandlerByAlias(this.getAlias()); 
            Ayllu_Data_Message theData = new Ayllu_Data_Message(Ayllu_EventTypes.DISPLAY,myHandler, datosEnviar );
            
            theData.setGuardToActivate(GuardaRecibirRetroalimentacionPreguntasGrupo.class);
            //encontrar handler secretario
            AgHandlerBESA handlerS = this.getAdmLocal().getHandlerByAlias(aliasSecretario); 
            
            theData.setReceiverHandler(handlerS);
            
            AgHandlerBESA smahandler = ((IA_SynchCommInterfaceState) state).getSessionManagerHandler();
            EventBESA event = new EventBESA(SMA_ReceiveIARequestGuard.class.getName(), theData);
            smahandler.sendEvent(event);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(IA_SynchCommInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarEstudioOa(String aliasCMA) {
        try {
            Estado_JigSaw_DiscusionGrupo datosEnviar = new Estado_JigSaw_DiscusionGrupo(null,0);
            AgHandlerBESA myHandler = this.getAdmLocal().getHandlerByAlias(this.getAlias()); 
            Ayllu_Data_Message theData = new Ayllu_Data_Message(Ayllu_EventTypes.DISPLAY,myHandler, datosEnviar );
            
            theData.setGuardToActivate(GuardaRecibirRespuestasEstudiarGrupo.class);
            //encontrar handler secretario
            AgHandlerBESA handlerS = this.getAdmLocal().getHandlerByAlias(aliasCMA); 
            
            theData.setReceiverHandler(handlerS);
            
            AgHandlerBESA smahandler = ((IA_SynchCommInterfaceState) state).getSessionManagerHandler();
            EventBESA event = new EventBESA(SMA_ReceiveIARequestGuard.class.getName(), theData);
            smahandler.sendEvent(event);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(IA_SynchCommInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarInstrucciones(String instrucciones, String aliasP)
    {
         try {
            DatosInstrucciones datosEnviar = new DatosInstrucciones(instrucciones);
            datosEnviar.setAliasP(aliasP);
            AgHandlerBESA myHandler = this.getAdmLocal().getHandlerByAlias(this.getAlias()); 
            Ayllu_Data_Message theData = new Ayllu_Data_Message(Ayllu_EventTypes.DISPLAY,myHandler, datosEnviar );
            
            theData.setGuardToActivate(GuardaRecibirInstrucciones.class);
            
            AgHandlerBESA handlerP = this.getAdmLocal().getHandlerByAlias(aliasP); 
            EstadoProfesor estadoP = (EstadoProfesor) handlerP.getAg().getState();
            
            //encontrar handler cma AT
            AgHandlerBESA handlerCAT = this.getAdmLocal().getHandlerByAlias("CMA_AT"); 
            theData.setReceiverHandler(handlerCAT);
            AgHandlerBESA smahandler = ((IA_SynchCommInterfaceState) state).getSessionManagerHandler();
            EventBESA event = new EventBESA(SMA_ReceiveIARequestGuard.class.getName(), theData);
            smahandler.sendEvent(event);
            
        } catch (ExceptionBESA ex) {
            Logger.getLogger(IA_SynchCommInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarRtaInstrucciones(String aliasCMA)
    {
         try {
            DatosAsignarTema datosEnviar = new DatosAsignarTema(null, null, null);
            AgHandlerBESA myHandler = this.getAdmLocal().getHandlerByAlias(this.getAlias()); 
            //Ayllu_Data_Message theData = new Ayllu_Data_Message(Ayllu_EventTypes.DISPLAY,myHandler, datosEnviar );
            Ayllu_Data_Message theData = new Ayllu_Data_Message(Ayllu_EventTypes.DISPLAY,null, datosEnviar );
            
            theData.setGuardToActivate(GuardaRecibirInstruccionesFinal.class);
            
            AgHandlerBESA handlerCMA = this.getAdmLocal().getHandlerByAlias(aliasCMA); 
            theData.setReceiverHandler(handlerCMA);
            AgHandlerBESA smahandler = ((IA_SynchCommInterfaceState) state).getSessionManagerHandler();
            EventBESA event = new EventBESA(SMA_ReceiveIARequestGuard.class.getName(), theData);
            smahandler.sendEvent(event);
            
        } catch (ExceptionBESA ex) {
            Logger.getLogger(IA_SynchCommInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void enviarRtaRetroOA(String aliasCMA)
    {
         try {
             if(aliasCMA.contains("CMA_GIT"))
            {
                DatosAsignarTema datosEnviar = new DatosAsignarTema(null, null, null);
                AgHandlerBESA myHandler = this.getAdmLocal().getHandlerByAlias(this.getAlias()); 
                Ayllu_Data_Message theData = new Ayllu_Data_Message(Ayllu_EventTypes.DISPLAY,myHandler, datosEnviar );

                theData.setGuardToActivate(GuardaRecibirRetroalimentacionFinal.class);

                AgHandlerBESA handlerCMA = this.getAdmLocal().getHandlerByAlias(aliasCMA); 
                theData.setReceiverHandler(handlerCMA);
                AgHandlerBESA smahandler = ((IA_SynchCommInterfaceState) state).getSessionManagerHandler();
                EventBESA event = new EventBESA(SMA_ReceiveIARequestGuard.class.getName(), theData);
                smahandler.sendEvent(event);
            }
             else
             {
                 DatosAsignarTema datosEnviar = new DatosAsignarTema(null, null, null);
                AgHandlerBESA myHandler = this.getAdmLocal().getHandlerByAlias(this.getAlias()); 
                Ayllu_Data_Message theData = new Ayllu_Data_Message(Ayllu_EventTypes.DISPLAY,myHandler, datosEnviar );

                theData.setGuardToActivate(GuardaRecibirRetroalimentacionFinalG.class);

                AgHandlerBESA handlerCMA = this.getAdmLocal().getHandlerByAlias(aliasCMA); 
                theData.setReceiverHandler(handlerCMA);
                AgHandlerBESA smahandler = ((IA_SynchCommInterfaceState) state).getSessionManagerHandler();
                EventBESA event = new EventBESA(SMA_ReceiveIARequestGuard.class.getName(), theData);
                smahandler.sendEvent(event);
             }
            
        } catch (ExceptionBESA ex) {
            Logger.getLogger(IA_SynchCommInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendWallMessage(String alias, String messageToSend) {
       String cmaId="";
       try {
            AgHandlerBESA handAgent = IA_SynchCommInterface.this.getAdmLocal().getHandlerByAlias(alias);
            AgentBESA ag = handAgent.getAg();
            if(ag instanceof CommunityManagerAgent){
                 cmaId= handAgent.getAgId();
            }else if(ag instanceof CommunityAgent){
                AgentBESA ag2 = ((CommunityAgentState)ag.getState()).getCommunityManagerHandler().getAg();
                cmaId = ag2.getAid();
            }
            
            Ayllu_WallMessageData theData = new Ayllu_WallMessageData(cmaId, alias, System.currentTimeMillis(), name, messageToSend);
            AgHandlerBESA smahandler = ((IA_SynchCommInterfaceState) state).getSessionManagerHandler();
            EventBESA event = new EventBESA(SMA_ReceiveIARequestGuard.class.getName(), theData);
            smahandler.sendEvent(event);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(IA_SynchCommInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
