/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import BESA.Kernel.Social.ServiceProvider.agent.SPServiceDataRequest;
import guardasagentesesion.InterfazGuardReply;

/**
 *
 * @author usuario
 */
public class MensajeRequest extends SPServiceDataRequest{
    private String query;
    public enum EnumReq{
        ERROR,MENSAJE;
    }
    private EnumReq miEnum;
    
    public MensajeRequest(String query,EnumReq en) {
        super(InterfazGuardReply.class.getName(), MensajeReply.class.getName());
        this.query = query;
        this.miEnum = en;
        
    }

    public String getQuery() {
        return query;
    }

    public EnumReq getMiEnum() {
        return miEnum;
    }
    
}
