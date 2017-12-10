package model;

import javafx.util.Pair;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Entity
@Table(name="projects")
public class Project extends Item {

    @Id
    protected Integer id;

    @Column(name="name", nullable=false)
    protected String name;

    @ManyToMany(mappedBy="projects")
    private List<Student> students = new ArrayList<>();

    @ManyToMany(cascade= CascadeType.ALL)
    @JoinTable(
            name = "project_skills",
            joinColumns = { @JoinColumn(name = "project") },
            inverseJoinColumns = { @JoinColumn(name = "skill") }
    )
    private List<Skill> skills;

    public Project(){}

    public Project(String name, List<Skill> skills){
        this.name = name;
        this.skills = skills;
    }

    public Project(Integer id, String name, List<Skill> skills){
        this.name = name;
        this.id = id;
        this.skills = skills;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
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
