/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.generarInformeTema.secretario;

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
import servicios.generarInformeTema.DatosGenerarInformeTema;
import servicios.generarInformeTema.EstadoGenerarInformeTema;
import servicios.generarInformeTema.GuardaRecibirOAGeneracionInformesTemas;

/**
 *
 * @author AylluAdmin
 */
public class GuardaRecibirRespuestasGenerarInformesTema extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        try {
            System.out.println("En la guarda GuardaRecibirRespuestasGenerarInformesTema");
            DatosSecretarioGIT misDatos = ((EstadoSecretarioGIT) this.getAgent().getState()).getMisDatos();
            EstadoSecretarioGIT miEstado = (EstadoSecretarioGIT) this.getAgent().getState();
            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
            
            Ayllu_Data_Message theData = (Ayllu_Data_Message) ebesa.getData();

            int numgrupo = (Integer)theData.getMessage();
            AgHandlerBESA handlerCMA = miEstado.getCommunityManagerHandler();

            Tema tema = ((EstadoSecretarioGIT) this.getAgent().getState()).getMisDatos().getTemaManejado();
            String aliasr = misDatos.getAliasr();
            
            switch (theData.getEventType()) {

                case QUEST_REQUEST:
                    
                     if (((EstadoGenerarInformeTema)miEstado.getCommunityManagerHandler().getAg().getState()).getMisDatos().getNumeroOAenviado() != ((EstadoGenerarInformeTema)miEstado.getCommunityManagerHandler().getAg().getState()).getMisDatos().getEstudiantes().size()) {
                         ((EstadoGenerarInformeTema)miEstado.getCommunityManagerHandler().getAg().getState()).getMisDatos().setNumeroOAenviado(((EstadoGenerarInformeTema)miEstado.getCommunityManagerHandler().getAg().getState()).getMisDatos().getNumeroOAenviado()+1);
                     }
                    //Todos han reportado el OA Resumen. 
                    if (((EstadoGenerarInformeTema)miEstado.getCommunityManagerHandler().getAg().getState()).getMisDatos().getNumeroOAenviado() == ((EstadoGenerarInformeTema)miEstado.getCommunityManagerHandler().getAg().getState()).getMisDatos().getEstudiantes().size()-1) 
                    {
                        
                        manejarPeticion(tema);//Metodo que maneja la petición al IA

                        manejarRespuesta(tema, myHandler, handlerCMA, aliasr, numgrupo);  //Metodo que maneja la respuesta del IA

                        
                    }
                    break;

                case QUEST_REPLY:

                    manejarRespuesta(tema, myHandler, handlerCMA, aliasr, numgrupo);  //Metodo que maneja la respuesta del IA

                    break;

            }
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRecibirRespuestasGenerarInformesTema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void manejarPeticion(Tema tema) {
        System.out.println("Todos los estudaintes del tema " + tema + "cargaron sus respectivos OA");
        System.out.println("Por favor generar el resumen integrado de temas");
    }
    
    private void manejarRespuesta(Tema tema, AgHandlerBESA myHandler, AgHandlerBESA senderHandler, String aliasr, int numgrupo) throws ExceptionBESA {
        try {
            Object OAresumenTema = "Recurso subido";
            
            System.out.println("Secretario: Por favor subir un OA resultado del proceso de su tema " + tema);
            JOptionPane.showMessageDialog(null,"Secretario: Por favor subir un OA resultado del proceso de su tema " + tema);
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
            WSManageResourceDataAYPUY dtaIns = new WSManageResourceDataAYPUY(SMA_ReceiveHandlerAYPUY.class.getName(),this.getAgent().getAid(), WorkSpaceAYPUY.UPDATE, elbitBitSet, "Secretario", "Tema_"+numgrupo, new FileDescriptionAYPUY("Secretario_Tema_"+numgrupo, fileToSend));
            EventBESA evIns = new EventBESA(WSM_ResourceREQGuardAYPUY.class.getName(), dtaIns);
            hand.sendEvent(evIns);
                    
            DatosGenerarInformeTema datosGenerarInformeTema = new DatosGenerarInformeTema(numgrupo);
            datosGenerarInformeTema.setIdRecurso("Secretario_Tema_"+numgrupo);
            datosGenerarInformeTema.getOAResumenPorTema().put(tema, OAresumenTema);
            EstadoGenerarInformeTema estadoGenerarInformeTema = new EstadoGenerarInformeTema(datosGenerarInformeTema);

            
            Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, myHandler, estadoGenerarInformeTema);
            EventBESA event = new EventBESA(GuardaRecibirOAGeneracionInformesTemas.class.getName(), data);
            senderHandler.sendEvent(event);
        } catch (IOException ex) {
            Logger.getLogger(GuardaRecibirRespuestasGenerarInformesTema.class.getName()).log(Level.SEVERE, null, ex);
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
