/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorayllu.servicioWall;

import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communitymanageragent.CommunityManagerAgentState;
import java.util.List;

/**
 *
 * @author luis
 */
public class WallCoopServState extends CommunityManagerAgentState{
    private List<String> usernames;

    public WallCoopServState(List<String> usernames, AgHandlerBESA agentToSendEndHandler, Class guardToSendEndHandler) {
        super(agentToSendEndHandler, guardToSendEndHandler);
        this.usernames = usernames;
        this.setCourseID("SIST_INT");
    }

    public List<String> getUsernames() {
        return usernames;
    }
    
    
    
}
