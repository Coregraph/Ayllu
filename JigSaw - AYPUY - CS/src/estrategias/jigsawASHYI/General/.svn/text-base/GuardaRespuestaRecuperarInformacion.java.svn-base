/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.General;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;

/**
 *
 * @author LG
 */
public class GuardaRespuestaRecuperarInformacion extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA event) {
        try {
             System.out.println("En GuardaRespuestaRecuperarInformacion");
            
            AgHandlerBESA handler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
            Ayllu_Data_Message theData = (Ayllu_Data_Message) event.getData();
            Estado_JigsawASHYI theMessage = (Estado_JigsawASHYI) theData.getMessage();
            ((Estado_JigsawASHYI) handler.getAg().getState()).getIdentificadoresAprendicesConTemaLeido().addAll(theMessage.getIdentificadoresAprendicesConTemaLeido());
            ((Estado_JigsawASHYI) handler.getAg().getState()).getOaPorEstudiante().putAll(theMessage.getOaPorEstudiante());
            ((Estado_JigsawASHYI) this.getAgent().getState()).setEstadoEstrategia(Estado_JigsawASHYI.DISCUSION_TEMATICA);
            Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.START_COOP, handler, ((Estado_JigsawASHYI) handler.getAg().getState()));
            EventBESA autoevento = new EventBESA(GuardaControlarJigsawASHYI.class.getName(), data);
            handler.sendEvent(autoevento);
        } catch (ExceptionBESA excbesa) {
            ReportBESA.error(excbesa);
        }

    }
}
