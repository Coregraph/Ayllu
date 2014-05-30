/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.sessionmanageragent;

import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.data.Ayllu_Data_Message;
import co.edu.javeriana.ayllu.data.Ayllu_UserLoginData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The state of a SMA
 * @author AYLLU
 */
public class SessionManagerAgentState extends StateBESA {

    private Map<String, AgHandlerBESA> messageSender;
    private List<AgHandlerBESA> interfaceAgentsID;
    private List<AgHandlerBESA> communityAgents;
    private double sessionManagerPassword;
    private AgHandlerBESA representativeAgentHandler;
    private Ayllu_UserLoginData loginData;

    public SessionManagerAgentState(AgHandlerBESA representativeAgentHandler) {
        this.interfaceAgentsID = new ArrayList<AgHandlerBESA>();
        this.representativeAgentHandler = representativeAgentHandler;
        this.messageSender = new HashMap<String, AgHandlerBESA>();
        this.communityAgents = new ArrayList<AgHandlerBESA>();
        //this.theUserProfile = new UserProfile(representativeAgentHandler.getAlias().split("_")[1]);
    }
    
    public void addSender(String messageId, AgHandlerBESA sender) {
        messageSender.put(messageId, sender);
    }

    public AgHandlerBESA getSenderOfMessage(String messageId) {
        return messageSender.get(messageId);
    }

    public List<AgHandlerBESA> getInterfaceAgentsID() {
        return interfaceAgentsID;
    }

    public void setInterfaceAgentsID(List<AgHandlerBESA> interfaceAgentsID) {
        this.interfaceAgentsID = interfaceAgentsID;
    }

    public AgHandlerBESA getRepresentativeAgentHandler() {
        return representativeAgentHandler;
    }

    public void setRepresentativeAgentHandler(AgHandlerBESA representativeAgentHandler) {
        this.representativeAgentHandler = representativeAgentHandler;
    }

    public double getSessionManagerPassword() {
        return sessionManagerPassword;
    }

    public void setSessionManagerPassword(double sessionManagerPassword) {
        this.sessionManagerPassword = sessionManagerPassword;
    }
    public void addInterfaceAgentHandler(AgHandlerBESA iaHandler){
        this.interfaceAgentsID.add(iaHandler);
    }

    /*TODO El tipo de evento MESSAGE es el mismo, lo que diferencia cada tipo
    es la guarda a la que se le manda*/
    public Ayllu_Data_Message handleCAMessage(Ayllu_Data_Message theData){
        //TODO _SMA hacer el manejo bien
        return theData;
    }

    public Ayllu_Data_Message handleIAMessage(Ayllu_Data_Message theData){
        //TODO _SMA hacer el manejo bien
        return theData;
    }
//    public List<String> getUsersInCourse(String courseId){
//        return this.theUserProfile.getCourse(courseId).getStudentsInClassUsernames();
//    }
//    void addMessageToWall(Ayllu_WallMessageData theData) {
//        String idCoopServ = theData.getCooperativeServiceId();
////        List<Course> thecourses = this.theUserProfile.getCourses();
//        for (Course course : thecourses) {
//            if(course.hasCooperativeService(idCoopServ)){
//                MessageInWall miw = new MessageInWall(
//                    theData.getCooperativeServiceId(),
//                    theData.getCooperativeServiceName(),
//                    theData.getDateMilliseconds(),
//                    theData.getSenderName(),
//                    theData.getMessage());
//                course.addMessageToWall(miw);
//                //reportToIa(this,theData);
//            }
//        }
        
                
//    }
//    void updateNumberOfStudents (String coopservid,int studentnumber){
//        List<Course> courseList = this.theUserProfile.getCourses();
//        for (Course course : courseList) {
//            if(course.hasCooperativeService(coopservid)){
//                course.getCooperativeService(coopservid).setNumberOfParticipantsThatHaveResponded(studentnumber);
//            }
//        }
//    }

    public List<AgHandlerBESA> getCommunityAgents() {
        return communityAgents;
    }
    public void addCommunityAgent(AgHandlerBESA caHandler){
        this.communityAgents.add(caHandler);
    }
//
//    void registerCooperativeService(String courseId, CooperativeServiceAwareness cooperativeService) {
//        //if(!isInState(courseId,cooperativeService.getId())){
//            this.theUserProfile.addCooperativeService(courseId, cooperativeService);
//        //}
//    }

    void setLoginData(Ayllu_UserLoginData loginData) {
        this.loginData = loginData;
    }

    public Ayllu_UserLoginData getLoginData() {
        return loginData;
    }

//    public UserProfile getTheUserProfile() {
//        return theUserProfile;
//    }
//
//    public void setTheUserProfile(UserProfile theUserProfile) {
//        this.theUserProfile = theUserProfile;
//    }
//    void addAwarenessMessage(String cooperativeServiceId, InformationRequestForm reqForm) {
//        List<Course> courseList = this.theUserProfile.getCourses();
//        for (Course course : courseList) {
//            if(course.hasCooperativeService(cooperativeServiceId)){
//                course.getCooperativeService(cooperativeServiceId).setMessage(reqForm);
//            }
//        }
////    }
//    boolean isInState (String courseId, String cooperativeServiceId){
//        Course theCourse = this.theUserProfile.getCourse(courseId);
//        if(theCourse==null){
//            return false;
//        }else if(theCourse.hasCooperativeService(cooperativeServiceId)){
//            return true;
//        }
//        return false;
//    }
    
    
}