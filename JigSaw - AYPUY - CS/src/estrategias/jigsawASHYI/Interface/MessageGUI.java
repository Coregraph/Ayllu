/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.Interface;

import BESA.Kernel.Agent.Event.DataBESA;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_WallMessageData;
import estrategias.jigsawASHYI.Interface.Agents.IA_SynchCommInterface;
import estrategias.jigsawASHYI.Servicios.Discusion.aprendiz.DatosDiscusionAprendiz;
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.aprendiz.DatosDiscusionAprendizGrupo;
import estrategias.jigsawASHYI.Servicios.DiscusionGrupo.aprendiz.DatosEstudiarOva;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import service.Profesor.DatosEvaluacionInforme;
import service.Profesor.DatosInstrucciones;
import service.Profesor.DatosRetroNota;
import service.Profesor.DatosRetroalimentacion;
import service.Profesor.DatosRetroalimentacionGrupo;

/**
 *
 * @author Ayllu
 */
public class MessageGUI implements Serializable{

    private JFrame jf_mainFrame = null;
    private JLabel jl_Titulo = null;
    private JLabel jl_ToDoLabel = null;
    private JLabel jl_ToDoToLabel = null;
    private JLabel jl_ChatLabel = null;
    private JTextArea jtf_MessageToSend = null;//retroalimentación
    private JTextArea jtf_GradeToSend = null;//retroalimentación
    private JButton jb_messageButton = null;//enviar retroalimentación
    private JButton jb_chatMessageButton = null;
    private JPanel jp_buttonPanel;
    private JPanel jp_labelsPanel = null;
    private JPanel jp_labelsPanel2 = null;
    private JPanel jp_mainPanel = null;
    private JPanel jp_chatPanel = null;
    private JPanel jp_chatButtonPanel = null;
    private JScrollPane jsp_MessagePane = null;
    private JScrollPane scroll1 = null;
    private JScrollPane scroll2 = null;
    private JTextArea jta_MessageQueue = null;
    private JTextField jtf_chatMessageToSend = null;
    private JTabbedPane jtp_content = null;
    private String name;
    private IA_SynchCommInterface iaAgent;
    private DataBESA datos;
    private String aliasCma; //repsuesta
    private String aliasTemp = ""; //repsuesta
    private boolean esProfe = false;

    public MessageGUI(IA_SynchCommInterface theInterfaceAgent, String name) {
        this.iaAgent = theInterfaceAgent;
        this.name = name;
        if (name.contains("Profesor")) {
            esProfe = true;
        }
        aliasCma = "";
        datos = new DataBESA() {
        };
    }

