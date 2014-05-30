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
import java.util.logging.Level;
import java.util.logging.Logger;
import service.Profesor.DatosEvaluacionInforme;
import service.Profesor.GuardaEvaluarInformeTema;

/**
 *
 * @author AylluAdmin
 */
public class GuardaRecibirOAGeneracionInformesTemas extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        try {
            System.out.println("En Guarda GuardaRecibirOAGeneracionInformesTemas");
            
            AgHandlerBESA muHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
            Ayllu_Data_Message the_Data = (Ayllu_Data_Message) ebesa.getData();
            EstadoGenerarInformeTema darosllegan = (EstadoGenerarInformeTema) the_Data.getMessage();
            EstadoGenerarInformeTema miEstado = (EstadoGenerarInformeTema) this.getAgent().getState();
            miEstado.getMisDatos().setIdRecurso(darosllegan.getMisDatos().getIdRecurso());
          
            //Yolima
            //Enviar al profesor para evaluación
            AgHandlerBESA handlerJS = this.getAgent().getAdmLocal().getHandlerByAlias("CMA_JS"); 
            Estado_JigsawASHYI estadoSender = (Estado_JigsawASHYI)handlerJS.getAg().getState();            
            AgHandlerBESA handlerProfe = estadoSender.getCommunityAgentsHandlersbyRole("Profesor").get(0);
            DatosEvaluacionInforme datosEnviar = new DatosEvaluacionInforme(miEstado.getMisDatos().getTemaManejado().getNombre(), miEstado.getMisDatos().getIdRecurso(),the_Data.getSenderHandler().getAlias());

            Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.START_COOP, muHandler, datosEnviar);
            EventBESA event = new EventBESA(GuardaEvaluarInformeTema.class.getName(), data);
            handlerProfe.sendEvent(event);
            
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRecibirOAGeneracionInformesTemas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
