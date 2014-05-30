/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.asignarTema.aprendiz;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author AylluAdmin
 */
public class GuardaNotificacionAceptacionesAwarnes extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {

        System.out.println("En GuardaNotificacionAceptacionesAwarnes");
        Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
        EstadoAprendizAT theMessage1 = (EstadoAprendizAT) theData.getMessage();
        HashMap<String, String> estudianteConTemaAceptado = theMessage1.getMisDatos().getEstudiantesConTemaAceptado();

        //TODO Acá se debe actualizar la interfaz del estudiante, con los estudiantes que han aceptado el tema
        System.out.println("Estudiante: " + this.getAgent().getAlias());
        System.out.println("MENSAJE DE AWARNES ESTUDIANTES CON TEMAS ACEPTADOS");
        System.out.println(estudianteConTemaAceptado);
    }
}
