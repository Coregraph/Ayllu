package besa_adaptado.filtros;

import adaptation.common.ProfileAES;
import adaptation.common.filter.FilterAES;
import adaptation.common.filter.FilterResultAES;
import besa_adaptado.perfiles.PerfilEstudiante;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FiltroEstudiante extends FilterAES {

    public static PerfilEstudiante estudiante;
    public static List<String> valoresArchivo;
    public String estudiantesFile;
    private float valorRango;

    public FiltroEstudiante(String category) {
        super(category);
    }

    @Override
    public FilterResultAES doFilter(List<ProfileAES> aList) {
        this.estudiantesFile = crearArchivoEstuds(aList);

        ResultadoFiltro rf = new ResultadoFiltro(this.estudiantesFile);
        rf.setCategory(getCategory());
        return rf;
    }

    public String crearArchivoEstuds(List<ProfileAES> aList) {
        FileWriter fichero = null;
        PrintWriter pw;

        int cantAtrib = 23;

        Map mapColores = new HashMap();
        mapColores.put("Azul", "8,9,10,19");
        mapColores.put("Verde", "4,11,12,13,20");
        mapColores.put("Rojo", "5,6,14,15,21");
        mapColores.put("Amarillo", "7,16,17,18,22");
        try {
            fichero = new FileWriter("./estudiantes.csv");
            pw = new PrintWriter(fichero);

            String linea = "Nombre;Genero;Contexto Educativo;Localización;Minucidad;Extroversion;Afabilidad;Apertura experiencia;Analisis;Cap. Tecnicas;Critica;Ejecucion;Planeacion;Organizacion;Escritura fluida;Rel. Interpersonales;Conceptualizar;Innovacion;Creatividad;Resol. Problemas;Administracion;Expresar ideas;Integracion";

            pw.println(linea);

            for (int i = 0; i < aList.size(); i++) {
                if (((ProfileAES) aList.get(i)).getCategory().equals("user")) {
                    estudiante = (PerfilEstudiante) aList.get(i);
                }
                valorRango = 1.0F;
                String[] variablesColores = new String[cantAtrib];
                linea = new String();
                variablesColores[0] = estudiante.getNombrestudiante();
                variablesColores[1] = estudiante.getGenero();
                variablesColores[2] = estudiante.getContextoEducativo();
                variablesColores[3] = estudiante.getLocalizacion();

                ArrayList colores = estudiante.getColoresHermann();

                int pasada = 0;
                for (int k = 0; k < colores.size(); k++) {
                    String elColor = (String) colores.get(k);
                    String rangoColor = (String) mapColores.get(elColor);
                    String[] rangoSeparados = rangoColor.split(",");
                    for (int m = 0; m < rangoSeparados.length; m++) {
                        int temp = Integer.parseInt(rangoSeparados[m]);
                        variablesColores[temp] = String.valueOf(valorRango);
                    }
                    pasada++;
                    if (pasada >= 2) {
                        valorRango = (float) (valorRango - 0.5D);
                        if (valorRango <= 0.0F) {
                            valorRango = 0.0F;
                        }
                    }
                }

                for (int h = 0; h < variablesColores.length; h++) {
                    if (h != variablesColores.length - 1) {
                        linea = linea.concat(variablesColores[h]);
                        linea = linea.concat(";");
                    } else {
                        linea = linea.concat(variablesColores[h]);
                    }

                }

                pw.println(linea);
            }
        } catch (IOException ex) {
            Logger.getLogger(FiltroEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        String nombreFichero = "./estudiantes.csv";
        return nombreFichero;
    }
}
