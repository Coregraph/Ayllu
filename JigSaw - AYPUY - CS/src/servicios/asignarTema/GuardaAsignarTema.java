/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.asignarTema;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import Util.Lock;
import co.edu.javeriana.ayllu.agents.communitymanageragent.CommunityManagerAgent;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_EventTypes;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Tema;
import servicios.asignarTema.aprendiz.DatosAprendizAT;
import servicios.asignarTema.aprendiz.EstadoAprendizAT;
import servicios.asignarTema.aprendiz.GuardaNotificacionAceptacionesAwarnes;
import servicios.asignarTema.aprendiz.GuardaReporteTemasDisponibles;
import servicios.asignarTema.aprendiz.GuardaSeleccionTemaConfirmada;
import servicios.asignarTema.aprendiz.GuardaSeleccionTemaNoConfirmada;

/**
 *
 * @author AylluAdmin
 */
public class GuardaAsignarTema extends GuardBESA {

    @Override
    public synchronized void funcExecGuard(EventBESA ebesa) {
        try {
            System.out.println("En GuardaAsignarTema");

            AgHandlerBESA myHandler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
            CommunityManagerAgent agenteCMA = (CommunityManagerAgent) myHandler.getAg();

            EstadoAsignarTema miEstado = (EstadoAsignarTema) this.getAgent().getState();
            ArrayList<ArrayList<String>> grupos = miEstado.getMisDatos().getGrupo();
            ArrayList<ArrayList<String>> rolesGrupos = miEstado.getMisDatos().getRolesGrupo();
            ArrayList<Tema> temas = miEstado.getMisDatos().getTemasDisponibles();
            List<AgHandlerBESA> listaAgentesComunidadParticipantes = new ArrayList<AgHandlerBESA>();
            AgHandlerBESA agenteComunidadParticipante = null;
            
            for(int j = 0;j <grupos.size();j++)
            {
                System.out.println("GRUPO EN " + this.getAgent().getAlias() + grupos.get(j));
                String idPersona="";
                AgHandlerBESA handlerPersona;
               
                for (int i = 0; i < grupos.get(j).size(); i++) {
//                    int y = 0;
//                    String linea = grupos.get(j).get(i);
//                    String [] campos = linea.split(":");
//                    while(y<campos.length){
//                        if(!campos[y].equals("Estudiante"))
//                            idPersona = campos[y];
//                        y++;
//                    }
                    idPersona = grupos.get(j).get(i);
                    try {

                        DatosAprendizAT datosAprendizAT = new DatosAprendizAT(null, null, null);
                        EstadoAprendizAT estadoAprendizAT = new EstadoAprendizAT(null, null, null);
                        estadoAprendizAT.setMisDatos(datosAprendizAT);

                        List<Class> guardas_CAAAT = new ArrayList<Class>();

                        guardas_CAAAT.add(GuardaReporteTemasDisponibles.class);
                        guardas_CAAAT.add(GuardaSeleccionTemaConfirmada.class);
                        guardas_CAAAT.add(GuardaSeleccionTemaNoConfirmada.class);
                        guardas_CAAAT.add(GuardaNotificacionAceptacionesAwarnes.class);

                        String aliasSMA = "SMA_" + idPersona;
                        handlerPersona = agenteCMA.getAdmLocal().getHandlerByAlias(aliasSMA);
                        Lock.getInstance().lock(); 
                        agenteComunidadParticipante = agenteCMA.createCommunityAgent(rolesGrupos.get(j).get(i), handlerPersona, guardas_CAAAT, estadoAprendizAT);
                        Lock.getInstance().unlock();
                        listaAgentesComunidadParticipantes.add(agenteComunidadParticipante);
                        
                         /*
                         * enviar ReporteTemasDisponibles
                         */
                        Ayllu_Data_Message data = new Ayllu_Data_Message(Ayllu_EventTypes.QUEST_REQUEST, myHandler, temas.get(j));
                        EventBESA eventoReportarTemasDisponibles = new EventBESA(GuardaReporteTemasDisponibles.class.getName(), data);
                        agenteComunidadParticipante.sendEvent(eventoReportarTemasDisponibles);
                        Thread.sleep(100);

                    } catch (ExceptionBESA ex) {
                        ReportBESA.error(ex);
                    }

                }               
            }
            
            miEstado.getMisDatos().setListaAgentesComunidadParticipantes(listaAgentesComunidadParticipantes);

            Thread.sleep(1000);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(GuardaAsignarTema.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(GuardaAsignarTema.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
}
