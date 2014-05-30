/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.communitymanageragent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * a basic message center
 * @author AYLLU
 */
public class CMA_MessageCenter {

    
    private Map<String,CA_Message> messageCenter;
    public class CA_Message{
        private String senderId;
        private String guardToRespond;

        public CA_Message(String senderId, String guardToRespond) {
            this.senderId = senderId;
            this.guardToRespond = guardToRespond;
        }

        public String getGuardToRespond() {
            return guardToRespond;
        }

        public String getSenderId() {
            return senderId;
        }
        
        
    }

    public CMA_MessageCenter() {
        this.messageCenter=new HashMap<String, CMA_MessageCenter.CA_Message>();
    }
    
    public void addEntry(String id,String agAliasToRespond, String guardToRespond){
        this.messageCenter.put(id, new CA_Message(agAliasToRespond,guardToRespond ));
    }
    public CA_Message getInfoByMessageId(String messageId){
        return this.messageCenter.get(messageId);
    }
    public String getSenderID(String messageId){
        return this.messageCenter.get(messageId).getSenderId();
    }
    public String getResponseGuard(String messageId){
        return this.messageCenter.get(messageId).getGuardToRespond();
    }
    
    
}
