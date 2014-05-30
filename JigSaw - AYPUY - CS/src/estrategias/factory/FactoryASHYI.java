/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.factory;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.factoryagent.FA_CMAEndNestedServiceGuard;
import co.edu.javeriana.ayllu.agents.factoryagent.FactoryAgent;
import co.edu.javeriana.ayllu.data.Ayllu_Agent_Creation_Message;
import estrategias.jigsawASHYI.General.CMA_Jigsaw;
import estrategias.jigsawASHYI.General.Estado_JigsawASHYI;
import estrategias.jigsawASHYI.General.GuardaControlarJigsawASHYI;
import estrategias.jigsawASHYI.General.GuardaCrearDirectorio;
import estrategias.jigsawASHYI.General.GuardaCrearProfesor;
import estrategias.jigsawASHYI.General.GuardaCrearRecursos;
import estrategias.jigsawASHYI.General.GuardaEnviarGrupos;
import estrategias.jigsawASHYI.General.GuardaRespuestaPreguntasTemas;
import estrategias.jigsawASHYI.General.GuardaRespuestaAsignarTemas;
import estrategias.jigsawASHYI.General.GuardaRespuestaMATEO;
import estrategias.jigsawASHYI.General.GuardaRespuestaOAGenerarInformeGrupo;
import estrategias.jigsawASHYI.General.GuardaRespuestaOAGenerarInformeTema;
import estrategias.jigsawASHYI.General.GuardaRespuestaPreguntasGrupo;
import estrategias.jigsawASHYI.General.GuardaRespuestaRecuperarInformacion;
import estrategias.jigsawASHYI.Servicios.Discusion.CMA_JigSaw_Discusion;
import estrategias.jigsawASHYI.Servicios.Discusion.Estado_JigSaw_Discusion;
import estrategias.jigsawASHYI.Servicios.Discusion.GuardaIniciarDiscusion;
import estrategias.jigsawASHYI.Servicios.Discusion.GuardaRecibirPreguntas;
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.CMA_JigSaw_DiscusionGrupo;
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.Estado_JigSaw_DiscusionGrupo;
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.GuardaIniciarDiscusionGrupo;
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.GuardaRecibirPreguntasAprendicesGrupo;
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.GuardaRecibirRespuestasEstudiarGrupo;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import servicios.asignarTema.CMA_AsignarTema;
import servicios.asignarTema.EstadoAsignarTema;
import servicios.asignarTema.GuardaAsignarTema;
import servicios.asignarTema.GuardaFinServicio;
import servicios.asignarTema.GuardaRecibirInstrucciones;
import servicios.asignarTema.GuardaRecibirInstruccionesFinal;
import servicios.asignarTema.GuardaRecibirSeleccionTemas;
import servicios.generarInformeGrupo.CMA_GenerarInformeGrupo;
import servicios.generarInformeGrupo.EstadoGenerarInformeGrupo;
import servicios.generarInformeGrupo.GuardaRecibirEvaluacionInformeGrupo;
import servicios.generarInformeGrupo.GuardaRecibirOAGeneracionInformesGrupo;
import servicios.generarInformeGrupo.GuardaRecibirRetroalimentacionFinalG;
import servicios.generarInformeTema.CMA_GenerarInformeTema;
import servicios.generarInformeTema.EstadoGenerarInformeTema;
import servicios.generarInformeTema.GuardaRecibirEvaluacionInforme;
import servicios.generarInformeTema.GuardaRecibirOAGeneracionInformesTemas;
import servicios.generarInformeTema.GuardaRecibirRetroalimentacionFinal;

/**
 *
 * @author Yolima
 */
public class FactoryASHYI extends FactoryAgent {

