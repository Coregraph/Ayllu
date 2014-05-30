/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.communityagent;

import AYLLU.model.data.CooperativeServiceAwareness;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Log.ReportBESA;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SMA_ReceiveCARequestGuard;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SMA_RegisterCooperativeServiceGuard;
import co.edu.javeriana.ayllu.data.Ayllu_CoopServRegistrationMessage;
import co.edu.javeriana.ayllu.data.Ayllu_NumberOfStudensAwarenessData;
import co.edu.javeriana.ayllu.data.Ayllu_WallMessageData;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CommunityAgent is an agent that executes the cooperative service
 * @author AYLLU
 */
public class CommunityAgent extends AgentBESA {

    private static StructBESA myStruct;

    /**
     * This method creates a StructBESA with user-defined BESA guards
     * @param guards the BESA guards to add to the particular instance of a CA
     */
    public static void prepareInstance(List<Class> guards) {
        myStruct = new StructBESA();
        try {
            prepareInstance();
            myStruct.addBehavior("CA_Behavior2");
            for (Class guardBESA : guards) {
                
                myStruct.bindGuard("CA_Behavior2", guardBESA);
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
        myStruct.addBehavior("CA_Behavior");
        try {
            myStruct.bindGuard("CA_Behavior", CA_DisplayMessageGuard.class);
            myStruct.bindGuard("CA_Behavior", CA_RegisterCoopServiceGuard.class);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(CommunityAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getMyRole() {
        return this.getAid().split("_")[0];
    }

    /**
     * It is NOT recommended to use this constructor, use the CMA's createCommunityAgent method instead.
     * This method creates a new community agent, the static method prepareInstance must be called before invoking this constructor
     * @param alias The alias of the CommunityAgent.
     * @param state The sate of the communityAgent (Must be an instance or child of CommunityAgentState class).
     * @param passwd The BESA password  to handle operation security.
     */
    public CommunityAgent(String alias, StateBESA state, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, myStruct, passwd);
    }

    /**
     * It is NOT recommended to use this constructor, use the CMA's createCommunityAgent method instead.
     * This method creates a new community agent, the static method prepareInstance must be called before invoking this constructor
     * @param alias The alias of the CommunityAgent.
     * @param state The sate of the communityAgent (Must be an instance or child of CommunityAgentState class).
     * @param passwd The BESA password  to handle operation security.
     */
    public CommunityAgent(String alias, StateBESA state, double passwd, boolean startNow) throws KernellAgentExceptionBESA {
        super(alias, state, myStruct, passwd);
        if (startNow) {
            this.start();
        }
    }

    @Override
    public void setupAgent() {
        this.getAdmLocal().addService("CommunityAgent");
        this.getAdmLocal().bindService(this.getAid(), "CommunityAgent");
        CommunityAgentState myState = (CommunityAgentState) this.getState();
        try {
            myState.getSessionManagerHandler().sendEvent(new EventBESA(SMA_RegisterCooperativeServiceGuard.class.getName(), new Ayllu_CoopServRegistrationMessage(myState.getCourseId(), new CooperativeServiceAwareness(myState.getCommunityManagerHandler().getAgId(), myState.getCommunityManagerHandler().getAlias()))));
        } catch (ExceptionBESA ex) {
            Logger.getLogger(CommunityAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void shutdownAgent() {
        this.getAdmLocal().unbindService(this.getAid(), "CommunityAgent");
        //this.getAdmLocal().removeService("CommunityAgent");
    }

    public String getRole() {
        String resp = this.getAlias().split("_")[0];
        return resp.equals("") ? resp : "NO_ROLE";
    }

    public void sendMesageToGUI(String senderName, String message) {
        CommunityAgentState myState = (CommunityAgentState) this.getState();

        String cooperativeServiceId = myState.getCommunityManagerHandler().getAgId();
        String cooperativeServiceName = myState.getCommunityManagerHandler().getAlias();
        long dateMilliseconds = System.currentTimeMillis();
        //String senderName = senderName;
        Ayllu_WallMessageData data = new Ayllu_WallMessageData(cooperativeServiceId, cooperativeServiceName, dateMilliseconds, senderName, message);
        EventBESA sendMsjEvnt = new EventBESA(SMA_ReceiveCARequestGuard.class.getName(), data);
        try {
            myState.getSessionManagerHandler().sendEvent(sendMsjEvnt);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(CommunityAgent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sendAwarenessStudentsMesageToGUI(String coopservID, int numEstudiantes) {
        CommunityAgentState myState = (CommunityAgentState) this.getState();
        Ayllu_NumberOfStudensAwarenessData awarData = new Ayllu_NumberOfStudensAwarenessData(coopservID, numEstudiantes);
        EventBESA sendMsjEvnt = new EventBESA(SMA_ReceiveCARequestGuard.class.getName(), awarData);
        try {
            myState.getSessionManagerHandler().sendEvent(sendMsjEvnt);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(CommunityAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void sendMessageToSMA(DataBESA data) {
        CommunityAgentState myState = (CommunityAgentState) this.getState();
        EventBESA sendMsjEvnt = new EventBESA(SMA_ReceiveCARequestGuard.class.getName(), data);
        try {
            myState.getSessionManagerHandler().sendEvent(sendMsjEvnt);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(CommunityAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
