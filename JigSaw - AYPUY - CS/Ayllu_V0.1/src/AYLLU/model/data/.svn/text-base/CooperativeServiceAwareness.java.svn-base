package AYLLU.model.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANDREA
 */
public class CooperativeServiceAwareness implements Serializable {

    private String id;
    private String baseName;
    private boolean compound;
    private String actualStageName;
    private int actualStagePosition;
    private int totalNumberOfStages;
    private int totalNumberOfParticipants;
    private int numberOfParticipantsThatHaveResponded;
    private long timeLeftMilliseconds;
    private InformationRequestForm message;
    private List<MessageInWall> wall;
    private List<OVA> ovas;

    public CooperativeServiceAwareness(String id, String baseName) {
        this.id = id;
        this.baseName = baseName;
        this.compound = false;
        this.actualStageName = "";
        this.actualStagePosition = 1;
        this.totalNumberOfStages = 1;
        this.totalNumberOfParticipants = 1;
        this.numberOfParticipantsThatHaveResponded = 0;
        this.timeLeftMilliseconds = 0;
        this.wall = new ArrayList<MessageInWall>();
        this.ovas = new ArrayList<OVA>();
    }
    //Constructor for existant simple cooperative service

    public CooperativeServiceAwareness(String id, String baseName, int totalNumberOfParticipants, int numberOfParticipatsThatHaveResponded, long timeLeftMilliseconds) {
        this.id = id;
        this.baseName = baseName;
        this.compound = false;
        this.actualStageName = "";
        this.actualStagePosition = 1;
        this.totalNumberOfStages = 1;
        this.totalNumberOfParticipants = totalNumberOfParticipants;
        this.numberOfParticipantsThatHaveResponded = numberOfParticipatsThatHaveResponded;
        this.timeLeftMilliseconds = timeLeftMilliseconds;
        this.wall = new ArrayList<MessageInWall>();
        this.ovas = new ArrayList<OVA>();
    }

    //Constructor for new simple cooperative service
    public CooperativeServiceAwareness(String id, String baseName, int totalNumberOfParticipants, long timeLeftMilliseconds) {
        this.id = id;
        this.baseName = baseName;
        this.compound = false;
        this.actualStageName = "";
        this.actualStagePosition = 1;
        this.totalNumberOfStages = 1;
        this.totalNumberOfParticipants = totalNumberOfParticipants;
        this.numberOfParticipantsThatHaveResponded = 0;
        this.timeLeftMilliseconds = timeLeftMilliseconds;
        this.wall = new ArrayList<MessageInWall>();
        this.ovas = new ArrayList<OVA>();
    }

    //Constructor for existant compound cooperative service
    public CooperativeServiceAwareness(String id, String baseName, String actualStageName, int actualStagePosition, int totalNumberOfStages, int totalNumberOfParticipants, int numberOfParticipatsThatHaveResponded, long timeLeftMilliseconds) {
        this.id = id;
        this.baseName = baseName;
        this.compound = true;
        this.actualStageName = actualStageName;
        this.actualStagePosition = actualStagePosition;
        this.totalNumberOfStages = totalNumberOfStages;
        this.totalNumberOfParticipants = totalNumberOfParticipants;
        this.numberOfParticipantsThatHaveResponded = numberOfParticipatsThatHaveResponded;
        this.timeLeftMilliseconds = timeLeftMilliseconds;
        this.wall = new ArrayList<MessageInWall>();
        this.ovas = new ArrayList<OVA>();
    }