    public FactoryASHYI(String alias, StateBESA state, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, passwd);
    }

    @Override
    public void createCMAAgent(String alias, Ayllu_Agent_Creation_Message theData, AgHandlerBESA agentToSendEndHandler, Class guardToReply) {
        /*
         * CMA principal jigsaw
         */
        if (alias.equals("CMA_JS")) {
            //estado
            Estado_JigsawASHYI estadoCMAJS = new Estado_JigsawASHYI();
            try {
                estadoCMAJS.setAgentToSendEndHandler(this.getAdmLocal().getHandlerByAid(this.getAid()));
            } catch (ExceptionBESA ex) {
                Logger.getLogger(FactoryASHYI.class.getName()).log(Level.SEVERE, null, ex);
            }
            estadoCMAJS.setGuardToSendEndHandler(FA_CMAEndNestedServiceGuard.class);
            //guardas
            List<Class> guardas_CMAJS = new ArrayList<Class>();
            guardas_CMAJS.add(GuardaControlarJigsawASHYI.class);
            guardas_CMAJS.add(GuardaRespuestaMATEO.class);
            guardas_CMAJS.add(GuardaCrearProfesor.class);
            guardas_CMAJS.add(GuardaRespuestaAsignarTemas.class);
            guardas_CMAJS.add(GuardaRespuestaRecuperarInformacion.class);
            guardas_CMAJS.add(GuardaRespuestaPreguntasTemas.class);
            guardas_CMAJS.add(GuardaRespuestaPreguntasGrupo.class);
            guardas_CMAJS.add(GuardaRespuestaOAGenerarInformeTema.class);
            guardas_CMAJS.add(GuardaRespuestaOAGenerarInformeGrupo.class);
            guardas_CMAJS.add(GuardaCrearRecursos.class);
            guardas_CMAJS.add(GuardaCrearDirectorio.class);
            guardas_CMAJS.add(GuardaEnviarGrupos.class);
            CMA_Jigsaw.prepareInstance(guardas_CMAJS);
            try {
                CMA_Jigsaw cmaJS = new CMA_Jigsaw(alias, estadoCMAJS, 0.91);
                cmaJS.start();
                                
            } catch (KernellAgentExceptionBESA ex) {
                Logger.getLogger(FactoryASHYI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /*
         * CMA Asignar temas
         */
        if (alias.startsWith("CMA_AT")) {
            EstadoAsignarTema estadoAT = (EstadoAsignarTema) theData.getMessage();           

            System.out.println("EN CMA_AT");
            //guardas                    
            List<Class> guardas_CMAAT = new ArrayList<Class>();
            guardas_CMAAT.add(GuardaAsignarTema.class);
            guardas_CMAAT.add(GuardaRecibirSeleccionTemas.class);
            guardas_CMAAT.add(GuardaFinServicio.class);
            guardas_CMAAT.add(GuardaRecibirInstrucciones.class);
            guardas_CMAAT.add(GuardaRecibirInstruccionesFinal.class);
            CMA_AsignarTema.prepareInstance(guardas_CMAAT);

            try {
                CMA_AsignarTema cmaAT = new CMA_AsignarTema(alias, estadoAT, 0.91);
                cmaAT.start();
            } catch (KernellAgentExceptionBESA ex) {
                Logger.getLogger(FactoryASHYI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /*
         * CMA Discusión grupo temático
         */
        if (alias.contains("CMA_DT")) {
            System.out.println("EN CMA_DT");
            Ayllu_Agent_Creation_Message msj = (Ayllu_Agent_Creation_Message) theData;
            Estado_JigSaw_Discusion estadoCMADT = (Estado_JigSaw_Discusion) msj.getMessage();
            
            //yolima
            List<Class> guardas_CMAAPT = new ArrayList<Class>();
            guardas_CMAAPT.add(GuardaIniciarDiscusion.class);
            guardas_CMAAPT.add(GuardaRecibirPreguntas.class);
            CMA_JigSaw_Discusion.prepareInstance(guardas_CMAAPT);
            try {
                CMA_JigSaw_Discusion cmaDT = new CMA_JigSaw_Discusion(alias , estadoCMADT, 0.91);
                cmaDT.start();
            } catch (KernellAgentExceptionBESA ex) {
                Logger.getLogger(FactoryASHYI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (alias.contains("CMA_GIT")) {
            System.out.println("EN CMA_GIT");
            Ayllu_Agent_Creation_Message msj = (Ayllu_Agent_Creation_Message) theData;
            EstadoGenerarInformeTema estadoCMAGIT = (EstadoGenerarInformeTema) msj.getMessage();
            //guardas
                List<Class> guardas_CMAAPT = new ArrayList<Class>();
                guardas_CMAAPT.add(GuardaRecibirEvaluacionInforme.class);
                guardas_CMAAPT.add(GuardaRecibirOAGeneracionInformesTemas.class);
                guardas_CMAAPT.add(GuardaRecibirRetroalimentacionFinal.class);
                CMA_GenerarInformeTema.prepareInstance(guardas_CMAAPT);
                try {
                    CMA_GenerarInformeTema cmaGIT = new CMA_GenerarInformeTema(alias , estadoCMAGIT, 0.91);
                    cmaGIT.start();
                } catch (KernellAgentExceptionBESA ex) {
                    Logger.getLogger(FactoryASHYI.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        /*
         * CMA discusión grupo
         */
        if (alias.contains("CMA_DG")) {
            System.out.println("EN CMA_DG");
            Ayllu_Agent_Creation_Message msj = (Ayllu_Agent_Creation_Message) theData;
            Estado_JigSaw_DiscusionGrupo estadoCMADT = (Estado_JigSaw_DiscusionGrupo) msj.getMessage();
            
            //yolima
            List<Class> guardas_CMAAPT = new ArrayList<Class>();
            guardas_CMAAPT.add(GuardaIniciarDiscusionGrupo.class);
            guardas_CMAAPT.add(GuardaRecibirRespuestasEstudiarGrupo.class);
            guardas_CMAAPT.add(GuardaRecibirPreguntasAprendicesGrupo.class);
            CMA_JigSaw_Discusion.prepareInstance(guardas_CMAAPT);
            try {
                CMA_JigSaw_DiscusionGrupo cmaDT = new CMA_JigSaw_DiscusionGrupo(alias , estadoCMADT, 0.91);
                cmaDT.start();
            } catch (KernellAgentExceptionBESA ex) {
                Logger.getLogger(FactoryASHYI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /*
         * CMA generar informa grupo
         */
        if (alias.contains("CMA_GIG")) {
            System.out.println("EN CMA_GIG");
            Ayllu_Agent_Creation_Message msj = (Ayllu_Agent_Creation_Message) theData;
            EstadoGenerarInformeGrupo estadoCMAGIG = (EstadoGenerarInformeGrupo) msj.getMessage();

            estadoCMAGIG.setAgentToSendEndHandler(agentToSendEndHandler);
            estadoCMAGIG.setGuardToSendEndHandler(guardToReply);

            List<Class> guardas_CMAGIG = new ArrayList<Class>();
            guardas_CMAGIG.add(GuardaRecibirEvaluacionInformeGrupo.class);
            guardas_CMAGIG.add(GuardaRecibirOAGeneracionInformesGrupo.class);            
            guardas_CMAGIG.add(GuardaRecibirRetroalimentacionFinalG.class);
            CMA_GenerarInformeGrupo.prepareInstance(guardas_CMAGIG);
            try {
                CMA_GenerarInformeGrupo cmaRI = new CMA_GenerarInformeGrupo(alias, estadoCMAGIG, 0.91);
                cmaRI.start();
            } catch (KernellAgentExceptionBESA ex) {
                Logger.getLogger(FactoryASHYI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
