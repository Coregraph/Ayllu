/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Francisco
 */
public class Pregunta implements Serializable{
    private String idPregunta;
    private String pregunta;
    

    public Pregunta() {
        
    }

    public Pregunta(String idPregunta, String pregunta, List<Curso> temasRelacionados) {
        this.idPregunta = idPregunta;
        this.pregunta = pregunta;
        
    }


    /**
     * @return the idPregunta
     */
    public String getIdPregunta() {
        return idPregunta;
    }

    /**
     * @param idPregunta the idPregunta to set
     */
    public void setIdPregunta(String idPregunta) {
        this.idPregunta = idPregunta;
    }

    /**
     * @return the pregunta
     */
    public String getPregunta() {
        return pregunta;
    }

    /**
     * @param pregunta the pregunta to set
     */
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    
}
