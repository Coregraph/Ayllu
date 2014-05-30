/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.interfaceagentagent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The state of an Interface Agent
 * @author AYLLU
 */
public abstract class InterfaceAgentState extends StateBESA {

    private String myAgentId;
    private AgHandlerBESA sessionManagerHandler;


    public InterfaceAgentState(AgHandlerBESA sessionManagerHandler) {
        this.sessionManagerHandler = sessionManagerHandler;

    }

    public AgHandlerBESA getSessionManagerHandler() {
        return sessionManagerHandler;
    }

  

    void setAgentID(String aid) {
        this.myAgentId = aid;
    }


    public void setMyAgentId(String myAgentId) {
        this.myAgentId = myAgentId;
    }

    public void setSessionManagerHandler(AgHandlerBESA sessionManagerHandler) {
        this.sessionManagerHandler = sessionManagerHandler;
    }

    public String getMyAgentId() {
        return myAgentId;
    }

    public void logout(String username) {
        try {
            AdmBESA.getInstance().kill(0.91);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(InterfaceAgentState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public abstract void handleSMRequest(DataBESA theData);

    public abstract void handleAARequest(DataBESA theData);

    public abstract void handleRAQuestion(DataBESA theData);

}