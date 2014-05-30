/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptation.adapter;

import BESA.Kernel.Agent.StateBESA;
import adaptation.common.adaptationmechanism.AdaptationMechanismAES;
import adaptation.common.filter.FilterAES;
import adaptation.common.filter.FilterResultAES;
import adaptation.common.query.AdaptationQueryAES;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AYLLU
 */
public class AdapterAgentStateAES extends StateBESA {

    private String filterGuard=null;//Guarda el nombre de la guarda a ejecutar los filtros;
    private boolean executing; //indica si se esta ejecutando una busqueda
    private int filtersToFill; // contador que indica el numero de filtros pendientes por llenar en el mecanismo de adaptación
    private AdaptationMechanismAES selectedAdaptationMechanism;
    private List<FilterAES> filters; //Lista de filtros
    private List<AdaptationMechanismAES> adaptationMechanisms; // Lista de mecanismos de adaptación
    private List<FilterResultAES> filterResults;
    private AdaptationQueryAES theQuery;
    private String agentToRespond;
    private String guardToRespond;

    public AdapterAgentStateAES() {
        super();
        executing = false;
        filters = new ArrayList<FilterAES>();
        adaptationMechanisms = new ArrayList<AdaptationMechanismAES>();
        filterResults = new ArrayList<FilterResultAES>();
    }
    public AdapterAgentStateAES(String filterGuard) {
        super();
        this.filterGuard=filterGuard;
        executing = false;
        filters = new ArrayList<FilterAES>();
        adaptationMechanisms = new ArrayList<AdaptationMechanismAES>();
        filterResults = new ArrayList<FilterResultAES>();
    }
    
    public String getAgentToRespond() {
        return agentToRespond;
    }

    public String getGuardToRespond() {
        return guardToRespond;
    }

    public void setAgentToRespond(String agentToRespond) {
        this.agentToRespond = agentToRespond;
    }

    public void setGuardToRespond(String guardToRespond) {
        this.guardToRespond = guardToRespond;
    }
    
    /**
     *Obtiene el numero de filtros a llenar po los perfiles solicitados al ProfileManager
     * @return obtiene el numero de filtros asociados que deben llenarse
     */
    public int getFiltersToFill() {
        return filtersToFill;
    }

    /**
     *Coloca el número de filtros a llenar
     * @param filtersToFill el numero de filtros a llenar
     */
    public void setFiltersToFill(int filtersToFill) {
        this.filtersToFill = filtersToFill;
    }

    /**
     * Función que indica si el agente esta atendiendo una solicitud
     * @return estado del agente (true) si hay una consulta en curso o (false) si esta desocupado
     */
    public boolean isExecuting() {
        return executing;
    }

    /**
     * Coloca el parámetro de ejecución
     * @param executing variable booleana que indica si se esta ejecutando una consulta (true) o si esta disponible (false)
     */
    public void setExecuting(boolean executing) {
        this.executing = executing;
    }

    /**
     * Añade un filtro a la lista general de filtros
     * @param filter el filtro a añadir
     */
    public void addFilter(FilterAES filter) {
        filters.add(filter);
    }

    /**
     * Agrega un Mecanismo de Adaptación
     * @param adaptationMechanism el mecanismo a agregar
     */
    public void addAdaptationMechanism(AdaptationMechanismAES adaptationMechanism) {
        adaptationMechanisms.add(adaptationMechanism);
    }

    /**
     * Decrementa el numero de filtros una vez se llena uno de los filtros
     */
    public void decrementFilterCount() {
        filtersToFill -= 1;
    }

    /**
     * Retorna la lista de Mecanismos de Adaptación
     * @return Lista de Mecanismos de Adaptación
     */
    public List<AdaptationMechanismAES> getAdaptationMechanisms() {
        return adaptationMechanisms;
    }

    /**
     * Obtiene la lista de filtros
     * @return Lista de Filtros
     */
    public List<FilterAES> getFilters() {
        return filters;
    }

    /**
     * Recibe una lista de filtros
     * @param filters La lista de filtros asociada
     */
    public void setFilters(List<FilterAES> filters) {
        this.filters = filters;
    }

    /**
     * Obtiene la lista de Resultados de los Filtros
     * @return lista de Información de Alta Abstracción
     */
    public List<FilterResultAES> getFilterResults() {
        return filterResults;
    }

    /**
     * Coloca una lista de Información de Alta Abstracción
     * @param filterResults La nueva lista de Información de Alta Abstracción
     */
    public void setFilterResults(List<FilterResultAES> filterResults) {
        this.filterResults = filterResults;
    }
    /**
     * Añade un nuevo resultado de filtro a la lita de resultados
     * @param filterResult El nuevo resultado de filtro a ser agregado
     */
    public void addFilterResult(FilterResultAES filterResult){
        this.filterResults.add(filterResult);
    }
    /**
     * Obtiene el mecanismo de Adaptación seleccionado
     * @return Mecanismo de Adaptación seleccionado
     */
    public AdaptationMechanismAES getSelectedAdaptationMechanism() {
        return selectedAdaptationMechanism;
    }
    /**
     * Indica cual es el mecanismo de adaptación seleccionado y llena el numero de filtros a llenar
     * @param selectedAdaptationMechanism el mecanismo de adaptacion seleccionado
     */
    public void setSelectedAdaptationMechanism(AdaptationMechanismAES selectedAdaptationMechanism) {
        this.selectedAdaptationMechanism = selectedAdaptationMechanism;
        this.setFiltersToFill(this.selectedAdaptationMechanism.getAssociateFilters().size());
    }

    void setQuery(AdaptationQueryAES query) {
        this.theQuery = query;
    }

    public AdaptationQueryAES getTheQuery() {
        return theQuery;
    }

    public void setAdaptationMechanisms(List<AdaptationMechanismAES> adaptationMechanisms) {
        this.adaptationMechanisms = adaptationMechanisms;
    }

    public String getFilterGuard() {
        return filterGuard;
    }

    public void setFilterGuard(String filterGuard) {
        this.filterGuard = filterGuard;
    }

    
    


}
