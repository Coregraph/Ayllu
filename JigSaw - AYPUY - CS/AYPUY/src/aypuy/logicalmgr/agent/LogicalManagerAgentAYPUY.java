/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aypuy.logicalmgr.agent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StructBESA;
import aypuy.directorymanager.agent.data.DIRDirectoryManagementData;
import aypuy.directorymanager.agent.guards.DIR_RegisterLogicalManagerAYPUY;
import aypuy.logicalmgr.agent.guards.LM_InsertResourceAYPUY;
import aypuy.logicalmgr.agent.guards.LM_RegisterPhysicalAgentAYPUY;
import aypuy.logicalmgr.agent.guards.LM_RequestResourceAYPUY;
import aypuy.logicalmgr.agent.guards.LM_ValidateHandlerAYPUY;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ayllu
 */
public class LogicalManagerAgentAYPUY extends AgentBESA{

    public LogicalManagerAgentAYPUY(String alias, BitSet myBitSet, double passwd) throws KernellAgentExceptionBESA {
        super(alias, new LogicalManagerStateAYPUY(myBitSet), getStruct(), passwd);
    }
    
    @Override
    public void setupAgent() {
        Properties props = new Properties();
        LogicalManagerStateAYPUY myState = (LogicalManagerStateAYPUY)state;
        DIRDirectoryManagementData datamgmt = new DIRDirectoryManagementData(myState.getMyBitSetId(), this.getAid());
        EventBESA event = new EventBESA(DIR_RegisterLogicalManagerAYPUY.class.getName(), datamgmt);
        try {
            props.load(new FileInputStream("aypuyConfig.properties"));
            getAdmLocal().getHandlerByAlias(props.getProperty("directoryalias")).sendEvent(event);
        } catch(IOException ioex){
            Logger.getLogger(LogicalManagerAgentAYPUY.class.getName()).log(Level.SEVERE, null, ioex);
        }catch (ExceptionBESA ex) {
            Logger.getLogger(LogicalManagerAgentAYPUY.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void shutdownAgent() {
        
    }
    private static StructBESA getStruct(){
        StructBESA myStruct = new StructBESA();
        myStruct.addBehavior("LM_Behavior");
        try {
            myStruct.bindGuard("LM_Behavior", LM_RequestResourceAYPUY.class);
            myStruct.bindGuard("LM_Behavior", LM_RegisterPhysicalAgentAYPUY.class);
            myStruct.bindGuard("LM_Behavior", LM_ValidateHandlerAYPUY.class);
            myStruct.bindGuard("LM_Behavior", LM_InsertResourceAYPUY.class);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(LogicalManagerAgentAYPUY.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myStruct;
    }
}
