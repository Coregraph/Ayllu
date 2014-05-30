/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.recuperarInformacion.aprendiz;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import co.edu.javeriana.ayllu.agents.communityagent.CommunityAgent;
import co.edu.javeriana.ayllu.agents.communityagent.CommunityAgentState;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import java.util.List;

/**
 *
 * @author AylluAdmin
 */
public class GuardaNotificacionLecturasAwarnes extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {

        System.out.println("En GuardaNotificacionLecturasAwarnes");
        Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
        EstadoAprendizRI theMessage1 = (EstadoAprendizRI) theData.getMessage();
        List<String> idEstudianteConOALeido = theMessage1.getMisDatos().getEstudiantesConOALeido();
        
        CommunityAgentState myState = (CommunityAgentState)this.getAgent().getState();
        
        
        //TODO OK! Acá se debe actualizar la interfaz del estudiante, con los estudiantes que han leido el OA
        ((CommunityAgent)this.getAgent()).sendAwarenessStudentsMesageToGUI(myState.getCommunityManagerHandler().getAgId(),idEstudianteConOALeido.size());
        //XXX CAMBIO!
        /*System.out.println("Estudiante: " + this.getAgent().getAlias());
        System.out.println("MENSAJE DE AWARNES ESTUDIANTES CON OAs Leidos");
        String idEstudiante;
        for (int i = 0; i < idEstudianteConOALeido.size(); i++) {
            idEstudiante = idEstudianteConOALeido.get(i);
            System.out.println("Leyó: " + idEstudiante);
        }*/
    }
}
