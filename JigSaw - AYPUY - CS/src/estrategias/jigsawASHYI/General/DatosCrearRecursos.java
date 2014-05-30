/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.General;

import BESA.Kernel.Agent.Event.DataBESA;

/**
 *
 * @author Yolima
 */
public class DatosCrearRecursos extends DataBESA{
   
    private int funcion;
    private int funcionAnterior;
    private String directorio;
    private String nombre;
    private String nombreLog;
    private int numGruposTema;
    private int numGruposBase;

    /*
     * constructor
     * @param funcion a realizar
     */
    public DatosCrearRecursos(int funcionHacer) {
        this.funcion = funcionHacer;
        this.directorio = "";
        this.nombre = "";
        this.nombreLog = "";
        this.numGruposBase = 0;
        this.numGruposTema = 0;
        funcionAnterior = 0;
    }

    /*
     * devuelve la funcion a realizar
     * @return funcion
     */
    public int getFuncion() {
        return funcion;
    }

    /*
     * cambia la funcion a realizar
     * @param funcion
     */
    public void setFuncion(int funcion) {
        this.funcion = funcion;
    }

    /*
     * devuelve Nombre recurso
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /*
     * devuelve nombreLog
     * @return nombreLog
     */
    public String getNombreLog() {
        return nombreLog;
    }
    /*
     * cambia nombreLog
     * @param nombreLog
     */
    public void setNombreLog(String nombreLog) {
        this.nombreLog = nombreLog;
    }

    /*
     * devuelve ruta del directorio
     * @return directorio
     */
    public String getDirectorio() {
        return directorio;
    }

    /*
     * cambia ruta del directorio
     * @param directorio
     */
    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }

    /*
     * devuelve numero de grupos base
     * @return numGruposBase
     */
    public int getNumGruposBase() {
        return numGruposBase;
    }

    /*
     * cambia numero de grupos base
     * @param numGruposBase
     */
    public void setNumGruposBase(int numGruposBase) {
        this.numGruposBase = numGruposBase;
    }

    /*
     * devuelve numero de grupos por tema
     * @return numGruposTema
     */
    public int getNumGruposTema() {
        return numGruposTema;
    }
    
    /*
     * cambia numero de grupos por tema
     * @param numGruposTema
     */
    public void setNumGruposTema(int numGruposTema) {
        this.numGruposTema = numGruposTema;
    }

    /*
     * devuelve la funcion anterior
     * @return funcionAnterior
     */
    public int getFuncionAnterior() {
        return funcionAnterior;
    }
    
    /*
     * cambia la funcion anterior
     * @param funcionAnterior
     */
    public void setFuncionAnterior(int funcionAnterior) {
        this.funcionAnterior = funcionAnterior;
    }

}
