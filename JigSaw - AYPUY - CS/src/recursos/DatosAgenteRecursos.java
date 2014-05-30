/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package recursos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yolima
 */
public class DatosAgenteRecursos {

    private List<Object> recursosTema;
    private String rootDirectory;
    private ArrayList<ArrayList<String>> grupos;
            
    public DatosAgenteRecursos(String directorio) {
      
        recursosTema = new ArrayList<Object>();
        rootDirectory = directorio;
        grupos = new ArrayList<ArrayList<String>>();
    }
      
    public List<Object> getRecursosTema() {
        return recursosTema;
    }

    public void setRecursosTema(List<Object> recursosTema) {
        this.recursosTema = recursosTema;
    }

    public String getRootDirectory() {
        return rootDirectory;
    }

    public void setRootDirectory(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public ArrayList<ArrayList<String>> getGrupos() {
        return grupos;
    }

    public void setGrupos(ArrayList<ArrayList<String>> grupos) {
        this.grupos = grupos;
    }
}
