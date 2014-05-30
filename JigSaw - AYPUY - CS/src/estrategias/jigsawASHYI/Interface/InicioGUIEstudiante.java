/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.Interface;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.interfaceagentagent.InterfaceAgent;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SMA_RegisterIAGuard;
import co.edu.javeriana.ayllu.data.Ayllu_IARegisterData;
import estrategias.jigsawASHYI.Interface.Agents.IA_SynchCommInterface;
import estrategias.jigsawASHYI.Interface.Agents.IA_SynchCommInterfaceState;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Ayllu
 */
public class InicioGUIEstudiante {

    private JFrame jf_mainFrame;
    private JLabel jl_Titulo;
    private JButton jb_messageButton;//enviar retroalimentación
    private JPanel jp_buttonPanel;
    private JPanel jp_optionPanel;
    private String[] grupo;
    private String numero;
    /*
     * Opciones Estudiante - Profesor
     */
    private JComboBox JCBopciones = null;
    private boolean profeRegistrado;
    private AdmBESA admLocal;
    
    public InicioGUIEstudiante(ArrayList<ArrayList<String>> grupos, AdmBESA admLocal, String numero) {
        this.numero = numero;
        this.grupo = new String[200];    
        this.profeRegistrado = false;
        int pos = 0;
            for(int i = 0;i<grupos.size();i++)
                for(int j = 0;j<grupos.get(i).size();j++)
                {
                    this.grupo[pos]=grupos.get(i).get(j);
                    pos++;
                }
            
        this.admLocal = admLocal;
         profeRegistrado = false;
        jp_optionPanel = null;
        jb_messageButton = null;
        jl_Titulo = null;
        jf_mainFrame = null;
    }

    public JButton getJb_messageButton() {
        if (jb_messageButton == null) {
            jb_messageButton = new JButton("Aceptar");
            jb_messageButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                        try 
                        {
                         
                            AgHandlerBESA handlerJS =  admLocal.getHandlerByAlias("CMA_JS");
                            int opSeleccionada = getOpciones().getSelectedIndex();
                            //solicitud agente ia
                            //admLocal.moveAgent("IA_"+String.valueOf(getOpciones().getItemAt(opSeleccionada)), "MAS_ServidorE"+numero,0.91);
//                            DatosSolicitarAg datos = new DatosSolicitarAg("MAS_ServidorE"+numero, "IA_"+String.valueOf(getOpciones().getItemAt(opSeleccionada)));
//                            Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, null,datos );
//                            EventBESA evento = new EventBESA(GuardaEnviarAgIA.class.getName(), data);
//                            handlerJS.sendEvent(evento);
//                            ReportBESA.info("Esperando Agente");
                             //IA
                                InterfaceAgent.prepareIntance();
                                AgHandlerBESA agHSMA = AdmBESA.getInstance().getHandlerByAlias("SMA_"+String.valueOf(getOpciones().getItemAt(opSeleccionada)));
                                IA_SynchCommInterfaceState estadoIA = new IA_SynchCommInterfaceState(agHSMA);                
                                IA_SynchCommInterface iface = new IA_SynchCommInterface("IA_"+String.valueOf(getOpciones().getItemAt(opSeleccionada)), estadoIA, 0.91);
                                MessageGUI gui = new MessageGUI(iface, "");
                                estadoIA.setTheGUI(gui);
                                iface.start();
                                Ayllu_IARegisterData datosR = new Ayllu_IARegisterData(iface.getAid());
                                EventBESA evento = new EventBESA(SMA_RegisterIAGuard.class.getName(), datosR);
                                agHSMA.sendEvent(evento);
                                System.out.println("Se creo: "+iface.getAlias());

                            getJf_mainFrame().setVisible(false);
                    } catch (ExceptionBESA ex) {
                        Logger.getLogger(InicioGUIEstudiante.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
            JCBopciones = new JComboBox(grupo);
        }
        return JCBopciones;
    }

    public JLabel getJl_Titulo() {
        if (jl_Titulo == null) {
            //jl_ToDoToLabel = new JLabel("This is " + name);
            jl_Titulo = new JLabel("Bienvenido!\nSeleccione su usuario");
        }
        return jl_Titulo;
    }

    public JFrame getJf_mainFrame() {

        if (jf_mainFrame == null) {
            jf_mainFrame = new JFrame("AYLLU_EDU - JigSaw");
            jf_mainFrame.setSize(200, 200);            
            jf_mainFrame.setContentPane(getJp_buttonPanel());
            jf_mainFrame.getContentPane().setVisible(true);
        }
        return jf_mainFrame;
    }
    
    public JPanel getJp_optionPanel() {
        if (jp_optionPanel == null) {
            jp_optionPanel = new JPanel();
            jp_optionPanel.setSize(50, 50);
            jp_optionPanel.add(getOpciones());
        }
        return jp_optionPanel;
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
}