    public JButton getJb_messageButton() {
        if (jb_messageButton == null) {
            jb_messageButton = new JButton("Enviar");
            jb_messageButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (datos instanceof DatosRetroNota) {
                        String preg1 = getJtf_MessageToSend().getText();
                        getJtf_MessageToSend().setText("");
                        String preg2 = getJtf_GradeToSend().getText();
                        getJtf_GradeToSend().setText("");

                        if (getJb_messageButton().getText().equals("Aceptar")) {
                            getJb_messageButton().setText("Enviar");
                            //MessageGUI.this.iaAgent.enviarRtaInstrucciones(aliasCma);
                            datos = new Ayllu_Data_Message(null, null, null);
                        }
                        MessageGUI.this.iaAgent.enviarRtaRetroOA(aliasCma);
                    }

                    if (datos instanceof DatosDiscusionAprendiz) {
                        String preg1 = getJtf_MessageToSend().getText();
                        getJtf_MessageToSend().setText("");
                        String preg2 = getJtf_GradeToSend().getText();
                        getJtf_GradeToSend().setText("");
                        MessageGUI.this.iaAgent.enviarPreguntas(preg1, preg2, aliasCma);
                    }

                    if (datos instanceof DatosDiscusionAprendizGrupo) {
                        getJb_messageButton().setText("Enviar");
                        String preg1 = getJtf_MessageToSend().getText();
                        getJtf_MessageToSend().setText("");
                        String preg2 = getJtf_GradeToSend().getText();
                        getJtf_GradeToSend().setText("");
                        getJb_messageButton().setText("Enviar");
                        MessageGUI.this.iaAgent.enviarPreguntasGrupo(preg1, preg2, aliasCma);
                    }

                    if (datos instanceof DatosEvaluacionInforme) {
                        String messageToSend = getJtf_MessageToSend().getText();
                        getJtf_MessageToSend().setText("");
                        String gradeToSend = getJtf_GradeToSend().getText();
                        getJtf_GradeToSend().setText("");

                        if (messageToSend.isEmpty()) {
                            messageToSend = "Ninguna";
                        }
                        MessageGUI.this.iaAgent.enviarRetroalimentacion(gradeToSend, messageToSend, aliasCma);
                    }

                    if (datos instanceof DatosRetroalimentacion) {
                        if (getJb_messageButton().getText().equals("Aceptar")) {
                            getJb_messageButton().setText("Enviar");  
                            datos = new Ayllu_Data_Message(null, null, null);
                            MessageGUI.this.iaAgent.enviarRtaRetroalimentacionPreg(aliasTemp);                            
                        } else {
                            String messageToSend = getJtf_MessageToSend().getText();
                            getJtf_MessageToSend().setText("");
                            String gradeToSend = getJtf_GradeToSend().getText();
                            getJtf_GradeToSend().setText("");

                            if (messageToSend.isEmpty()) {
                                messageToSend = "Ninguna";
                            }
                            getJb_messageButton().setText("Aceptar");
                            MessageGUI.this.iaAgent.enviarRetroalimentacionPreguntas(messageToSend, ((DatosRetroalimentacion) datos).getPreguntas(), ((DatosRetroalimentacion) datos).getTema(), aliasCma);
                        }
                    }
                    if (datos instanceof DatosRetroalimentacionGrupo) {
                        if (getJb_messageButton().getText().equals("Aceptar")) {
                            getJb_messageButton().setText("Enviar");                            
                            datos = new Ayllu_Data_Message(null, null, null);
                            MessageGUI.this.iaAgent.enviarRtaRetroalimentacionPregGrupo(aliasTemp);  
                        } else {
                            String messageToSend = getJtf_MessageToSend().getText();
                            getJtf_MessageToSend().setText("");
                            String gradeToSend = getJtf_GradeToSend().getText();
                            getJtf_GradeToSend().setText("");

                            if (messageToSend.isEmpty()) {
                                messageToSend = "Ninguna";
                            }
                            getJb_messageButton().setText("Aceptar");
                            MessageGUI.this.iaAgent.enviarRetroalimentacionPreguntasGrupo(messageToSend, ((DatosRetroalimentacionGrupo) datos).getPreguntas(), ((DatosRetroalimentacionGrupo) datos).getTema(), aliasCma);
                        }
                    }

                    if (datos instanceof DatosEstudiarOva) {
                        String messageToSend = getJtf_MessageToSend().getText();
                        getJtf_MessageToSend().setText("");
                        String gradeToSend = getJtf_GradeToSend().getText();
                        getJtf_GradeToSend().setText("");
                        MessageGUI.this.iaAgent.enviarEstudioOa(aliasCma);
                    }

                    if (datos instanceof DatosInstrucciones) {
                        if (getJb_messageButton().getText().equals("Aceptar")) {
                            getJb_messageButton().setText("Enviar");
                            MessageGUI.this.iaAgent.enviarRtaInstrucciones(aliasCma);
                            datos = new Ayllu_Data_Message(null, null, null);
                        } else {
                            String messageToSend = getJtf_MessageToSend().getText();
                            getJtf_MessageToSend().setText("");
                            String gradeToSend = getJtf_GradeToSend().getText();
                            getJtf_GradeToSend().setText("");
                            MessageGUI.this.iaAgent.enviarInstrucciones(messageToSend, aliasCma);
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

    public JLabel getJl_ToDoLabel() {
        if (jl_ToDoLabel == null) {
            //jl_ToDoLabel = new JLabel("This is " + name);
            jl_ToDoLabel = new JLabel("");
        }
        return jl_ToDoLabel;
    }

    public JLabel getJl_ToDoToLabel() {
        if (jl_ToDoToLabel == null) {
            //jl_ToDoToLabel = new JLabel("This is " + name);
            jl_ToDoToLabel = new JLabel("");
        }
        return jl_ToDoToLabel;
    }

    public JLabel getJl_Titulo() {
        if (jl_Titulo == null) {
            //jl_ToDoToLabel = new JLabel("This is " + name);
            jl_Titulo = new JLabel("Bienvenido!");
        }
        return jl_Titulo;
    }

    public JFrame getJf_mainFrame() {

        if (jf_mainFrame == null) {
            jf_mainFrame = new JFrame("AYLLU_EDU");
            jf_mainFrame.setSize(500, 500);
            if (!esProfe) {
                jf_mainFrame.setContentPane(getJp_tabbedPane());
            } else {
                jf_mainFrame.setContentPane(getJp_mainPanel());
            }
            jf_mainFrame.getContentPane().setVisible(true);
        }
        return jf_mainFrame;
    }

    public JPanel getJp_labelsPanel() {
        if (jp_labelsPanel == null) {
            jp_labelsPanel = new JPanel();
            jp_labelsPanel.setLayout(new GridLayout(2, 1));
            jp_labelsPanel.add(getJl_ToDoToLabel());
            jp_labelsPanel.add(getScroll1(getJtf_GradeToSend()));
        }
        return jp_labelsPanel;
    }

    public JPanel getJp_labelsPanel2() {
        if (jp_labelsPanel2 == null) {
            jp_labelsPanel2 = new JPanel();
            jp_labelsPanel2.setLayout(new GridLayout(2, 1));
            jp_labelsPanel2.add(getJl_ToDoLabel());
            jp_labelsPanel2.add(getScroll2(getJtf_MessageToSend()));
        }
        return jp_labelsPanel2;
    }

    public JPanel getJp_buttonPanel() {
        if (jp_buttonPanel == null) {
            jp_buttonPanel = new JPanel();
            jp_buttonPanel.setLayout(new BorderLayout());
            getJb_messageButton().setSize(2, 2);
            jp_buttonPanel.add(getJb_messageButton(), BorderLayout.PAGE_END);
        }
        return jp_buttonPanel;
    }
//    public JPanel getJp_buttonPanel() {
//        if (jp_buttonPanel == null) {
//            jp_buttonPanel = new JPanel();
//            jp_buttonPanel.setLayout(new GridLayout(1, 2));
//            jp_buttonPanel.add(getJtf_MessageToSend());
//            jp_buttonPanel.add(getJb_messageButton());
//        }
//        return jp_buttonPanel;
//    }

    public JPanel getJp_mainPanel() {
        if (jp_mainPanel == null) {
            jp_mainPanel = new JPanel();
            jp_mainPanel.setLayout(new GridLayout(4, 1));
            jp_mainPanel.add(getJl_Titulo());
            jp_mainPanel.add(getJp_labelsPanel());
            jp_mainPanel.add(getJp_labelsPanel2());
            jp_mainPanel.add(getJp_buttonPanel());
//            jp_mainPanel.setLayout(new BorderLayout());                 
//            jp_mainPanel.add(getJl_Titulo(), BorderLayout.NORTH);
//            jp_mainPanel.add(getJp_labelsPanel(), BorderLayout.CENTER);
//            jp_mainPanel.add(getJp_buttonPanel(), BorderLayout.SOUTH);

        }
        return jp_mainPanel;
    }

    public JPanel getJp_chatPanel() {
        if (jp_chatPanel == null) {
            jp_chatPanel = new JPanel();
            jp_chatPanel.setLayout(new BorderLayout());
            jp_chatPanel.add(getJl_ChatLabel(), BorderLayout.NORTH);
            jp_chatPanel.add(getJsp_chatMessagePane(), BorderLayout.CENTER);
            jp_chatPanel.add(getJp_chatButtonPanel(), BorderLayout.SOUTH);
        }
        return jp_chatPanel;
    }

    public JPanel getJp_chatButtonPanel() {
        if (jp_chatButtonPanel == null) {
            jp_chatButtonPanel = new JPanel();
            jp_chatButtonPanel.setLayout(new GridLayout(1, 2));
            jp_chatButtonPanel.add(getJtf_chatMessageToSend());
            jp_chatButtonPanel.add(getJb_chatMessageButton());
        }
        return jp_chatButtonPanel;
    }

    public JButton getJb_chatMessageButton() {
        if (jb_chatMessageButton == null) {
            jb_chatMessageButton = new JButton("Send");
            jb_chatMessageButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String messageToSend = getJtf_chatMessageToSend().getText();
                    getJtf_chatMessageToSend().setText("");
                    MessageGUI.this.iaAgent.sendWallMessage(aliasCma, messageToSend);
                }
            });
        }
        return jb_chatMessageButton;
    }

    public JTextField getJtf_chatMessageToSend() {
        if (jtf_chatMessageToSend == null) {
            jtf_chatMessageToSend = new JTextField();
            jtf_chatMessageToSend.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    int key = e.getKeyCode();
                    if (key == KeyEvent.VK_ENTER) {
                        getJb_messageButton().doClick();
                    }
                }
            });
        }
        return jtf_chatMessageToSend;
    }

    public JLabel getJl_ChatLabel() {
        String nombreUsuario = this.iaAgent.getAlias();
        if (nombreUsuario.contains("IA_")) {
            nombreUsuario = nombreUsuario.substring(3);
        }
        if (jl_ChatLabel == null) {
            jl_ChatLabel = new JLabel("This is " + nombreUsuario);
        }
        return jl_ChatLabel;
    }

    public JScrollPane getJsp_chatMessagePane() {
        if (jsp_MessagePane == null) {
            jsp_MessagePane = new JScrollPane(getJta_MessageQueue());
        }
        return jsp_MessagePane;
    }

    public JTextArea getJta_MessageQueue() {
        if (jta_MessageQueue == null) {
            jta_MessageQueue = new JTextArea("");
            jta_MessageQueue.setEditable(false);
        }
        return jta_MessageQueue;
    }

    public JTextArea getJtf_MessageToSend() {
        if (jtf_MessageToSend == null) {
            jtf_MessageToSend = new JTextArea();
        }
        return jtf_MessageToSend;
    }

    public void mostrarOVA(DataBESA message) {

        if (message instanceof Ayllu_Data_Message) {
            if (((Ayllu_Data_Message) message).getMessage() instanceof DatosEvaluacionInforme) {
                getJb_messageButton().setText("Enviar");
                DatosEvaluacionInforme dato = (DatosEvaluacionInforme) ((Ayllu_Data_Message) message).getMessage();
                aliasCma = dato.getAliasCMA();
                datos = dato;
                getJl_ToDoLabel().setText("Nota");
                getJl_ToDoToLabel().setText("Retroalimentación OVA: " + dato.getOva());
                getJtf_GradeToSend().setEditable(true);
                getJtf_MessageToSend().setEditable(true);
                getJf_mainFrame().repaint();
            }

            if (((Ayllu_Data_Message) message).getMessage() instanceof DatosRetroNota) {
                DatosRetroNota dato = (DatosRetroNota) ((Ayllu_Data_Message) message).getMessage();
                datos = dato;
                aliasCma = dato.getAliasCma();
                getJl_ToDoLabel().setText("Nota");
                getJl_ToDoToLabel().setText("Retroalimentación OVA");
                name = "Usuario";
                getJtf_GradeToSend().setEditable(false);
                getJtf_GradeToSend().setText(dato.getNota());
                getJtf_MessageToSend().setEditable(false);
                getJtf_MessageToSend().setText(dato.getRetroalimentacion());
                getJtf_MessageToSend().setEditable(false);
                getJb_messageButton().setText("Aceptar");
                getJf_mainFrame().repaint();
            }

            if (((Ayllu_Data_Message) message).getMessage() instanceof DatosDiscusionAprendiz) {
                DatosDiscusionAprendiz dato = (DatosDiscusionAprendiz) ((Ayllu_Data_Message) message).getMessage();
                datos = dato;
                getJl_Titulo().setText("Bienvenido para el tema: " + dato.getTema());
                aliasCma = dato.getAliasSecretario();
                getJl_ToDoLabel().setText("Pregunta 2");
                getJl_ToDoToLabel().setText("Pregunta 1");
                getJtf_GradeToSend().setEditable(true);
                getJtf_GradeToSend().setText("");
                getJtf_MessageToSend().setEditable(true);
                getJtf_MessageToSend().setText("");
                name = "Usuario";
                getJf_mainFrame().repaint();
            }

            if (((Ayllu_Data_Message) message).getMessage() instanceof DatosRetroalimentacion) {
                DatosRetroalimentacion dato = (DatosRetroalimentacion) ((Ayllu_Data_Message) message).getMessage();
                datos = dato;
                aliasCma = dato.getAliasSecretario();
                aliasTemp = dato.getAliasCMA();//cma
                if(!aliasTemp.equals(""))
                {
                    String temp = aliasCma;
                    aliasCma = aliasTemp;
                    aliasTemp = temp;
                }
                if (!aliasCma.contains("DT"))//caso gui profesor
                {
                    getJl_ToDoLabel().setText("Retroalimentación");
                    getJl_ToDoToLabel().setText("Preguntas");
                    name = "Usuario";
                    String preguntas = "";
                    for (int i = 0; i < dato.getPreguntas().size(); i++) {
                        preguntas += "Pregunta " + (i + 1) + ": " + dato.getPreguntas().get(i) + "\n";
                    }
                    getJtf_GradeToSend().setText(preguntas);
                    getJtf_GradeToSend().setEditable(false);
                } else //caso aprendices
                {
                    getJl_ToDoLabel().setText("Retroalimentación");
                    getJl_ToDoToLabel().setText("Preguntas");
                    name = "Usuario";
                    getJtf_GradeToSend().setEditable(false);
                    String preg = "";
                    for (int i = 0; i < dato.getPreguntas().size(); i++) {
                        preg += "Pregunta " + (i + 1) + ": " + dato.getPreguntas().get(i) + "\n";
                    }
                    getJtf_GradeToSend().setText(preg);
                    preg = "";
                    for (int i = 0; i < dato.getRetroalimentacionPreguntas().size(); i++) {
                        preg += dato.getRetroalimentacionPreguntas().get(i) + "\n";
                    }
                    getJtf_MessageToSend().setText(preg);
                    getJtf_MessageToSend().setEditable(false);
                    getJb_messageButton().setText("Aceptar");
                }

                getJf_mainFrame().repaint();
            }

            if (((Ayllu_Data_Message) message).getMessage() instanceof DatosRetroalimentacionGrupo) {
                DatosRetroalimentacionGrupo dato = (DatosRetroalimentacionGrupo) ((Ayllu_Data_Message) message).getMessage();
                datos = dato;
                aliasCma = dato.getAliasSecretario();
                aliasTemp = dato.getAliasCMA();//cma
                if(!aliasTemp.equals(""))
                {
                    String temp = aliasCma;
                    aliasCma = aliasTemp;
                    aliasTemp = temp;
                }
                if (!aliasCma.contains("DG"))//caso gui profesor
                {
                    getJl_ToDoLabel().setText("Retroalimentación");
                    getJl_ToDoToLabel().setText("Preguntas");
                    name = "Usuario";
                    getJtf_GradeToSend().setEditable(true);

                    String preguntas = "";
                    for (int i = 0; i < dato.getPreguntas().size(); i++) {
                        preguntas += "Pregunta " + (i + 1) + ": " + dato.getPreguntas().get(i) + "\n";
                    }
                    getJtf_GradeToSend().setText(preguntas);
                } else //caso aprendices
                {
                    getJl_ToDoLabel().setText("Retroalimentación");
                    getJl_ToDoToLabel().setText("Preguntas");
                    name = "Usuario";
                    getJtf_GradeToSend().setEditable(false);
                    String preg = "";
                    for (int i = 0; i < dato.getPreguntas().size(); i++) {
                        preg += "Pregunta " + (i + 1) + ": " + dato.getPreguntas().get(i) + "\n";
                    }
                    getJtf_GradeToSend().setText(preg);
                    preg = "";
                    for (int i = 0; i < dato.getRetroalimentacionPreguntas().size(); i++) {
                        preg += dato.getRetroalimentacionPreguntas().get(i) + "\n";
                    }
                    getJtf_MessageToSend().setText(preg);
                    getJtf_MessageToSend().setEditable(false);
                    getJb_messageButton().setText("Aceptar");
                }

                getJf_mainFrame().repaint();
            }
            if (((Ayllu_Data_Message) message).getMessage() instanceof DatosDiscusionAprendizGrupo) {
                DatosDiscusionAprendizGrupo dato = (DatosDiscusionAprendizGrupo) ((Ayllu_Data_Message) message).getMessage();
                datos = dato;
                getJl_Titulo().setText("Bienvenido para el tema: " + dato.getTema());
                aliasCma = dato.getAliasSecretario();
                getJl_ToDoLabel().setText("Pregunta 2");
                getJl_ToDoToLabel().setText("Pregunta 1");
                getJtf_GradeToSend().setEditable(true);
                getJtf_GradeToSend().setText("");
                getJtf_MessageToSend().setEditable(true);
                getJtf_MessageToSend().setText("");
                getJb_messageButton().setText("Enviar");
                name = "Usuario";
                getJf_mainFrame().repaint();
            }

            if (((Ayllu_Data_Message) message).getMessage() instanceof DatosEstudiarOva) {
                DatosEstudiarOva dato = (DatosEstudiarOva) ((Ayllu_Data_Message) message).getMessage();
                datos = dato;
                aliasCma = dato.getAliasCMA();
                getJl_ToDoLabel().setText("Objeto de aprendizaje");
                getJl_ToDoToLabel().setText("");
                name = "Usuario";
                getJtf_GradeToSend().setEditable(false);
                getJf_mainFrame().repaint();
            }

            if (((Ayllu_Data_Message) message).getMessage() instanceof DatosInstrucciones) {
                DatosInstrucciones dato = (DatosInstrucciones) ((Ayllu_Data_Message) message).getMessage();
                datos = dato;
                aliasCma = dato.getAliasP();
                if (!aliasCma.contains("AT")) {
                    //aliasCma = ((Ayllu_Data_Message)message).getReceiverHandler().getAlias();
                    getJl_ToDoLabel().setText("Instrucciones");
                    getJl_ToDoToLabel().setText("");
                    getJtf_GradeToSend().setText("");
                    name = "Usuario";
                    getJtf_GradeToSend().setEditable(false);
                } else {
                    getJl_ToDoLabel().setText("");
                    getJl_ToDoToLabel().setText("Instrucciones");
                    name = "Usuario";
                    getJtf_GradeToSend().setEditable(false);
                    getJtf_GradeToSend().setText(dato.getInstrucciones());
                    getJtf_MessageToSend().setEditable(false);
                    getJtf_MessageToSend().setText("");
                    getJtf_MessageToSend().setEditable(false);
                    getJb_messageButton().setText("Aceptar");
                }
                getJf_mainFrame().repaint();
            }
            if (this.name.equals("") || this.name.equals("Profesor")) {
                this.getJp_mainPanel().setVisible(false);
                this.getJp_chatPanel().setVisible(false);
            } else if (!getJf_mainFrame().isVisible()) {
                showFrame();
            }
        } else if (message instanceof Ayllu_WallMessageData) {
            Ayllu_WallMessageData data = (Ayllu_WallMessageData) message;
            String userName = (this.iaAgent.getAlias().contains(data.getSenderName()) ? "Yo" : data.getSenderName().substring(3));
            String theMessage = userName + ": " + String.valueOf(data.getMessage());
            this.updateMessageQueue(theMessage);
        }

    }

    public String getName() {
        return name;
    }

    public IA_SynchCommInterface getIaAgent() {
        return iaAgent;
    }

    public void setIaAgent(IA_SynchCommInterface iaAgent) {
        this.iaAgent = iaAgent;
    }

    public JTextArea getJtf_GradeToSend() {
        if (jtf_GradeToSend == null) {
            jtf_GradeToSend = new JTextArea();
        }
        return jtf_GradeToSend;
    }

    public void setJtf_GradeToSend(JTextArea jtf_GradeToSend) {
        this.jtf_GradeToSend = jtf_GradeToSend;
    }

    public JScrollPane getScroll1(JTextArea area) {
        if (scroll1 == null) {
            scroll1 = new JScrollPane(area);
            scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        }
        return scroll1;
    }

    public JScrollPane getScroll2(JTextArea area) {
        if (scroll2 == null) {
            scroll2 = new JScrollPane(area);
            scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        }
        return scroll2;
    }

    private JTabbedPane getJp_tabbedPane() {
        if (jtp_content == null) {
            jtp_content = new JTabbedPane();
            jtp_content.addTab("Principal", getJp_mainPanel());
            jtp_content.addTab("Chat", getJp_chatPanel());
        }
        return jtp_content;
    }

    private void updateMessageQueue(String message) {
        String oldText = getJta_MessageQueue().getText();
        getJta_MessageQueue().setText(oldText + (oldText.equals("") ? "" : "\n") + message);
    }
}
