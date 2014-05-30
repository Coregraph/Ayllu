package lectorcsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeerColoresHermman {

    ArrayList<String> coloresDominantes = new ArrayList();
    ArrayList<ArrayList<String>> rtaGlobal = new ArrayList();
    private File archivo;
    Integer[] pregAmarillo = {Integer.valueOf(32), Integer.valueOf(30), Integer.valueOf(28), Integer.valueOf(19), Integer.valueOf(14), Integer.valueOf(10), Integer.valueOf(5), Integer.valueOf(3), Integer.valueOf(16)};
    Integer[] pregRojo = {Integer.valueOf(35), Integer.valueOf(34), Integer.valueOf(29), Integer.valueOf(25), Integer.valueOf(23), Integer.valueOf(18), Integer.valueOf(17), Integer.valueOf(8), Integer.valueOf(1)};
    Integer[] pregVerde = {Integer.valueOf(33), Integer.valueOf(31), Integer.valueOf(26), Integer.valueOf(24), Integer.valueOf(22), Integer.valueOf(20), Integer.valueOf(15), Integer.valueOf(13), Integer.valueOf(1)};
    Integer[] pregAzul = {Integer.valueOf(36), Integer.valueOf(2), Integer.valueOf(4), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(9), Integer.valueOf(11), Integer.valueOf(21), Integer.valueOf(27)};

    public LeerColoresHermman(File archivo) {
        this.archivo = archivo;
    }

    public ArrayList<ArrayList<String>> analisisColores() {
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(this.archivo));
            input.readLine();
            String linea;
            while ((linea = input.readLine()) != null) {
                String[] cadena = linea.split(";");
                String[] cadenalimpia = new String[cadena.length - 2];

                cadenalimpia[0] = cadena[1];
                for (int i = 1, j = 3; i < cadenalimpia.length; j++) {
                    cadenalimpia[i] = cadena[j];

                    i++;
                }
                int rtaAmarilla = 0;
                int rtaRoja = 0;
                int rtaVerde = 0;
                int rtaAzul = 0;

                for (int j = 0; j < this.pregAmarillo.length; j++) {
                    rtaAmarilla += Integer.parseInt(cadenalimpia[this.pregAmarillo[j].intValue()]);
                }

                for (int j = 0; j < this.pregRojo.length; j++) {
                    rtaRoja += Integer.parseInt(cadenalimpia[this.pregRojo[j].intValue()]);
                }

                for (int j = 0; j < this.pregVerde.length; j++) {
                    rtaVerde += Integer.parseInt(cadenalimpia[this.pregVerde[j].intValue()]);
                }

                for (int j = 0; j < this.pregAzul.length; j++) {
                    rtaAzul += Integer.parseInt(cadenalimpia[this.pregAzul[j].intValue()]);
                }

                this.coloresDominantes = dominanciaColor(rtaAmarilla, rtaRoja, rtaVerde, rtaAzul);
                System.out.println("Nombre: " + cadena[1]);
                for (int i = 0; i < this.coloresDominantes.size(); i++) {
                    System.out.println("[" + (String) this.coloresDominantes.get(i) + "]");
                }

                ArrayList rtaUnestud = new ArrayList();
                rtaUnestud.add(cadena[1]);
                for (int i = 0; i < this.coloresDominantes.size(); i++) {
                    rtaUnestud.add(this.coloresDominantes.get(i));
                }
                this.rtaGlobal.add(rtaUnestud);
            }
        } catch (IOException ex) {
            Logger.getLogger(LeerColoresHermman.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                Logger.getLogger(LeerColoresHermman.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.rtaGlobal;
    }

    private ArrayList<String> dominanciaColor(int rtaAmarilla, int rtaRoja, int rtaVerde, int rtaAzul) {
        Integer[] coloresNum = {Integer.valueOf(rtaAmarilla), Integer.valueOf(rtaRoja), Integer.valueOf(rtaVerde), Integer.valueOf(rtaAzul)};
        String[] nombreColor = {"Amarillo", "Rojo", "Verde", "Azul"};
        ArrayList ordenColores = new ArrayList();

        for (int i = 0; i < coloresNum.length; i++) {
            for (int j = 0; j < coloresNum.length - 1; j++) {
                if (coloresNum[j].intValue() < coloresNum[(j + 1)].intValue()) {
                    int temp = coloresNum[(j + 1)].intValue();
                    String aux = nombreColor[(j + 1)];

                    coloresNum[(j + 1)] = coloresNum[j];
                    nombreColor[(j + 1)] = nombreColor[j];

                    coloresNum[j] = Integer.valueOf(temp);
                    nombreColor[j] = aux;
                }
            }
        }

        ordenColores.addAll(Arrays.asList(nombreColor));
        return ordenColores;
    }
}
