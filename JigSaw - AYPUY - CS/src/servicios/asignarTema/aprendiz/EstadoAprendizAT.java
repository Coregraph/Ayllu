/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.asignarTema.aprendiz;

import AYLLU.model.data.InformationRequestForm;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.agents.communityagent.AgentRole;
import co.edu.javeriana.ayllu.agents.communityagent.CommunityAgentState;
import co.edu.javeriana.ayllu.agents.communitymanageragent.CMA_SendAwarenessMessageGuard;
import co.edu.javeriana.ayllu.data.Ayllu_AwarenessMessageRequestData;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Tema;

/**
 *
 * @author AylluAdmin
 */
public class EstadoAprendizAT extends CommunityAgentState {

    private DatosAprendizAT misDatos;
    private List<Tema> temasEnviados;
    private AgHandlerBESA lastSenderHandler;

    public EstadoAprendizAT(List<AgentRole> sameVolatileGroupHandlers, AgHandlerBESA sessionManagerHandler, AgHandlerBESA communityManagerHandler) {
        super(sameVolatileGroupHandlers, sessionManagerHandler, communityManagerHandler);
    }

    public EstadoAprendizAT(DatosAprendizAT misDatos, List<AgentRole> sameVolatileGroupHandlers, AgHandlerBESA sessionManagerHandler, AgHandlerBESA communityManagerHandler) {
        super(sameVolatileGroupHandlers, sessionManagerHandler, communityManagerHandler);
        this.misDatos = misDatos;
    }
    
    

    

    public DatosAprendizAT getMisDatos() {
        return misDatos;
    }

    /**
     * @param misDatos the misDatos to set
     */
    public void setMisDatos(DatosAprendizAT misDatos) {
        this.misDatos = misDatos;
    }
    //XXX _nuevo!
    public void sendAwarenessMessageToGUI(String senderId, String guardname, String destination, String coopserID, List<Tema> temasDisponibles) {
        InformationRequestForm reqInfoForm = new InformationRequestForm("Selección de Temas");
        //reqInfoForm.setId(UUID.randomUUID().toString());
        List<String> nombresTemas= new ArrayList<String>();
        for (Tema tema : temasDisponibles) {
            nombresTemas.add(tema.getNombre() /*+ ":\n\t"+tema.getDescripcion() */);
        }
        
        reqInfoForm.addSelectOneQuestion(coopserID, nombresTemas);
        
        Ayllu_AwarenessMessageRequestData reqData = new Ayllu_AwarenessMessageRequestData(Ayllu_AwarenessMessageRequestData.MessageType.REQUEST,reqInfoForm,destination, coopserID);
        reqData.setSenderId(senderId);
        reqData.setGuardToRespond(guardname);
        
        
        EventBESA evMsjAwrn = new EventBESA(CMA_SendAwarenessMessageGuard.class.getName(),reqData);
        try {
            this.getCommunityManagerHandler().sendEvent(evMsjAwrn);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(EstadoAprendizAT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void setTemasEnviados(List<Tema> temasDisponibles) {
        this.temasEnviados=temasDisponibles;
    }

    public List<Tema> getTemasEnviados() {
        return temasEnviados;
    }

    void setSenderHandler(AgHandlerBESA senderHandler) {
        this.lastSenderHandler = senderHandler;
    }

    public AgHandlerBESA getLastSenderHandler() {
        return lastSenderHandler;
    }
    
}
