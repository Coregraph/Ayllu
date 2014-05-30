/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guardasagentesesion;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.Social.ServiceProvider.agent.GuardServiceProviderRequest;
import BESA.Kernel.Social.ServiceProvider.agent.ServiceProviderDataRequest;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import paginasamarillas.PaginasAmarillas;
import service.MensajeRequest;

/**
 *
 * @author usuario
 */
public class InterfazGuardRequest extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA event) {
        MensajeRequest msj = new MensajeRequest("Prueba", MensajeRequest.EnumReq.MENSAJE);
        try {
            //String spID = this.getAgent().getAdmLocal().lookupSPServiceInDirectory(InterfazAYLLUAgent.SERVICEONDIRECTORY);
            String spID = this.getAgent().getAdmLocal().searchAidByAlias(PaginasAmarillas.getMiIAServiceProviderAlias());
            AgHandlerBESA agH = AdmBESA.getInstance().getHandlerByAid(spID);
            ServiceProviderDataRequest spDataRequest = new ServiceProviderDataRequest(this.getAgent().getAid(), "SERVICIO_INTERFAZ", msj);
            EventBESA ev = new EventBESA(GuardServiceProviderRequest.class.getName(), spDataRequest);
            agH.sendEvent(ev);
        }catch(ExceptionBESA eb){
            ReportBESA.error(eb);
        }
    }
}
