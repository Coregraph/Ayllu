/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.General;

import co.edu.javeriana.ayllu.agents.communitymanageragent.CommunityManagerAgentState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import persistencia.GrupoPreguntas;
import persistencia.Tema;

/**
 *
 * @author yolima
 */
public class Estado_JigsawASHYI extends CommunityManagerAgentState{
    public static final int INICIAL = 0;
    public static final int INVOCAR_MATEO_GB = 1;//Organización de equipos base 
    public static final int INVOCAR_MATEO_GT = 2;//Organización de equipos temáticos
    public static final int ASIGNAR_TEMAS = 3;
    public static final int CREAR_DIRECTORIOS = 4;
    public static final int ESPERAR_REGISTRO = 5;
    public static final int DAR_INSTRUCCIONES = 6;
    public static final int DISCUSION_TEMATICA = 7;
    public static final int GENERAR_INFORME_TEMA = 8;
    public static final int DISCUSION_GRUPO = 9;
    public static final int GENERAR_INFORME_GRUPO = 10;
    public static final int FINAL = 11; 
   
    
    private int totalEstudiantes;
    private int estadoEstrategia;
    private int numeroGruposBase;
    private int numeroGruposTema;    
    private int numeroGruposQueEntregaronInforme;
    private int numeroGruposQuePresentaronTema;
    private int numeroGruposPorTema;
    private int numeroGruposPreguntasTema;
    private int numeroGruposPreguntasGrupo;    
    
    private ArrayList<ArrayList<String>> gruposBase;
    private ArrayList<ArrayList<String>> rolesPorGrupoBase;
    private ArrayList<ArrayList<String>> gruposTema;
    private ArrayList<ArrayList<String>> rolesPorGrupoTema;
    private ArrayList<Tema> temas;
    private HashMap<Tema, List<GrupoPreguntas>> gruposPreguntasPorTema;
        
    private List<String> identificadoresAprendicesQueAceptaronTema;
    private List<String> identificadoresAprendicesQueEstudiaron;
      
    private List<String> identificadoresCMASQueHanAsignadoTemas;
    
    private HashMap<String, GrupoPreguntas> gruposPreguntasPorEstudiante;
    private HashMap<String, Object> oaPorEstudiante;
    private HashMap<Tema, Object> oaPorTema;
    
    private String directorio;
    private ArrayList<String> estudiantesRegistrados;
    


    public Estado_JigsawASHYI() {
        super(null, null);
        this.estadoEstrategia = INICIAL;
        numeroGruposBase=0;
        numeroGruposQueEntregaronInforme = 0;
        numeroGruposQuePresentaronTema = 0;
        numeroGruposTema = 0;
        numeroGruposPorTema = 0;
        numeroGruposPreguntasTema = 0;
        numeroGruposPreguntasGrupo = 0;
        
        gruposBase = new ArrayList<ArrayList<String>>();
        identificadoresAprendicesQueAceptaronTema = new ArrayList<String>();
        identificadoresAprendicesQueEstudiaron = new ArrayList<String>();
        identificadoresCMASQueHanAsignadoTemas = new ArrayList<String>();
        rolesPorGrupoBase = new ArrayList<ArrayList<String>>();
        gruposTema = new ArrayList<ArrayList<String>>();
        rolesPorGrupoTema = new ArrayList<ArrayList<String>>();
        temas = new ArrayList<Tema>();
        gruposPreguntasPorTema = new HashMap<Tema, List<GrupoPreguntas>>();     
        
        estudiantesRegistrados = new ArrayList<String>();
        gruposPreguntasPorEstudiante = new HashMap<String, GrupoPreguntas>();
        oaPorEstudiante = new HashMap<String, Object>();
        oaPorTema = new HashMap<Tema, Object>();
         
        this.setCourseID("SIST_INT");
        directorio = "";
    }

    public HashMap<String, GrupoPreguntas> getGruposPreguntasPorEstudiante() {
        return gruposPreguntasPorEstudiante;
    }

