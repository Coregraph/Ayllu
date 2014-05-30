/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.generarInformeTema.aprendiz;

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
import servicios.generarInformeTema.EstadoGenerarInformeTema;
import servicios.generarInformeTema.secretario.EstadoSecretarioGIT;
import servicios.generarInformeTema.secretario.GuardaRecibirRespuestasGenerarInformesTema;

/**
 *
 * @author AylluAdmin
 */
public class GuardaRecibirSolicitudInformeTema extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        try {
            System.out.println("En GuardaRecibirSolicitudInformeTema " + this.getAgent().getAlias());

            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
            EstadoAprendizGIT miEstado = (EstadoAprendizGIT) myHandler.getAg().getState();
            AgHandlerBESA secretaryHandler = this.getAgent().getAdmLocal().getHandlerByAlias(miEstado.getMisDatos().getAliasSecretario());

            Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
            EstadoGenerarInformeTema estadoAT = (EstadoGenerarInformeTema) theData.getMessage();
            AgHandlerBESA senderHandler = theData.getSenderHandler();
            String aliasr = estadoAT.getMisDatos().getAliasRecurso();

            switch (theData.getEventType()) {

                case QUEST_REQUEST:
                    

                    manejarPeticion(aliasr,miEstado.getMisDatos().getGruposPreguntasPorEstudiante(), estadoAT.getMisDatos().getNumeroGrupo());//Metodo que maneja la petición al IA

                    
                    String informe = "Informe que da respuesta a preguntas -> " + miEstado.getMisDatos().getGruposPreguntasPorEstudiante();
                    manejarRespuesta(aliasr, myHandler, secretaryHandler, estadoAT.getMisDatos().getNumeroGrupo());
                    //-------------------------------------------------------

                    break;

                case QUEST_REPLY:

                    manejarRespuesta(aliasr, myHandler, senderHandler, estadoAT.getMisDatos().getNumeroGrupo());  //Metodo que maneja la respuesta del IA

                    break;

            }
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRecibirSolicitudInformeTema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void manejarPeticion(String aliasr,GrupoPreguntas grupoPreguntas, int numgrupo) throws ExceptionBESA {
        try {
            
            System.out.println("Por favor subir un OA resultado de sus preguntas y discusión seleccionadas " + grupoPreguntas);
            JOptionPane.showMessageDialog(null,"Por favor subir un OA resultado de sus preguntas y discusión seleccionadas " + grupoPreguntas);
             /**
             * |||||||||Inserción Recurso||||||||||||
             */            
            BitSet elbitBitSet = new BitSet(4);
            elbitBitSet.set(0);
            elbitBitSet.set(1);
            Properties props = new Properties();
            props.load(new FileInputStream("aypuyConfig.properties"));
            AgHandlerBESA hand = this.getAgent().getAdmLocal().getHandlerByAlias(props.getProperty("wsmanageralias"));
            AgHandlerBESA recursosHandler = this.getAgent().getAdmLocal().getHandlerByAlias(aliasr);
            String fileToSend=showChooser(((EstadoAgenteRecursos)recursosHandler.getAg().getState()).getMisDatos().getRootDirectory(),false);        
            WSManageResourceDataAYPUY dtaIns = new WSManageResourceDataAYPUY(SMA_ReceiveHandlerAYPUY.class.getName(),this.getAgent().getAid(), WorkSpaceAYPUY.UPDATE, elbitBitSet, "Participante", "Tema_"+numgrupo, new FileDescriptionAYPUY("Tema_"+numgrupo+"_"+this.getAgent().getAlias(), fileToSend));
            EventBESA evIns = new EventBESA(WSM_ResourceREQGuardAYPUY.class.getName(), dtaIns);
            hand.sendEvent(evIns);
        } catch (IOException ex) {
            Logger.getLogger(GuardaRecibirSolicitudInformeTema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void manejarRespuesta(String aliasr, AgHandlerBESA myHandler, AgHandlerBESA senderHandler, int numgrupo) throws ExceptionBESA {
        //TODO Se debe esperar respuesta de la generación del informe para las preguntas seleccionadas.

        try {
            ((EstadoSecretarioGIT)senderHandler.getAg().getState()).getMisDatos().getOAPorAprendiz().put(myHandler.getAlias(), aliasr);
            ((EstadoSecretarioGIT)senderHandler.getAg().getState()).getMisDatos().setAliasr(aliasr);
            Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, myHandler, numgrupo);
            EventBESA event = new EventBESA(GuardaRecibirRespuestasGenerarInformesTema.class.getName(), data);
            senderHandler.sendEvent(event);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRecibirSolicitudInformeTema.class.getName()).log(Level.SEVERE, null, ex);
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
