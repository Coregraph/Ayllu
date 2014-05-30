/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.data;

import BESA.Kernel.Agent.Event.DataBESA;

/**
 *
 * @author Ayllu
 */
public class Ayllu_IARegisterData extends DataBESA{
    String iaHandler;

    public Ayllu_IARegisterData(String iaHandler) {
        this.iaHandler = iaHandler;
    }

    public String getIaHandler() {
        return iaHandler;
    }
    
    
}
