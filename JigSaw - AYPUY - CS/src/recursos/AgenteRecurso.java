/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Log.ReportBESA;

/**
 *
 * @author yolima
 */
public class AgenteRecurso extends AgentBESA{

    public AgenteRecurso(String alias,EstadoAgenteRecursos state, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, getStruct(), passwd);
    }
    
    @Override
    public void setupAgent() {
        
    }

    @Override
    public void shutdownAgent() {
       
    }
    
    private static StructBESA getStruct() {
        StructBESA aStruct = new StructBESA();
        try {
            aStruct.addBehavior("BehAgentAypuy");
            aStruct.bindGuard("BehAgentAypuy", SMA_ReceiveFileAYPUY.class);
            aStruct.bindGuard("BehAgentAypuy", SMA_ReceiveHandlerAYPUY.class);
            aStruct.bindGuard("BehAgentAypuy", GuardaRecibirGrupos.class);
        } catch (ExceptionBESA exbesa) {
            ReportBESA.error(exbesa);
        }
        return aStruct;
    }
}
