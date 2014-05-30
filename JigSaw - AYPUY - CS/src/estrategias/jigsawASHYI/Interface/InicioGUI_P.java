/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.Interface;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import co.edu.javeriana.ayllu.agents.factoryagent.FA_CMACreationGuard;
import co.edu.javeriana.ayllu.agents.factoryagent.FA_CMAEndNestedServiceGuard;
import co.edu.javeriana.ayllu.agents.factoryagent.FactoryAgent;
import co.edu.javeriana.ayllu.data.Ayllu_Agent_Creation_Message;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import estrategias.factory.EstadoFactory;
import estrategias.factory.FactoryASHYI;
import estrategias.jigsawASHYI.General.Estado_JigsawASHYI;
import estrategias.jigsawASHYI.General.GuardaEnviarGrupos;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import recursos.AgenteRecurso;
import recursos.EstadoAgenteRecursos;

/**
 *
 * @author Ayllu
 */
public class InicioGUI_P {

    private JFrame jf_mainFrame;
    private JLabel jl_Titulo;
    private JButton jb_messageButton;//enviar retroalimentación
    private JPanel jp_buttonPanel;
    private JPanel jp_optionPanel;
    /*
     * Opciones Estudiante - Profesor
     */
    private JComboBox JCBopciones = null;
    private boolean profeRegistrado;
    private String numero;
    
    public InicioGUI_P(String numero) {
            profeRegistrado = false;
            jp_optionPanel = null;
            jb_messageButton = null;
            jl_Titulo = null;
            jf_mainFrame = null;
            this.numero = numero;
    }

