/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DisplayAgent;

import BESA.Kernel.Agent.StateBESA;
import OdoAgent.StateOdo.State;
import Odometer.GUI.OdoGUI;

/**
 *
 * @author fabianjose
 */
public class DisplayState extends StateBESA {
    /**
     * References to the presentantion object.
     */
    private OdoGUI odoGUI;

    /**
     * 
     */
    public DisplayState() {
        odoGUI = new OdoGUI();
        odoGUI.setVisible(true);
    }
        
    /**
     * Changues the states.
     * 
     * @param name
     */
    public void changueState(State state) {
        this.odoGUI.getjState().setText(state.name());
    }
    
    /**
     * Gets the GUI object.
     *
     * @return OdoGUI.
     */
    public OdoGUI getOdoGUI() {
        return odoGUI;
    }
}
