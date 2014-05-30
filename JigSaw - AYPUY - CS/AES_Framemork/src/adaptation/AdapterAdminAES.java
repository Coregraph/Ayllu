package adaptation;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import adaptation.adapter.AdapterAgentAES;
import adaptation.adapter.AdapterAgentStateAES;
import adaptation.adapter.GuardRecieveProfiles;
import adaptation.common.query.AdaptationQueryAES;
import adaptation.profilemanager.GuardRecieveNewContext;
import adaptation.profilemanager.ProfileManagerAES;
import adaptation.profilemanager.ProfileManagerStateAES;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LG
 */
public class AdapterAdminAES {

    private Class queryQuard;
    private Class filterGuard;
    AdmBESA admLocal = null;
    AdapterAgentAES adapterAgent = null;
    ProfileManagerAES profileManagerBESAagent = null;

    public AdapterAdminAES(Class queryGuard, Class filterGuard) {
        this.queryQuard = queryGuard;
        this.filterGuard = filterGuard;
        try {
            admLocal = AdmBESA.getInstance();
        } catch (Exception e) {
            System.err.println("ERROR INICIALIZANDO EL ADMINISTRADOR: " + e.getMessage());
            System.exit(1);
        }
    }

    public void createAdapterAgent(AdapterAgentStateAES adapterState,String aliasToSendEnd, String guardToSendEnd) {
        /**
         * CREACION AGENTE ADAPTADOR
         */
        //estructura
        StructBESA structAdapter = new StructBESA();
        try {
            structAdapter.addBehavior("QueryBehavior");
            structAdapter.bindGuard(queryQuard);
            structAdapter.bindGuard("QueryBehavior", GuardRecieveProfiles.class);
        } catch (ExceptionBESA eb) {
            ReportBESA.info(eb);
        }
        //estado -  recibe el nombre de la guarda que implementa el obtainFilters
        adapterState.setFilterGuard(filterGuard.getName());
        //estado -  recibe el nombre del agente a responder
        adapterState.setAgentToRespond(aliasToSendEnd);
        //estado -  recibe el nombre de la guarda a responder
        adapterState.setGuardToRespond(guardToSendEnd);
        try {
            //llenarEstadoAdaptador(adapterState);
            adapterAgent = new AdapterAgentAES("Adapter", adapterState, structAdapter, 0.91);
            adapterAgent.start();
        } catch (KernellAgentExceptionBESA ex) {
            Logger.getLogger(AdapterAdminAES.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createProfileManager() {
        StructBESA structManager = new StructBESA();
        try {
            //estructura
            structManager.addBehavior("ProfileBehavior");
            structManager.bindGuard("ProfileBehavior", filterGuard);
            structManager.bindGuard("ProfileBehavior", GuardRecieveNewContext.class);
            //estado
            ProfileManagerStateAES managerState = new ProfileManagerStateAES();
            profileManagerBESAagent = new ProfileManagerAES("Profile_Manager", managerState, structManager, 0.91);
            profileManagerBESAagent.start();
        } catch (ExceptionBESA ex) {
            Logger.getLogger(AdapterAdminAES.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void launchQuery(AdaptationQueryAES theQuery) {
// Buscar handler al agente destinatario
        try {
            AgHandlerBESA ah = admLocal.getHandlerByAid(adapterAgent.getAid());
            // Crear evento - Tipo de evento debe coincidir con el nombre de la guarda
            String evType = queryQuard.getName();
            // Crear evento - usando el tipo de evento y los datos
            EventBESA ev = new EventBESA(evType, theQuery);

            ah.sendEvent(ev);
        } catch (ExceptionBESA e) {
            System.err.println("Error enviando evento: " + e.getMessage());
        }
    }
}
