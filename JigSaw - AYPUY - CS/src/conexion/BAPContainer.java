/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package conexion;

import BESA.Kernel.System.AdmBESA;
import BESA.Log.ReportBESA;

/**
 *
 * @author fabianjose
 */
public class BAPContainer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AdmBESA admLocal = AdmBESA.getInstance("res/BAPContainer.xml");
        ReportBESA.info(admLocal.getAdmHandler().getAlias() + " [OK].");
    }
}