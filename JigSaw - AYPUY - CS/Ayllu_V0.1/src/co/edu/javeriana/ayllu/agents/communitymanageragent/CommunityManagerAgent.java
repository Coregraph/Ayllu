/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.communitymanageragent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import co.edu.javeriana.ayllu.agents.communityagent.AgentRole;
import co.edu.javeriana.ayllu.agents.communityagent.CommunityAgent;
import co.edu.javeriana.ayllu.agents.communityagent.CommunityAgentState;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SMA_ReceiveCMARequestGuard;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SessionManagerAgentState;
import co.edu.javeriana.ayllu.data.Ayllu_CARegisterData;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Community Manager Agent (CMA) creates volatile groups of CA that correspond to a cooperative service.
 * This agent is in charge of managing the cooperative service
 * It is an abstract class that must be extended
 * @author AYLLU
 */
public abstract class CommunityManagerAgent extends AgentBESA {

    private static StructBESA myStruct;

    /**
     * This method creates a StructBESA with user-defined BESA guards
     * @param guards the BESA guards to add to the particular instance of a CA
     */
    public static void prepareInstance(List<Class> guards) {
        myStruct = new StructBESA();
        try {
            prepareInstance();
            for (Class guardBESA : guards) {
                myStruct.bindGuard("CMA_Behavior", guardBESA);
            }
        } catch (ExceptionBESA ex) {
            ReportBESA.error(ex);
        }
    }

    /**
     * This method creates a StructBESA with default BESA guards
     */
    public static void prepareInstance() {
        myStruct = new StructBESA();
        try {
            myStruct.addBehavior("CMA_Behavior");
            myStruct.bindGuard("CMA_Behavior", CMA_EndVolatileGroupGuard.class);
            myStruct.bindGuard("CMA_Behavior", CMA_UserLookupGuard.class);
            myStruct.bindGuard("CMA_Behavior", CMA_SendMessageToCAsGuard.class);
            myStruct.bindGuard("CMA_Behavior", CMA_SendAwarenessMessageGuard.class);
            
        } catch (ExceptionBESA ex) {
            ReportBESA.error(ex);
        }
    }

    /**
     * Constructor of a CommunityManagerAgent
     * @param alias the Alias of the Community Manager Agent to be created
     * @param state the state of the agent (Must be an instance or child of CommunityManagerAgentState class).
     * @param passwd the password to set the volitile groups
     * @param agentToSendEndHandler the BESA AgHandler that this CMA invokes when ending its service.
     */
    public CommunityManagerAgent(String alias, StateBESA state, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, myStruct, passwd);

