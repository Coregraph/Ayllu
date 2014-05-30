/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.recuperarInformacion;

import java.util.HashMap;
import java.util.Set;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import Util.Lock;
import co.edu.javeriana.ayllu.agents.communitymanageragent.CommunityManagerAgent;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Tema;
import servicios.recuperarInformacion.aprendiz.DatosAprendizRI;
import servicios.recuperarInformacion.aprendiz.EstadoAprendizRI;
import servicios.recuperarInformacion.aprendiz.GuardaEstudiarOA;
import servicios.recuperarInformacion.aprendiz.GuardaNotificacionLecturasAwarnes;

/**
 *
 * @author AylluAdmin
 */
public class GuardaRecuperarInformacion extends GuardBESA {

    @Override
    public synchronized void funcExecGuard(EventBESA ebesa) {
        try {
            System.out.println("En GuardaRecuperarInformacion");

            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
            CommunityManagerAgent agenteCMA = (CommunityManagerAgent) myHandler.getAg();

            EstadoRecuperarInformacion miEstado = (EstadoRecuperarInformacion) this.getAgent().getState();
            HashMap<String, Tema> temasPorEstudiante = miEstado.getMisDatos().getTemasPorEstudiante();

            System.out.println("temasPorEstudiante " + this.getAgent().getAlias() + temasPorEstudiante);
            Set<String> keys = temasPorEstudiante.keySet();
            String key;
            Tema tema;
            AgHandlerBESA handlerPersona;
            AgHandlerBESA agenteComunidadParticipante = null;
            
            for (Iterator<String> it = keys.iterator(); it.hasNext();) {
                key = it.next();
                tema = temasPorEstudiante.get(key);


                DatosAprendizRI datosAprendizRI = new DatosAprendizRI(null, null, null);
                EstadoAprendizRI estadoAprendizRI = new EstadoAprendizRI(null, null, null);
                estadoAprendizRI.setMisDatos(datosAprendizRI);

                List<Class> guardas_CAAAT = new ArrayList<Class>();
                guardas_CAAAT.add(GuardaEstudiarOA.class);
                guardas_CAAAT.add(GuardaNotificacionLecturasAwarnes.class);

                String aliasSMA = "SMA_" + key;
                handlerPersona = agenteCMA.getAdmLocal().getHandlerByAlias(aliasSMA);
                Lock.getInstance().lock();
                agenteComunidadParticipante = agenteCMA.createCommunityAgent(key, handlerPersona, guardas_CAAAT, estadoAprendizRI);
                miEstado.getMisDatos().getAgentesComunidadAprendiz().add(agenteComunidadParticipante);
                Lock.getInstance().unlock();

                Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, myHandler, tema);
                EventBESA eventoReportarTemasDisponibles = new EventBESA(GuardaEstudiarOA.class.getName(), data);
                agenteComunidadParticipante.sendEvent(eventoReportarTemasDisponibles);

            }
        } catch (InterruptedException ex) {
            Logger.getLogger(GuardaRecuperarInformacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaRecuperarInformacion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
