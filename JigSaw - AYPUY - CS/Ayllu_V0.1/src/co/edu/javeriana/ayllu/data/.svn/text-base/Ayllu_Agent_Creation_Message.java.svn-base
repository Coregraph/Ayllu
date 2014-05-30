/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.data;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Message to create an agent
 * @author AYLLU
 */
public class Ayllu_Agent_Creation_Message extends DataBESA{
    private String agentAlias;
    private String replyHandler;
    private Class guardToReply;
    private Object message;
    

    public Ayllu_Agent_Creation_Message(String agentAlias, String replyHandler, Class guardToReply) {
        this.agentAlias = agentAlias;
        this.replyHandler = replyHandler;
        this.guardToReply = guardToReply;
    }

    public String getAgentAlias() {
        return agentAlias;
    }

    public void setAgentAlias(String agentAlias) {
        this.agentAlias = agentAlias;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public AgHandlerBESA getReplyHandler() {
        try {
            return AdmBESA.getInstance().getHandlerByAid(replyHandler);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(Ayllu_Agent_Creation_Message.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setReplyHandler(String replyHandler) {
        this.replyHandler = replyHandler;
    }

    public Class getGuardToReply() {
        return guardToReply;
    }

    public void setGuardToReply(Class guardToReply) {
        this.guardToReply = guardToReply;
    }
    
    
    
}
