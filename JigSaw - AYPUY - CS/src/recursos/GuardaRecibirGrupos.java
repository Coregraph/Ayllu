/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;
import estrategias.jigsawASHYI.General.*;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Yolima
 */
public class GuardaRecibirGrupos extends GuardBESA implements Serializable{

    @Override
    public void funcExecGuard(EventBESA event) {
            System.out.println("En GuardaRecibirGrupos");
            
            Ayllu_Data_Message theData = (Ayllu_Data_Message) event.getData();
            ArrayList<ArrayList<String>> grupos = (ArrayList<ArrayList<String>>) theData.getMessage();
            EstadoAgenteRecursos estado = (EstadoAgenteRecursos) this.getAgent().getState();
            estado.getMisDatos().setGrupos(grupos);
            

    }
}
