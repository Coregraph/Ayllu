package AYLLU.model.mediators;

import AYLLU.model.data.CooperativeServiceAwareness;
import AYLLU.model.data.Course;
import AYLLU.model.data.InformationRequestForm;
import AYLLU.model.data.MessageInWall;
import AYLLU.model.data.OVA;
import AYLLU.model.data.UserProfile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author ANDREA
 */
public class DataCreator {

    private static List<String> studentNames = new ArrayList<String>(
            Arrays.asList("STUDENT_1", "STUDENT_2", "STUDENT_3", "STUDENT_4", "STUDENT_5", "STUDENT_6", "STUDENT_7"));
    private static final long daysMilli = (1000 * 60 * 60 * 24);
    private static final long hoursMilli = (1000 * 60 * 60);
    private static final long minutesMilli = (1000 * 60);
    private static final String[] cooperativeServiceNamesCreate = new String[]{"CoopNameH", "CoopNameI", "CoopNameJ", "CoopNameK", "CoopNameL", "CoopNameM", "CoopNameN"};
    private static final String[] cooperativeServiceNames = new String[]{"CoopNameA", "CoopNameB", "CoopNameC", "CoopNameD", "CoopNameE", "CoopNameF", "CoopNameG"};
    private static final String[] courseName = new String[]{"Class A", "Class B", "Class C", "Class D", "Class E", "Class F", "Class G"};
    private static final String[] nameStages = new String[]{"Stage 1", "Stage 2", "Stage 4", "Stage 2", "Stage 5", "Stage 7", "Stage 1"};
    private static final String[] tittles = new String[]{"Form 1", "Form 2", "Form 3", "Form 4", "Form 5", "Form 6", "Form 7"};
    private static final String[] questions = new String[]{"Question 1", "Question 2", "Question 3", "Question 4", "Question 5", "Question 6", "Question 7"};
    private static final String[] answers = new String[]{"Answer 1", "Answer 2", "Answer 3", "Answer 4", "Answer 5", "Answer 6", "Answer 7"};
    private static final String[] ova_names = new String[]{"OVA 1", "OVA 2", "OVA 3", "OVA 4", "OVA 5", "OVA 6", "OVA 7"};
    private static final int[] stagePosition = new int[]{1, 2, 4, 2, 5, 7, 1};
    private static final int[] totalNumberOfStages = new int[]{10, 5, 4, 8, 6, 8, 3};
    private static final int[] totalNumberParticipants = new int[]{5, 3, 4, 3, 5, 8, 3};
    private static final int[] totalParticipantsResponded = new int[]{2, 3, 1, 1, 2, 3, 1};
    private static final long[] timeLeft = new long[]{
        30 * daysMilli + 23 * hoursMilli + 12 * minutesMilli,
        40 * daysMilli + 20 * hoursMilli + 15 * minutesMilli,
        10 * daysMilli + 14 * hoursMilli + 57 * minutesMilli,
        5 * daysMilli + 17 * hoursMilli + 29 * minutesMilli,
        9 * daysMilli + 8 * hoursMilli + 50 * minutesMilli,
        4 * daysMilli + 0 * hoursMilli + 0 * minutesMilli,
        0 * daysMilli + 20 * hoursMilli + 10 * minutesMilli};
    private static final long[] date = new long[]{290348509, 33333333, 1000000000, 1111111, 22222222, 3000000, 98237498};
    private static final String[] message = new String[]{"Message A", "Message B", "Message C", "Message D", "Message E", "Message F", "Message G"};
    private static final String[] senderName = new String[]{"sender1", "sender2", "sender3", "sender4", "sender5", "sender6", "sender7"};
    private static final Random randomGenerator = new Random();

