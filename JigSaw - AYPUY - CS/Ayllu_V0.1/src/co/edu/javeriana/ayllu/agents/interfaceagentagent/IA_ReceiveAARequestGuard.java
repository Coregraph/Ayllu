/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.interfaceagentagent;

import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Log.ReportBESA;

/**
 * This guard handles the request from an Administrator Agent (AA), it calls a method on the Interface Agent
 * @author AYLLU
 */
public class IA_ReceiveAARequestGuard extends GuardBESA {



    @Override
    public void funcExecGuard(EventBESA ebesa) {
        DataBESA theData = ebesa.getData();
        try {
            handleAARequest(theData);
        } catch (Exception ex) {
            ReportBESA.error("[" + this.getClass().getName() + ":funcExecGuard]->" + ex.toString());
        }
    }

    private void handleAARequest(DataBESA theData) {
        ((InterfaceAgentState)this.getAgent().getState()).handleAARequest(theData);
    }
}
