/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.generarInformeGrupo.aprendiz;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import aypuy.logicalmgr.agent.logicalregistry.FileDescriptionAYPUY;
import aypuy.workspacemgr.agent.data.WSManageResourceDataAYPUY;
import aypuy.workspacemgr.agent.guards.WSM_ResourceREQGuardAYPUY;
import aypuy.workspacemgr.workspace.WorkSpaceAYPUY;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import persistencia.GrupoPreguntas;
import recursos.EstadoAgenteRecursos;
import recursos.SMA_ReceiveHandlerAYPUY;
import servicios.generarInformeGrupo.EstadoGenerarInformeGrupo;
import servicios.generarInformeGrupo.secretario.EstadoSecretarioGIG;
import servicios.generarInformeGrupo.secretario.GuardaRecibirRespuestasGenerarInformesGrupo;

/**
 *
 * @author AylluAdmin
 */
public class GuardaRecibirSolicitudInformeGrupo extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        try {
            //try {
            System.out.println("En GuardaRecibirSolicitudInformeGrupo " + this.getAgent().getAlias());


            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
            EstadoAprendizGIG miEstado = (EstadoAprendizGIG) myHandler.getAg().getState();
            AgHandlerBESA secretaryHandler = this.getAgent().getAdmLocal().getHandlerByAlias(miEstado.getMisDatos().getAliasSecretario());

            Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
            AgHandlerBESA senderHandler = theData.getSenderHandler();
            EstadoGenerarInformeGrupo estadoGI = (EstadoGenerarInformeGrupo) theData.getMessage();
            String aliasr = ((EstadoGenerarInformeGrupo)miEstado.getCommunityManagerHandler().getAg().getState()).getMisDatos().getAliasr();

            switch (theData.getEventType()) {

                case QUEST_REQUEST:

                    manejarPeticion(aliasr,miEstado.getMisDatos().getGruposPreguntasPorEstudiante(), estadoGI.getMisDatos().getNumeroGrupo());//Metodo que maneja la petición al IA

              
                    String informe = "Informe que da respuesta a preguntas -> " + miEstado.getMisDatos().getGruposPreguntasPorEstudiante();
                    manejarRespuesta(aliasr, informe, myHandler, secretaryHandler, estadoGI.getMisDatos().getNumeroGrupo());
                    //-------------------------------------------------------

                    break;

                case QUEST_REPLY:

                    Object OAInforme = (Object) theData.getMessage();
                    manejarRespuesta(aliasr, OAInforme, myHandler, senderHandler, estadoGI.getMisDatos().getNumeroGrupo());  //Metodo que maneja la respuesta del IA

                    break;

            }
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRecibirSolicitudInformeGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void manejarPeticion(String aliasr, GrupoPreguntas grupoPreguntas, int numgrupo) {
        try {
            System.out.println("Por favor subir un OA resultado de sus preguntas y discusión seleccionadas " + grupoPreguntas);
            JOptionPane.showMessageDialog(null,"Por favor subir un OA resultado de sus preguntas y discusión seleccionadas " + grupoPreguntas);
             /**
             * |||||||||Inserción Recurso||||||||||||
             */
            BitSet elbitBitSet = new BitSet();
            elbitBitSet.set(0);
            elbitBitSet.set(1);
            Properties props = new Properties();
            props.load(new FileInputStream("aypuyConfig.properties"));
            AgHandlerBESA hand = this.getAgent().getAdmLocal().getHandlerByAlias(props.getProperty("wsmanageralias"));
            AgHandlerBESA recursosHandler = this.getAgent().getAdmLocal().getHandlerByAlias(aliasr);
            String fileToSend=showChooser(((EstadoAgenteRecursos)recursosHandler.getAg().getState()).getMisDatos().getRootDirectory(),false);        
            WSManageResourceDataAYPUY dtaIns = new WSManageResourceDataAYPUY(SMA_ReceiveHandlerAYPUY.class.getName(),this.getAgent().getAid(), WorkSpaceAYPUY.UPDATE, elbitBitSet, "Participante", "Base_"+numgrupo, new FileDescriptionAYPUY("Recurso", fileToSend));
            EventBESA evIns = new EventBESA(WSM_ResourceREQGuardAYPUY.class.getName(), dtaIns);
            hand.sendEvent(evIns);
            
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRecibirSolicitudInformeGrupo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GuardaRecibirSolicitudInformeGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void manejarRespuesta(String aliasr, Object OAInforme, AgHandlerBESA myHandler, AgHandlerBESA senderHandler, int numgrupo) throws ExceptionBESA {
        //TODO Se debe esperar respuesta de la generación del informe para las preguntas seleccionadas.

        try {
            ((EstadoSecretarioGIG)senderHandler.getAg().getState()).getMisDatos().getOAResumenPorTema().put(((EstadoAprendizGIG) myHandler.getAg().getState()).getMisDatos().getTema(), aliasr);
            ((EstadoSecretarioGIG)senderHandler.getAg().getState()).getMisDatos().setAliasr(aliasr);
            Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, myHandler, numgrupo);
            EventBESA event = new EventBESA(GuardaRecibirRespuestasGenerarInformesGrupo.class.getName(), data);
            senderHandler.sendEvent(event);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRecibirSolicitudInformeGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static String showChooser(String rootDirectory, boolean directoryOnly) throws HeadlessException {
        JFileChooser chooser = new JFileChooser(rootDirectory);
        if(directoryOnly){
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        }else {
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        }
        int returnVal = chooser.showOpenDialog(new JFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            if(directoryOnly){
                rootDirectory=chooser.getSelectedFile().getAbsolutePath();
            }else{
                rootDirectory=chooser.getSelectedFile().getName();
            }
        }
        return rootDirectory;
    }
    
}
