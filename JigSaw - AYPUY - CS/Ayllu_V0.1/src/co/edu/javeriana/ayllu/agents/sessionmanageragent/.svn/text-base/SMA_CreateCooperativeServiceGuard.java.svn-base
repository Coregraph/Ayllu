/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.sessionmanageragent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import co.edu.javeriana.ayllu.agents.factoryagent.FA_CMACreationGuard;
import co.edu.javeriana.ayllu.data.Ayllu_Agent_Creation_Message;
import co.edu.javeriana.ayllu.data.Ayllu_CoopServCreationMessage;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class SMA_CreateCooperativeServiceGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA event) {
        try {
            Iterator<String> factoryIte = this.agent.getAdmLocal().searchAidByService("FactoryAgent");
            String factoryId;
            if (factoryIte != null) {
                factoryId = factoryIte.next();
            } else {
                factoryId = this.getAgent().getAdmLocal().getHandlerByAlias("FACTORY").getAgId();
            }
            AgHandlerBESA factoryHandler;
            SessionManagerAgentState myState = (SessionManagerAgentState) this.getAgent().getState();

            Ayllu_Agent_Creation_Message creamsj = (Ayllu_Agent_Creation_Message) event.getData();
            if (creamsj.getMessage() instanceof Ayllu_CoopServCreationMessage) {
                Ayllu_CoopServCreationMessage create = (Ayllu_CoopServCreationMessage) creamsj.getMessage();
                List<String> userList;
                try {
                    userList = create.getTheForm().getSelectedAnswersFromQuestion(1);
                    if (userList.isEmpty()) {
//                        create.setUserNames(myState.getUsersInCourse(create.getCourseId()));
                    } else {
                        create.setUserNames(userList);
                    }
                } catch (Exception e) {
                    ReportBESA.warn("AYLLU: COOPERATIVE SERVICE CREATION WITH NO ASSOCIATED USERS");
                }

            }


            factoryHandler = this.agent.getAdmLocal().getHandlerByAid(factoryId);


            EventBESA eventToFactory = new EventBESA(FA_CMACreationGuard.class.getName(), event.getData());
            factoryHandler.sendEvent(eventToFactory);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(SMA_CreateCooperativeServiceGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
