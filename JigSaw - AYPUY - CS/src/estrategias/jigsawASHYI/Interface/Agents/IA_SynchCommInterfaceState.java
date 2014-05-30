/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.Interface.Agents;

import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.interfaceagentagent.InterfaceAgentState;
import estrategias.jigsawASHYI.Interface.MessageGUI;

/**
 * The state of the SynchIA interface, it contains the CA that invoked it
 * @author Ayllu
 */
public class IA_SynchCommInterfaceState extends InterfaceAgentState {

    private AgHandlerBESA catoreply = null;
    private MessageGUI theGUI = null;

    public IA_SynchCommInterfaceState(AgHandlerBESA sessionManagerHandler) {
        super(sessionManagerHandler);
    }

    public AgHandlerBESA getCatoreply() {
        return catoreply;
    }

    public void setCatoreply(AgHandlerBESA catoreply) {
        this.catoreply = catoreply;
    }

    @Override
    public void handleSMRequest(DataBESA theData) {
       theGUI.mostrarOVA(theData);     
    }

    @Override
    public void handleAARequest(DataBESA theData) {

    }

    @Override
    public void handleRAQuestion(DataBESA datos) {        
        theGUI.mostrarOVA(datos);
    }

    public void setTheGUI(MessageGUI theGUI) {
        this.theGUI = theGUI;
    }

}
