/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.generarInformeGrupo.secretario;

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
import persistencia.Tema;
import recursos.EstadoAgenteRecursos;
import recursos.SMA_ReceiveHandlerAYPUY;
import servicios.generarInformeGrupo.DatosGenerarInformeGrupo;
import servicios.generarInformeGrupo.EstadoGenerarInformeGrupo;
import servicios.generarInformeGrupo.GuardaRecibirOAGeneracionInformesGrupo;

/**
 *
 * @author AylluAdmin
 */
public class GuardaRecibirRespuestasGenerarInformesGrupo extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        try {
            System.out.println("En la guarda GuardaRecibirRespuestasGenerarInformesGrupo");
            DatosSecretarioGIG misDatos = ((EstadoSecretarioGIG) this.getAgent().getState()).getMisDatos();
            EstadoSecretarioGIG miEstado = (EstadoSecretarioGIG) this.getAgent().getState();
            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
            
            Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();
            
            int numgrupo = (Integer)theData.getMessage();
            
            AgHandlerBESA handlerCMA = miEstado.getCommunityManagerHandler();
            String aliasr = misDatos.getAliasr();

            Tema tema = miEstado.getMisDatos().getTemaManejado();
            
            switch (theData.getEventType()) {

                case QUEST_REQUEST:

                    
                    manejarPeticion(tema);//Metodo que maneja la petición al IA

                    manejarRespuesta(tema, myHandler, handlerCMA, aliasr, numgrupo);
                    //--------------------------------------------------------------

                    break;

                case QUEST_REPLY:

                    manejarRespuesta(tema, myHandler, handlerCMA, aliasr, numgrupo);  //Metodo que maneja la respuesta del IA

                    break;

            }
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRecibirRespuestasGenerarInformesGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void manejarPeticion(Tema tema) {
        System.out.println("Todos los estudaintes del tema " + tema + "cargaron sus respectivos OA");
        System.out.println("Por favor generar el resumen integrado de temas");
    }
    
    private void manejarRespuesta(Tema tema, AgHandlerBESA myHandler, AgHandlerBESA senderHandler, String aliasr, int numgrupo) throws ExceptionBESA {
        try {
            Object OAresumenTema = "Recurso subido";
                
            System.out.println("Secretario: Por favor subir un OA resultado del proceso de su grupo " + tema);
            JOptionPane.showMessageDialog(null,"Secretario: Por favor subir un OA resultado del proceso de su grupo " + tema);
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
            WSManageResourceDataAYPUY dtaIns = new WSManageResourceDataAYPUY(SMA_ReceiveHandlerAYPUY.class.getName(),this.getAgent().getAid(), WorkSpaceAYPUY.UPDATE, elbitBitSet, "Secretario", "Base_"+numgrupo, new FileDescriptionAYPUY("Secretario_Base_"+numgrupo, fileToSend));
            EventBESA evIns = new EventBESA(WSM_ResourceREQGuardAYPUY.class.getName(), dtaIns);
            hand.sendEvent(evIns);
            
            
            DatosGenerarInformeGrupo datosGenerarInformeTema = new DatosGenerarInformeGrupo(numgrupo);
            datosGenerarInformeTema.setIdRecurso("Secretario_Base_"+numgrupo);
            datosGenerarInformeTema.getOAResumenPorTema().put(tema, OAresumenTema);
            EstadoGenerarInformeGrupo estadoGenerarInformeTema = new EstadoGenerarInformeGrupo(datosGenerarInformeTema);

            
            Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.START_COOP, myHandler, estadoGenerarInformeTema);
            EventBESA event = new EventBESA(GuardaRecibirOAGeneracionInformesGrupo.class.getName(), data);
            senderHandler.sendEvent(event);
        } catch (IOException ex) {
            Logger.getLogger(GuardaRecibirRespuestasGenerarInformesGrupo.class.getName()).log(Level.SEVERE, null, ex);
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
