/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.data;

import AYLLU.model.data.CooperativeServiceAwareness;
import BESA.Kernel.Agent.Event.DataBESA;

/**
 *
 * @author luis
 */
public class Ayllu_CoopServRegistrationMessage extends DataBESA{
    private String courseId;
    private CooperativeServiceAwareness cooperativeService;

    public Ayllu_CoopServRegistrationMessage(String courseId, CooperativeServiceAwareness cooperativeService) {
        this.courseId = courseId;
        this.cooperativeService = cooperativeService;
    }

    public CooperativeServiceAwareness getCooperativeService() {
        return cooperativeService;
    }

    public String getCourseId() {
        return courseId;
    }
    
    
    
}
