/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import aypuy.handler.ResourceHandlerAYPUY;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ayllu
 */
public class SMA_ReceiveHandlerAYPUY extends GuardBESA{

    @Override
    public void funcExecGuard(EventBESA event) {
        ResourceHandlerAYPUY resourceHandlerAYPUY = (ResourceHandlerAYPUY)event.getData();
        try {
            resourceHandlerAYPUY.getResource(this.getAgent().getAid(),SMA_ReceiveFileAYPUY.class.getName());
        } catch (ExceptionBESA ex) {
            Logger.getLogger(SMA_ReceiveHandlerAYPUY.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