    public JButton getJb_messageButton() {
        if (jb_messageButton == null) {
            jb_messageButton = new JButton("Aceptar");
            jb_messageButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   
                    //BESA Administrator
                    AdmBESA admLocal=null;

                    int opSeleccionada = getOpciones().getSelectedIndex();
                    if(opSeleccionada == 0)
                    {
                        try {
                            admLocal = AdmBESA.getInstance("res/confbesa.xml");                    
                        
                            ReportBESA.info("Creando Contenedor Profesor "+ admLocal.getAdmHandler().getAlias());
//                            Enumeration<String> containers = admLocal.getAdmAliasList();
//                            while(!containers.hasMoreElements())
//                            {
//                               containers = admLocal.getAdmAliasList();                                
//                            }
                                
                                //ingreso del profesor
                                profeRegistrado = true;
                                
                                //Se crea en struct y se añaden guardas por defecto al agente que herede de FactoryAgent
                                FactoryAgent.prepareInstance();
                                //Se crea la nueva instancia del agente que hereda de FactoryAgent
                                //args: ID, Estado, password
                                FactoryASHYI factory = new FactoryASHYI("FACTORY", new EstadoFactory(), 0.91);   
                                //se inicia el agente
                                factory.start();
                                inicio(admLocal);
                        } catch (KernellAgentExceptionBESA ex) {
                            Logger.getLogger(InicioGUI_P.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else if(opSeleccionada == 1)
                    {                        
                        try {            
                    
                            admLocal = AdmBESA.getInstance("res/confbesa_"+numero+".xml");
                            ReportBESA.info("Creando Contenedor "+ admLocal.getAdmHandler().getAlias());
//                            Enumeration<String> containers = admLocal.getAdmAliasList();
//                            while(!containers.hasMoreElements())
//                            {
//                               containers = admLocal.getAdmAliasList();                                
//                            }
//                            while(containers.hasMoreElements()){
//                                    ReportBESA.info(containers.nextElement());    
//                                }
                            
                            AgenteRecurso agentB = new AgenteRecurso("AgenteP"+numero, new EstadoAgenteRecursos(), 77.77);
                            agentB.start();
                            int encuentra = 0;                            
                            
                            AgHandlerBESA handlerJS = null;
                            do
                            {
                                    handlerJS =  admLocal.getHandlerByAlias("CMA_JS");
                                    if(handlerJS!=null)
                                    {
                                            System.out.println(handlerJS.getAlias());
                                            encuentra=1;
                                    }
                                    else
                                    {
                                            System.out.println("Esperando CMA pricipal");
                                            Thread.sleep(4000); 
                                    }
                            }while(encuentra==0);
                            
                            System.out.println(handlerJS.getAg());
                            
                            //solicitar grupos                                
                            Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, null,"AgenteP"+numero );
                            EventBESA evento = new EventBESA(GuardaEnviarGrupos.class.getName(), data);
                            handlerJS.sendEvent(evento);
                            EstadoAgenteRecursos estR = (EstadoAgenteRecursos) admLocal.getHandlerByAlias("AgenteP"+numero).getAg().getState();
                     
                            while(estR.getMisDatos().getGrupos().isEmpty())
                            {
                                System.out.println("Esperando grupos");
                            }
                            InicioGUIEstudiante estuGUI = new InicioGUIEstudiante(estR.getMisDatos().getGrupos(),admLocal,numero);
                            estuGUI.showFrame();

                        } catch (InterruptedException ex) {
                            Logger.getLogger(InicioGUI_P.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ExceptionBESA ex) {
                            Logger.getLogger(InicioGUI_P.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                    getJf_mainFrame().setVisible(false);
                }
            });
        }
        return jb_messageButton;
    }

    public void showFrame() {
        getJf_mainFrame().setVisible(true);
    }
     /*
     * Inicialización de opciones
     */
    public JComboBox getOpciones() {
        if (JCBopciones == null) {
            //jl_ToDoLabel = new JLabel("This is " + name);
            String[] opciones = {"Profesor","Estudiante"};
            JCBopciones = new JComboBox(opciones);
        }
        return JCBopciones;
    }
       
    public JLabel getJl_Titulo() {
        if (jl_Titulo == null) {
            //jl_ToDoToLabel = new JLabel("This is " + name);
            jl_Titulo = new JLabel("Bienvenido!\nSeleccione el tipo de usuario");
        }
        return jl_Titulo;
    }

    public JFrame getJf_mainFrame() {

        if (jf_mainFrame == null) {
            jf_mainFrame = new JFrame("AYLLU_EDU - JigSaw");
            jf_mainFrame.setSize(300, 300);
            jf_mainFrame.setContentPane(getJp_buttonPanel());
            jf_mainFrame.getContentPane().setVisible(true);
        }
        return jf_mainFrame;
    }
       
    public JPanel getJp_buttonPanel() {
        if (jp_buttonPanel == null) {
            jp_buttonPanel = new JPanel();
            jp_buttonPanel.setLayout(new BorderLayout());
            getJb_messageButton().setSize(2, 2);
            jp_buttonPanel.add(getJl_Titulo(), BorderLayout.PAGE_START);
            jp_buttonPanel.add(getJp_optionPanel());
            jp_buttonPanel.add(getJb_messageButton(), BorderLayout.PAGE_END);
        }
        return jp_buttonPanel;
    }
    
    public JPanel getJp_optionPanel() {
        if (jp_optionPanel == null) {
            jp_optionPanel = new JPanel();
            jp_optionPanel.setSize(100, 50);
            jp_optionPanel.add(getOpciones());
        }
        return jp_optionPanel;
    }

    public static void inicio(AdmBESA admLocal)
    {
        try {
            //Se obtiene el manejador de la fabrica
            AgHandlerBESA handlerFactory = admLocal.getHandlerByAlias("FACTORY");
            
            Estado_JigsawASHYI estadoJS = new Estado_JigsawASHYI();
            estadoJS.setCourseID("SIST_INT");//sistemas inteligentes
            Ayllu_Agent_Creation_Message dataCreacion = new Ayllu_Agent_Creation_Message("CMA_JS", handlerFactory.getAgId(), FA_CMAEndNestedServiceGuard.class);
            dataCreacion.setMessage(estadoJS);
            EventBESA evento = new EventBESA(FA_CMACreationGuard.class.getName(), dataCreacion);
            handlerFactory.sendEvent(evento);
            
//            AgHandlerBESA handlerCMA = admLocal.getHandlerByAlias("CMA_JS");
//            Estado_Jigsaw miEstado = (Estado_Jigsaw) handlerCMA.getAg().getState();
//            Ayllu_Data_Message misDatos = new Ayllu_Data_Message(Ayllu_EventTypes.COOP_REPLY, handlerFactory, miEstado);
//            EventBESA evI = new EventBESA(GuardaControlarJigsaw.class.getName(), misDatos);
//            handlerCMA.sendEvent(evI);
            
        } catch (ExceptionBESA ex) {
            Logger.getLogger(InicioGUI_P.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
