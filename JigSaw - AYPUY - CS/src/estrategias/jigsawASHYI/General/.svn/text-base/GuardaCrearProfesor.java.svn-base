/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estrategias.jigsawASHYI.General;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import co.edu.javeriana.ayllu.agents.communityagent.AgentRole;
import co.edu.javeriana.ayllu.agents.interfaceagentagent.InterfaceAgent;
import co.edu.javeriana.ayllu.agents.representativeagent.RepresentativeAgent;
import co.edu.javeriana.ayllu.agents.representativeagent.RepresentativeAgentState;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SessionManagerAgent;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SessionManagerAgentState;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import estrategias.jigsawASHYI.Interface.Agents.IA_SynchCommInterface;
import estrategias.jigsawASHYI.Interface.Agents.IA_SynchCommInterfaceState;
import estrategias.jigsawASHYI.Interface.MessageGUI;
import java.util.ArrayList;
import java.util.List;
import service.Profesor.DatosProfesor;
import service.Profesor.EstadoProfesor;
import service.Profesor.GuardaDarInstrucciones;
import service.Profesor.GuardaEvaluarInformeGrupo;
import service.Profesor.GuardaEvaluarInformeTema;
import service.Profesor.GuardaRecibirSMEvaluarInformeGrupo;
import service.Profesor.GuardaRecibirSMEvaluarInformeTema;
import service.Profesor.GuardaRetroalimentacionPreg;
import service.Profesor.GuardaRetroalimentacionPregGrupo;

/**
 *
 * @author Yolima
 */
public class GuardaCrearProfesor extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA event) {
        try {     
            
            System.out.println("en GuardaCrearProfesor");
            AgHandlerBESA handler = this.getAgent().getAdmLocal().getHandlerByAid(this.getAgent().getAid());
            Estado_JigsawASHYI miEstado = (Estado_JigsawASHYI) handler.getAg().getState();
            Ayllu_Data_Message theData = (Ayllu_Data_Message) event.getData();
            DatosCrearProfesor datos = (DatosCrearProfesor)theData.getMessage();
            
            //Creacion Profesor: RA - SMA - CA
            DatosProfesor dat = new DatosProfesor(datos.getEstudiantes());
            EstadoProfesor estadoP = new EstadoProfesor(null, null, null);
            estadoP.setMisDatos(dat);

            List<Class> guardas_Prof = new ArrayList<Class>();
            guardas_Prof.add(GuardaDarInstrucciones.class);
            guardas_Prof.add(GuardaEvaluarInformeTema.class);
            guardas_Prof.add(GuardaRecibirSMEvaluarInformeGrupo.class);
            guardas_Prof.add(GuardaRecibirSMEvaluarInformeTema.class);
            guardas_Prof.add(GuardaEvaluarInformeGrupo.class);
            guardas_Prof.add(GuardaRetroalimentacionPreg.class);
            guardas_Prof.add(GuardaRetroalimentacionPregGrupo.class);

            //crear RA
            RepresentativeAgentState raState = new RepresentativeAgentState();
            raState.addAnswer("Profesor", "OK");
            RepresentativeAgent.prepareInstance();//cambio para que sea consistente en todo AYLLU, primero se prepara la instancia
            RepresentativeAgent ra = new RepresentativeAgent("RA_Profesor" , raState, 0.91);//luego se crea con una constructora normal
            ra.start(); 
            SessionManagerAgentState smaState = new SessionManagerAgentState(ra.getAdmLocal().getHandlerByAid(ra.getAid()));

            //crear SMA
            SessionManagerAgent.prepareInstance(guardas_Prof); //cambio para que sea consistente en todo AYLLU, primero se prepara la instancia
            SessionManagerAgent sma = new SessionManagerAgent("SMA_Profesor" , smaState, 0.91); //luego se crea con una constructora normal
            System.out.println("Se creo: "+sma.getAlias());
            sma.start();
            
            //crear CA
            AgHandlerBESA handlerSMA = sma.getAdmLocal().getHandlerByAid(this.getAgent().getAdmLocal().searchAidByAlias("SMA_Profesor"));                 
            CMA_Jigsaw cma = (CMA_Jigsaw)handler.getAg();
            miEstado.addCommunityAgent(new AgentRole("Profesor", cma.createCommunityAgent("Profesor",handlerSMA, guardas_Prof, estadoP )));
            System.out.println("Creado CA profesor");
            
            //crear IA
            InterfaceAgent.prepareIntance();
            IA_SynchCommInterfaceState estadoIA = new IA_SynchCommInterfaceState(handlerSMA);
            IA_SynchCommInterface iface = new IA_SynchCommInterface("IA_Profesor", estadoIA, miEstado.getVolatileGroupPassword());
            MessageGUI gui = new MessageGUI(iface, "Profesor");
            estadoIA.setTheGUI(gui);
            iface.start();
            smaState.addInterfaceAgentHandler(this.getAgent().getAdmLocal().getHandlerByAid(iface.getAid()));
            System.out.println("Creado IA profesor");
            System.out.println("Profesor totalmente creado");
            
        } catch (ExceptionBESA excbesa) {
            ReportBESA.error(excbesa);
        }

    }
}
