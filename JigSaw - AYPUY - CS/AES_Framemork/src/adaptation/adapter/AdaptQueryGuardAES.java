/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptation.adapter;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import adaptation.common.adaptationmechanism.AdaptationMechanismAES;
import adaptation.common.query.AdaptationQueryAES;
import adaptation.adapter.data.DataAdaptertoProfileManager;
import adaptation.adapter.data.DataContextToProfileManager;
import adaptation.common.filter.FilterAES;
import adaptation.common.ProfileAES;
import adaptation.profilemanager.GuardRecieveNewContext;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AYLLU
 */
public abstract class AdaptQueryGuardAES extends GuardBESA {

    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) {
        boolean attending = ((AdapterAgentStateAES) objEvalBool).isExecuting();
        return !attending;
    }

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        List<ProfileAES> context; //El contexto se compone de diferentes perfiles actuales (pueden estar incompletos)
        List<ProfileAES> restrictions; //Las rrestricciones son perfiles con campos que se deben sobrescribir en los perfiles del contexto
        List<ProfileAES> newContext; //El nuevo contexto es el contexto + restricciones
        //Poniendo el estado en "atendiendo"
        AdapterAgentStateAES agentState = (AdapterAgentStateAES) this.getAgent().getState();
        agentState.setExecuting(true);
        //<Obtener el handler del Agente
        AgHandlerBESA handler = null;
        try {
            handler = this.getAgent().getAdmLocal().getHandlerByAlias("Profile_Manager");
        } catch (ExceptionBESA eb) {
            ReportBESA.error(eb);
        }
        //obteniendo el contexto y las restricciones del query
        AdaptationQueryAES query = (AdaptationQueryAES) ebesa.getData();
        context = query.getContext();
        restrictions = query.getRestrictions();
        agentState.setQuery(query);
        //aplicando restricciones al contexto para generar el nuevo contexto
        newContext = applyRestrictions(context, restrictions);
        DataContextToProfileManager dataContext = new DataContextToProfileManager(newContext);
        EventBESA nc = new EventBESA(GuardRecieveNewContext.class.getName(), dataContext);
        try {
            //Enviando el evento
            handler.sendEvent(nc);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(AdaptQueryGuardAES.class.getName()).log(Level.SEVERE, null, ex);
        }
        //obteniendo el mecanismo de adaptación
        AdaptationMechanismAES adapMechanism = selectAdaptationMechanism(context, restrictions);
        //coloca mecanismo de adaptación el cual coloca el numero de filtros a llenar
        agentState.setSelectedAdaptationMechanism(adapMechanism);
        List<FilterAES> filterList = adapMechanism.getAssociateFilters();
        //ciclo por cada uno de los filtros

        for (FilterAES FilterAES : filterList) {
            DataAdaptertoProfileManager dataToProfileManager = new DataAdaptertoProfileManager(FilterAES);

            EventBESA eventToProfileManager = new EventBESA(agentState.getFilterGuard(), dataToProfileManager);

            //Envio de evento al profile manager
            try {
                handler.sendEvent(eventToProfileManager);
            } catch (ExceptionBESA ex) {
                Logger.getLogger(AdaptQueryGuardAES.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     *
     * @param context El contexto actual
     * @param restrictions Las restricciones a la busqueda
     * @return El nejor mecanismo de adaptación
     */
    public AdaptationMechanismAES selectAdaptationMechanism(List<ProfileAES> context, List<ProfileAES> restrictions) {
        List<ProfileAES> newContext = applyRestrictions(context, restrictions);
        AdapterAgentStateAES agentState = (AdapterAgentStateAES) this.getAgent().getState();
        List<AdaptationMechanismAES> adapMech = agentState.getAdaptationMechanisms();
        int coincidences[] = new int[adapMech.size()];
        int mostCoincidences = 0, bestAdaptationMechanismPos = -1;
        for (int i = 0; i < adapMech.size(); i++) {
            int numberCoincidences = 0;
            AdaptationMechanismAES adaptationMechanismBESA = adapMech.get(i);
            List<FilterAES> filters = adaptationMechanismBESA.getAssociateFilters();
            for (Iterator<FilterAES> it = filters.iterator(); it.hasNext();) {
                FilterAES FilterAES = it.next();
                for (Iterator<ProfileAES> ctxInfo = newContext.iterator(); ctxInfo.hasNext();) {
                    ProfileAES ProfileAES = ctxInfo.next();
                    if (FilterAES.getCategory().equals(ProfileAES.getCategory())) {
                        numberCoincidences++;
                    }
                }

            }
            coincidences[i] = numberCoincidences;
        }
        for (int i = 0; i < coincidences.length; i++) {
            if (mostCoincidences <= coincidences[i]) {
                mostCoincidences = coincidences[i];
                bestAdaptationMechanismPos = i;
            }
        }
        return adapMech.get(bestAdaptationMechanismPos);
    }

    /**
     *
     * @param context El contexto actual
     * @param restrictions Las restricciones a la busqueda
     * @return Nuevo contexto luego de aplicar las restricciones
     */
    public abstract List<ProfileAES> applyRestrictions(List<ProfileAES> context, List<ProfileAES> restrictions);
}
