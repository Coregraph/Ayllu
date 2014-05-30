package besa_adaptado.perfiles;

import adaptation.common.ProfileAES;
import java.util.HashMap;
import java.util.Map;

public class PerfilUsuario extends ProfileAES {

    private String nombre;
    private String grado;
    private Map<String, Float> experticias;

    public PerfilUsuario(String categoria) {
        super(categoria);
        this.experticias = new HashMap();
    }

    public PerfilUsuario() {
        super("user");
        this.experticias = new HashMap();
    }

    public void setExperticias(Map<String, Float> experticias) {
        this.experticias = experticias;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addExperticia(String nombre, Float valor) {
        if (this.experticias == null) {
            this.experticias = new HashMap();
        }
        this.experticias.put(nombre, valor);
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public Map<String, Float> getExperticias() {
        return this.experticias;
    }

    public String getGrado() {
        return this.grado;
    }

    @Override
    public String toString() {
        String resp = "Nombre: " + this.nombre + "\nGrado: " + this.grado + "\nExperticias: ";
        for (String key : this.experticias.keySet()) {
            resp = resp + "\t\n" + key + " -> " + String.valueOf(this.experticias.get(key));
        }
        return resp;
    }
}
