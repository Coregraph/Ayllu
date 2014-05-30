/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guardasagentesesion;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
//import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SMA_CreateCooperativeServiceGuard;
import co.edu.javeriana.ayllu.data.Ayllu_Agent_Creation_Message;

/**
 *
 * @author usuario
 */
public class GuardaSubscripcion extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA event) {
        try {
            //AgHandlerBESA fact = this.getAgent().getAdmLocal().getHandlerByAlias("FACTORY");
            Ayllu_Agent_Creation_Message ag = new Ayllu_Agent_Creation_Message("CMA_JS", null, null);
            //EstadoManejadorJigsaw estado = new EstadoManejadorJigsaw();
            //ag.setMessage(estado);
            EventBESA ev = new EventBESA(SMA_CreateCooperativeServiceGuard.class.getName(), ag);
            this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid()).sendEvent(ev);
        } catch (ExceptionBESA eb) {
            ReportBESA.error(eb);
        }
    }
    /*
     * try {
     * //----------------------------------------------------------------// //
     * SYNCHRONIC SERVICE //
     * //----------------------------------------------------------------//
     * //Busca en el directorio el agente que presta el servicio AdmBESA adm =
     * AdmBESA.getInstance();
     * //adm.lookupSPServiceInDirectory(InterfazAYLLUAgent.SERVICEONDIRECTORY);
     * String spAgId =
     * adm.searchAidByAlias(PaginasAmarillas.getMiIAServiceProviderAlias());
     * AgHandlerBESA agH = adm.getHandlerByAid(spAgId); //Crea el data de
     * suscripcion ServiceProviderDataSuscribe spDataSuscribe = new
     * ServiceProviderDataSuscribe( InterfazGuardReply.class.getName(),
     * ServiceProviderBESA.SYNCHRONIC_SERVICE, "SERVICIO_INTERFAZ",
     * MensajeReply.class.getName()); //Crea el evento a enviar EventBESA evSP =
     * new EventBESA(GuardServiceProviderSuscribe.class.getName(),
     * spDataSuscribe); evSP.setSenderAgId(this.getAgent().getAid()); //Envía el
     * evento agH.sendEvent(evSP);
     *
     *
     * spDataSuscribe = new ServiceProviderDataSuscribe(
     * GuardCommMundoAgentes.class.getName(),
     * ServiceProviderBESA.ASYNCHRONIC_SERVICE, "SERVICIO_INTERFAZ",
     * MensajeAsincrono.class.getName()); evSP = new
     * EventBESA(GuardServiceProviderSuscribe.class.getName(), spDataSuscribe);
     * evSP.setSenderAgId(this.getAgent().getAid()); //Envía el evento
     * agH.sendEvent(evSP);
     *
     * } catch (ExceptionBESA e) { ReportBESA.error(e); }
     */
}
