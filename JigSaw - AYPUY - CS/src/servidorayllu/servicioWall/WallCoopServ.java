/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorayllu.servicioWall;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communitymanageragent.CommunityManagerAgent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class WallCoopServ extends CommunityManagerAgent{

    public WallCoopServ(String alias, StateBESA state, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, passwd);
    }
    
    @Override
    public void cmaInitService() {
        WallCoopServState myState = (WallCoopServState)this.getState();
        List<String> usuarios = myState.getUsernames();
        for (String usuario : usuarios) {
            AgHandlerBESA sma = null;
            try {
                sma = this.getAdmLocal().getHandlerByAlias("SMA_"+usuario);
            } catch (ExceptionBESA ex) {
                Logger.getLogger(WallCoopServ.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.createCommunityAgent("CA_"+usuario+this.getAid(), sma, null);
        }
    }
    
}
