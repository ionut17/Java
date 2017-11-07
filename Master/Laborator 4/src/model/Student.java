package model;

import javax.faces.bean.ManagedBean;
import java.util.List;
@ManagedBean(name="student", eager = true)
public class Student extends Item {

    private List<Project> projects;

    public Student(){}

    public Student(String name, List<String> skills){
        super(name, skills);
    }

    public Student(Integer id, String name, List<String> skills){
        super(id, name, skills);
    }

    public Student(String name, List<String> skills, List<Project> projects){
        super(name, skills);
        this.projects = projects;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
