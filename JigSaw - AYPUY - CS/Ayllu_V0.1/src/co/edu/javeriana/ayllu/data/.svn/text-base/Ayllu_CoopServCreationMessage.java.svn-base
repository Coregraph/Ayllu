/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.data;

import AYLLU.model.data.InformationRequestForm;
import BESA.Kernel.Agent.Event.DataBESA;
import java.util.List;

/**
 *
 * @author LG
 */
public class Ayllu_CoopServCreationMessage extends DataBESA {

    private String courseId;
    private InformationRequestForm theForm;
    private List<String> userNames;

    public Ayllu_CoopServCreationMessage(String courseId, InformationRequestForm theForm) {
        this.courseId = courseId;
        this.theForm = theForm;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }

    public InformationRequestForm getTheForm() {
        return theForm;
    }

    public String getCourseId() {
        return courseId;
    }

    public List<String> getUserNames() {
        return userNames;
    }
}
