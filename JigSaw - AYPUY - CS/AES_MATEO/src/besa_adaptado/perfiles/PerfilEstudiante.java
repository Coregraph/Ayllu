package besa_adaptado.perfiles;

import adaptation.common.ProfileAES;
import java.util.ArrayList;

public class PerfilEstudiante extends ProfileAES {

    private String nombrestudiante;
    private int edad;
    private String genero;
    private ArrayList<String> estiloAprendizaje;
    private String[] preferenciaDespliegue = new String[2];
    private ArrayList<String> preferenciaFormatos;
    private ArrayList<String> coloresHermann = new ArrayList();
    private String contextoEducativo = new String();
    private String localizacion = new String();

    public PerfilEstudiante() {
        super("user");
        this.estiloAprendizaje = new ArrayList();
        this.preferenciaFormatos = new ArrayList();
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getContextoEducativo() {
        return this.contextoEducativo;
    }

    public void setContextoEducativo(String contextoEducativo) {
        this.contextoEducativo = contextoEducativo;
    }

    public String getLocalizacion() {
        return this.localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public void setEstiloAprendizaje(ArrayList<String> estilos) {
        this.estiloAprendizaje = estilos;
    }

    public void setNombrestudiante(String nombrestudiante) {
        this.nombrestudiante = nombrestudiante;
    }

    public void setPreferenciaDespliegue(String grande, String pequeño) {
        this.preferenciaDespliegue[0] = grande;
        this.preferenciaDespliegue[1] = pequeño;
    }

    public void addFormatos(String nombreForm) {
        this.preferenciaFormatos.add(nombreForm);
        this.estiloAprendizaje = this.preferenciaFormatos;
    }

    public ArrayList<String> getColoresHermann() {
        return this.coloresHermann;
    }

    public void setColoresHermann(ArrayList<String> coloresHermann) {
        this.coloresHermann = coloresHermann;
    }

    public ArrayList<String> getPreferenciaFormatos() {
        return this.preferenciaFormatos;
    }

    public void setPreferenciaFormatos(ArrayList<String> preferenciaFormatos) {
        this.preferenciaFormatos = preferenciaFormatos;
    }

    public int getEdad() {
        return this.edad;
    }

    public ArrayList<String> getEstiloAprendizaje() {
        return this.estiloAprendizaje;
    }

    public String getNombrestudiante() {
        return this.nombrestudiante;
    }

    public String[] getPreferenciaDespliegue() {
        return this.preferenciaDespliegue;
    }

    public ArrayList<String> getPreferenciaFormato() {
        return this.preferenciaFormatos;
    }

    public void addEstiloAprendizaje(String nombreEstilo) {
        this.estiloAprendizaje.add(nombreEstilo);
    }

    @Override
    public String toString() {
        String resp = "Nombre estudiante: " + this.nombrestudiante + "\nEdad: " + this.edad + "\nEstilos de aprendizaje: ";
        for (int i = 0; i < this.estiloAprendizaje.size(); i++) {
            resp = resp.concat((String) this.estiloAprendizaje.get(i) + "| ");
        }
        String resp1 = "\nPreferencias de despliegue: ";
        resp1 = resp1.concat(this.preferenciaDespliegue[0] + "|");
        resp1 = resp1.concat(this.preferenciaDespliegue[1]);

        String resp2 = "\nPreferencias de formato: ";
        for (int i = 0; i < this.preferenciaFormatos.size(); i++) {
            resp2 = resp2.concat((String) this.preferenciaFormatos.get(i) + "| ");
        }
        resp1 = resp1.concat(resp2);
        resp = resp.concat(resp1);
        return resp;
    }
}
