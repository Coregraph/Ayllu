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
import aypuy.directorymanager.agent.DirectoryAgentAYPUY;
import aypuy.directorymanager.agent.data.DIRDirectoryManagementData;
import aypuy.directorymanager.agent.guards.DIR_RegisterLogicalManagerAYPUY;
import aypuy.logicalmgr.agent.LogicalManagerAgentAYPUY;
import aypuy.logicalmgr.agent.data.LMRegisterPhysicalManagerData;
import aypuy.logicalmgr.agent.guards.LM_RegisterPhysicalAgentAYPUY;
import aypuy.physicalmgr.agent.PhysicalManagerAgentAYPUY;
import aypuy.workspacemgr.agent.WorkSpaceManagerAgentAYPUY;
import aypuy.workspacemgr.agent.data.RoleManagementDataAYPUY;
import aypuy.workspacemgr.agent.data.WSManagementDataAYPUY;
import aypuy.workspacemgr.agent.guards.WSM_ManagementGuardAYPUY;
import aypuy.workspacemgr.workspace.WorkSpaceAYPUY;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yolima
 */
public class GuardaCrearDirectorio extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA event) {
        try { 
            
          System.out.println("En GuardaCrearDirectorio ");
          
          //el jandler del agente 
          AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
          
          //datos a recibir de la guarda entrante
          Ayllu_Data_Message theData = (Ayllu_Data_Message) event.getData();
          DatosCrearRecursos datosR = (DatosCrearRecursos) theData.getMessage();
            
          //crear directorio de recursos principal
          createDirectory();
          System.out.println("AYPUY: Directorio creado");

          //crear espacios de trabajo: uno general, uno para base, uno para tema, uno por cada grupo base, uno porcada grupo tematico
          createWorkSpaceManager(datosR.getNumGruposBase(), datosR.getNumGruposTema());
          System.out.println("AYPUY: Espacios de trabajo creados");

          //crear agente manejador logico -  auto evento
          
          datosR.setNombreLog("Log_Profesor");
          Ayllu_Data_Message datosInst = new Ayllu_Data_Message(Ayllu_EventTypes.AUTH_REQUEST, myHandler, datosR);
          EventBESA evI = new EventBESA(GuardaCrearRecursos.class.getName(), datosInst);
          myHandler.sendEvent(evI);  
          System.out.println("AYPUY: Agente lógico principal creado");
            
        } catch (InterruptedException ex) {
            Logger.getLogger(GuardaCrearDirectorio.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private void createDirectory() throws ExceptionBESA, InterruptedException {
         Properties props = new Properties();
        try {           
            props.load(new FileInputStream("aypuyConfig.properties"));
            DirectoryAgentAYPUY directory = new DirectoryAgentAYPUY(props.getProperty("directoryalias"), 0.91);
            directory.start();
        } catch (IOException ex) {
            Logger.getLogger(GuardaControlarJigsawASHYI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createWorkSpaceManager(int gb, int gt) throws ExceptionBESA {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("aypuyConfig.properties"));
            WorkSpaceManagerAgentAYPUY manager = new WorkSpaceManagerAgentAYPUY(props.getProperty("wsmanageralias"), 0.91);
            manager.start();

            AgHandlerBESA handler = this.getAgent().getAdmLocal().getHandlerByAid(manager.getAid());

            RoleManagementDataAYPUY rolemgmt;
            WSManagementDataAYPUY wsmgmt;
            
            /*
             * Espacio de trabajo general
             */
            wsmgmt = new WSManagementDataAYPUY("Jigsaw", WSManagementDataAYPUY.EnumWSMgmtAYPUY.CREATE_ROOTWS);
            EventBESA ev = new EventBESA(WSM_ManagementGuardAYPUY.class.getName(), wsmgmt);
            handler.sendEvent(ev);

            /**
             *  Espacio de trabajo general grupo base
             */
            wsmgmt = new WSManagementDataAYPUY("Base", "Jigsaw");
            ev = new EventBESA(WSM_ManagementGuardAYPUY.class.getName(), wsmgmt);
            handler.sendEvent(ev);
            
            /**
             *  Espacio de trabajo para cada grupo base
             */
            for(int i = 0;i < gb ;i++)
            {
                 
                wsmgmt = new WSManagementDataAYPUY("Base_"+i, "Base");
                ev = new EventBESA(WSM_ManagementGuardAYPUY.class.getName(), wsmgmt);
                handler.sendEvent(ev);
                
                /**
                 * rol: Participante
                 * ws: base
                 */
                rolemgmt = new RoleManagementDataAYPUY("Participante", "Base_"+i, RoleManagementDataAYPUY.EnumRoleMgmtAYPUY.ADD_ROLE);
                ev = new EventBESA(WSM_ManagementGuardAYPUY.class.getName(), rolemgmt);
                handler.sendEvent(ev);
                
                /**
                 * rol: secretario
                 * ws: tematico
                 */
                rolemgmt = new RoleManagementDataAYPUY("Secretario", "Base_"+i, RoleManagementDataAYPUY.EnumRoleMgmtAYPUY.ADD_ROLE);
                ev = new EventBESA(WSM_ManagementGuardAYPUY.class.getName(), rolemgmt);
                handler.sendEvent(ev);
                
                //permisos Participante: todos los permisos (no eliminar)
                rolemgmt = new RoleManagementDataAYPUY("Participante", "Base_"+i, RoleManagementDataAYPUY.EnumRoleMgmtAYPUY.ADD_PERM, WorkSpaceAYPUY.UPDATE, WorkSpaceAYPUY.RETRIEVE, WorkSpaceAYPUY.CREATE);
                ev = new EventBESA(WSM_ManagementGuardAYPUY.class.getName(), rolemgmt);
                handler.sendEvent(ev);
                
                //permisos secretario: todos los permisos
                rolemgmt = new RoleManagementDataAYPUY("Secretario", "Base_"+i, RoleManagementDataAYPUY.EnumRoleMgmtAYPUY.ADALL_PERM);
                ev = new EventBESA(WSM_ManagementGuardAYPUY.class.getName(), rolemgmt);
                handler.sendEvent(ev);
            }
            
            /**
             * Espacio de trabajo general grupo temático
             */
            wsmgmt = new WSManagementDataAYPUY("Tematico", "Jigsaw");
            ev = new EventBESA(WSM_ManagementGuardAYPUY.class.getName(), wsmgmt);
            handler.sendEvent(ev);
            
             /**
             *  Espacio de trabajo para cada grupo temático
             */
            for(int i = 0;i < gt ;i++)
            {
                 
                wsmgmt = new WSManagementDataAYPUY("Tema_"+i, "Tematico");
                ev = new EventBESA(WSM_ManagementGuardAYPUY.class.getName(), wsmgmt);
                handler.sendEvent(ev);
                
                /**
                 * rol: Participante
                 * ws: tematico
                 */
                rolemgmt = new RoleManagementDataAYPUY("Participante", "Tema_"+i, RoleManagementDataAYPUY.EnumRoleMgmtAYPUY.ADD_ROLE);
                ev = new EventBESA(WSM_ManagementGuardAYPUY.class.getName(), rolemgmt);
                handler.sendEvent(ev);
                
                /**
                 * rol: secretario
                 * ws: tematico
                 */
                rolemgmt = new RoleManagementDataAYPUY("Secretario", "Tema_"+i, RoleManagementDataAYPUY.EnumRoleMgmtAYPUY.ADD_ROLE);
                ev = new EventBESA(WSM_ManagementGuardAYPUY.class.getName(), rolemgmt);
                handler.sendEvent(ev);
                
                //permisos Participante: todos los permisos (no eliminar)
                rolemgmt = new RoleManagementDataAYPUY("Participante", "Tema_"+i, RoleManagementDataAYPUY.EnumRoleMgmtAYPUY.ADD_PERM, WorkSpaceAYPUY.UPDATE, WorkSpaceAYPUY.RETRIEVE, WorkSpaceAYPUY.CREATE);
                ev = new EventBESA(WSM_ManagementGuardAYPUY.class.getName(), rolemgmt);
                handler.sendEvent(ev);
                
                //permisos secretario: todos los permisos
                rolemgmt = new RoleManagementDataAYPUY("Secretario", "Tema_"+i, RoleManagementDataAYPUY.EnumRoleMgmtAYPUY.ADALL_PERM);
                ev = new EventBESA(WSM_ManagementGuardAYPUY.class.getName(), rolemgmt);
                handler.sendEvent(ev);
            }            
            
            /**
             * rol: profesor
             * ws: jigsaw
             */            
            rolemgmt = new RoleManagementDataAYPUY("Profesor", "Jigsaw", RoleManagementDataAYPUY.EnumRoleMgmtAYPUY.ADD_ROLE);
            ev = new EventBESA(WSM_ManagementGuardAYPUY.class.getName(), rolemgmt);
            handler.sendEvent(ev);
            
            /**
             * rol: Participante
             * ws: base
             */
            rolemgmt = new RoleManagementDataAYPUY("Participante", "Base", RoleManagementDataAYPUY.EnumRoleMgmtAYPUY.ADD_ROLE);
            ev = new EventBESA(WSM_ManagementGuardAYPUY.class.getName(), rolemgmt);
            handler.sendEvent(ev);
            
            /**
             * rol: secretario
             * ws: base
             */
            rolemgmt = new RoleManagementDataAYPUY("Secretario", "Base", RoleManagementDataAYPUY.EnumRoleMgmtAYPUY.ADD_ROLE);
            ev = new EventBESA(WSM_ManagementGuardAYPUY.class.getName(), rolemgmt);
            handler.sendEvent(ev);
            
            /**
             * rol: Participante
             * ws: tematico
             */
            rolemgmt = new RoleManagementDataAYPUY("Participante", "Tematico", RoleManagementDataAYPUY.EnumRoleMgmtAYPUY.ADD_ROLE);
            ev = new EventBESA(WSM_ManagementGuardAYPUY.class.getName(), rolemgmt);
            handler.sendEvent(ev);    
            
            /**
             * rol: secretario
             * ws: tematico
             */
            rolemgmt = new RoleManagementDataAYPUY("Secretario", "Tematico", RoleManagementDataAYPUY.EnumRoleMgmtAYPUY.ADD_ROLE);
            ev = new EventBESA(WSM_ManagementGuardAYPUY.class.getName(), rolemgmt);
            handler.sendEvent(ev);
            
            /*
             * Permisos a roles sobre espacios de trabajo
             */
            
            //profesor: todos los permisos
            rolemgmt = new RoleManagementDataAYPUY("Profesor", "Jigsaw", RoleManagementDataAYPUY.EnumRoleMgmtAYPUY.ADALL_PERM);
            ev = new EventBESA(WSM_ManagementGuardAYPUY.class.getName(), rolemgmt);
            handler.sendEvent(ev);
            
            //estudainte: todos los permisos (no eliminar)
            rolemgmt = new RoleManagementDataAYPUY("Participante", "Base", RoleManagementDataAYPUY.EnumRoleMgmtAYPUY.ADD_PERM, WorkSpaceAYPUY.UPDATE, WorkSpaceAYPUY.RETRIEVE, WorkSpaceAYPUY.CREATE);
            ev = new EventBESA(WSM_ManagementGuardAYPUY.class.getName(), rolemgmt);
            handler.sendEvent(ev);
            
            //estudainte: todos los permisos (no eliminar)
            rolemgmt = new RoleManagementDataAYPUY("Participante", "Tematico", RoleManagementDataAYPUY.EnumRoleMgmtAYPUY.ADD_PERM, WorkSpaceAYPUY.UPDATE, WorkSpaceAYPUY.RETRIEVE, WorkSpaceAYPUY.CREATE);
            ev = new EventBESA(WSM_ManagementGuardAYPUY.class.getName(), rolemgmt);
            handler.sendEvent(ev);
            
            //secretario: todos los permisos
            rolemgmt = new RoleManagementDataAYPUY("Secretario", "Base", RoleManagementDataAYPUY.EnumRoleMgmtAYPUY.ADALL_PERM);
            ev = new EventBESA(WSM_ManagementGuardAYPUY.class.getName(), rolemgmt);
            handler.sendEvent(ev);
            
            //secretario: todos los permisos
            rolemgmt = new RoleManagementDataAYPUY("Secretario", "Tematico", RoleManagementDataAYPUY.EnumRoleMgmtAYPUY.ADALL_PERM);
            ev = new EventBESA(WSM_ManagementGuardAYPUY.class.getName(), rolemgmt);
            handler.sendEvent(ev);            
            
        } catch (IOException ex) {
            Logger.getLogger(GuardaControlarJigsawASHYI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
