/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.Servicios.DiscusionGrupo;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import Util.Lock;
import co.edu.javeriana.ayllu.agents.communitymanageragent.CommunityManagerAgent;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import estrategias.jigsawASHYI.Servicios.Discusion.DatosDiscusion;
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.Secretario.Estado_JigSaw_DiscusionSecretarioGrupo;
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.Secretario.GuardaRecibirPreguntasGrupo;
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.Secretario.GuardaRecibirRetroalimentacionPreguntasGrupo;
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.Secretario.GuardaRecibirRtaRetroalimentacionGrupo;
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.aprendiz.GuardaEnviarPreguntasSecretarioGrupo;
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.aprendiz.GuardaRedactarPreguntasGrupo;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yolima
 */
public class CMA_JigSaw_DiscusionGrupo extends CommunityManagerAgent{

    public CMA_JigSaw_DiscusionGrupo(String alias, StateBESA state, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, passwd);
    }
     @Override
    public void cmaInitService() {

        System.out.println("INICIALIZANDO EL CMA_JigSaw_DiscusionGrupo");

        Estado_JigSaw_DiscusionGrupo estadoAT = (Estado_JigSaw_DiscusionGrupo) this.getState();

        try {
            DatosDiscusion datos = estadoAT.getDatos();
            List<Class> guardas = new ArrayList<Class>();
            AgHandlerBESA myHandler = this.getAdmLocal().getHandlerByAid(this.getAid());
                                   
            AgHandlerBESA agenteComunidadParticipante = null;
            CommunityManagerAgent agenteCMA = (CommunityManagerAgent) myHandler.getAg();
            
            for (int i = 0;i < datos.getGrupo().size(); i++)
            {
                 String idPersona = datos.getGrupo().get(i);
                 String aliasSMA = "SMA_" + idPersona;
                 AgHandlerBESA handlerPersona = agenteCMA.getAdmLocal().getHandlerByAlias(aliasSMA);
                 
                 guardas.add(GuardaEnviarPreguntasSecretarioGrupo.class);
                 guardas.add(GuardaRedactarPreguntasGrupo.class);
                 guardas.add(GuardaRecibirPreguntasGrupo.class);
                 guardas.add(GuardaRecibirRetroalimentacionPreguntasGrupo.class);
                 guardas.add(GuardaRecibirRtaRetroalimentacionGrupo.class);
                 
                // if(datos.getGrupoRoles().get(i).equals("Secretario"))
                 if(i==0)
                 {
                    datos.setAliasSecretario(datos.getGrupo().get(i));
                    
                     Estado_JigSaw_DiscusionSecretarioGrupo estadoSec = new Estado_JigSaw_DiscusionSecretarioGrupo(null,null,null);
                     estadoSec.setNumEstudiantes(datos.getGrupo().size());
                     Lock.getInstance().lock(); 
                     agenteComunidadParticipante = agenteCMA.createCommunityAgent(datos.getGrupoRoles().get(i), handlerPersona, guardas,estadoSec );
                     //estadoAT.getCommunityAgentsHandlers().add(new AgentRole(datos.getGrupoRoles().get(i), agenteComunidadParticipante));
                     datos.setAliasSecretario(agenteComunidadParticipante.getAlias());
                     Lock.getInstance().unlock();   
                 }
                 else// aprendiz
                 {                    
                    Lock.getInstance().lock(); 
                    agenteComunidadParticipante = agenteCMA.createCommunityAgent(datos.getGrupoRoles().get(i), handlerPersona, guardas);
                    //estadoAT.getCommunityAgentsHandlers().add(new AgentRole(datos.getGrupoRoles().get(i), agenteComunidadParticipante));
                    Lock.getInstance().unlock();
                 }
            }
            
            //List<AgHandlerBESA> handlerEstudiantes = estadoAT.getCommunityAgentsHandlersbyRole("Participante");
            List<AgHandlerBESA> handlerEstudiantes = estadoAT.getCommunityAgentsHandlersbyRole(datos.getGrupoRoles().get(1));            

             for (int i = 0; i < handlerEstudiantes.size(); i++) {

                Ayllu_Data_Message misDatos = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, myHandler, estadoAT.getDatos());
                EventBESA evI = new EventBESA(GuardaRedactarPreguntasGrupo.class.getName(), misDatos);
                handlerEstudiantes.get(i).sendEvent(evI);
            }
         
           
        } catch (InterruptedException ex) {
            Logger.getLogger(CMA_JigSaw_DiscusionGrupo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(CMA_JigSaw_DiscusionGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
