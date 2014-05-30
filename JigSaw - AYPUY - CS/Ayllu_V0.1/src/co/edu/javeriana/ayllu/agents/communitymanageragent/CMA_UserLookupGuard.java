/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.communitymanageragent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SMA_ReceiveCMARequestGuard;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.directories.user.AylluDataManagement;
import co.edu.javeriana.ayllu.directories.user.UserDirectory;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This guard makes the CMA agent to find users among the SessionManagers
 * This guard is automatically added in the static CMA prepareInstance method.
 * This guard is automatically added in the static CMA prepareInstance method.
 * @author AYLLU
 */
public class CMA_UserLookupGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        AylluDataManagement dir = UserDirectory.getInstance();
        Ayllu_Data_Message message = (Ayllu_Data_Message) ebesa.getData();
        String request = message.getMessage().toString();
        List<AgHandlerBESA> list = dir.getUsersbyHability(request);
        Collections.reverse(list);
        try {
            message.setSenderHandler(this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid()));

            for (AgHandlerBESA thisAgent : list) {
                EventBESA theevent = new EventBESA(SMA_ReceiveCMARequestGuard.class.getName(), message);
                ReportBESA.info("IDENTIFICADO: "+request+" = "+thisAgent.getAlias());
                thisAgent.sendEvent(theevent);
            }
        }catch (ExceptionBESA ex) {
            Logger.getLogger(CMA_UserLookupGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
