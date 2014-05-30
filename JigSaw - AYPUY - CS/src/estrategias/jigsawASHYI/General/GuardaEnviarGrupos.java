/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.General;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import recursos.GuardaRecibirGrupos;

/**
 *
 * @author Yolima
 */
public class GuardaEnviarGrupos extends GuardBESA implements Serializable{

    @Override
    public void funcExecGuard(EventBESA event) {
        try {
            System.out.println("En GuardaEnviarGrupos");            
            
            Ayllu_Data_Message theData = (Ayllu_Data_Message) event.getData();
            String alias = (String) theData.getMessage();
            AgHandlerBESA handler =  this.getAgent().getAdmLocal().getHandlerByAlias(alias);
            AgHandlerBESA myhandler =  this.getAgent().getAdmLocal().getHandlerByAlias(this.getAgent().getAlias());
            //enviar grupos
            Estado_JigsawASHYI miestado = (Estado_JigsawASHYI)this.getAgent().getState();            
            Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, null,miestado.getGruposBase());
            EventBESA evento = new EventBESA(GuardaRecibirGrupos.class.getName(), data);
            handler.sendEvent(evento);
            
            System.out.println("Esperando registro de estudiantes T:"+miestado.getTotalEstudiantes()+"    R"+miestado.getEstudiantesRegistrados().size());
            if(miestado.getTotalEstudiantes()-1 == miestado.getEstudiantesRegistrados().size())
            {                          
                ((Estado_JigsawASHYI) this.getAgent().getState()).setEstadoEstrategia(Estado_JigsawASHYI.DAR_INSTRUCCIONES);
                data = new Ayllu_Data_Message(Ayllu_EventTypes.START_COOP, myhandler, ((miestado)));
                EventBESA autoevento = new EventBESA(GuardaControlarJigsawASHYI.class.getName(),data);
                myhandler.sendEvent(autoevento);
            }
            else
            {
                miestado.getEstudiantesRegistrados().add(alias);
            }
            
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaEnviarGrupos.class.getName()).log(Level.SEVERE, null, ex);
        }
              

    }
}
