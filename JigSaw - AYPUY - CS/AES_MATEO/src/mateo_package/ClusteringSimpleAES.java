package mateo_package;

import besa_adaptado.perfiles.PerfilEquipoAES;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.clusterers.SimpleKMeans;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class ClusteringSimpleAES {

    ArrayList<String> listaCentroide;
    String[] titulosVar = null;
    ArrayList<String> clustersTodos;
    ArrayList<ArrayList<String>> rolesAsignados;
    private static ClusteringSimpleAES instance = null;
    int canttotalpersonas;

    public static ClusteringSimpleAES getInstance() {
        if (instance == null) {
            instance = new ClusteringSimpleAES();
        }
        return instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public NuevosEquiposAES recibirHeterogeneo(PerfilEquipoAES peq, String nombreArchivoEstudiantes, int cantParticipantesTotal) {
        ArrayList clustersRoles = new ArrayList();
        ArrayList listaRoles = peq.getLosRoles();
        String cantClus = Integer.toString(peq.getCantEquipos());
        ArrayList nombreRol = new ArrayList();
        this.titulosVar = leerTitulos(nombreArchivoEstudiantes);

        System.out.println("-----EVALUANDO ROLES");
        System.out.println(" ");
        for (int i = 0; i < listaRoles.size(); i++) {
            System.out.println();
            System.out.println("**********************************************");
            System.out.println("ROL " + ((RolAES) listaRoles.get(i)).getNombreRol() + ":");

            String rangoVars = new String();
            String nomRol = ((RolAES) listaRoles.get(i)).getNombreRol();
            ArrayList varsRolLista = ((RolAES) listaRoles.get(i)).traerVariablesRol();
            List rangoVarsLista = hallarRangos(varsRolLista);
            for (int j = 0; j < rangoVarsLista.size(); j++) {
                rangoVars = rangoVars.concat((String) rangoVarsLista.get(j) + ",");
            }
            rangoVars = rangoVars.replace("[", "");
            rangoVars = rangoVars.replace("]", "");
            rangoVars = rangoVars.substring(0, rangoVars.length() - 1);

            ArrayList rtaClusters = crearClusters(peq, cantClus, rangoVars, i);
            System.out.println();
            System.out.println("Los participantes seleccionados para " + nomRol + " fueron: ");
            nombreRol.add(nomRol);
            ArrayList rtaClustersNombres = dejarNombres(rtaClusters);
            for (int k = 0; k < rtaClustersNombres.size(); k++) {
                System.out.println((String) rtaClustersNombres.get(k));
            }
            clustersRoles.add(rtaClustersNombres);
        }

        System.out.println(" ");
        System.out.println("---------- EVALUANDO VARS DE COLABORACION");
        this.clustersTodos = new ArrayList();
        ArrayList varsColab = new ArrayList();
        varsColab.add("Resol. Problemas,1.0");
        varsColab.add("Administracion,1.0");
        varsColab.add("Expresar ideas,1.0");
        varsColab.add("Integracion,1.0");
        peq.setVarsColaboracion(varsColab);

        String rangoVars = new String();
        List rangoVarsLista = hallarRangos(peq.getVarsColaboracion());
        for (int i = 0; i < rangoVarsLista.size(); i++) {
            rangoVars = rangoVars.concat((String) rangoVarsLista.get(i) + ",");
        }
        rangoVars = rangoVars.replace("[", "");
        rangoVars = rangoVars.replace("]", "");
        rangoVars = rangoVars.substring(0, rangoVars.length() - 1);
        System.out.println("rangovars " + rangoVars);

        ArrayList rtaClustersColaborac = crearClusters(peq, cantClus, rangoVars, 0);
        ArrayList rtaClustersNombresColab = dejarNombres(rtaClustersColaborac);
        this.canttotalpersonas = cantParticipantesTotal;
        int cantPersEq = this.canttotalpersonas / peq.getCantEquipos();
        peq.setCantPersonasEquipo(cantPersEq);

        NuevosEquiposAES equiposInclHetero = incluHetero(peq, clustersRoles, rtaClustersNombresColab, nombreRol);

        return equiposInclHetero;
    }

    public NuevosEquiposAES incluHetero(PerfilEquipoAES peq, ArrayList<ArrayList<String>> clustEscogidoRol, ArrayList<String> rtaClustersNombresColab, ArrayList<String> nombreRol) {
        int numEquipos = peq.getCantEquipos();
        int numPersEq = peq.getCantPersonasEquipo();
        NuevosEquiposAES nuevoEq = new NuevosEquiposAES();
        nuevoEq.setCantEquipos(numEquipos);
        ArrayList equiposConformadosRoles = new ArrayList();
        this.rolesAsignados = new ArrayList();
        int[] posEnLista = new int[clustEscogidoRol.size()];
        int colocado = 0;

        for (int i = 0; i < numEquipos; i++) {
            ArrayList equipoConf = new ArrayList();
            ArrayList rolAsig = new ArrayList();
            for (int j = 0; j < peq.getLosRoles().size(); j++) {
                int persRol = ((RolAES) peq.getLosRoles().get(j)).getNumPersRol();
                for (int k = 0; (k < persRol) && (posEnLista[j] < ((ArrayList) clustEscogidoRol.get(j)).size()); k++) {
                    boolean esta = revisarPersonaAsig(equiposConformadosRoles, (String) ((ArrayList) clustEscogidoRol.get(j)).get(posEnLista[j]));
                    if ((!equipoConf.contains(((ArrayList) clustEscogidoRol.get(j)).get(posEnLista[j]))) && (!esta)) {
                        equipoConf.add(((ArrayList) clustEscogidoRol.get(j)).get(posEnLista[j]));
                        rolAsig.add(((RolAES) peq.getLosRoles().get(j)).getNombreRol());
                        posEnLista[j] += 1;
                        colocado++;
                    } else {
                        posEnLista[j] += 1;
                        k--;
                    }
                }
            }
            equiposConformadosRoles.add(equipoConf);
            this.rolesAsignados.add(rolAsig);
            nuevoEq.setEquiposConformados(equiposConformadosRoles);
            nuevoEq.setRolesEquipos(this.rolesAsignados);
        }
        System.out.println(" ");

        MontecarloSimpleAES mcsi = new MontecarloSimpleAES();
        ArrayList equiposFinales = mcsi.mcHetero(this.clustersTodos, nuevoEq, peq, this.canttotalpersonas);

        nuevoEq.setEquiposConformados(equiposFinales);
        return nuevoEq;
    }

    public ArrayList<String> crearClusters(PerfilEquipoAES pe, String cantClust, String rangoVar, int rolactual) {
        this.clustersTodos = new ArrayList();
        ArrayList clustersSelecc = new ArrayList();

        String[] args = new String[2];
        args[0] = cantClust;
        args[1] = "./estudiantesWEKA.csv";
        try {
            List nombreEstudiantes = leerNombres(args[1]);

            SimpleKMeans clusterer = new SimpleKMeans();
            clusterer.setNumClusters(Integer.parseInt(args[0]));
            clusterer.setMaxIterations(100);

            ConverterUtils.DataSource source = new ConverterUtils.DataSource(args[1]);
            Instances dataset = source.getDataSet();

            String[] opciones = new String[2];
            opciones[0] = "-R";
            opciones[1] = rangoVar;
            Remove remove = new Remove();
            remove.setOptions(opciones);
            remove.setInputFormat(dataset);

            dataset = Filter.useFilter(dataset, remove);

            clusterer.buildClusterer(dataset);

            for (int i = 0; (i < dataset.numInstances()) && (dataset.numInstances() == nombreEstudiantes.size()); i++) {
                Instance instancia = dataset.instance(i);
                int cluster = clusterer.clusterInstance(instancia);
                this.clustersTodos.add("[Cluster " + cluster + "] Instancia: Estudiante:" + (String) nombreEstudiantes.get(i) + " " + instancia.toString());
            }

            int[] tamanoClusters = clusterer.getClusterSizes();
            Instances centroides = clusterer.getClusterCentroids();

            this.listaCentroide = new ArrayList();
            for (int cluster = 0; cluster < clusterer.numberOfClusters(); cluster++) {
                Instance centroide = centroides.instance(cluster);
                this.listaCentroide.add(centroide.toString());
            }
            System.out.println();
            clustersSelecc = separarClusters(pe, this.clustersTodos, this.listaCentroide, rolactual);
        } catch (Exception e) {
            System.out.println("Error en clustering: " + e.getMessage());
        }

        return clustersSelecc;
    }

    private List<String> hallarRangos(ArrayList<String> vars) {
        ArrayList numRangos = new ArrayList();

        ArrayList variables = new ArrayList();
        for (int i = 0; i < vars.size(); i++) {
            String[] temp = ((String) vars.get(i)).split(",");
            variables.add(temp[0]);
        }
        for (int i = 0; i < variables.size(); i++) {
            for (int j = 0; j < this.titulosVar.length; j++) {
                if (((String) variables.get(i)).equals(this.titulosVar[j])) {
                    numRangos.add(Integer.valueOf(j + 1));
                }
            }
        }
        ArrayList numRangosString = new ArrayList();
        for (int i = 0; i < numRangos.size(); i++) {
            numRangosString.add(String.valueOf(numRangos.get(i) + ","));
        }
        String[] necesito = (String[]) variables.toArray(new String[variables.size()]);
        boolean loNecesito = false;
        List rangos = new ArrayList();
        String rango = "";
        for (int i = 0; i < this.titulosVar.length; i++) {
            int myRange = i + 1;
            loNecesito = false;
            for (int j = 0; j < necesito.length; j++) {
                if (this.titulosVar[i].contains(necesito[j])) {
                    loNecesito = true;
                    break;
                }
            }
            if (loNecesito) {
                if (!rango.equals("")) {
                    rango = rango + String.valueOf(myRange - 1);
                    String[] values = rango.split("-");
                    if (values[0].equals(values[1])) {
                        rango = values[0];
                    }
                    rangos.add(rango);
                    rango = "";
                }
            } else if (!rango.contains("-")) {
                rango = String.valueOf(myRange) + "-";
            }
        }

        if ((!loNecesito) && (rango.contains("-"))) {
            rango = rango + this.titulosVar.length;
            String[] values = rango.split("-");
            if (values[0].equals(values[1])) {
                rango = values[0];
            }
            rangos.add(rango);
        }
        return rangos;
    }

    public ArrayList<String> separarClusters(PerfilEquipoAES peq, ArrayList<String> clusts, ArrayList<String> listaCentroide, int rolactual) {
        ArrayList seleccionados = new ArrayList();
        ArrayList results = new ArrayList();
        ArrayList nomVars = new ArrayList();

        Collections.sort(clusts);

        List clus = new ArrayList();
        List clusNum = null;

        String eval = "";
        for (int k = 0; k < clusts.size(); k++) {
            String linea = (String) clusts.get(k);
            if (linea.substring(1, linea.lastIndexOf("]")).equals(eval)) {
                if (clusNum != null) {
                    clusNum.add(clusts.get(k));
                }
            } else {
                if (clusNum != null) {
                    clus.add(clusNum);
                }
                clusNum = new ArrayList();
                eval = linea.substring(1, linea.lastIndexOf("]"));
                clusNum.add(clusts.get(k));
            }
        }
        clus.add(clusNum);
        for (int y = 0; y < clus.size(); y++) {
            System.out.println(clus.get(y));
        }

        ArrayList variables = new ArrayList();
        if (peq.getComposición().contains("heterogéneo")) {
            if (peq.getVarsColaboracion().isEmpty()) {
                ArrayList hetero = ((RolAES) peq.getLosRoles().get(rolactual)).traerVariablesRol();
                for (int i = 0; i < hetero.size(); i++) {
                    String[] temp = ((String) hetero.get(i)).split(",");
                    nomVars.add(temp[0]);
                    variables.add(Float.valueOf(Float.parseFloat(temp[1])));
                }
            } else {
                variables = new ArrayList();
                for (int i = 0; i < peq.getVarsColaboracion().size(); i++) {
                    String[] temp = ((String) peq.getVarsColaboracion().get(i)).split(",");
                    nomVars.add(temp[0]);
                    variables.add(Float.valueOf(Float.parseFloat(temp[1])));
                }
            }
        } else {
            for (int i = 0; i < peq.getVarsHomog().size(); i++) {
                String[] temp = ((String) peq.getVarsHomog().get(i)).split(",");
                nomVars.add(temp[0]);
                variables.add(Float.valueOf(Float.parseFloat(temp[1])));
            }
        }


        ArrayList arrCentroides = new ArrayList();
        for (int i = 0; i < listaCentroide.size(); i++) {
            ArrayList line = new ArrayList();
            String[] lineaCentroi = ((String) listaCentroide.get(i)).split(",");
            for (int e = 0; e < lineaCentroi.length; e++) {
                line.add(Float.valueOf(Float.parseFloat(lineaCentroi[e])));
            }
            arrCentroides.add(line);
        }

        int tam = variables.size();
        for (int k = 0; k < tam; k++) {
            ArrayList valorvariable = new ArrayList();
            for (int i = 0; i < arrCentroides.size(); i++) {
                valorvariable.add(((ArrayList) arrCentroides.get(i)).get(k));
            }

            ArrayList posiciones = new ArrayList();
            for (int b = 0; b < valorvariable.size(); b++) {
                posiciones.add(String.valueOf(b));
            }
            for (int a = 0; a < valorvariable.size() - 1; a++) {
                if (((Float) valorvariable.get(a)).floatValue() > ((Float) valorvariable.get(a + 1)).floatValue()) {
                    float tmp = ((Float) valorvariable.get(a)).floatValue();
                    valorvariable.set(a, valorvariable.get(a + 1));
                    valorvariable.set(a + 1, Float.valueOf(tmp));

                    String pos = (String) posiciones.get(a);
                    posiciones.set(a, posiciones.get(a + 1));
                    posiciones.set(a + 1, pos);
                }

            }

            float vari = ((Float) variables.get(k)).floatValue();
            if (vari == 1.0D) {
                seleccionados.add(posiciones.get(valorvariable.size() - 1));
            } else if (vari == 0.0D) {
                seleccionados.add(posiciones.get(0));
            } else {
                BigDecimal big = new BigDecimal(posiciones.size() / 2);
                big = big.setScale(1, RoundingMode.HALF_UP);
                seleccionados.add(posiciones.get(big.intValueExact()));
            }

        }

        for (int num = 0; num < seleccionados.size(); num++) {
            int cuenta = 0;
            for (int posic = 0; posic < seleccionados.size(); posic++) {
                if (num == Integer.parseInt((String) seleccionados.get(posic))) {
                    cuenta++;
                }
            }
            if (cuenta != 0) {
                results.add(Integer.valueOf(cuenta));
            }
        }
        ArrayList posiciones = new ArrayList();
        for (int b = 0; b < results.size(); b++) {
            posiciones.add(String.valueOf(b));
        }
        for (int a = 0; a < results.size() - 1; a++) {
            if (((Integer) results.get(a)).intValue() > ((Integer) results.get(a + 1)).intValue()) {
                int tmp = ((Integer) results.get(a)).intValue();
                results.set(a, results.get(a + 1));
                results.set(a + 1, Integer.valueOf(tmp));

                String pos = (String) posiciones.get(a);
                posiciones.set(a, posiciones.get(a + 1));
                posiciones.set(a + 1, pos);
            }
        }

        int posMayor = posiciones.size() - 1;
        return (ArrayList) clus.get(Integer.parseInt((String) posiciones.get(posMayor)));
    }

    private static void mensajeError() {
        System.out.println("ERROR: parametros incorrectos");
        System.out.println("formato: java ClusteringSimple [num. clusters] [fichero CSV]");
    }

    public String[] leerTitulos(String archivo) {
        String[] titulos = null;
        try {
            BufferedReader input = new BufferedReader(new FileReader(archivo));

            String linea = input.readLine();
            titulos = linea.split(",");
        } catch (IOException ex) {
            Logger.getLogger(ClusteringSimpleAES.class.getName()).log(Level.SEVERE, null, ex);
        }
        return titulos;
    }

    private ArrayList<String> dejarNombres(ArrayList<String> rtaClusters) {
        ArrayList nombres = new ArrayList();

        for (int i = 0; i < rtaClusters.size(); i++) {
            String[] lineaNombre = ((String) rtaClusters.get(i)).split(" ");
            nombres.add(lineaNombre[3]);
        }
        return nombres;
    }

    private boolean revisarPersonaAsig(ArrayList<ArrayList<String>> equiposConformadosRoles, String persona) {
        for (int i = 0; i < equiposConformadosRoles.size(); i++) {
            if (((ArrayList) equiposConformadosRoles.get(i)).contains(persona)) {
                return true;
            }
        }
        return false;
    }

    private int[] llamarRandom(int tamano, int campos) {
        int[] numRandom = new int[tamano];
        for (int i = 0; i < tamano; i++) {
            numRandom[i] = i;
        }
        Random rnd = new Random();
        for (int i = 0; i < numRandom.length; i++) {
            int nuevaPos = rnd.nextInt(tamano);

            int temp = numRandom[i];
            numRandom[i] = numRandom[nuevaPos];
            numRandom[nuevaPos] = temp;
        }
        int[] numeleg = new int[campos];
        System.arraycopy(numRandom, 0, numeleg, 0, numeleg.length);

        return numeleg;
    }

    private List<String> leerNombres(String archivo) {
        List nombres = new ArrayList();
        try {
            BufferedReader input = new BufferedReader(new FileReader(archivo));
            input.readLine();
            String linea;
            while ((linea = input.readLine()) != null) {
                String[] cadena = linea.split(",");
                nombres.add(cadena[0]);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return nombres;
    }
}