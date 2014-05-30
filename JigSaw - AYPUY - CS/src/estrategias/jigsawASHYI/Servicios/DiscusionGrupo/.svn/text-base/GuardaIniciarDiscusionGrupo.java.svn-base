/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.Servicios.DiscusionGrupo;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.aprendiz.GuardaEstudiarOAGrupo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yolima
 */
public class GuardaIniciarDiscusionGrupo extends GuardBESA {

    @Override
    public synchronized void funcExecGuard(EventBESA ebesa) {
        try {
                System.out.println("En GuardaIniciarDiscusionGrupo");

                AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());

                Estado_JigSaw_DiscusionGrupo miEstado = (Estado_JigSaw_DiscusionGrupo) this.getAgent().getState();
                //verificar alias
                List<AgHandlerBESA> handlerEstudiantes = miEstado.getCommunityAgentsHandlersbyRole("Participante");

                 for (int i = 0; i < handlerEstudiantes.size(); i++) {

                    Ayllu_Data_Message misDatos = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, myHandler, miEstado);
                    EventBESA evI = new EventBESA(GuardaEstudiarOAGrupo.class.getName(), misDatos);
                    handlerEstudiantes.get(i).sendEvent(evI);
                }
             
            }
        catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaIniciarDiscusionGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
}
