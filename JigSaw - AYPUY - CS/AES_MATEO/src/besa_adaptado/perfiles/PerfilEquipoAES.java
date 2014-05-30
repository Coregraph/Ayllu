package besa_adaptado.perfiles;

import adaptation.common.ProfileAES;
import java.util.ArrayList;
import mateo_package.RolAES;

public class PerfilEquipoAES extends ProfileAES {

    private String composición;
    private int cantEquipos;
    private int cantPersonasEquipo;
    ArrayList<String> varsHomog = new ArrayList();
    ArrayList<String> varsColaboracion = new ArrayList();
    ArrayList<RolAES> losRoles = new ArrayList();

    public PerfilEquipoAES() {
        super("team");
        this.varsHomog = new ArrayList();
        this.varsColaboracion = new ArrayList();
    }

    @Override
    public Object clone()
            throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public ArrayList<String> getVarsHomog() {
        return this.varsHomog;
    }

    public void setVarsHomog(String varsH) {
        this.varsHomog.add(varsH);
    }

    public int getCantEquipos() {
        return this.cantEquipos;
    }

    public void setCantEquipos(int cantEquipos) {
        this.cantEquipos = cantEquipos;
    }

    public int getCantPersonasEquipo() {
        return this.cantPersonasEquipo;
    }

    public void addRol(RolAES elrol) {
        this.losRoles.add(elrol);
    }

    public void setCantPersonasEquipo(int cantPersonasEquipo) {
        this.cantPersonasEquipo = cantPersonasEquipo;
    }

    public String getComposición() {
        return this.composición;
    }

    public void setComposición(String composición) {
        this.composición = composición;
    }

    public ArrayList<RolAES> getLosRoles() {
        return this.losRoles;
    }

    public void setLosRoles(ArrayList<RolAES> losRoles) {
        this.losRoles = losRoles;
    }

    public ArrayList<String> getVarsColaboracion() {
        return this.varsColaboracion;
    }

    public void setVarsColaboracion(ArrayList<String> varsColaboracion) {
        this.varsColaboracion = varsColaboracion;
    }

    @Override
    public String toString() {
        return this.composición + "\n" + this.cantEquipos + "\n" + this.cantPersonasEquipo + "\n" + this.losRoles + "\n----\n";
    }
}
