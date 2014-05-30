/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.javeriana.ayllu.agents.administratoragent;


import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Log.ReportBESA;


/**
 *
 * @author AYLLU
 * Directory Manager Guard FALTA TERMINAR
 */
public class AA_ReceiveDirectoryManagementGuard extends GuardBESA{

 

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        DataBESA theData = ebesa.getData();
        try{
            manageDirectories(theData);
        }catch(Exception ex){
            ReportBESA.error(ex);
        }
    }

    private void manageDirectories(DataBESA theData) throws Exception{
        //TODO _AA elegir que se va a hacer
    }
    


}
