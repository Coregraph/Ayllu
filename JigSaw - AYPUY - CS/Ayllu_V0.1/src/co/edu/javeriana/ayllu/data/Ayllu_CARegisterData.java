/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.data;

import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;

/**
 *
 * @author LG
 */
public class Ayllu_CARegisterData extends DataBESA {
    private AgHandlerBESA theCAtoregister;

    public Ayllu_CARegisterData(AgHandlerBESA cahandler) {
        this.theCAtoregister=cahandler;
    }

    public AgHandlerBESA getTheCAtoregister() {
        return theCAtoregister;
    }
    
    
}
