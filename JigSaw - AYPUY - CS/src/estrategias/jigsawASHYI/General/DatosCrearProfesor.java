/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.General;

import BESA.Kernel.Agent.Event.DataBESA;
import java.util.List;

/**
 *
 * @author Yolima
 */
public class DatosCrearProfesor extends DataBESA{
    //lista de estudiantes
    private List<String> estudiantes;

    /*
     * constructor
     * @param lista estudiantes
     */
    public DatosCrearProfesor(List<String> estudiantes) {
        this.estudiantes = estudiantes;
    }

    /*
     * devuelve la lisat de estudiantes
     * @return lista estudiantes
     */
    public List<String> getEstudiantes() {
        return estudiantes;
    }

    /*
     * devuelve la lisat de estudiantes
     * @param lista estudiantes
     */
    public void setEstudiantes(List<String> estudiantes) {
        this.estudiantes = estudiantes;
    }
    
}
