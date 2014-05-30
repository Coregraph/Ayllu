/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.General;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communitymanageragent.CommunityManagerAgent;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AylluAdmin
 */
public class CMA_Jigsaw extends CommunityManagerAgent {

    public CMA_Jigsaw(String alias, StateBESA state, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, passwd);
    }

    @Override
    public void cmaInitService() {
        System.out.println("INICIALIZANDO EL CMA_ManejadorJigsaw");
        try {
            AgHandlerBESA handler = this.getAdmLocal().getHandlerByAid(this.getAid());
            Estado_JigsawASHYI myState = (Estado_JigsawASHYI)this.getState();
            /*
             * inicio de servicio
             */
            Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.START_COOP, handler, myState);
            EventBESA event = new EventBESA(GuardaControlarJigsawASHYI.class.getName(), data);
            handler.sendEvent(event);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(CMA_Jigsaw.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}