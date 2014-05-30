/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.asignarTema;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communitymanageragent.CommunityManagerAgent;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import estrategias.jigsawASHYI.General.CMA_Jigsaw;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AylluAdmin
 */
public class CMA_AsignarTema extends CommunityManagerAgent implements Serializable {

    public CMA_AsignarTema(String alias, StateBESA state, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, passwd);
    }
    
    public CMA_AsignarTema() throws KernellAgentExceptionBESA {
        super("", new StateBESA() {}, 0.91);
    }

    @Override
    public void cmaInitService() {

        System.out.println("INICIALIZANDO EL CMA_AsignarTema");

        EstadoAsignarTema estadoAT = (EstadoAsignarTema) this.getState();

        try {
            AgHandlerBESA handler = this.getAdmLocal().getHandlerByAid(this.getAid());
            Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.START_COOP, handler, estadoAT);
            EventBESA event = new EventBESA(GuardaAsignarTema.class.getName(), data);
            handler.sendEvent(event);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(CMA_Jigsaw.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
