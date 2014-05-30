/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.General;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import aypuy.directorymanager.agent.data.DIRDirectoryManagementData;
import aypuy.directorymanager.agent.guards.DIR_RegisterLogicalManagerAYPUY;
import aypuy.logicalmgr.agent.LogicalManagerAgentAYPUY;
import aypuy.logicalmgr.agent.data.LMRegisterPhysicalManagerData;
import aypuy.logicalmgr.agent.guards.LM_RegisterPhysicalAgentAYPUY;
import aypuy.physicalmgr.agent.PhysicalManagerAgentAYPUY;
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

/**
 *
 * @author Yolima
 */
public class GuardaCrearRecursos extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA event) {
        try {     
            
            System.out.println("en GuardaCrearRecursos");
            AgHandlerBESA  handler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
            Estado_JigsawASHYI miEstado = (Estado_JigsawASHYI) handler.getAg().getState();
            Ayllu_Data_Message theData = (Ayllu_Data_Message) event.getData();
            DatosCrearRecursos datos = (DatosCrearRecursos)theData.getMessage();
            
            if(datos.getFuncion() == 1)//directorio base de jigsaw
            {
                createLogicalAgent(datos.getNombreLog());
                //crear manejador de directorios físicos                 
                  JOptionPane.showMessageDialog(null,"Por favor seleccione el directorio padre ");
                  String rootDirectory = "";
                  rootDirectory = showChooser(rootDirectory,true)+"\\";
                  miEstado.setDirectorio(rootDirectory);
                  int opcion = 2;
                  datos.setFuncionAnterior(datos.getFuncion());
                  datos.setFuncion(opcion);
                  datos.setNombreLog("Log_Profesor");
                  datos.setNombre("PM_Profesor");
                  datos.setDirectorio(rootDirectory);
                  Ayllu_Data_Message datosInst = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, handler, datos);
                  EventBESA evI = new EventBESA(GuardaCrearRecursos.class.getName(), datosInst);
                  handler.sendEvent(evI); 
                  System.out.println("AYPUY: Agente físico principal creado");
            }
            else  if(datos.getFuncion() == 2)//directorio temático
            {
                createPhysicalAgent(datos.getDirectorio(),datos.getNombre(),datos.getNombreLog());                  
                //Pasar control a jigsaw --> despúes que todos los estudiantes se registren
                
                if(datos.getFuncionAnterior() == 1)
                {
                    miEstado.setEstadoEstrategia(Estado_JigsawASHYI.ESPERAR_REGISTRO);
                    Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.START_COOP, handler, ((Estado_JigsawASHYI)handler.getAg().getState()));
                    EventBESA autoevento = new EventBESA(GuardaControlarJigsawASHYI.class.getName(),data);
                    handler.sendEvent(autoevento);
                }
            }
            else  if(datos.getFuncion() == 3)//grupo temático
            {
                createLogicalAgent(datos.getNombreLog());
                //crear manejador de directorios físicos     
                  int opcion = 2;
                  String alias = datos.getNombreLog();
                  datos.setFuncion(opcion);
                  datos.setNombre("PM_"+alias);
                  datos.setDirectorio(miEstado.getDirectorio());
                  Ayllu_Data_Message datosInst = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, handler, datos);
                  EventBESA evI = new EventBESA(GuardaCrearRecursos.class.getName(), datosInst);
                  handler.sendEvent(evI); 
                  System.out.println("AYPUY: Agente físico principal creado");
            }
            else  if(datos.getFuncion() == 4)//grupo base
            {
                createLogicalAgent(datos.getNombreLog());
                //crear manejador de directorios físicos     
                  int opcion = 5;
                  String alias = datos.getNombreLog();
                  datos.setFuncion(opcion);
                  datos.setNombre("PM_"+alias);
                  datos.setDirectorio(miEstado.getDirectorio());
                  Ayllu_Data_Message datosInst = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, handler, datos);
                  EventBESA evI = new EventBESA(GuardaCrearRecursos.class.getName(), datosInst);
                  handler.sendEvent(evI); 
                  System.out.println("AYPUY: Agente físico principal creado");
            }
             else  if(datos.getFuncion() == 5)//directorio base
            {
                createPhysicalAgent(datos.getDirectorio(),datos.getNombre(),datos.getNombreLog());
            }
            
        } catch (ExceptionBESA excbesa) {
            ReportBESA.error(excbesa);
        }

    }
    
    private void createLogicalAgent(String nombre) {
        BitSet abitset = new BitSet();
        abitset.set(0);
        abitset.set(1);
        Properties props = new Properties();            
        try {
            new LogicalManagerAgentAYPUY(nombre, abitset, 0.91).start();
       
            DIRDirectoryManagementData datamgmt = new DIRDirectoryManagementData(abitset, this.getAgent().getAdmLocal().getHandlerByAlias(nombre).getAgId());
            EventBESA event = new EventBESA(DIR_RegisterLogicalManagerAYPUY.class.getName(), datamgmt);
            props.load(new FileInputStream("aypuyConfig.properties"));
            this.getAgent().getAdmLocal().getHandlerByAlias(props.getProperty("directoryalias")).sendEvent(event);
            
        } catch (IOException ex) {
            Logger.getLogger(GuardaControlarJigsawASHYI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(LogicalManagerAgentAYPUY.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createPhysicalAgent(String rootFolder, String nombre, String nombreLog) {
        try {
            PhysicalManagerAgentAYPUY pagent = new PhysicalManagerAgentAYPUY(nombre, rootFolder, 0.91);
            pagent.start();

            LMRegisterPhysicalManagerData reg = new LMRegisterPhysicalManagerData(pagent.getAid());
            EventBESA eventreg = new EventBESA(LM_RegisterPhysicalAgentAYPUY.class.getName(), reg);
            this.getAgent().getAdmLocal().getHandlerByAlias(nombreLog).sendEvent(eventreg);

        } catch (KernellAgentExceptionBESA ex) {
            Logger.getLogger(GuardaControlarJigsawASHYI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaControlarJigsawASHYI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String showChooser(String rootDirectory, boolean directoryOnly) throws HeadlessException {
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
