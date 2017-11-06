package model;

import java.util.List;

public class Project extends Item {
    private int quota;

    public Project(String name, List<String> skills, int quota){
        super(name, skills);
        this.quota = quota;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }
}
