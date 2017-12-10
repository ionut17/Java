package model;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.util.List;
@ManagedBean(name="student", eager = true)
@Entity
@Table(name="students")
public class Student extends Item {

    @Id
    protected Integer id;

    @Column(name="name", nullable=false)
    protected String name;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "student_preferences",
            joinColumns = { @JoinColumn(name = "student") },
            inverseJoinColumns = { @JoinColumn(name = "project") }
    )
    private List<Project> projects;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "student_skills",
            joinColumns = { @JoinColumn(name = "student") },
            inverseJoinColumns = { @JoinColumn(name = "skill") }
    )
    List<Skill> skills;

    public Student(){}

    public Student(String name, List<Skill> skills){
        this.name = name;
        this.skills = skills;
    }

    public Student(Integer id, String name, List<Skill> skills){
        this.name = name;
        this.id = id;
        this.skills = skills;
    }

    public Student(String name, List<Skill> skills, List<Project> projects){
        this.name = name;
        this.skills = skills;
        this.projects = projects;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
