package service;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "skillService")
@ApplicationScoped
public class SkillService {

    private List<String> skills;

    public SkillService(){
        skills = new ArrayList<>();
        skills.add("Programming");
        skills.add("Design");
        skills.add("Architecture");
        skills.add("Problem Solving");
    }

    public List<String> getSkills() {
        int x =5;
        return this.skills;
    }
}
