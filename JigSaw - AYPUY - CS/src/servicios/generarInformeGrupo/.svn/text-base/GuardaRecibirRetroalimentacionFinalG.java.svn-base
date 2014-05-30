/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.generarInformeGrupo;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import estrategias.jigsawASHYI.General.Estado_JigsawASHYI;
import estrategias.jigsawASHYI.General.GuardaRespuestaOAGenerarInformeGrupo;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yolima
 */
public class GuardaRecibirRetroalimentacionFinalG extends GuardBESA {

    @Override
    public synchronized void funcExecGuard(EventBESA ebesa) {
        try {
            System.out.println("En GuardaRecibirRetroalimentacionFinalG");

            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
            EstadoGenerarInformeGrupo miestado = (EstadoGenerarInformeGrupo)myHandler.getAg().getState();
            
            if(miestado.getMisDatos().getNumeroRetroalimentacionConfirmado()!=miestado.getMisDatos().getGrupos().size())
            {
                miestado.getMisDatos().setNumeroRetroalimentacionConfirmado(miestado.getMisDatos().getNumeroRetroalimentacionConfirmado()+1);
            }
            if(miestado.getMisDatos().getNumeroRetroalimentacionConfirmado()==miestado.getMisDatos().getGrupos().size())
            {   
                //Control a JS
                AgHandlerBESA handler = this.getAgent().getAdmLocal().getHandlerByAlias("CMA_JS");
                Estado_JigsawASHYI estadoJS = new Estado_JigsawASHYI();
                Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, myHandler, estadoJS);
                EventBESA event = new EventBESA(GuardaRespuestaOAGenerarInformeGrupo.class.getName(), data);
                handler.sendEvent(event);
                
            }
            
        }
        catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRecibirRetroalimentacionFinalG.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
}
