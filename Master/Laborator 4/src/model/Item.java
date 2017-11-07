package model;

import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name="item", eager = true)
public class Item {

    Integer id;

    String name;

    List<String> skills;

    public Item(){}

    public Item(String name, List<String> skills){
        setName(name);
        setSkills(skills);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
