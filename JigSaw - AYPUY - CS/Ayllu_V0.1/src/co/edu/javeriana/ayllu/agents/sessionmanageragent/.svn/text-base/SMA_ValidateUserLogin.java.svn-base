/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.agents.sessionmanageragent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import co.edu.javeriana.ayllu.data.Ayllu_UserLoginData;
import co.edu.javeriana.ayllu.data.ResponseData;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ayllu
 */
public class SMA_ValidateUserLogin extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA event) {
        SessionManagerAgentState myState = (SessionManagerAgentState) this.getAgent().getState();
        if (event.getData() instanceof Ayllu_UserLoginData) {

            Ayllu_UserLoginData loginData = (Ayllu_UserLoginData) event.getData();
            myState.setLoginData(loginData);
            
            ResponseData response = new ResponseData(loginData, "OK"); //RESPUESTA AUTOMÁTICA

        //} else if (event.getData() instanceof ResponseData) {  //RESPUESTA separada, para las pruebas se deja directo
            ResponseData resp = (ResponseData)response;// event.getData();
            Ayllu_UserLoginData usuario = (Ayllu_UserLoginData) resp.getResult();
            if (usuario.getUsername().equalsIgnoreCase(myState.getLoginData().getUsername()) && usuario.getPassword().equals(myState.getLoginData().getPassword())) {
//                UserProfile userprof = myState.getTheUserProfile();
                //XXX_ Para pruebas
                if (usuario.getUsername().contains("Francisco")) {
//                    userprof.setRole(UserProfile.RoleType.TEACHER);
                }
                //XXX_ Para pruebas
                EventBESA loginResponse = new EventBESA(myState.getLoginData().getGuardToSendResp(), new DataBESA() {});
                try {
                    AgHandlerBESA hand = this.getAgent().getAdmLocal().getHandlerByAid(myState.getLoginData().getAgToSendResp());
                    hand.sendEvent(loginResponse);
                } catch (ExceptionBESA ex) {
                    Logger.getLogger(SMA_ValidateUserLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                //TODO mandar error
            }
        }
    }
}

