package model;

import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name="item", eager = true)
public class Item {

    String name;

    List<Skill> skills;

    public Item(){}

    public Item(String name, List<Skill> skills){
        setName(name);
        setSkills(skills);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

}
