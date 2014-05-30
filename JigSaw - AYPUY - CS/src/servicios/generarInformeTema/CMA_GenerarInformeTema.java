/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.generarInformeTema;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communitymanageragent.CommunityManagerAgent;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import estrategias.jigsawASHYI.General.DatosCrearRecursos;
import estrategias.jigsawASHYI.General.GuardaCrearRecursos;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Tema;
import servicios.generarInformeTema.aprendiz.DatosAprendizGIT;
import servicios.generarInformeTema.aprendiz.EstadoAprendizGIT;
import servicios.generarInformeTema.aprendiz.GuardaRecibirNotaInforme;
import servicios.generarInformeTema.aprendiz.GuardaRecibirSolicitudInformeTema;
import servicios.generarInformeTema.secretario.DatosSecretarioGIT;
import servicios.generarInformeTema.secretario.EstadoSecretarioGIT;
import servicios.generarInformeTema.secretario.GuardaRecibirRespuestasGenerarInformesTema;

/**
 *
 * @author AylluAdmin
 */
public class CMA_GenerarInformeTema extends CommunityManagerAgent {
    
    public CMA_GenerarInformeTema(String alias, StateBESA state, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, passwd);
    }
    
    @Override
    public void cmaInitService() {
        try {
            System.out.println("INICIALIZANDO EL CMA_GenerarInformeTema");
            
            AgHandlerBESA myHandler = this.getAdmLocal().getHandlerByAid(this.getAid());
            
            EstadoGenerarInformeTema estadoAT = (EstadoGenerarInformeTema) this.getState();

            Tema temaManejado = estadoAT.getMisDatos().getTemaManejado();
            System.out.println("INICIALIZANDO EL CMA_GenerarInformeTema encargado de manejar el tema: " + temaManejado);
                        
            AgHandlerBESA agenteComunidadSecretario = null;
            List<AgHandlerBESA> agenteComunidadParticipantes = new ArrayList<AgHandlerBESA>();
            
            
            for (int i = 0; i < estadoAT.getMisDatos().getEstudiantes().size(); i++) 
            {
                try {

                    //TODO Aca se crea un secretario por grupo temático de forma arbitraria, esto se debe mejorar --> YA
                    String aliasPersona = estadoAT.getMisDatos().getEstudiantes().get(i);
                    String aliasSMA = "SMA_" + aliasPersona;
                    
                    AgHandlerBESA handlerPersona = this.getAdmLocal().getHandlerByAlias(aliasSMA);
                    List<Class> guardas_CAGIT = new ArrayList<Class>();
                     //Para cualquier caso se crea un aprendiz
                    DatosAprendizGIT datosAprendizGIT = new DatosAprendizGIT();
                    datosAprendizGIT.setGrupo(estadoAT.getMisDatos().getEstudiantes());
                    if (i == 0) {//secretario
                        DatosSecretarioGIT datosSecretarioGIT = new DatosSecretarioGIT(null, null, null);
                        datosSecretarioGIT.setGrupo(estadoAT.getMisDatos().getEstudiantes());
                        datosSecretarioGIT.setTemaManejado(temaManejado);
                        datosSecretarioGIT.setAliasr(estadoAT.getMisDatos().getAliasRecurso());
                        EstadoSecretarioGIT estadoSecretarioGIT = new EstadoSecretarioGIT(null, null, null);
                        estadoSecretarioGIT.setMisDatos(datosSecretarioGIT);
                                                
                        guardas_CAGIT.add(GuardaRecibirRespuestasGenerarInformesTema.class); 
                        agenteComunidadSecretario = this.createCommunityAgent(estadoAT.getMisDatos().getRolesEstudiantes().get(i), handlerPersona, guardas_CAGIT, estadoSecretarioGIT);
                        datosAprendizGIT.setAliasSecretario(agenteComunidadSecretario.getAlias());
                                                   
                    }
                    else//aprendiz
                    {
                        EstadoAprendizGIT estadoAprendizGIT = new EstadoAprendizGIT(null, null, null);
                        estadoAprendizGIT.setMisDatos(datosAprendizGIT);      
                        guardas_CAGIT.add(GuardaRecibirNotaInforme.class);
                        guardas_CAGIT.add(GuardaRecibirSolicitudInformeTema.class);
                        if(agenteComunidadSecretario!=null)
                        {
                            datosAprendizGIT.setAliasSecretario(agenteComunidadSecretario.getAlias());
                        }
                        agenteComunidadParticipantes.add(this.createCommunityAgent(estadoAT.getMisDatos().getRolesEstudiantes().get(i), handlerPersona, guardas_CAGIT, estadoAprendizGIT));
                    }

                    //estadoAT.addCommunityAgent();
                } catch (ExceptionBESA ex) {
                    Logger.getLogger(CMA_GenerarInformeTema.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //crear agente manejador logico
              AgHandlerBESA handlerJS = this.getAdmLocal().getHandlerByAlias("CMA_JS");
              int opcion = 3;
              DatosCrearRecursos datosR = new DatosCrearRecursos(opcion);
              datosR.setNombreLog("Log_"+this.getAlias());
              Ayllu_Data_Message datosInst = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, myHandler, datosR);
              EventBESA evI = new EventBESA(GuardaCrearRecursos.class.getName(), datosInst);
              handlerJS.sendEvent(evI);  
              System.out.println("AYPUY: Agente lógico principal creado en CMA_GenerarInformeTema");
              Thread.sleep(50);
              
            
            AgHandlerBESA agenteComunidadParticipante;
            for (int i = 0; i < agenteComunidadParticipantes.size(); i++) {
                try {
                    agenteComunidadParticipante = agenteComunidadParticipantes.get(i);
                    Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, myHandler, estadoAT);
                    EventBESA eventoSolicitarInformeTema = new EventBESA(GuardaRecibirSolicitudInformeTema.class.getName(), data);
                    agenteComunidadParticipante.sendEvent(eventoSolicitarInformeTema);
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CMA_GenerarInformeTema.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExceptionBESA ex) {
                    System.out.println("ERROR EN GuardaAsignarTema" + ex.toString());
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(CMA_GenerarInformeTema.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(CMA_GenerarInformeTema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