        //this.start();
    }

    /**
     * 
     * @param alias the Alias of the Community Manager Agent to be created
     * @param state the state of the agent (Must be an instance or child of CommunityManagerAgentState class).
     * @param passwd the password to set the volitile groups
     * @param agentToSendEndHandler the BESA AgHandler that this CMA invokes when ending its service.
     * @param startNow if the agents wants to start now
     * @throws KernellAgentExceptionBESA 
     */
    public CommunityManagerAgent(String alias, StateBESA state, double passwd, AgHandlerBESA agentToSendEndHandler, boolean startNow) throws KernellAgentExceptionBESA {
        super(alias, state, myStruct, passwd);
        ((CommunityManagerAgentState) this.getState()).setAgentToSendEndHandler(agentToSendEndHandler);
        if (startNow) {
            this.start();
        }
    }

    @Override
    public void setupAgent() {
        this.getAdmLocal().addService("CommunityManagerAgent");
        this.getAdmLocal().bindService(this.getAid(), "CommunityManagerAgent");
        cmaInitService();
    }

    @Override
    public void shutdownAgent() {
        this.getAdmLocal().unbindService(this.getAid(), "CommunityManagerAgent");
        //this.getAdmLocal().removeService("CommunityAgent");
    }

    /**
     * This method creates a community agent and adds it to the volatilegroup list in this CMA's state
     * @param role The role of the CA in the volatilegroup
     * @param sessionHandler The sessionHandler tthat will handle the CA
     * @param guards the user-defined guards that the CA will execute
     * @return The BESA AgHandler of the created CA
     */
    public AgHandlerBESA createCommunityAgent(String role, AgHandlerBESA sessionHandler, List<Class> guards) {
        CommunityManagerAgentState myState = (CommunityManagerAgentState) this.getState(); //estado del agente this
        //SessionManagerAgentState smState = (SessionManagerAgentState) sessionHandler.getAg().getState(); //estado del sessionManager
        CommunityAgentState caState = null;
        try {
            caState = new CommunityAgentState(myState.getCommunityAgentsHandlers(), sessionHandler, this.getAdmLocal().getHandlerByAid(this.getAid()));
        } catch (ExceptionBESA ex) {
            Logger.getLogger(CommunityManagerAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        AgHandlerBESA cahandler = createAgent(guards, role, caState, myState, sessionHandler);
        Ayllu_CARegisterData caregData = new Ayllu_CARegisterData(cahandler);
        try {
            sessionHandler.sendEvent(new EventBESA(SMA_ReceiveCMARequestGuard.class.getName(), caregData));
        } catch (ExceptionBESA ex) {
            Logger.getLogger(CommunityManagerAgent.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cahandler;
    }

    /**
     * This method creates a community agent and adds it to the volatilegroup list in this CMA's state
     * This method acceps a user-defined state 
     * @param role The role of the CA in the volatilegroup
     * @param sessionHandler The sessionHandler tthat will handle the CA
     * @param guards the user-defined guards that the CA will execute 
     * @param state a user-defined state (Must be an instance or child of StateBESA class).
     * @return 
     */
    public AgHandlerBESA createCommunityAgent(String role, AgHandlerBESA sessionHandler, List<Class> guards, StateBESA state) {
        CommunityManagerAgentState myState = (CommunityManagerAgentState) this.getState(); //estado del agente this
        SessionManagerAgentState smState = (SessionManagerAgentState) sessionHandler.getAg().getState(); //estado del sessionManager
        CommunityAgentState caState = (CommunityAgentState) state;
        caState.setVolatileGroupPassword(myState.getVolatileGroupPassword());
        caState.setSessionManagerPassword(smState.getSessionManagerPassword());
        caState.setSameVolatileGroupHandlers(myState.getCommunityAgentsHandlers());
        caState.setSessionManagerHandler(sessionHandler);
        try {
            caState.setCommunityManagerHandler(this.getAdmLocal().getHandlerByAid(this.getAid()));
            //ReportBESA.info("CREADO AGENTE DE COMUNIDAD PARA: "+role);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(CommunityManagerAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return createAgent(guards, role, caState, myState, sessionHandler);
    }

    /**
     * This method destroys the volatile group and properly kills the agents in it
     * @param theData TODO _ASK Data that includes if all of them are killed or ¿only one?
     */
    public void destroyAllCommunityAgents(Ayllu_Data_Message theData) {
        CommunityManagerAgentState myState = (CommunityManagerAgentState) this.getState();
        for (AgentRole caRole : myState.getCommunityAgentsHandlers()) {
            AgHandlerBESA handler = caRole.getCommunityAgentHandler();
            try {
                this.getAdmLocal().killAgent(handler.getAgId(), myState.getVolatileGroupPassword());
            } catch (ExceptionBESA ex) {
                Logger.getLogger(CommunityManagerAgent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * This method is used to create the agent
     * @param guards theguards
     * @param role therole
     * @param state thestate
     * @param myState this cma's state
     * @param sessionHandler thesessionhandler
     * @return theAgHandler
     */
    private AgHandlerBESA createAgent(List<Class> guards, String role, CommunityAgentState state, CommunityManagerAgentState myState, AgHandlerBESA sessionHandler) {
        if (guards == null) {
            CommunityAgent.prepareInstance();
        }else{
            CommunityAgent.prepareInstance(guards);
        }
        CommunityAgent newCaInstance;
        try {
            if (myState.getCourseID() != null) {
                state.setCourseId(myState.getCourseID());
            }else{
                String errorLog = "THE COURSE ID WAS NOT SET IN CMA: "+this.getAlias();
                Logger.getLogger(CommunityManagerAgent.class.getName()).log(Level.SEVERE, errorLog);
            }
            newCaInstance = new CommunityAgent(role + "_" + UUID.randomUUID().toString().substring(0, 5), state, myState.getVolatileGroupPassword());
            myState.addCommunityAgent(new AgentRole(role, newCaInstance.getAdmLocal().getHandlerByAid(newCaInstance.getAid())));
            newCaInstance.start();
            String logInfo = "CREADO CA: " + newCaInstance.getAlias() + " CORRESPONDIENTE AL SM: " + sessionHandler.getAlias();
            Logger.getLogger(CommunityManagerAgent.class.getName()).log(Level.INFO,logInfo);
            return this.getAdmLocal().getHandlerByAid(newCaInstance.getAid());
        } catch (ExceptionBESA ex) {
            Logger.getLogger(CommunityManagerAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public abstract void cmaInitService();
}
