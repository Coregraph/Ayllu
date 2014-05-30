/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.communitymanageragent;

import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communityagent.AgentRole;
import co.edu.javeriana.ayllu.agents.communitymanageragent.CMA_MessageCenter.CA_Message;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The ComminityManagerAgentState (CMAS) contains all of the attributes that are required by a CommunityManagerAgent(CMA)
 * @author AYLLU
 */
public abstract class CommunityManagerAgentState extends StateBESA {
    private CMA_MessageCenter messageCenter;
    private List<AgentRole> communityAgentsHandlers;
    private AgHandlerBESA agentToSendEndHandler;
    private Class guardToSendEndHandler;
    private double volatileGroupPassword;
    private String courseID;
    private boolean free;

    public CommunityManagerAgentState(AgHandlerBESA agentToSendEndHandler,Class guardToSendEndHandler) {
        communityAgentsHandlers = new ArrayList<AgentRole> ();
        courseID=null;
        free = true;
        this.messageCenter=new CMA_MessageCenter();
    }
    public synchronized void esperar() {
        while (!free) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(CommunityManagerAgentState.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        free=false;
    }
    public boolean isFree() {
        return free;
    }

    public synchronized void setFree(boolean free) {
        this.free = free;
        notify();
    }
    
    public double getVolatileGroupPassword() {
        return volatileGroupPassword;
    }

    public void setVolatileGroupPassword(double volatileGroupPassword) {
        this.volatileGroupPassword = volatileGroupPassword;
    }

    public List<AgentRole> getCommunityAgentsHandlers() {
        return communityAgentsHandlers;
    }

    public void setCommunityAgentsHandlers(List<AgentRole> communityAgentsHandlers) {
        this.communityAgentsHandlers = communityAgentsHandlers;
    }

    public AgHandlerBESA getAgentToSendEndHandler() {
        return agentToSendEndHandler;
    }

    public void setAgentToSendEndHandler(AgHandlerBESA agentToSendEndHandler) {
        this.agentToSendEndHandler = agentToSendEndHandler;
    }

    public synchronized void addCommunityAgent(AgentRole comunityAgentHandler) {
        this.communityAgentsHandlers.add(comunityAgentHandler);
        notify();
    }

    public Class getGuardToSendEndHandler() {
        return guardToSendEndHandler;
    }
    
    

    public void setGuardToSendEndHandler(Class guardToSendEndHandler) {
        this.guardToSendEndHandler = guardToSendEndHandler;
    }
    /**
     * This method obtains the BESA AgHandlers of the CA with specific roles
     * @param role the role of a CA
     * @return The list of handlers of the CA in the same volatile group whose role was specified in the parameter
     */
    public List<AgHandlerBESA> getCommunityAgentsHandlersbyRole(String role) {
        List<AgHandlerBESA> handlers = new ArrayList<AgHandlerBESA>();
        for (AgentRole agRole : communityAgentsHandlers) {
            if(agRole.getRole().equals(role)){
                handlers.add(agRole.getCommunityAgentHandler());
            }
        }
        return handlers;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
    public void addMessageEntry(String id,String agAliasToRespond, String guardToRespond){
        this.messageCenter.addEntry(id, agAliasToRespond,guardToRespond);
    }
    public CA_Message getInfoByMessageId(String messageId){
        return this.messageCenter.getInfoByMessageId(messageId);
    }
    public String getSenderID(String messageId){
        return this.messageCenter.getSenderID(messageId);
    }
    public String getResponseGuard(String messageId){
        return this.messageCenter.getResponseGuard(messageId);
    }
}
