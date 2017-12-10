package model;

import javax.faces.bean.ManagedBean;
import java.util.List;

public class StudentDto extends Item {

    private Integer id;

    protected String name;

    private List<String> projects;

    private List<Skill> skills;

    public StudentDto(){}

    public StudentDto(String name, List<Skill> skills){
        this.name = name;
        this.skills = skills;
    }

    public StudentDto(Integer id, String name, List<Skill> skills){
        this.name = name;
        this.id = id;
        this.skills = skills;
    }

    public StudentDto(String name, List<Skill> skills, List<String> projects){
        this.name = name;
        this.skills = skills;
        this.projects = projects;
    }

    public List<String> getProjects() {
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
