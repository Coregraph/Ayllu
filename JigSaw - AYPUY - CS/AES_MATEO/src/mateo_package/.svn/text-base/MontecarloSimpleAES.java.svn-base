package mateo_package;

import besa_adaptado.perfiles.PerfilEquipoAES;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MontecarloSimpleAES {

    boolean quedanPartics = false;

    public ArrayList<ArrayList<String>> mcHetero(ArrayList<String> clustersTodos, NuevosEquiposAES nuevoEq, PerfilEquipoAES pequ, int canttotalpersonas) {
        ArrayList equiposConformados = nuevoEq.getEquiposConformados();
        Iteraciones_MCAES itermc = new Iteraciones_MCAES();
        int cantEqs = pequ.getCantEquipos();

        ArrayList distritemp = new ArrayList();
        for (int a = 0; a < clustersTodos.size(); a++) {
            boolean esta = false;
            for (int b = 0; b < equiposConformados.size(); b++) {
                for (int x = 0; x < ((ArrayList) equiposConformados.get(b)).size(); x++) {
                    int ini = ((String) clustersTodos.get(a)).indexOf(":") + 2;
                    int fin = ((String) clustersTodos.get(a)).lastIndexOf(" ");
                    String estudianteABuscar = ((String) clustersTodos.get(a)).substring(ini, fin);
                    if ((estudianteABuscar.equals(((ArrayList) equiposConformados.get(b)).get(x))) || (esta == true)) {
                        esta = true;
                    }
                }
            }
            if (!esta) {
                distritemp.add(clustersTodos.get(a));
            }

        }

        ArrayList distribuc = new ArrayList();

        for (int c = 0; c < distritemp.size(); c++) {
            ArrayList datosPart = new ArrayList();
            String[] linea = ((String) distritemp.get(c)).split(" ");
            String linearestante = linea[3];
            String[] linea1 = linea[4].split(",");
            datosPart.add(linearestante);
            datosPart.addAll(Arrays.asList(linea1));
            distribuc.add(datosPart);
            itermc.setDistribucion(distribuc);
        }

        for (int i = 0; i < equiposConformados.size(); i++) {
            ArrayList equipo = (ArrayList) equiposConformados.get(i);
            int faltantes = pequ.getCantPersonasEquipo() - equipo.size();
            if (faltantes > 0) {
                for (int h = 0; h < 10; h++) {
                    int[] numRandom = llamarRandom(faltantes, itermc.getDistribucion());

                    ArrayList escogidosDistr = new ArrayList();
                    for (int y = 0; y < numRandom.length; y++) {
                        int posic = numRandom[y];
                        escogidosDistr.add(itermc.getDistribucion().get(posic));
                    }
                    itermc.setSeleccXiterac(escogidosDistr);
                    float sumaIterac = sumatoriaVariables(escogidosDistr);
                    itermc.setSumaIteraciones(Float.valueOf(sumaIterac));
                }

                int numIterEscogida = funcionObjetivoHomo(itermc.getSumaIteraciones(), pequ.getVarsColaboracion()).intValue();
                ArrayList iteracEscogida = itermc.tomarIteracionFO(numIterEscogida);

                itermc.limpiarSeleccXiterac();
                itermc.limpiarSumaIteraciones();

                ArrayList restoRoles = (ArrayList) nuevoEq.getRolesEquipos().get(i);
                for (int u = 0; u < iteracEscogida.size(); u++) {
                    equipo.add(((ArrayList) iteracEscogida.get(u)).get(0));
                    restoRoles.add("Participante");
                }

                nuevoEq.actualizarEquipo(equipo, i);
                nuevoEq.actualizarListaRoles(restoRoles, i);

                itermc.actualizarDistribucion(iteracEscogida);
            }

        }

        ArrayList quedaronD = quedanFaltantes(itermc.getDistribucion());
        if (this.quedanPartics == true) {
            int a = 0;
            for (int b = 0; (b < nuevoEq.getEquiposConformados().size()) && (a < quedaronD.size()); a++) {
                ((ArrayList) nuevoEq.getEquiposConformados().get(b)).add(((ArrayList) quedaronD.get(a)).get(0));
                ((ArrayList) nuevoEq.getRolesEquipos().get(b)).add("Participante");

                b++;
            }

        }

        ArrayList equiposFinales = nuevoEq.getEquiposConformados();

        return equiposFinales;
    }

    private int[] llamarRandom(int campos, ArrayList<ArrayList<String>> distrib) {
        ArrayList numsaElegir = new ArrayList();
        for (int pos = 0; pos < distrib.size(); pos++) {
            ArrayList arr = (ArrayList) distrib.get(pos);
            if ((!((String) arr.get(0)).equals("X")) && (!((String) arr.get(arr.size() - 1)).equals("X"))) {
                numsaElegir.add(Integer.valueOf(pos));
            }
        }
        int[] numRandom = new int[numsaElegir.size()];
        for (int i = 0; i < numRandom.length; i++) {
            numRandom[i] = ((Integer) numsaElegir.get(i)).intValue();
        }
        Random rnd = new Random();
        for (int i = 0; i < numRandom.length; i++) {
            int nuevaPos = rnd.nextInt(numRandom.length);

            int temp = numRandom[i];
            numRandom[i] = numRandom[nuevaPos];
            numRandom[nuevaPos] = temp;
        }
        int longi = numRandom.length < campos ? numRandom.length : campos;
        int[] numeleg = new int[longi];
        System.arraycopy(numRandom, 0, numeleg, 0, longi);
        return numeleg;
    }

    private float sumatoriaVariables(ArrayList<ArrayList<String>> escogidosIterac) {
        float suma = 0.0F;
        for (int k = 0; k < escogidosIterac.size(); k++) {
            for (int e = 1; e < ((ArrayList) escogidosIterac.get(k)).size(); e++) {
                suma += Float.valueOf((String) ((ArrayList) escogidosIterac.get(k)).get(e)).floatValue();
            }
        }
        return suma;
    }

    private Integer funcionObjetivoHomo(ArrayList<Float> sumaIteraciones, ArrayList<String> varsHomog) {
        ArrayList valores = new ArrayList();
        for (int i = 0; i < varsHomog.size(); i++) {
            String[] varVals = ((String) varsHomog.get(i)).split(",");
            String valor = varVals[1];
            valores.add(valor);
        }

        int bajo = 0;
        int medio = 0;
        int alto = 0;
        for (int j = 0; j < valores.size(); j++) {
            if (((String) valores.get(j)).equals("0.0")) {
                bajo += 1;
            } else if (((String) valores.get(j)).equals("0.5")) {
                medio += 1;
            } else if (((String) valores.get(j)).equals("1.0")) {
                alto += 1;
            }
        }
        ArrayList posiciones = new ArrayList();
        for (int posici = 0; posici < sumaIteraciones.size(); posici++) {
            posiciones.add(Integer.valueOf(posici));
        }
        for (int a = 0; a < sumaIteraciones.size() - 1; a++) {
            if (((Float) sumaIteraciones.get(a)).floatValue() > ((Float) sumaIteraciones.get(a + 1)).floatValue()) {
                float tmp = ((Float) sumaIteraciones.get(a)).floatValue();
                sumaIteraciones.set(a, sumaIteraciones.get(a + 1));
                sumaIteraciones.set(a + 1, Float.valueOf(tmp));

                int pos = ((Integer) posiciones.get(a)).intValue();
                posiciones.set(a, posiciones.get(a + 1));
                posiciones.set(a + 1, Integer.valueOf(pos));
            }
        }
        int po = 0;
        if ((alto >= medio) && (alto > bajo)) {
            po = posiciones.size() - 1;
        } else if ((medio >= alto) && (medio > bajo)) {
            po = posiciones.size() - 1;
        } else if ((bajo > medio) && (bajo > alto)) {
            po = posiciones.size() - 1;
        }

        return (Integer) posiciones.get(po);
    }

    private ArrayList<ArrayList<String>> quedanFaltantes(ArrayList<ArrayList<String>> distribucion) {
        ArrayList distrFinal = new ArrayList();
        for (int m = 0; m < distribucion.size(); m++) {
            if (!((String) ((ArrayList) distribucion.get(m)).get(0)).equals("X")) {
                distrFinal.add(distribucion.get(m));
                this.quedanPartics = true;
            }
        }
        return distrFinal;
    }
}
