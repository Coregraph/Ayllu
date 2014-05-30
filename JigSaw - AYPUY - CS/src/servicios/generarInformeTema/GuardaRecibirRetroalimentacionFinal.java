/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.generarInformeTema;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import estrategias.jigsawASHYI.General.Estado_JigsawASHYI;
import estrategias.jigsawASHYI.General.GuardaRespuestaOAGenerarInformeTema;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yolima
 */
public class GuardaRecibirRetroalimentacionFinal extends GuardBESA {

    @Override
    public synchronized void funcExecGuard(EventBESA ebesa) {
        try {
            System.out.println("En GuardaRecibirRetroalimentacionFinal");

            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
            EstadoGenerarInformeTema miestado = (EstadoGenerarInformeTema)myHandler.getAg().getState();
            if(miestado.getMisDatos().getEstConfirmanRetro()!=miestado.getMisDatos().getEstudiantes().size())
            {
                miestado.getMisDatos().setEstConfirmanRetro(miestado.getMisDatos().getEstConfirmanRetro()+1);
            }
            if(miestado.getMisDatos().getEstConfirmanRetro()==miestado.getMisDatos().getEstudiantes().size())
            {   
                //Control a JS
                AgHandlerBESA handler = this.getAgent().getAdmLocal().getHandlerByAlias("CMA_JS");
                Estado_JigsawASHYI estadoJS = new Estado_JigsawASHYI();
                Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, myHandler, estadoJS);
                EventBESA event = new EventBESA(GuardaRespuestaOAGenerarInformeTema.class.getName(), data);
                handler.sendEvent(event);
                
            }
            
        }
        catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRecibirRetroalimentacionFinal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
}
