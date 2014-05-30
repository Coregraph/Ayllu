package mateo_package;

import java.io.Serializable;
import java.util.ArrayList;

public class RolAES
        implements Serializable {

    private String nombreRol = new String();
    private int numPersRol;
    private ArrayList<String> varsRol = new ArrayList();

    public RolAES() {
    }

    public ArrayList<String> traerVariablesRol() {
        return this.varsRol;
    }

    public int getNumPersRol() {
        return this.numPersRol;
    }

    public void setNumPersRol(int numPersRol) {
        this.numPersRol = numPersRol;
    }

    public RolAES(String rol) {
        this.nombreRol = rol;
    }

    public String getNombreRol() {
        return this.nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public ArrayList<String> getVarsRol(String rolEscogido) {
        if (rolEscogido.equals("Secretario")) {
            this.varsRol.add("Minucidad, 1");
            this.varsRol.add("Ejecucion, 1");
            this.varsRol.add("Planeacion, 1");
            this.varsRol.add("Organizacion, 1");
            this.varsRol.add("Administracion, 1");
        }
        if (rolEscogido.equals("Presentador")) {
            this.varsRol.add("Extroversion, 1");
            this.varsRol.add("Afabilidad, 1");
            this.varsRol.add("Escritura fluida, 1");
            this.varsRol.add("Rel. Interpersonales, 1");
            this.varsRol.add("Expresar ideas, 1");
        }
        if (rolEscogido.equals("Diseñador")) {
            this.varsRol.add("Apertura experiencia, 1");
            this.varsRol.add("Conceptualizar, 1");
            this.varsRol.add("Innovacion, 1");
            this.varsRol.add("Creatividad, 1");
            this.varsRol.add("Integracion, 1");
        }
        if (rolEscogido.equals("Operativo")) {
            this.varsRol.add("Analisis, 1");
            this.varsRol.add("Cap. Tecnicas, 1");
            this.varsRol.add("Critica, 1");
            this.varsRol.add("Resol. Problemas, 1");
        }

        if (rolEscogido.equals("Aprendiz")) {
            this.varsRol.add("Analisis, 1");
            this.varsRol.add("Ejecucion, 1");
            this.varsRol.add("Conceptualizar, 1");
            this.varsRol.add("Expresar ideas, 1");
        }

        return this.varsRol;
    }

    public void setVarsRol(ArrayList<String> varsRol) {
        this.varsRol = varsRol;
    }
}
