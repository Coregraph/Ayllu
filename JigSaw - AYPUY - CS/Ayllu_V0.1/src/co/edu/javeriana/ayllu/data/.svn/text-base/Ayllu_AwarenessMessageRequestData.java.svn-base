/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.data;

import AYLLU.model.data.InformationRequestForm;
import BESA.Kernel.Agent.Event.DataBESA;

/**
 *
 * @author luis
 */
public class Ayllu_AwarenessMessageRequestData extends DataBESA{
    private MessageType myType;
    private InformationRequestForm reqForm;
    private String smaDestinatioId;
    private String cooperativeServiceId;
    private String senderId;
    private String guardToRespond;

    public Ayllu_AwarenessMessageRequestData(MessageType myType, InformationRequestForm reqForm, String smaDestinatioId, String cooperativeServiceId) {
        this.myType = myType;
        this.reqForm = reqForm;
        this.smaDestinatioId = smaDestinatioId;
        this.cooperativeServiceId = cooperativeServiceId;
    }

    

    public void setGuardToRespond(String guardToRespond) {
        this.guardToRespond = guardToRespond;
    }

    public String getSmaDestinatioId() {
        return smaDestinatioId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
    
    
    
    public MessageType getMyType() {
        return myType;
    }

    public String getGuardToRespond() {
        return guardToRespond;
    }

    public String getSenderId() {
        return senderId;
    }

    
   

    public String getCooperativeServiceId() {
        return cooperativeServiceId;
    }

    public InformationRequestForm getReqForm() {
        return reqForm;
    }
    public static enum MessageType {

        REQUEST, REPLY;
    };
    
}
