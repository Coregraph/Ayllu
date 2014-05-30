/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.Servicios.DiscusionGrupo.Secretario;

import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communityagent.AgentRole;
import co.edu.javeriana.ayllu.agents.communityagent.CommunityAgentState;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yolima
 */
public class Estado_JigSaw_DiscusionSecretarioGrupo extends CommunityAgentState{
         
    private int numEstudiantes;
    private int numEstudiantesPregListas;
    private ArrayList<String> preguntas;
    
    
    public Estado_JigSaw_DiscusionSecretarioGrupo (List<AgentRole> sameVolatileGroupHandlers, AgHandlerBESA sessionManagerHandler, AgHandlerBESA communityManagerHandler) 
    {    super(sameVolatileGroupHandlers, sessionManagerHandler, communityManagerHandler);
        this.numEstudiantes = 0;  
        numEstudiantesPregListas = 0;
        preguntas = new ArrayList<String>();
    }
    
    public int getNumEstudiantesPregListas() {
        return numEstudiantesPregListas;
    }

    public void setNumEstudiantesPregListas(int numEstudiantesPregListas) {
        this.numEstudiantesPregListas = numEstudiantesPregListas;
    }
    
    public void aumentarNumEstudiantesPregListas() {
        this.numEstudiantesPregListas += 1;
    }

    public int getNumEstudiantes() {
        return numEstudiantes;
    }

    public void setNumEstudiantes(int numEstudiantes) {
        this.numEstudiantes = numEstudiantes;
    }

    public ArrayList<String> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<String> preguntas) {
        this.preguntas = preguntas;
    }
    
}
