/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guardasagentesesion;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import mensajesagentesesion.MensajeAsincrono;

/**
 *
 * @author usuario
 */
public class GuardCommMundoAgentes extends GuardBESA{

    @Override
    public void funcExecGuard(EventBESA event) {
        System.out.println("GUARDA DEL MUNDO DE AGENTES!!!");
        MensajeAsincrono msjAsynch = (MensajeAsincrono)event.getData();
        System.out.println("ME ENVIARON: "+msjAsynch.getMensaje());
        //ya estoy en el mundo de agentes y puedo solicitar datos a otros agentes
    }
    
}
