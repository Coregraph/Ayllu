/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.generarInformeGrupo;

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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Tema;
import servicios.generarInformeGrupo.aprendiz.DatosAprendizGIG;
import servicios.generarInformeGrupo.aprendiz.EstadoAprendizGIG;
import servicios.generarInformeGrupo.aprendiz.GuardaRecibirNotaInformeG;
import servicios.generarInformeGrupo.aprendiz.GuardaRecibirSolicitudInformeGrupo;
import servicios.generarInformeGrupo.secretario.DatosSecretarioGIG;
import servicios.generarInformeGrupo.secretario.EstadoSecretarioGIG;
import servicios.generarInformeGrupo.secretario.GuardaRecibirRespuestasGenerarInformesGrupo;

/**
 *
 * @author AylluAdmin
 */
public class CMA_GenerarInformeGrupo extends CommunityManagerAgent {

    public CMA_GenerarInformeGrupo(String alias, StateBESA state, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, passwd);
    }

    @Override
    public void cmaInitService() {
        try {
            System.out.println("INICIALIZANDO EL CMA_GenerarInformeGrupo");

            AgHandlerBESA myHandler = this.getAdmLocal().getHandlerByAid(this.getAid());

            EstadoGenerarInformeGrupo estadoAT = (EstadoGenerarInformeGrupo) this.getState();
            ArrayList<String> grupo = estadoAT.getMisDatos().getGrupos();
            ArrayList<String> roles = estadoAT.getMisDatos().getRolesPorGrupo();
            HashMap<Tema, Object> oaResumenPorGrupo = estadoAT.getMisDatos().getOAResumenPorTema();
            
            AgHandlerBESA agenteComunidadSecretario = null;            
            List<AgHandlerBESA> agenteComunidad = new ArrayList<AgHandlerBESA>();
            
            for (int i = 0; i < grupo.size(); i++) 
            {
                List<Class> guardas_CAGIT = new ArrayList<Class>();
                String rol;
                String aliasPersona = grupo.get(i);
                String aliasSMA = "SMA_" + aliasPersona;
                AgHandlerBESA handlerPersona = this.getAdmLocal().getHandlerByAlias(aliasSMA);
                rol = roles.get(i);
                if (i==0) //secretario
                {  
                    DatosSecretarioGIG datosSecretarioGIG = new DatosSecretarioGIG(null, null, null);
                    datosSecretarioGIG.setOAResumenPorTema(oaResumenPorGrupo);
                    datosSecretarioGIG.setTemaManejado(estadoAT.getMisDatos().getTema());
                    EstadoSecretarioGIG estadoSecretarioGIG = new EstadoSecretarioGIG(null, null, null);
                    estadoSecretarioGIG.setMisDatos(datosSecretarioGIG);

                    guardas_CAGIT.add(GuardaRecibirRespuestasGenerarInformesGrupo.class);

                    agenteComunidadSecretario = this.createCommunityAgent(rol, handlerPersona, guardas_CAGIT, estadoSecretarioGIG);
                }
                else
                {
                    EstadoAprendizGIG estadoAprendiz = new EstadoAprendizGIG(null, null, null);
                    DatosAprendizGIG datosAprendizGIT = new DatosAprendizGIG();
                    datosAprendizGIT.setTema(estadoAT.getMisDatos().getTema());
                    estadoAprendiz.setMisDatos(datosAprendizGIT);      
                    guardas_CAGIT.add(GuardaRecibirNotaInformeG.class);
                    guardas_CAGIT.add(GuardaRecibirSolicitudInformeGrupo.class);
                    if(agenteComunidadSecretario!=null)
                    {
                        datosAprendizGIT.setAliasSecretario(agenteComunidadSecretario.getAlias());
                    }
                    agenteComunidad.add(this.createCommunityAgent(rol, handlerPersona, guardas_CAGIT, estadoAprendiz));
                }
            }
            
            //crear agente manejador logico
              AgHandlerBESA handlerJS = this.getAdmLocal().getHandlerByAlias("CMA_JS");
              int opcion = 4;
              DatosCrearRecursos datosR = new DatosCrearRecursos(opcion);
              datosR.setNombreLog("Log_"+this.getAlias());
              Ayllu_Data_Message datosInst = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, myHandler, datosR);
              EventBESA evI = new EventBESA(GuardaCrearRecursos.class.getName(), datosInst);
              handlerJS.sendEvent(evI);  
              System.out.println("AYPUY: Agente lógico principal creado en CMA_GenerarInformeTema");
              Thread.sleep(50);
            
            for (Iterator<AgHandlerBESA> it = agenteComunidad.iterator(); it.hasNext();) {
                agenteComunidadSecretario = it.next();
                Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, myHandler, estadoAT);
                EventBESA eventoSolicitarInformeTema = new EventBESA(GuardaRecibirSolicitudInformeGrupo.class.getName(), data);
                agenteComunidadSecretario.sendEvent(eventoSolicitarInformeTema);
                Thread.sleep(50);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(CMA_GenerarInformeGrupo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(CMA_GenerarInformeGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
