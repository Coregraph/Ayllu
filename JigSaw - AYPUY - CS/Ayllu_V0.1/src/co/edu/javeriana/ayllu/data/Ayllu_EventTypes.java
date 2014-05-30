/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ayllu.data;

import java.io.Serializable;

/**
 * Diferrent event types that will idnicate an agent what to do
 * @author AYLLU
 */
public enum Ayllu_EventTypes implements Serializable{
    //-------CA_EVENTS-------
    COOP_REQUEST, COOP_REPLY, QUEST_REQUEST,
    QUEST_REPLY, START_COOP,CMA_REGISTER,
    //-------CMA_EVENTS-------
    AUTH_REPLY, STOP_TASK,CREATE_AGENT,USER_LOOKUP,START_TIMEOUT,
    //-------SM_EVENTS-------
    AUTH_REQUEST, /*QUEST_REQUEST, QUEST_REPLY,*/MESSAGE,STATE_REPORT,
    /*QUEST_REPLY*/
    //-------RA_EVENTS-------
    /*QUEST_REQUEST, QUEST_REPLY*/
    //-------IA_EVENTS
    ADMINISTER, DISPLAY,DISPLAYESPECIFIC,
    //-------FA_EVENTS-------
    CREATE_SERVICE,
    //-------AA_EVENTS-------
    /*ADMINISTER*/
    //-------SYNCH_EVENTS-------
    SEND_SYNCH_MESSAGE
    
    ;
}