    public void setGruposPreguntasPorEstudiante(HashMap<String, GrupoPreguntas> gruposPreguntasPorEstudiante) {
        this.gruposPreguntasPorEstudiante = gruposPreguntasPorEstudiante;
    }

    public int getNumeroGruposPorTema() {
        return numeroGruposPorTema;
    }

    public void setNumeroGruposPorTema(int numeroGruposPorTema) {
        this.numeroGruposPorTema = numeroGruposPorTema;
    }

    public HashMap<String, Object> getOaPorEstudiante() {
        return oaPorEstudiante;
    }

    public void setOaPorEstudiante(HashMap<String, Object> oaPorEstudiante) {
        this.oaPorEstudiante = oaPorEstudiante;
    }

    public HashMap<Tema, Object> getOaPorTema() {
        return oaPorTema;
    }

    public void setOaPorTema(HashMap<Tema, Object> oaPorTema) {
        this.oaPorTema = oaPorTema;
    }
   
    public ArrayList<ArrayList<String>> getGruposBase() {
        return gruposBase;
    }

    public void setGruposBase(ArrayList<ArrayList<String>> gruposBase) {
        this.gruposBase = gruposBase;
    }

    public int getNumeroGruposBase() {
        return numeroGruposBase;
    }

    public void setNumeroGruposBase(int numeroGruposBase) {
        this.numeroGruposBase = numeroGruposBase;
    }

    public ArrayList<ArrayList<String>> getRolesPorGrupoBase() {
        return rolesPorGrupoBase;
    }

    public void setRolesPorGrupoBase(ArrayList<ArrayList<String>> rolesPorGrupoBase) {
        this.rolesPorGrupoBase = rolesPorGrupoBase;
    }

    public HashMap<Tema, List<GrupoPreguntas>> getGruposPreguntasPorTema() {
        return gruposPreguntasPorTema;
    }

    public void setGruposPreguntasPorTema(HashMap<Tema, List<GrupoPreguntas>> gruposPreguntasPorTema) {
        this.gruposPreguntasPorTema = gruposPreguntasPorTema;
    }

    public ArrayList<Tema> getTemas() {
        return temas;
    }

    public void setTemas(ArrayList<Tema> temas) {
        this.temas = temas;
    }


    /**
     * @return the numeroGruposBase
     */
    
    public int getNumeroGruposCreados() {
        return numeroGruposBase;
    }
    
    /**
     * @param the numeroGruposBase
     */

    public void setNumeroGruposCreados(int numeroGruposCreados) {
        this.numeroGruposBase = numeroGruposCreados;
    }    
    
    /**
     * @return the estadoEstrategia
     */
    
    public void setEstadoEstrategia(int estadoEstartegia) {
        this.estadoEstrategia = estadoEstartegia;
    }

    /**
     * @param the estadoEstrategia
     */
    
    public int getEstadoEstartegia() {
        return estadoEstrategia;
    }

    /**
     * @return the identificadoresAprendicesConTemaLeido
     */
    public List<String> getIdentificadoresAprendicesConTemaLeido() {
        return identificadoresAprendicesQueEstudiaron;
    }

    /**
     * @param identificadoresAprendicesConTemaLeido the identificadoresAprendicesConTemaLeido to set
     */
    public void setIdentificadoresAprendicesConTemaLeido(List<String> identificadoresAprendicesConTemaLeido) {
        this.identificadoresAprendicesQueEstudiaron = identificadoresAprendicesConTemaLeido;
    }
    
    public int getTotalEstudiantes() {
        return totalEstudiantes;
    }

    public void setTotalEstudiantes(int totalEstudiantes) {
        this.totalEstudiantes = totalEstudiantes;
    }

    /**
     * @return the numeroGruposQueEntregaronInforme
     */
    public int getNumeroGruposQueEntregaronInforme() {
        return numeroGruposQueEntregaronInforme;
    }

    /**
     * @param numeroGruposQueEntregaronInforme the numeroGruposQueEntregaronInforme to set
     */
    public void setNumeroGruposQueEntregaronInforme(int numeroGruposQueEntregaronInforme) {
        this.numeroGruposQueEntregaronInforme = numeroGruposQueEntregaronInforme;
    }

