package mateo_package;

import besa_adaptado.perfiles.PerfilEquipoAES;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class RtaFinalIncluHeteroAES extends JFrame {

    NuevosEquiposAES equiposInclHeter;
    PerfilEquipoAES perEq;
    ArrayList<ArrayList<String>> rolesAsig;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel9;
    private JScrollPane jScrollPane2;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JSeparator jSeparator3;
    private JSeparator jSeparator4;
    private JTextArea jTA_rtaEqsIHETE;
    private JTextField jTF_cantPersEq;
    private JTextField jTF_canteq;
    private JTextField jTF_canteqIHET;
    private JTextField jTF_composIHET;
    private JTextField jTF_composi;
    private JTextField jTF_roles;
    private JTextField jTF_rolesIHETE;
    private JLabel jlab_titulo;
    private JLabel jlab_titulo1;
    private JLabel jlab_titulo_making;
    private JLabel jlab_titulo_making1;

    public RtaFinalIncluHeteroAES(NuevosEquiposAES equiposInclHetero) {
        this.equiposInclHeter = equiposInclHetero;
        this.rolesAsig = equiposInclHetero.getRolesEquipos();
        initComponents();
    }

    private void initComponents() {
        this.jLabel3 = new JLabel();
        this.jLabel7 = new JLabel();
        this.jTF_composi = new JTextField();
        this.jlab_titulo = new JLabel();
        this.jlab_titulo_making = new JLabel();
        this.jTF_canteq = new JTextField();
        this.jLabel1 = new JLabel();
        this.jTF_cantPersEq = new JTextField();
        this.jSeparator1 = new JSeparator();
        this.jSeparator3 = new JSeparator();
        this.jLabel2 = new JLabel();
        this.jLabel4 = new JLabel();
        this.jTF_roles = new JTextField();
        this.jLabel5 = new JLabel();
        this.jTF_composIHET = new JTextField();
        this.jlab_titulo1 = new JLabel();
        this.jlab_titulo_making1 = new JLabel();
        this.jTF_canteqIHET = new JTextField();
        this.jLabel6 = new JLabel();
        this.jSeparator2 = new JSeparator();
        this.jSeparator4 = new JSeparator();
        this.jLabel9 = new JLabel();
        this.jLabel10 = new JLabel();
        this.jTF_rolesIHETE = new JTextField();
        this.jScrollPane2 = new JScrollPane();
        this.jTA_rtaEqsIHETE = new JTextArea();
        this.jLabel11 = new JLabel();

        this.jLabel3.setText("Tipo de equipo (Composición): ");

        this.jLabel7.setText("Cantidad de personas por equipo:");

        this.jTF_composi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                RtaFinalIncluHeteroAES.this.jTF_composiActionPerformed(evt);
            }
        });
        this.jlab_titulo.setFont(new Font("Tahoma", 0, 18));
        this.jlab_titulo.setText("Bienvenido a MATEO");

        this.jlab_titulo_making.setFont(new Font("Tahoma", 0, 18));
        this.jlab_titulo_making.setText("Making Adapted TEams Oriented to collaboration");

        this.jLabel1.setText("DATOS DE EQUIPO A CONFORMAR:");

        this.jLabel2.setText("Roles solicitados (con la cantidad de participantes requerida por rol):");

        this.jLabel4.setText("Cantidad de equipos solicitados:");

        setDefaultCloseOperation(3);

        this.jLabel5.setText("Tipo de equipo (Composición): ");

        this.jTF_composIHET.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                RtaFinalIncluHeteroAES.this.jTF_composIHETActionPerformed(evt);
            }
        });
        this.jlab_titulo1.setFont(new Font("Tahoma", 0, 18));
        this.jlab_titulo1.setText("Bienvenido a MATEO");

        this.jlab_titulo_making1.setFont(new Font("Tahoma", 0, 18));
        this.jlab_titulo_making1.setText("Making Adapted TEams Oriented to collaboration");

        this.jLabel6.setText("DATOS DE EQUIPO A CONFORMAR:");

        this.jLabel9.setText("Roles solicitados (con la cantidad de participantes requerida por rol):");

        this.jLabel10.setText("Cantidad de equipos solicitados:");

        this.jTA_rtaEqsIHETE.setColumns(20);
        this.jTA_rtaEqsIHETE.setRows(5);
        this.jScrollPane2.setViewportView(this.jTA_rtaEqsIHETE);

        this.jLabel11.setText("EQUIPOS CONFORMADOS:");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(36, 36, 36).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(24, 24, 24).addComponent(this.jScrollPane2, -2, 573, -2)).addComponent(this.jLabel11))).addGroup(layout.createSequentialGroup().addGap(58, 58, 58).addComponent(this.jLabel9))).addContainerGap()).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(19, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jSeparator4, -2, 666, -2).addComponent(this.jTF_rolesIHETE, -2, 636, -2)).addGap(23, 23, 23)).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(-1, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(248, 248, 248).addComponent(this.jlab_titulo1)).addGroup(layout.createSequentialGroup().addGap(27, 27, 27).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(27, 27, 27).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel10).addComponent(this.jLabel5)).addGap(44, 44, 44).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jTF_composIHET, -1, 148, 32767).addComponent(this.jTF_canteqIHET, -1, 148, 32767))).addComponent(this.jLabel6))).addComponent(this.jSeparator2, -2, 688, -2).addGroup(layout.createSequentialGroup().addGap(149, 149, 149).addComponent(this.jlab_titulo_making1))).addContainerGap())));

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(234, 32767).addComponent(this.jLabel9).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jTF_rolesIHETE, -2, 29, -2).addGap(26, 26, 26).addComponent(this.jSeparator4, -2, 10, -2).addGap(18, 18, 18).addComponent(this.jLabel11).addGap(18, 18, 18).addComponent(this.jScrollPane2, -2, 176, -2).addGap(81, 81, 81)).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(27, 27, 27).addComponent(this.jlab_titulo1).addGap(12, 12, 12).addComponent(this.jlab_titulo_making1).addGap(18, 18, 18).addComponent(this.jSeparator2, -2, 3, -2).addGap(31, 31, 31).addComponent(this.jLabel6).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel5).addComponent(this.jTF_composIHET, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel10).addComponent(this.jTF_canteqIHET, -2, -1, -2)).addContainerGap(420, 32767))));

        pack();
    }

    private void jTF_composiActionPerformed(ActionEvent evt) {
    }

    private void jTF_composIHETActionPerformed(ActionEvent evt) {
    }

    public void mostrarEqsInclHetero(PerfilEquipoAES peqaes) {
        this.perEq = peqaes;

        String composic = this.perEq.getComposición();
        this.jTF_composIHET.setText(composic);
        this.jTF_composIHET.repaint();
        this.jTF_composIHET.setEditable(false);

        String cantEqs = String.valueOf(this.perEq.getCantEquipos());
        this.jTF_canteqIHET.setText(cantEqs);
        this.jTF_canteqIHET.repaint();
        this.jTF_canteqIHET.setEditable(false);

        String roles = new String();
        for (int i = 0; i < this.perEq.getLosRoles().size(); i++) {
            roles = roles.concat(" | " + ((RolAES) this.perEq.getLosRoles().get(i)).getNombreRol() + ", " + ((RolAES) this.perEq.getLosRoles().get(i)).getNumPersRol());
        }
        this.jTF_rolesIHETE.setText(roles);
        this.jTF_rolesIHETE.repaint();
        this.jTF_rolesIHETE.setEditable(false);

        String linea = new String();
        ArrayList equiposConf = this.equiposInclHeter.getEquiposConformados();
        for (int k = 0; k < equiposConf.size(); k++) {
            linea = linea.concat("\n");
            linea = linea.concat("\n");
            linea = linea.concat("=========== EQUIPO No. " + (k + 1) + " ===========");
            for (int m = 0; m < ((ArrayList) equiposConf.get(k)).size(); m++) {
                linea = linea.concat("\n");
                linea = linea.concat((String) ((ArrayList) equiposConf.get(k)).get(m) + " - Rol: " + (String) ((ArrayList) this.rolesAsig.get(k)).get(m));
            }
        }
        this.jTA_rtaEqsIHETE.setText(linea);
        this.jTA_rtaEqsIHETE.repaint();
        this.jTA_rtaEqsIHETE.setEditable(false);
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RtaFinalIncluHeteroAES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RtaFinalIncluHeteroAES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RtaFinalIncluHeteroAES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(RtaFinalIncluHeteroAES.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                RtaFinalIncluHeteroAES.this.setVisible(true);
            }
        });
    }
}
