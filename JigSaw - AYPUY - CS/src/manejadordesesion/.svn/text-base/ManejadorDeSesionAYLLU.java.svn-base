/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manejadordesesion;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.Social.ServiceProvider.agent.GuardServiceProviderSuscribe;
import BESA.Kernel.Social.ServiceProvider.agent.ServiceProviderBESA;
import BESA.Kernel.Social.ServiceProvider.agent.ServiceProviderDataSuscribe;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SessionManagerAgent;
import guardasagentesesion.GuardCommMundoAgentes;
import guardasagentesesion.InterfazGuardReply;
import mensajesagentesesion.MensajeAsincrono;
import mensajesagentesesion.MensajeReply;
import paginasamarillas.PaginasAmarillas;

/**
 *
 * @author usuario
 */
public class ManejadorDeSesionAYLLU extends SessionManagerAgent{

    public ManejadorDeSesionAYLLU(String alias, StateBESA state, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, passwd);
    }

    @Override
    public void setupAgent() {
        super.setupAgent();
        try {
            //----------------------------------------------------------------//
            // SYNCHRONIC SERVICE                                             //
            //----------------------------------------------------------------//
            //Busca en el directorio el agente que presta el servicio
            AdmBESA adm = AdmBESA.getInstance();
            //adm.lookupSPServiceInDirectory(InterfazAYLLUAgent.SERVICEONDIRECTORY);
            String spAgId = adm.searchAidByAlias(PaginasAmarillas.getMiIAServiceProviderAlias());
            AgHandlerBESA agH = adm.getHandlerByAid(spAgId);
            //Crea el data de suscripcion
            ServiceProviderDataSuscribe spDataSuscribe = new ServiceProviderDataSuscribe(
                    InterfazGuardReply.class.getName(),
                    ServiceProviderBESA.SYNCHRONIC_SERVICE,
                    "SERVICIO_INTERFAZ",
                    MensajeReply.class.getName());
            //Crea el evento a enviar
            EventBESA evSP = new EventBESA(GuardServiceProviderSuscribe.class.getName(), spDataSuscribe);
            evSP.setSenderAgId(this.getAid());
            //Envía el evento
            agH.sendEvent(evSP);

            
            spDataSuscribe = new ServiceProviderDataSuscribe(
                    GuardCommMundoAgentes.class.getName(),
                    ServiceProviderBESA.ASYNCHRONIC_SERVICE,
                    "SERVICIO_INTERFAZ",
                    MensajeAsincrono.class.getName());
            evSP = new EventBESA(GuardServiceProviderSuscribe.class.getName(), spDataSuscribe);
            evSP.setSenderAgId(this.getAid());
            //Envía el evento
            agH.sendEvent(evSP);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
