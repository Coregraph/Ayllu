package besa_adaptado.adaptador;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import adaptation.common.adaptationmechanism.AdaptationMechanismAES;
import adaptation.common.filter.FilterResultAES;
import adaptation.common.query.AdaptationQueryAES;
import adaptation.common.query.EnrichedAdaptationQueryAES;
import besa_adaptado.filtros.ResultadoFiltro;
import besa_adaptado.perfiles.PerfilEquipoAES;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mateo_package.ClusteringSimpleAES;
import mateo_package.NuevosEquiposAES;

public class MecanismoAdaptacion extends AdaptationMechanismAES {

    private int canttotalpersonas;
    private AgHandlerBESA agResponse;
    private String guardResponse;

    public MecanismoAdaptacion(String alias,String guardResponse) {
        try {
            this.canttotalpersonas = 0;
            this.agResponse = AdmBESA.getInstance().getHandlerByAlias(alias);        
            this.guardResponse = guardResponse;
        } catch (ExceptionBESA ex) {
            Logger.getLogger(MecanismoAdaptacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public EnrichedAdaptationQueryAES enrichQuery(AdaptationQueryAES theQuery, List<FilterResultAES> filterResults) {
        String resultFileEstudiante = new String();
        PerfilEquipoAES resultPerfilEquipo = null;

        for (FilterResultAES filterResult : filterResults) {
            ResultadoFiltro result = (ResultadoFiltro) filterResult;
            if (result.getCategory().equals("user")) {
                resultFileEstudiante = result.getRespuestaEstudiante();
            } else if (result.getCategory().equals("team")) {
                resultPerfilEquipo = result.getRespuestaEquipo();
            }

        }

        NuevosEquiposAES rtaEquipos = coreMateo(resultFileEstudiante, resultPerfilEquipo);
        ConsultaEnriquecida resp = new ConsultaEnriquecida(rtaEquipos);
        System.out.println("");

        for (int i = 0; i < rtaEquipos.getEquiposConformados().size(); i++) {
            System.out.println("==========================");
            int numeq = i + 1;
            System.out.println("EQUIPO " + numeq);
            ArrayList equipoActual = (ArrayList) rtaEquipos.getEquiposConformados().get(i);
            ArrayList equipoActualRoles = (ArrayList) rtaEquipos.getRolesEquipos().get(i);
            for (int k = 0; k < equipoActual.size(); k++) {
                System.out.println((String) equipoActual.get(k) + " " + (String) equipoActualRoles.get(k));
            }
        }
        EventBESA event = new EventBESA(this.guardResponse, resp);
        try {
            this.agResponse.sendEvent(event);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(MecanismoAdaptacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    private NuevosEquiposAES coreMateo(String resultFileEstudiante, PerfilEquipoAES resultPerfilEquipo) {
        String nombreFileWeka = compatibleWeka(resultFileEstudiante);

        ClusteringSimpleAES cs = ClusteringSimpleAES.getInstance();
        NuevosEquiposAES respuestaDefinitivaEquipos = cs.recibirHeterogeneo(resultPerfilEquipo, nombreFileWeka, this.canttotalpersonas);
        return respuestaDefinitivaEquipos;
    }

    private String compatibleWeka(String resultFileEstudiante) {
        try {
            BufferedReader input = new BufferedReader(new FileReader(resultFileEstudiante));
            FileWriter fichero = new FileWriter("./estudiantesWEKA.csv");
            PrintWriter pw = new PrintWriter(fichero);
            String linea;
            while ((linea = input.readLine()) != null) {
                this.canttotalpersonas += 1;
                linea = linea.replace(",", ".");
                linea = arreglarNulos(linea);
                linea = linea.replace(";", ",");
                pw.println(linea);
            }
            fichero.close();
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(MecanismoAdaptacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        String nombreArchWeka = "./estudiantesWEKA.csv";
        return nombreArchWeka;
    }

    private String arreglarNulos(String linea) {
        if (linea.charAt(0) == ';') {
            linea = linea.replaceFirst(";", "-;");
        }
        while (linea.contains(";;")) {
            linea = linea.replaceAll(";;", ";-;");
        }
        if (linea.charAt(linea.length() - 1) == ';') {
            linea = linea += "-";
        }
        return linea;
    }
}