    public static List<CooperativeServiceAwareness> getCooperativeServiceAwareness() {
        List<CooperativeServiceAwareness> coopsAwareness = new ArrayList<CooperativeServiceAwareness>();

        //Does it contain cooperative service awareness
//        boolean contains = (randomGenerator).nextBoolean();
//        if (!contains) {
//            return coopsAwareness;
//        }

        int size = (int) (Math.ceil(Math.random() * 6) + 1);
        int j = -1;
        List<Integer> previous = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            do {
                previous.add(j);
                j = (int) (Math.ceil(Math.random() * 7) - 1);
            } while (previous.contains(j));
            boolean isCompound = ((int) (Math.ceil(Math.random() * 7) - 1)) < 3 ? true : false;
            boolean hasMessage = ((int) (Math.ceil(Math.random() * 7) - 1)) < 3 ? true : false;
            if (isCompound) {
                coopsAwareness.add(new CooperativeServiceAwareness(
                        cooperativeServiceNames[j] + "_ID" + Math.random(),
                        cooperativeServiceNames[j],
                        nameStages[j],
                        stagePosition[j],
                        totalNumberOfStages[j],
                        totalNumberParticipants[j],
                        totalParticipantsResponded[j],
                        timeLeft[j]));
            } else {
                coopsAwareness.add(new CooperativeServiceAwareness(
                        cooperativeServiceNames[j] + "_ID" + Math.random(),
                        cooperativeServiceNames[j],
                        totalNumberParticipants[j],
                        totalParticipantsResponded[j],
                        timeLeft[j]));
            }

            if (hasMessage) {
                coopsAwareness.get(i).setMessage(getInformationRequestFormAwareness());
            }

            if (!hasMessage) {
                coopsAwareness.get(i).setOVAs(getOVAs(coopsAwareness.get(i).getId()));
            }

            coopsAwareness.get(i).setWall(getWall(coopsAwareness.get(i).getId(), cooperativeServiceNames[j]));

        }
        return coopsAwareness;
    }

    public static InformationRequestForm getInformationRequestFormAwareness() {
        int rand = (int) (Math.ceil(Math.random() * 7) - 1);
        InformationRequestForm info = new InformationRequestForm(tittles[rand]);
        int pos = 1;
        for (int i = 0; i < cooperativeServiceNamesCreate.length; i++) {

            rand = (int) (Math.ceil(Math.random() * 7) - 1);

            if (pos == 1) {
                info.addOpenAnswerQuestion(questions[rand]);
            } else {
                List<String> panswers = new ArrayList<String>();
                if (rand < 2) {
                    rand = 2;
                }
                for (int j = 0; j < rand; j++) {
                    int rand2 = (int) (Math.ceil(Math.random() * 7) - 1);
                    if (!panswers.contains(answers[rand2])) {
                        panswers.add(answers[rand2]);
                        j--;
                    }
                }
                if (pos == 2) {
                    info.addMultipleChoiceQuestion(questions[rand], panswers);
                } else {
                    info.addSelectOneQuestion(questions[rand], panswers);
                }
            }

            pos++;
            if (pos == 4) {
                pos = 1;
            }
        }
        return info;
    }

    public static List<MessageInWall> getWall(String cooperativeServiceId, String cooperativeServiceName) {
        List<MessageInWall> wall = new ArrayList<MessageInWall>();
        //Does it contain wall
        boolean contains = (randomGenerator).nextBoolean();
        if (!contains) {
            return wall;
        }

        int size = (int) (Math.ceil(Math.random() * 6) + 1);//Wall size
        int j = -1;
        List<Integer> previous = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            do {
                previous.add(j);
                j = (int) (Math.ceil(Math.random() * 7) - 1);
            } while (previous.contains(j));

            wall.add(new MessageInWall(cooperativeServiceId, cooperativeServiceName, date[j], senderName[j], message[j]));
        }
        return wall;
    }

    public static List<Course> getCourses() {
        //COURSES
        int size = (int) (Math.ceil(Math.random() * 6) + 1);
        List<Course> coursesList = new ArrayList<Course>();
        List<Integer> previous = new ArrayList<Integer>();
        int j = -1;
        for (int i = 0; i < size; i++) {
            do {
                previous.add(j);
                j = (int) (Math.ceil(Math.random() * 7) - 1);
            } while (previous.contains(j));
            Course course = new Course(courseName[j] + "_ID", courseName[j]);
            course.setCooperativeServices(DataCreator.getCooperativeServiceAwareness());
            List<String> names = new ArrayList<String>();
            names.addAll(studentNames);
            names.add(courseName[j]);
            course.setStudentsInClassUsernames(names);
            coursesList.add(course);
        }

        return coursesList;
    }

    public static UserProfile getLogInUserProfile(String username, String password) {
        UserProfile userProfile = null;
        if (username.equals("Student_UserName") || username.equals("Teacher_UserName")) {
            //COURSES
            List<Course> coursesList = DataCreator.getCourses();
            if (username.equals("Student_UserName") && password.equals("password")) {
                userProfile = new UserProfile("Student_Name", username, UserProfile.RoleType.STUDENT, coursesList);

            } else if (username.equals("Teacher_UserName") && password.equals("password")) {
                userProfile = new UserProfile("Teacher_Name", username, UserProfile.RoleType.TEACHER, coursesList);
            }
        }
        return userProfile;
    }

    public static CooperativeServiceAwareness getCooperativeService() {

        CooperativeServiceAwareness coop;
        boolean isCompound = ((int) (Math.ceil(Math.random() * 7) - 1)) < 3 ? true : false;
        boolean hasMessage = ((int) (Math.ceil(Math.random() * 7) - 1)) < 3 ? true : false;
        int j = (int) (Math.ceil(Math.random() * 7) - 1);
        if (isCompound) {
            coop = (new CooperativeServiceAwareness(
                    cooperativeServiceNamesCreate[j] + "_ID",
                    cooperativeServiceNamesCreate[j],
                    nameStages[j],
                    stagePosition[j],
                    totalNumberOfStages[j],
                    totalNumberParticipants[j],
                    totalParticipantsResponded[j],
                    timeLeft[j]));
        } else {
            coop = (new CooperativeServiceAwareness(
                    cooperativeServiceNamesCreate[j] + "_ID",
                    cooperativeServiceNamesCreate[j],
                    totalNumberParticipants[j],
                    totalParticipantsResponded[j],
                    timeLeft[j]));
        }

        if (hasMessage) {
            coop.setMessage(new InformationRequestForm("idMessage"));
        }

        if (!hasMessage) {
            coop.setOVAs(getOVAs(cooperativeServiceNamesCreate[j] + "_ID"));
        }

        coop.setWall(getWall(cooperativeServiceNamesCreate[j] + "_ID", cooperativeServiceNamesCreate[j]));
        return coop;
    }

    public static List<OVA> getOVAs(String cooperativeServiceID) {

        List<OVA> ovas = new ArrayList<OVA>();
        int size = (int) (Math.ceil(Math.random() * 6) + 1);//Number of OVAs
        int j = -1;
        List<Integer> previous = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            do {
                previous.add(j);
                j = (int) (Math.ceil(Math.random() * 7) - 1);
            } while (previous.contains(j));

            List<String> metadata = new ArrayList<String>(
                    Arrays.asList("METADATA_2", "METADATA_4", "METADATA_7"));


            ovas.add(new OVA(cooperativeServiceID + "_" + ova_names[j] + "_" + String.valueOf(j), ova_names[j],ova_names[j]+".jpg", metadata));



        }

        return ovas;
    }
}
