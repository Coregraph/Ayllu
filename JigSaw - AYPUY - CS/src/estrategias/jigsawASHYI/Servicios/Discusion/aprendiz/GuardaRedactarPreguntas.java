/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.Servicios.Discusion.aprendiz;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communityagent.CommunityAgentState;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SMA_ReceiveCARequestGuard;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import estrategias.jigsawASHYI.Servicios.Discusion.DatosDiscusion;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yolima
 */
public class GuardaRedactarPreguntas extends GuardBESA {

    @Override
    public synchronized void funcExecGuard(EventBESA ebesa) {
        try {
                System.out.println("En GuardaRedactarPreguntas");
                
                Ayllu_Data_Message datosLlegan = (Ayllu_Data_Message) ebesa.getData();
                DatosDiscusion datos = (DatosDiscusion) datosLlegan.getMessage();
                DatosDiscusionAprendiz datosEnviar = new DatosDiscusionAprendiz(datos.getTema(), new ArrayList<String>(), datos.getAliasSecretario());
                AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
                                                
                //pedir redacción preguntas al estudiante
                
                // sma
                CommunityAgentState miEstado = (CommunityAgentState) this.getAgent().getState();
                AgHandlerBESA smaEstudiante = miEstado.getSessionManagerHandler();
                Ayllu_Data_Message dataProfesor = new Ayllu_Data_Message(Ayllu_EventTypes.DISPLAY, null,datosEnviar );
                EventBESA evento = new EventBESA(SMA_ReceiveCARequestGuard.class.getName(),dataProfesor );
                smaEstudiante.sendEvent(evento);
                
            }
        catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRedactarPreguntas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
}
