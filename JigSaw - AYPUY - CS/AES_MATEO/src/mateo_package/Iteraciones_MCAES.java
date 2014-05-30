package mateo_package;

import java.util.ArrayList;

public class Iteraciones_MCAES {

    private ArrayList<ArrayList<String>> distribucion;
    private ArrayList<ArrayList<ArrayList<String>>> seleccXiterac;
    private ArrayList<Float> sumaIteraciones;

    public Iteraciones_MCAES() {
        this.seleccXiterac = new ArrayList();
        this.sumaIteraciones = new ArrayList();
        this.distribucion = new ArrayList();
    }

    public ArrayList<ArrayList<String>> getDistribucion() {
        return this.distribucion;
    }

    public void setDistribucion(ArrayList<ArrayList<String>> distribucion) {
        this.distribucion = distribucion;
    }

    public void actualizarDistribucion(ArrayList<ArrayList<String>> iteracEscogida) {
        for (int i = 0; i < iteracEscogida.size(); i++) {
            for (int j = 0; j < this.distribucion.size(); j++) {
                if (((ArrayList) this.distribucion.get(j)).equals(iteracEscogida.get(i))) {
                    for (int k = 0; k < ((ArrayList) this.distribucion.get(j)).size(); k++) {
                        ((ArrayList) this.distribucion.get(j)).set(k, "X");
                    }
                }
            }
        }
    }

    public ArrayList<ArrayList<ArrayList<String>>> getSeleccXiterac() {
        return this.seleccXiterac;
    }

    public void setSeleccXiterac(ArrayList<ArrayList<String>> seleccIter) {
        this.seleccXiterac.add(seleccIter);
    }

    public ArrayList<Float> getSumaIteraciones() {
        return this.sumaIteraciones;
    }

    public void setSumaIteraciones(Float suma) {
        this.sumaIteraciones.add(suma);
    }

    public ArrayList<ArrayList<String>> tomarIteracionFO(int posic) {
        return (ArrayList) this.seleccXiterac.get(posic);
    }

    public void limpiarSeleccXiterac() {
        this.seleccXiterac = new ArrayList();
    }

    public void limpiarSumaIteraciones() {
        this.sumaIteraciones = new ArrayList();
    }
}
