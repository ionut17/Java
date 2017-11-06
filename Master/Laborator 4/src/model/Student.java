package model;

import java.util.List;

public class Student extends Item {

    private List<Project> projects;

    public Student(String name, List<String> skills){
        super(name, skills);
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
