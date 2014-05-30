/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.Servicios.Discusion.aprendiz;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import estrategias.jigsawASHYI.Servicios.Discusion.Secretario.GuardaRecibirPreguntasAprendices;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yolima
 */
public class GuardaEnviarPreguntasSecretario extends GuardBESA {

    @Override
    public synchronized void funcExecGuard(EventBESA ebesa) {
        try {
                System.out.println("En GuardaEnviarPreguntasSecretario");

                AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
                Ayllu_Data_Message datosLlegan = (Ayllu_Data_Message) ebesa.getData();
                DatosDiscusionAprendiz datos = (DatosDiscusionAprendiz) datosLlegan.getMessage();
                
                AgHandlerBESA handlerSecretario = this.getAgent().getAdmLocal().getHandlerByAlias(datos.getAliasSecretario());
                Ayllu_Data_Message misDatos = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, myHandler, datos);
                EventBESA evento = new EventBESA(GuardaRecibirPreguntasAprendices.class.getName(), misDatos);
                handlerSecretario.sendEvent(evento);
           
            }
        catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaEnviarPreguntasSecretario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
}
