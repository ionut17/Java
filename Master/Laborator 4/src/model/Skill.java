package model;

public class Skill {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Skill(String name){
        setName(name);
    }

}
