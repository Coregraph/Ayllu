package besa_adaptado;

import adaptation.AdapterAdminAES;
import adaptation.adapter.AdapterAgentStateAES;
import adaptation.common.filter.FilterAES;
import besa_adaptado.adaptador.Consulta;
import besa_adaptado.adaptador.MecanismoAdaptacion;
import besa_adaptado.adaptador.guardas.GuardRecieveQuery;
import besa_adaptado.filtros.FiltroEquipo;
import besa_adaptado.filtros.FiltroEstudiante;
import besa_adaptado.guardas.manejadorperfiles.GuardReceiveFilter;
import besa_adaptado.perfiles.PerfilEquipoAES;
import besa_adaptado.perfiles.PerfilEstudiante;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import lectorcsv.LectorCSV;

public class CreadorEjemplo_Mateo {

    private String laConsulta;
    public static String Respuesta;

    public CreadorEjemplo_Mateo(String laConsulta) {
        this.laConsulta = laConsulta;
    }

    public void lanzarEjemplo(String agentReplyAlias, String guardReply) {
        AdapterAdminAES adm = new AdapterAdminAES(GuardRecieveQuery.class, GuardReceiveFilter.class);

        AdapterAgentStateAES adapterState = new AdapterAgentStateAES();
        llenarEstadoAdaptador(adapterState, agentReplyAlias, guardReply);
        adm.createAdapterAgent(adapterState, agentReplyAlias, guardReply);
        adm.createProfileManager();

        Consulta consulta = new Consulta(this.laConsulta);
        llenarConsulta(consulta, adapterState.getAgentToRespond(), adapterState.getGuardToRespond());
        adm.launchQuery(consulta);
    }

    public String loadCategory(String property) {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("res/adaptationCategories.properties"));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return props.getProperty(property);
    }

    private void llenarEstadoAdaptador(AdapterAgentStateAES estado, String aliasToSendEnd, String guardToSendEnd) {
        ArrayList mecanismos = new ArrayList();
        ArrayList filtros = new ArrayList();
        String[] tema = this.laConsulta.split(" ");

        FiltroEstudiante f_est = new FiltroEstudiante("user");
        f_est.addSubCategory("coloresHermann");

        FiltroEquipo f_equipo = new FiltroEquipo("team");
        f_est.addSubCategory("composiciÃ³n");

        filtros.add(f_est);
        filtros.add(f_equipo);

        MecanismoAdaptacion mecanismo1 = new MecanismoAdaptacion(aliasToSendEnd, guardToSendEnd);

        mecanismo1.putFilter((FilterAES) filtros.get(0));
        mecanismo1.putFilter((FilterAES) filtros.get(1));

        mecanismos.add(mecanismo1);

        estado.setAdaptationMechanisms(mecanismos);
        estado.setFilters(filtros);
    }

    private void llenarConsulta(Consulta consulta, String alias, String guard) {
        ArrayList context = new ArrayList();

        LectorCSV lectorColores = new LectorCSV();

        ArrayList<ArrayList<String>> datosColores = lectorColores.leerarchivo();
        for (ArrayList arrayList : datosColores) {
            PerfilEstudiante usu = new PerfilEstudiante();
            usu.setNombrestudiante((String) arrayList.get(0));
            context.add(usu);
        }

        PerfilEquipoAES pequipo = new PerfilEquipoAES();
        pequipo.setComposición("heterogéneo");
        context.add(pequipo);

        ArrayList restrictions = new ArrayList();

        consulta.setContext(context);
    }
}