    /**
     * @return the numeroGruposQuePresentaronTema
     */
    public int getNumeroGruposQuePresentaronTema() {
        return numeroGruposQuePresentaronTema;
    }

    /**
     * @param numeroGruposQuePresentaronTema the numeroGruposQuePresentaronTema to set
     */
    public void setNumeroGruposQuePresentaronTema(int numeroGruposQuePresentaronTema) {
        this.numeroGruposQuePresentaronTema = numeroGruposQuePresentaronTema;
    }  

    /**
     * @return the identificadoresAprendicesQueAceptaronTema
     */
    public List<String> getIdentificadoresAprendicesQueAceptaronTema() {
        return identificadoresAprendicesQueAceptaronTema;
    }

    /**
     * @param identificadoresAprendicesQueAceptaronTema the identificadoresAprendicesQueAceptaronTema to set
     */
    public void setIdentificadoresAprendicesQueAceptaronTema(List<String> identificadoresAprendicesQueAceptaronTema) {
        this.identificadoresAprendicesQueAceptaronTema = identificadoresAprendicesQueAceptaronTema;
    }

    /**
     * @return the identificadoresSecretariosQueHanAsignadoTemas
     */
    public List<String> getIdentificadoresCMASQueHanAsignadoTemas() {
        return identificadoresCMASQueHanAsignadoTemas;
    }

    /**
     * @param identificadoresSecretariosQueHanAsignadoTemas the identificadoresSecretariosQueHanAsignadoTemas to set
     */
    public void setIdentificadoresCMASQueHanAsignadoTemas(List<String> identificadoresSecretariosQueHanAsignadoTemas) {
        this.identificadoresCMASQueHanAsignadoTemas = identificadoresSecretariosQueHanAsignadoTemas;
    }

    /**
     * @return the numeroGruposPorTema
     */
    public int getNumeroGruposTema() {
        return numeroGruposTema;
    }

    /**
     * @param numeroGruposPorTema the numeroGruposPorTema to set
     */
    public void setNumeroGruposTema(int numeroGruposPorTema) {
        this.numeroGruposTema = numeroGruposPorTema;
    }

    public List<String> getIdentificadoresAprendicesQueEstudiaron() {
        return identificadoresAprendicesQueEstudiaron;
    }

    public void setIdentificadoresAprendicesQueEstudiaron(List<String> identificadoresAprendicesQueEstudiaron) {
        this.identificadoresAprendicesQueEstudiaron = identificadoresAprendicesQueEstudiaron;
    }

    public ArrayList<ArrayList<String>> getGruposTema() {
        return gruposTema;
    }

    public void setGruposTema(ArrayList<ArrayList<String>> gruposTema) {
        this.gruposTema = gruposTema;
    }

    public ArrayList<ArrayList<String>> getRolesPorGrupoTema() {
        return rolesPorGrupoTema;
    }

    public void setRolesPorGrupoTema(ArrayList<ArrayList<String>> rolesPorGrupoTema) {
        this.rolesPorGrupoTema = rolesPorGrupoTema;
    }

    public int getNumeroGruposPreguntasGrupo() {
        return numeroGruposPreguntasGrupo;
    }

    public void setNumeroGruposPreguntasGrupo(int numeroGruposPreguntasGrupo) {
        this.numeroGruposPreguntasGrupo = numeroGruposPreguntasGrupo;
    }

    public int getNumeroGruposPreguntasTema() {
        return numeroGruposPreguntasTema;
    }

    public void setNumeroGruposPreguntasTema(int numeroGruposPreguntasTema) {
        this.numeroGruposPreguntasTema = numeroGruposPreguntasTema;
    }

    public String getDirectorio() {
        return directorio;
    }

    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }
    
    public ArrayList<String> getEstudiantesRegistrados() {
        return estudiantesRegistrados;
    }

    public void setEstudiantesRegistrados(ArrayList<String> estudiantesRegistrados) {
        this.estudiantesRegistrados = estudiantesRegistrados;
    }
}