    //Constructor for new compound cooperative service
    public CooperativeServiceAwareness(String id, String baseName, String firstStageName, int totalNumberOfStages, int totalNumberOfParticipants, long timeLeftMilliseconds) {
        this.id = id;
        this.baseName = baseName;
        this.compound = true;
        this.actualStageName = firstStageName;
        this.actualStagePosition = 1;
        this.totalNumberOfStages = totalNumberOfStages;
        this.totalNumberOfParticipants = totalNumberOfParticipants;
        this.numberOfParticipantsThatHaveResponded = 0;
        this.timeLeftMilliseconds = timeLeftMilliseconds;
        this.wall = new ArrayList<MessageInWall>();
        this.ovas = new ArrayList<OVA>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public boolean isCompound() {
        return compound;
    }

    public void setCompound(boolean compound) {
        this.compound = compound;
    }

    public String getActualStageName() {
        return actualStageName;
    }

    public void setActualStageName(String actualStageName) {
        this.actualStageName = actualStageName;
    }

    public int getActualStagePosition() {
        return actualStagePosition;
    }

    public void setActualStagePosition(int actualStagePosition) {
        this.actualStagePosition = actualStagePosition;
    }

    public int getNumberOfParticipantsThatHaveResponded() {
        return numberOfParticipantsThatHaveResponded;
    }

    public void setNumberOfParticipantsThatHaveResponded(int numberOfParticipantsThatHaveResponded) {
        this.numberOfParticipantsThatHaveResponded = numberOfParticipantsThatHaveResponded;
        if (totalNumberOfParticipants < numberOfParticipantsThatHaveResponded) {
            totalNumberOfParticipants = numberOfParticipantsThatHaveResponded;
        }

    }

    public int getTotalNumberOfParticipants() {
        return totalNumberOfParticipants;
    }

    public void setTotalNumberOfParticipants(int totalNumberOfParticipants) {
        this.totalNumberOfParticipants = totalNumberOfParticipants;
    }

    public int getTotalNumberOfStages() {
        return totalNumberOfStages;
    }

    public void setTotalNumberOfStages(int totalNumberOfStages) {
        this.totalNumberOfStages = totalNumberOfStages;
    }

    public long getTimeLeftMilliseconds() {
        return timeLeftMilliseconds;
    }

    public void setTimeLeftMilliseconds(long timeLeftMilliseconds) {
        this.timeLeftMilliseconds = timeLeftMilliseconds;
    }

    public boolean hasMessage() {
        return message != null;
    }

    public InformationRequestForm getMessage() {
        return message;
    }

    public void setMessage(InformationRequestForm message) {
        this.message = message;
        message.setId("CooperativeServiceAwarenessMessage" + "<sptA>" + id);
    }

    public void removeMessage() {
        message = null;
    }

    public String getName() {
        String name = baseName;
        if (compound) {
            name += " - " + actualStageName;
        }
        return name;
    }

    public List<MessageInWall> getWall() {
        return wall;
    }

    public void setWall(List<MessageInWall> wall) {
        this.wall = wall;
    }

    public void addMessageToWall(MessageInWall message) {
        wall.add(message);
    }

    public void advanceAStage(String newStageName, long timeLeftMilliseconds) {
        if (!compound) {
            return;
        }
        this.actualStagePosition++;
        if (totalNumberOfStages < actualStagePosition) { //Just In Case
            totalNumberOfStages = actualStagePosition;
        }
        this.actualStageName = newStageName;
        this.numberOfParticipantsThatHaveResponded = 0;
        this.timeLeftMilliseconds = timeLeftMilliseconds; //TODO ... este tiempo es por etapa o por cooperative service
    }

    public void reduceTimeLeftByAMinute() {
        this.timeLeftMilliseconds -= 1000 * 60;
        if (timeLeftMilliseconds < 0) {
            timeLeftMilliseconds = 0;
        }
    }

    public List<OVA> getOVAs() {
        return ovas;
    }

    public void setOVAs(List<OVA> ovas) {
        if (ovas == null) {
            ovas = new ArrayList<OVA>();
            ovas.clear();
        }
        this.ovas = ovas;
    }

    public void addOVA(OVA ova) {
        ovas.add(ova);
    }

    public OVA getOVA(String ovaId) {
        for (int i = 0; i < ovas.size(); i++) {
            if (ovas.get(i).getId().equals(ovaId)) {
                return ovas.get(i);
            }
        }
        return null;
    }

    public void removeOVA(String ovaId) {
        String logInfo = "ENTRO A REMOVE OVA " + ovaId;
        Logger.getLogger(CooperativeServiceAwareness.class.getName()).log(Level.INFO, logInfo);
        for (int i = 0; i < ovas.size(); i++) {
            Logger.getLogger(CooperativeServiceAwareness.class.getName()).log(Level.INFO, "ENTRO");
            if (ovas.get(i).getId().equals(ovaId)) {
                ovas.remove(i);
                Logger.getLogger(CooperativeServiceAwareness.class.getName()).log(Level.INFO, "ENTRO w");
                return;
            }
        }
    }
}
