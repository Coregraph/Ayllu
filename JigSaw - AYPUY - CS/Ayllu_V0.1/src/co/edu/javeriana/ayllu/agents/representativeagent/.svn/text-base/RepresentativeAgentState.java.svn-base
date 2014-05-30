/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.javeriana.ayllu.agents.representativeagent;

import BESA.Kernel.Agent.StateBESA;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author AYLLU
 */
public class RepresentativeAgentState extends StateBESA{
    private Map<String,Object> raAnswers;

    public RepresentativeAgentState() {
        raAnswers = new HashMap<String, Object>();
    }

    public Map<String, Object> getRaAnswers() {
        return raAnswers;
    }
    
    public void addAnswer(String key, Object answer){
        this.raAnswers.put(key, answer);
    }
    
    public Object getAnswer(String key){
        return this.getRaAnswers().get(key);
    }
}
