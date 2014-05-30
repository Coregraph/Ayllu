/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import service.Profesor.*;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AylluAdmin
 */
public class GuardaOAInicialTema extends GuardBESA {

    @Override
    public synchronized void funcExecGuard(EventBESA ebesa) {
        try {
            System.out.println("En GuardaOAInicialTema");

            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
            
            EstadoProfesor miEstado = (EstadoProfesor) this.getAgent().getState();
            Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
            DatosInstrucciones instrucciones = (DatosInstrucciones)theData.getMessage();
            instrucciones.setAliasP(this.getAgent().getAlias());
            
           // AgenteRecurso recursosProfesor = new AgenteRecurso("Recursos_"+datosRecuperarInformacion.getGruposTemas().get(i), estadoR,0.91);
           // recursosProfesor.start();
           
            }
        catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaOAInicialTema.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
}
