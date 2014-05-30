/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.handler;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import aypuy.logicalmgr.agent.data.LMValidateHandlerData;
import aypuy.logicalmgr.agent.guards.LM_ValidateHandlerAYPUY;

/**
 *
 * @author Ayllu
 */
public class ResourceHandlerAYPUY extends DataBESA {

    private String fileName;
    private String agToRequest;
    private String agToValidate;
    private String theId;

    /**
     * 
     * @param fileName
     * @param reqAgent
     * @param agToValidate
     */
    public ResourceHandlerAYPUY(String fileName, String reqAgent, String agToValidate) {
        this.fileName = fileName;
        this.agToRequest = reqAgent;
        this.agToValidate = agToValidate;
    }

    /**
     * 
     * @param theId
     */
    public void setTheId(String theId) {
        this.theId = theId;
    }

    /**
     * 
     * @return
     */
    public String getTheId() {
        return theId;
    }
    
    /*
     * public ResourceHandlerAYPUY(String path, String filename, String
     * reqAgent) { this.fileName=path+"/"+filename; this.agToRequest = reqAgent;
    }
     */
    /**
     * 
     * @param agToReceive
     * @param guardToReceive
     * @throws ExceptionBESA
     */
    public void getResource(String agToReceive, String guardToReceive) throws ExceptionBESA {
        //llamar al validador
        LMValidateHandlerData val = new LMValidateHandlerData(agToReceive, guardToReceive, this.fileName, this.agToRequest,this.theId);
        EventBESA evValHnd = new EventBESA(LM_ValidateHandlerAYPUY.class.getName(), val);
        AdmBESA.getInstance().getHandlerByAid(this.agToValidate).sendEvent(evValHnd);
    }
}
