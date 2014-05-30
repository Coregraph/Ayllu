/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.Servicios.DiscusionGrupo.Secretario;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import estrategias.jigsawASHYI.Servicios.Discusion.aprendiz.DatosDiscusionAprendiz;
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.GuardaRecibirPreguntasAprendicesGrupo;
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.aprendiz.DatosDiscusionAprendizGrupo;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yolima
 */
public class GuardaRecibirPreguntasGrupo extends GuardBESA {

    @Override
    public synchronized void funcExecGuard(EventBESA ebesa) {
        try {
                System.out.println("En GuardaRecibirPreguntasGrupo");

                AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
                Ayllu_Data_Message datosLlegan = (Ayllu_Data_Message) ebesa.getData();
                DatosDiscusionAprendiz datos = (DatosDiscusionAprendiz) datosLlegan.getMessage();
                Estado_JigSaw_DiscusionSecretarioGrupo miEstado = (Estado_JigSaw_DiscusionSecretarioGrupo) this.getAgent().getState();
                
                
                if(miEstado.getNumEstudiantes()-1 != miEstado.getNumEstudiantesPregListas())
                {
                    miEstado.aumentarNumEstudiantesPregListas();
                    for (int i = 0; i<datos.getPreguntas().size(); i++)
                        miEstado.getPreguntas().add(datos.getPreguntas().get(i));
                }
                if(miEstado.getNumEstudiantes()-1 == miEstado.getNumEstudiantesPregListas())
                {
                    AgHandlerBESA handlerCMA = miEstado.getCommunityManagerHandler();
                    DatosDiscusionAprendizGrupo datosEnviar = new DatosDiscusionAprendizGrupo(datos.getTema(),miEstado.getPreguntas(), this.getAgent().getAlias());
                    Ayllu_Data_Message misDatos = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, myHandler, datosEnviar);
                    EventBESA evento = new EventBESA(GuardaRecibirPreguntasAprendicesGrupo.class.getName(), misDatos);
                    handlerCMA.sendEvent(evento);
                }
                
            }
        catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRecibirPreguntasGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
}
