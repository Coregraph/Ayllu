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
 * @author Yolima
 */
public class GuardaRespuestaPreguntasGrupo extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA event) {
        try {
            System.out.println("En GuardaRespuestaPreguntasGrupo");
            
            AgHandlerBESA handler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
            Ayllu_Data_Message theData = (Ayllu_Data_Message) event.getData();
            Estado_JigsawASHYI miEstado = (Estado_JigsawASHYI) this.getAgent().getState();
         

            if (miEstado.getNumeroGruposPreguntasGrupo() != miEstado.getNumeroGruposBase()) {
                miEstado.setNumeroGruposPreguntasGrupo(miEstado.getNumeroGruposPreguntasGrupo()+1);
            }
            if (miEstado.getNumeroGruposPreguntasGrupo() == miEstado.getNumeroGruposBase())
            {
                //seguir con el siguiente paso
                Estado_JigsawASHYI estadoJS = (Estado_JigsawASHYI) handler.getAg().getState();
                estadoJS.setEstadoEstrategia(Estado_JigsawASHYI.GENERAR_INFORME_GRUPO);
                Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.START_COOP, handler,estadoJS );
                EventBESA evento = new EventBESA(GuardaControlarJigsawASHYI.class.getName(), data);
                handler.sendEvent(evento);
            }

        } catch (ExceptionBESA excbesa) {
            ReportBESA.error(excbesa);
        }

    }
}
