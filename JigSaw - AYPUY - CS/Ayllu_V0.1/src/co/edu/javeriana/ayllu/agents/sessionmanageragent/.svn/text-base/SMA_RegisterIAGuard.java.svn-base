/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.sessionmanageragent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import co.edu.javeriana.ayllu.data.Ayllu_IARegisterData;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ayllu
 */
public class SMA_RegisterIAGuard extends GuardBESA{

    @Override
    public void funcExecGuard(EventBESA event) {
        SessionManagerAgentState myState = (SessionManagerAgentState)this.getAgent().getState();
        Ayllu_IARegisterData IaregData= (Ayllu_IARegisterData)event.getData();
        try {
            myState.addInterfaceAgentHandler(this.getAgent().getAdmLocal().getHandlerByAid(IaregData.getIaHandler()));
        } catch (ExceptionBESA ex) {
            Logger.getLogger(SMA_RegisterIAGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
