/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import aypuy.physicalmgr.agent.data.ReceiveResourceDataAYPUY;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ayllu
 */
public class SMA_ReceiveFileAYPUY extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        BufferedOutputStream output;
        ReceiveResourceDataAYPUY receiveData = (ReceiveResourceDataAYPUY) ebesa.getData();
        byte[] filedata = receiveData.getTheFile();
        try {
            String msjToLog = "Receiving DATA!: "+receiveData.getFilename();
            Logger.getLogger(SMA_ReceiveFileAYPUY.class.getName()).log(Level.INFO,msjToLog);
            File file = new File("./"+receiveData.getFilename());
            output = new BufferedOutputStream(new FileOutputStream(file.getName()));
            output.write(filedata, 0, filedata.length);
            output.flush();
            output.close();
            Logger.getLogger(SMA_ReceiveFileAYPUY.class.getName()).log(Level.INFO,"Finished!");
        } catch (IOException ex) {
            Logger.getLogger(SMA_ReceiveFileAYPUY.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
