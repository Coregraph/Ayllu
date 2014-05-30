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
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.aprendiz.GuardaRedactarPreguntasGrupo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yolima
 */
public class GuardaRecibirRespuestasEstudiarGrupo extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA event) {
        System.out.println("En GuardaIniciarDiscusionGrupo");
        try {
            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
            
            Estado_JigSaw_DiscusionGrupo miEstado = (Estado_JigSaw_DiscusionGrupo) this.getAgent().getState();
            
            Ayllu_Data_Message theData = (Ayllu_Data_Message) event.getData();
            Estado_JigSaw_DiscusionGrupo datos = (Estado_JigSaw_DiscusionGrupo) theData.getMessage();
        
            //Se finaliza el servicioa, debido a que todos los estudiantes han leido el tema
            if(miEstado.getDatosEstudio().getNumEstudiantes()==miEstado.getDatosEstudio().getNumEstudiantesEstudio())
            {
                List<AgHandlerBESA> handlerEstudiantes = miEstado.getCommunityAgentsHandlersbyRole("Participante");

                for (int i = 0; i < handlerEstudiantes.size(); i++) {

                   Ayllu_Data_Message misDatos = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, myHandler, miEstado);
                   EventBESA evento = new EventBESA(GuardaRedactarPreguntasGrupo.class.getName(), misDatos);
                   handlerEstudiantes.get(i).sendEvent(evento);
                }
            }
            else
            {
                datos.getDatosEstudio().setNumEstudiantesEstudio(datos.getDatosEstudio().getNumEstudiantesEstudio()+1);
            }
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRecibirRespuestasEstudiarGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
}
