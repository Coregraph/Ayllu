/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.communityagent;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import co.edu.javeriana.ayllu.data.Ayllu_WallMessageData;

/**
 *
 * @author luis
 */
public class CA_DisplayMessageGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA event) {
        if (event.getData() instanceof Ayllu_WallMessageData) {
            Ayllu_WallMessageData message = (Ayllu_WallMessageData) event.getData();
            ((CommunityAgent) this.getAgent()).sendMesageToGUI(message.getSenderName(), message.getMessage());
        } else {
            ((CommunityAgent)this.getAgent()).sendMessageToSMA(event.getData());
        }
    }
}
