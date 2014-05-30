/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.javeriana.ayllu.data;

import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import java.util.UUID;

/**
 * Generic Message
 * @author AYLLU
 */
public class Ayllu_Data_Message extends DataBESA{
    private String messageId; //id unico que identifica al mensaje
    private Ayllu_EventTypes eventType;
    private Object message;
    private AgHandlerBESA receiverHandler; //en caso de tener un destinatario especifico en lugar del usual
    private AgHandlerBESA senderHandler;
    private Class guardToActivate;

    public void setGuardToActivate(Class guardToActivate) {
        this.guardToActivate = guardToActivate;
    }

    public Class getGuardToActivate() {
        return guardToActivate;
    }
    


    public Ayllu_Data_Message(Ayllu_EventTypes eventType, AgHandlerBESA senderHandler, Object message) {
        this.messageId = UUID.randomUUID().toString();
        this.eventType = eventType;
       
        this.senderHandler=senderHandler;
        this.message = message;

    }
    public Ayllu_EventTypes getEventType() {
        return eventType;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public void setEventType(Ayllu_EventTypes eventType) {
        this.eventType = eventType;
    }
    

    public AgHandlerBESA getReceiverHandler() {
        return receiverHandler;
    }

    public void setReceiverHandler(AgHandlerBESA receiverHandler) {
        this.receiverHandler = receiverHandler;
    }

    public String getMessageId() {
        return messageId;
    }

    public AgHandlerBESA getSenderHandler() {
        return senderHandler;
    }

    public void setSenderHandler(AgHandlerBESA senderHandler) {
        this.senderHandler = senderHandler;
    }
    
    
}
