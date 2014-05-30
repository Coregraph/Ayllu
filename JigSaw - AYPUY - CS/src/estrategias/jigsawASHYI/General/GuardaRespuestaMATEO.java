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

/**
 *
 * @author LG
 */
public class GuardaRespuestaMATEO extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA event) {
        try {
            System.out.println("en GuardaRespuestaMATEO");
            AgHandlerBESA handler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
            Estado_JigsawASHYI miEstado = (Estado_JigsawASHYI) handler.getAg().getState();

            switch (miEstado.getEstadoEstartegia()) {
                case Estado_JigsawASHYI.INICIAL:

                    miEstado.setEstadoEstrategia(Estado_JigsawASHYI.INVOCAR_MATEO_GB);
                    break;

                case Estado_JigsawASHYI.INVOCAR_MATEO_GB:
                    miEstado.setEstadoEstrategia(Estado_JigsawASHYI.INVOCAR_MATEO_GT);
                    break;

                case Estado_JigsawASHYI.INVOCAR_MATEO_GT:

                    miEstado.setEstadoEstrategia(Estado_JigsawASHYI.ASIGNAR_TEMAS);
                    break;

            }
            EventBESA autoevento = new EventBESA(GuardaControlarJigsawASHYI.class.getName(), event.getData());
            handler.sendEvent(autoevento);
        } catch (ExceptionBESA excbesa) {
            ReportBESA.error(excbesa);
        }

    }
}
