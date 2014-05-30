package AYLLU.model.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ANDREA
 */
public class Course implements Serializable {

    private String id;
    private String name;
    private List<CooperativeServiceAwareness> cooperativeServices;
    private List<String> studentsInClassUsernames;

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
        cooperativeServices = Collections.synchronizedList(new ArrayList<CooperativeServiceAwareness>());
    }

    public Course(String id, String name, List<CooperativeServiceAwareness> cooperativeServices) {
        this.id = id;
        this.name = name;
        this.cooperativeServices = Collections.synchronizedList(cooperativeServices);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public List<CooperativeServiceAwareness> getCooperativeServices() {
        return cooperativeServices;
    }

    public void setCooperativeServices(List<CooperativeServiceAwareness> cooperativeServices) {
        this.cooperativeServices = Collections.synchronizedList(cooperativeServices);
    }

    public boolean hasCooperativeService(String idCooperativeService) {
        for (int i = 0; i < cooperativeServices.size(); i++) {
            if (cooperativeServices.get(i).getId().equals(idCooperativeService)) {
                return true;
            }
        }
        return false;
    }

    public CooperativeServiceAwareness getCooperativeService(String idCooperativeService) {
        for (int i = 0; i < cooperativeServices.size(); i++) {
            if (cooperativeServices.get(i).getId().equals(idCooperativeService)) {
                return cooperativeServices.get(i);
            }
        }
        return null;
    }
    
    public void removeCooperativeService(String idCooperativeService) {
        for (int i = 0; i < cooperativeServices.size(); i++) {
            if (cooperativeServices.get(i).getId().equals(idCooperativeService)) {
                cooperativeServices.remove(i);
                break;
            }
        }
    }

    public void addCooperativeService(CooperativeServiceAwareness cooperativeService) {
        cooperativeServices.add(cooperativeService);
    }

    public List<MessageInWall> getWall() {
        List<MessageInWall> wall = new ArrayList<MessageInWall>();
        //Unify all messages from cooperative services individual walls
        for (int i = 0; i < cooperativeServices.size(); i++) {
            wall.addAll(cooperativeServices.get(i).getWall());
        }
        //Sort by date
        for (int i = 0; i < wall.size(); i++) {
            for (int j = 0; j < wall.size(); j++) {
                if (wall.get(i).getDateMilliseconds() > wall.get(j).getDateMilliseconds()) {
                    MessageInWall aux = wall.get(i);
                    wall.set(i, wall.get(j));
                    wall.set(j, aux);
                }
            }
        }
        return wall;
    }

    public void addMessageToWall(MessageInWall message) {
        for (int i = 0; i < cooperativeServices.size(); i++) {
            if (cooperativeServices.get(i).getId().equals(message.getCooperativeServiceId())) {
                cooperativeServices.get(i).addMessageToWall(message);
                break;
            }
        }
    }

    public List<String> getStudentsInClassUsernames() {
        return studentsInClassUsernames;
    }

    public void setStudentsInClassUsernames(List<String> studentsInClassUsernames) {
        this.studentsInClassUsernames = studentsInClassUsernames;
    }
    

}
