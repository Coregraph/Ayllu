/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.sessionmanageragent;

import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.interfaceagentagent.IA_ReceiveSMRequestGuard;
import co.edu.javeriana.ayllu.data.Ayllu_CoopServRegistrationMessage;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ayllu
 */
public class SMA_RegisterCooperativeServiceGuard extends GuardBESA {

    @Override
    public synchronized void funcExecGuard(EventBESA event) {   
            Ayllu_CoopServRegistrationMessage regmsj = (Ayllu_CoopServRegistrationMessage) event.getData();
            SessionManagerAgentState myState = (SessionManagerAgentState)this.getAgent().getState();
            handleCoopServRegistration(myState, regmsj);
            
    }
    private void handleCoopServRegistration(SessionManagerAgentState myState, Ayllu_CoopServRegistrationMessage regmsj) {
        //myState.registerCooperativeService(regmsj.getCourseId(), regmsj.getCooperativeService());
        //FIXME _Registro de CMA ante el SMA
        reportToIa(myState, regmsj);
    }
    private void reportToIa(SessionManagerAgentState myState, DataBESA regmsj) {
        List<AgHandlerBESA> iaList = myState.getInterfaceAgentsID();
        for (AgHandlerBESA agHandler : iaList) {
            EventBESA iaEvent = new EventBESA(IA_ReceiveSMRequestGuard.class.getName(), regmsj);
            try {
                agHandler.sendEvent(iaEvent);
            } catch (Exception ex) {
                Logger.getLogger(SMA_RegisterCooperativeServiceGuard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
