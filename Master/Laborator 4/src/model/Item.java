package model;

import java.util.List;

public abstract class Item {

    String name;

    List<Skill> skills;

    Item(String name, List<Skill> skills){
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
