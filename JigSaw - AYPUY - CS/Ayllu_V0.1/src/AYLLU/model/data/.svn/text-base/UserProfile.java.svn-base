package AYLLU.model.data;

import BESA.Kernel.Agent.Event.DataBESA;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ANDREA
 */
public final class UserProfile extends DataBESA implements Serializable {

    private String name;
    private String username;
    private RoleType role;
    private List<Course> courses;

    public UserProfile(String username) {
        this.username = username;
        courses = new ArrayList<Course>();

    }

   public UserProfile(String username, RoleType role) {
        courses = Collections.synchronizedList(new ArrayList<Course>());
        setUserInformation(username, username, role, courses);
    }

    public UserProfile(String name, String username, RoleType role) {
        setUserInformation(name, username, role, new ArrayList<Course>());
    }

    public UserProfile(String name, String username, RoleType role, List<Course> courses) {
        if (courses == null) {
            courses = new ArrayList<Course>();
        }
        setUserInformation(name, username, role, courses);
    }

    private void setUserInformation(String name, String username, RoleType role, List<Course> courses) {
        this.name = name;
        this.username = username;
        this.role = role;
        this.courses = Collections.synchronizedList(courses);
    }
    
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public List<String> getCourseNames() {
        List<String> names = new ArrayList<String>();
        for (int i = 0; i < courses.size(); i++) {
            names.add(courses.get(i).getName());
        }
        return names;
    }

    public void setCourses(List<Course> courses) {
        this.courses = Collections.synchronizedList(courses);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Course getCourse(String courseId) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(courseId)) {
                return courses.get(i);
            }
        }
        return null;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(String courseId) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(courseId)) {
                courses.remove(i);
            }
        }
    }

    public void addCooperativeService(String courseId, CooperativeServiceAwareness cooperativeService) {
        getCourse(courseId).addCooperativeService(cooperativeService);
    }

    //returns courseId from which the cooperative service was removed
    public void removeCooperativeService(String idCooperativeService) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).hasCooperativeService(idCooperativeService)) {
                courses.get(i).removeCooperativeService(idCooperativeService);
                return;
            }
        }
    }

    public static enum RoleType {

        STUDENT, TEACHER
    };
}
