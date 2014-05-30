/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.Servicios.Discusion;

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
 * @author yolima
 */
public class CMA_JigSaw_Discusion extends CommunityManagerAgent{

    public CMA_JigSaw_Discusion(String alias, StateBESA state, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, passwd);
    }
     @Override
    public void cmaInitService() {

        System.out.println("INICIALIZANDO EL CMA_JigSaw_Discusion");
        
        Estado_JigSaw_Discusion estadoDT = (Estado_JigSaw_Discusion) this.getState();
        
        try
        {
            AgHandlerBESA handler = this.getAdmLocal().getHandlerByAid(this.getAid());
            Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.START_COOP, handler, estadoDT);
            EventBESA event = new EventBESA(GuardaIniciarDiscusion.class.getName(), data);
            handler.sendEvent(event);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(CMA_JigSaw_Discusion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
