package mateo_package;

import java.util.ArrayList;

public class NuevosEquiposAES {

    private int cantEquipos;
    private ArrayList<ArrayList<String>> equiposConformados;
    private ArrayList<ArrayList<String>> rolesEquipos;

    public NuevosEquiposAES() {
        this.equiposConformados = new ArrayList();
        this.rolesEquipos = new ArrayList();
    }

    public int getCantEquipos() {
        return this.cantEquipos;
    }

    public void setCantEquipos(int cantEquipos) {
        this.cantEquipos = cantEquipos;
    }

    public ArrayList<ArrayList<String>> getEquiposConformados() {
        return this.equiposConformados;
    }

    public void setEquiposConformados(ArrayList<ArrayList<String>> equiposConformados) {
        this.equiposConformados = equiposConformados;
    }

    public ArrayList<ArrayList<String>> getRolesEquipos() {
        return this.rolesEquipos;
    }

    public void setRolesEquipos(ArrayList<ArrayList<String>> rolesEquipos) {
        this.rolesEquipos = rolesEquipos;
    }

    public ArrayList<String> getEquipoEspecífico(int numEquipo) {
        return (ArrayList) this.equiposConformados.get(numEquipo - 1);
    }

    public void addParticipante(ArrayList<String> equipoEspecífico, ArrayList<String> rolesEquipoEspecífico, String participante, String rol) {
        equipoEspecífico.add(participante);
        rolesEquipoEspecífico.add(rol);
    }

    public void actualizarEquipo(ArrayList<String> equipo, int i) {
        this.equiposConformados.set(i, equipo);
    }

    public void actualizarListaRoles(ArrayList<String> restoRoles, int i) {
        this.rolesEquipos.set(i, restoRoles);
    }
}
