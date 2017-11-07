package model;

import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name="student", eager = true)
public class StudentDto extends Item {

    private List<String> projects;

    public StudentDto(){}

    public StudentDto(String name, List<String> skills){
        super(name, skills);
    }

    public StudentDto(Integer id, String name, List<String> skills){
        super(id, name, skills);
    }

    public StudentDto(String name, List<String> skills, List<String> projects){
        super(name, skills);
        this.projects = projects;
    }

    public List<String> getProjects() {
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }
}
