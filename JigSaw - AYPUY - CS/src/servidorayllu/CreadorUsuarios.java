/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorayllu;

import AYLLU.model.data.Course;
import BESA.ExceptionBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Log.ReportBESA;
import co.edu.javeriana.ayllu.agents.interfaceagentagent.InterfaceAgent;
import co.edu.javeriana.ayllu.agents.representativeagent.RepresentativeAgent;
import co.edu.javeriana.ayllu.agents.representativeagent.RepresentativeAgentState;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SessionManagerAgent;
import co.edu.javeriana.ayllu.agents.sessionmanageragent.SessionManagerAgentState;
import estrategias.jigsawASHYI.Interface.Agents.IA_SynchCommInterface;
import estrategias.jigsawASHYI.Interface.Agents.IA_SynchCommInterfaceState;
import estrategias.jigsawASHYI.Interface.MessageGUI;
import guardasagentesesion.GuardCommMundoAgentes;
import guardasagentesesion.GuardaSubscripcion;
import guardasagentesesion.InterfazGuardReply;
import guardasagentesesion.InterfazGuardRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import persistencia.BinaryDAOFactory;
import persistencia.ProfileUsuarioAyllu;

/**
 *
 * @author luis
 */
public class CreadorUsuarios {

     public void crearUsuarios(ArrayList<String> datos) {
        
        

        RepresentativeAgentState raState = null;
        RepresentativeAgent ra = null;

        SessionManagerAgentState smaState = null;
        SessionManagerAgent sma = null;

        ProfileUsuarioAyllu perfilpersistente;

        List<String> usernames=new ArrayList<String>();

        BinaryDAOFactory baseDatos = new BinaryDAOFactory();
//        List<Object> datos = baseDatos.getListaDatos(BinaryDAOFactory.TIPO_DATO_PERSONAS);        
        
        for (int i = 0; i < datos.size(); i++) {
            //persona = (Persona) datos.get(i);
            try {
                Course firstCourse = new Course("SIST_INT", "Sistemas Inteligentes");
                raState = new RepresentativeAgentState();
                raState.addAnswer("Persona", "OK");
                RepresentativeAgent.prepareInstance();//cambio para que sea consistente en todo AYLLU, primero se prepara la instancia
                ra = new RepresentativeAgent("RA_" + datos.get(i), raState, 0.91);//luego se crea con una constructora normal
                ra.start(); 
                System.out.println("Se creo: "+ra.getAlias());
                smaState = new SessionManagerAgentState(ra.getAdmLocal().getHandlerByAid(ra.getAid()));
                //smaState.getTheUserProfile().addCourse(firstCourse);
                Class guardasSMA[]={GuardaSubscripcion.class,GuardCommMundoAgentes.class,InterfazGuardReply.class,InterfazGuardRequest.class}; 
                List<Class> guardas_SMA=Arrays.asList(guardasSMA);
                SessionManagerAgent.prepareInstance(guardas_SMA); //cambio para que sea consistente en todo AYLLU, primero se prepara la instancia
                sma = new SessionManagerAgent("SMA_" + datos.get(i), smaState, 0.91); //luego se crea con una constructora normal
                System.out.println("Se creo: "+sma.getAlias());
                sma.start(); 
                
//                //IA
//                InterfaceAgent.prepareIntance();
//                IA_SynchCommInterfaceState estadoIA = new IA_SynchCommInterfaceState(AdmBESA.getInstance().getHandlerByAid(sma.getAid()));                
//                IA_SynchCommInterface iface = new IA_SynchCommInterface("IA_"+datos.get(i), estadoIA, 0.91);
//                MessageGUI gui = new MessageGUI(iface, "");
//                estadoIA.setTheGUI(gui);
//                iface.start();
//                smaState.addInterfaceAgentHandler(AdmBESA.getInstance().getHandlerByAid(iface.getAid()));
//                System.out.println("Se creo: "+iface.getAlias());
                
                usernames.add(datos.get(i));
                
            } catch (ExceptionBESA ex) {
                ReportBESA.error(ex);
            }
            perfilpersistente = new ProfileUsuarioAyllu(datos.get(i), ra.getAlias(), sma.getAid());
            baseDatos.persist(perfilpersistente);
        }
        /*WallCoopServState astate  = new WallCoopServState(usernames, null, null);
        astate.setCourseID("SIST_INT");
        CommunityManagerAgent.prepareInstance();
        try {
            WallCoopServ wallagent =  new WallCoopServ("Sistemas Inteligentes", astate, 0.91);
            wallagent.start();
        } catch (KernellAgentExceptionBESA ex) {
            Logger.getLogger(CreadorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    }
}
