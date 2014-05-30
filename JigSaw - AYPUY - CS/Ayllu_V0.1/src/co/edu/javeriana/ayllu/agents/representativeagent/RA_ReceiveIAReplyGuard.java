/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.representativeagent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SMA_ReceiveRAReplyGuard;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AylluAdmin
 */
public class RA_ReceiveIAReplyGuard extends GuardBESA{

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        Ayllu_Data_Message message = (Ayllu_Data_Message)ebesa.getData(); 
        EventBESA replyEvent = new EventBESA(SMA_ReceiveRAReplyGuard.class.getName(), message);
        try {
            message.getReceiverHandler().sendEvent(replyEvent);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(RA_ReceiveIAReplyGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
