/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.recuperarInformacion;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communitymanageragent.CommunityManagerAgent;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import estrategias.factory.FactoryASHYI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import servicios.recuperarInformacion.aprendiz.DatosAprendizRI;
import servicios.recuperarInformacion.aprendiz.EstadoAprendizRI;
import servicios.recuperarInformacion.aprendiz.GuardaEstudiarOA;
import servicios.recuperarInformacion.aprendiz.GuardaNotificacionLecturasAwarnes;

/**
 *
 * @author luis
 */
public class GuardaRespuestaAdaptacionOVA extends GuardBESA{

    @Override
    public void funcExecGuard(EventBESA event) {
        //TODO recibir key y rutaOA del mecanismo de adaptación!
        String key = "";
        String rutaOA="";
        
        EstadoRecuperarInformacion estadoAT = (EstadoRecuperarInformacion) this.getAgent().getState();
        String[] idPersona = key.split("_"); //esto se hace en ADAPt_OVA
        AgHandlerBESA myHandler = null;
        try {
            myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRespuestaAdaptacionOVA.class.getName()).log(Level.SEVERE, null, ex);
        }
        AgHandlerBESA agenteComunidadAprendiz;
        estadoAT.getMisDatos().getOAPorEstudiante().put(key, rutaOA);//Al responder se coloca esto (respuesta DE ADAPT es RutaOA) y responde con el key
        //Se crea una agente aprendiz de este servicio y se le reporta el tema
        DatosAprendizRI datosAprendizRI = new DatosAprendizRI(null, null, null);
        datosAprendizRI.setOA(rutaOA);

        EstadoAprendizRI estadoAprendizRI = new EstadoAprendizRI(null, null, null);
        estadoAprendizRI.setMisDatos(datosAprendizRI);

        List<Class> guardas_CAAAT = new ArrayList<Class>();
        guardas_CAAAT.add(GuardaEstudiarOA.class);
        guardas_CAAAT.add(GuardaNotificacionLecturasAwarnes.class);
        try {
            CommunityManagerAgent myAgent = (CommunityManagerAgent)this.getAgent();
            String aliasSMA = "SMA_" + idPersona[0];
            AgHandlerBESA handlerPersona = this.getAgent().getAdmLocal().getHandlerByAlias(aliasSMA);
            estadoAT.getAgentesComunidadAprendiz().add(myAgent.createCommunityAgent(idPersona[0], handlerPersona, guardas_CAAAT, estadoAprendizRI));

        } catch (Exception ex) {
            Logger.getLogger(FactoryASHYI.class.getName()).log(Level.SEVERE, null, ex);
        }
        estadoAT.aumentarContador();
        if (estadoAT.getNumeroUsuarios() == estadoAT.getContadorUsuarios()) {
            estadoAT.getMisDatos().setAgentesComunidadAprendiz(estadoAT.getAgentesComunidadAprendiz());
            for (int i = 0; i < estadoAT.getAgentesComunidadAprendiz().size(); i++) {
                try {
                    agenteComunidadAprendiz = estadoAT.getAgentesComunidadAprendiz().get(i);
                    Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, myHandler, null);
                    EventBESA eventestudiar = new EventBESA(GuardaEstudiarOA.class.getName(), data);
                    agenteComunidadAprendiz.sendEvent(eventestudiar);
                } catch (ExceptionBESA ex) {
                    Logger.getLogger(CMA_RecuperarInformacion.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            estadoAT.getAgentesComunidadAprendiz().clear();
            estadoAT.initContadorUsuarios();
        }
    }
    
}
