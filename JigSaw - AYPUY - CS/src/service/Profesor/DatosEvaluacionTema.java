/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.Profesor;

import BESA.Kernel.Agent.Event.DataBESA;
import java.util.HashMap;
import java.util.List;
import persistencia.Tema;

/**
 *
 * @author Yolima
 */
public class DatosEvaluacionTema extends DataBESA{
    
    private List<Tema> temas;
    private List<Object> ovas;
    private HashMap<String,String> notasEstudiantes;

    public DatosEvaluacionTema(List<Tema> tema,List<Object> ova ) {
       temas = tema;
       ovas = ova;
       notasEstudiantes = new HashMap<String, String>();
    }

    public HashMap<String, String> getNotasEstudiantes() {
        return notasEstudiantes;
    }

    public void setNotasEstudiantes(HashMap<String, String> notasEstudiantes) {
        this.notasEstudiantes = notasEstudiantes;
    }

    public List<Object> getOvas() {
        return ovas;
    }

    public void setOvas(List<Object> ovas) {
        this.ovas = ovas;
    }

    public List<Tema> getTemas() {
        return temas;
    }

    public void setTemas(List<Tema> temas) {
        this.temas = temas;
    }

   

}
