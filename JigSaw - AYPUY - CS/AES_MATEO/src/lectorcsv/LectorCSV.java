package lectorcsv;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

public class LectorCSV {

    public ArrayList<ArrayList<String>> leerarchivo() {
        JFileChooser filechooser = new JFileChooser("./");
        filechooser.setAcceptAllFileFilterUsed(false);
        filechooser.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                String filename = f.getName();
                return filename.endsWith(".csv");
            }

            @Override
            public String getDescription() {
                return "*.csv";
            }
        });
        ArrayList msj = new ArrayList();
        int returnval = filechooser.showOpenDialog(null);
        if (returnval == 0) {
            LeerColoresHermman reader = new LeerColoresHermman(filechooser.getSelectedFile());
            msj = reader.analisisColores();
        } else {
            JOptionPane.showMessageDialog(null, "Apertura de archivo cancelada", "X", 1);
        }
        return msj;
    }

    public ArrayList<ArrayList<String>> leerarchivo(String elArchivo) {
        LeerColoresHermman reader = new LeerColoresHermman(new File(elArchivo));
        ArrayList msj = reader.analisisColores();

        return msj;
    }
}
