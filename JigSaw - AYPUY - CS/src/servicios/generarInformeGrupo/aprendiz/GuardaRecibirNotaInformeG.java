/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.generarInformeGrupo.aprendiz;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;

/**
 *
 * @author Yolima
 */
public class GuardaRecibirNotaInformeG extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
//        try {
//            System.out.println("En la guarda GuardaRecibirNotaInforme");
//            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
//            EstadoAprendizGIT miEstado = (EstadoAprendizGIT) this.getAgent().getState();
//            
//            Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
//            DatosEvaluacionInforme nota = (DatosEvaluacionInforme) theData.getMessage();
//            
//            //mostrar en chat notas y retroalimentacion
//            
//            System.out.println("nota: "+nota.getTema()+" \nretroalimentacón: "+nota.getOva());
//            
//            AgHandlerBESA handler = this.getAgent().getAdmLocal().getHandlerByAlias("CMA_JS");
//            EstadoManejadorJigsaw estadoJS = new EstadoManejadorJigsaw();
//            estadoJS.setOaPorTema(miEstado.getMisDatos().getOAResumenPorTema());
//            Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, handler, estadoJS);
//            EventBESA event = new EventBESA(GuardaRespuestaOAGenerarInformeTema.class.getName(), data);
//            handler.sendEvent(event);
//            
//        } catch (ExceptionBESA ex) {
//            Logger.getLogger(GuardaRecibirNotaInforme.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

   
}
