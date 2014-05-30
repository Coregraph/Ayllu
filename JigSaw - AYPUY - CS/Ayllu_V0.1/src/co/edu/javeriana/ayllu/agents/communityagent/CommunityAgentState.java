/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.communityagent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communitymanageragent.CommunityManagerAgentState;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SessionManagerAgentState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The ComminityAgentState (CAS) contains all of the attributes that are required by a CommunityAgent(CA)
 * @author AYLLU
 */
public class CommunityAgentState extends StateBESA {

    private double volatileGroupPassword;
    private double sessionManagerPassword;
    private List<AgentRole> sameVolatileGroupHandlers;
    private AgHandlerBESA sessionManagerHandler;
    private AgHandlerBESA communityManagerHandler;
    private String courseId;

    /**
     * The Constructor of a CAS
     * @param volatileGroupPassword the password of the volatile group of this CA
     * @param sessionManagerPassword the passsword of the SessionManager(SM) that manages this CA
     * @param sameVolatileGroupHandlers the BESA agHAndlers of the agents in the same volatile group (used to send events)
     * @param sessionManagerHandler the BESA agHAndler of the SessionManager(SM) that manages this CA (used to send events)
     * @param communityManagerHandler the BESA agHAndler of the CommunityManagerAgent(CMA) that manages this volatile group (used to send events)
     */
    public CommunityAgentState(List<AgentRole> sameVolatileGroupHandlers, AgHandlerBESA sessionManagerHandler, AgHandlerBESA communityManagerHandler) {
        if (communityManagerHandler != null) {
            this.volatileGroupPassword = ((CommunityManagerAgentState) communityManagerHandler.getAg().getState()).getVolatileGroupPassword();
        }
        if (sessionManagerHandler != null) {
            this.sessionManagerPassword = ((SessionManagerAgentState) sessionManagerHandler.getAg().getState()).getSessionManagerPassword();
        }
        if(sameVolatileGroupHandlers!=null){
            this.sameVolatileGroupHandlers = Collections.unmodifiableList(sameVolatileGroupHandlers);
        }
        this.sessionManagerHandler = sessionManagerHandler;
        this.communityManagerHandler = communityManagerHandler;
    }

    public AgHandlerBESA getCommunityManagerHandler() {
        return communityManagerHandler;
    }

    public void setCommunityManagerHandler(AgHandlerBESA communityManagerHandler) {
        this.communityManagerHandler = communityManagerHandler;
    }

    public AgHandlerBESA getSessionManagerHandler() {
        return sessionManagerHandler;
    }

    public void setSessionManagerHandler(AgHandlerBESA sessionManagerHandler) {
        this.sessionManagerHandler = sessionManagerHandler;
    }

    public List<AgentRole> getSameVolatileGroupHandlers() {
        return sameVolatileGroupHandlers;
    }

    public void setSameVolatileGroupHandlers(List<AgentRole> sameVolatileGroupHandlers) {
        this.sameVolatileGroupHandlers = Collections.unmodifiableList(sameVolatileGroupHandlers);
    }

    public double getSessionManagerPassword() {
        return sessionManagerPassword;
    }

    public void setSessionManagerPassword(double sessionManagerPassword) {
        this.sessionManagerPassword = sessionManagerPassword;
    }

    public double getVolatileGroupPassword() {
        return volatileGroupPassword;
    }

    public void setVolatileGroupPassword(double volatileGroupPassword) {
        this.volatileGroupPassword = volatileGroupPassword;
    }


    /**
     * This method obtains the BESA AgHandlers of the CA with specific roles
     * @param role the role of a CA
     * @return The list of handlers of the CA in the same volatile group whose role was specified in the parameter
     */
    public List<AgHandlerBESA> getAgentHandlersByRole(String role) {
        List<AgHandlerBESA> agentList = new ArrayList<AgHandlerBESA>();
        for (AgentRole agent : this.sameVolatileGroupHandlers) {
            if (agent.getRole().equals(role)) {
                agentList.add(agent.getCommunityAgentHandler());
            }
        }
        return agentList;

    }
    /**
     * Sends an event to the members of the same volatile group with the specified role
     * @param role
     * @param ev
     * @throws ExceptionBESA 
     */
    public void sendEventToRole(String role, EventBESA ev) throws ExceptionBESA{
        List<AgHandlerBESA> list = getAgentHandlersByRole(role);
        for (AgHandlerBESA agHandlerBESA : list) {
            agHandlerBESA.sendEvent(ev);            
        }
        
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    
}
