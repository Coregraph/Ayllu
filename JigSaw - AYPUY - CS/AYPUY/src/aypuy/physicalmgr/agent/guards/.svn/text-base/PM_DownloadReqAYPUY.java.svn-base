/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.physicalmgr.agent.guards;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Log.ReportBESA;
import aypuy.physicalmgr.agent.PhysicalManagerStateAYPUY;
import aypuy.physicalmgr.agent.data.PMResourceReqDataAYPUY;
import aypuy.physicalmgr.agent.data.ReceiveResourceDataAYPUY;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ayllu
 */
public class PM_DownloadReqAYPUY extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA event) {
        try {
            PMResourceReqDataAYPUY reqData = (PMResourceReqDataAYPUY) event.getData();
            PhysicalManagerStateAYPUY myState = (PhysicalManagerStateAYPUY)this.getAgent().getState();
            String agToRespond = reqData.getAgentToReply();
            String guardToRespond = reqData.getGuardToReply();
            String filename =reqData.getFileName();
            byte[] binFile = parseFile(myState.getGeneralPath()+filename);
            ReceiveResourceDataAYPUY resRep = new ReceiveResourceDataAYPUY(binFile,filename);
            EventBESA sendFileEv = new EventBESA(guardToRespond, resRep);
            agent.getAdmLocal().getHandlerByAid(agToRespond).sendEvent(sendFileEv);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(PM_DownloadReqAYPUY.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    /**
     * 
     * @param fileName
     * @return
     */
    public byte[] parseFile(String fileName) {
        try {
            File file = new File(fileName);
            byte buffer[] = new byte[(int) file.length()];
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(fileName));
            input.read(buffer, 0, buffer.length);
            input.close();
            return (buffer);
        } catch (Exception e) {
            ReportBESA.error("parseFile: " + e.getMessage());
            return (null);
        }
    }
}
