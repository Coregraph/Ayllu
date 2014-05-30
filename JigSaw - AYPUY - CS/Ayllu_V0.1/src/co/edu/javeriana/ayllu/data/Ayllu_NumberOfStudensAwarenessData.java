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
public class Ayllu_NumberOfStudensAwarenessData extends DataBESA{
    private String coopservId;
    private int numstudents;

    public Ayllu_NumberOfStudensAwarenessData(String coopservId, int numstudents) {
        this.coopservId = coopservId;
        this.numstudents = numstudents;
    }

    public String getCoopservId() {
        return coopservId;
    }

    public int getNumstudents() {
        return numstudents;
    }
    
    
    
    
}
