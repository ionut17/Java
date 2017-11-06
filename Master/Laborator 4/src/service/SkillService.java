package service;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "skillService")
@ApplicationScoped
public class SkillService {

    private List<Skill> skills;

    public SkillService(){
        skills = new ArrayList<>();
        skills.add(new Skill("Programming"));
        skills.add(new Skill("Design"));
        skills.add(new Skill("Architecture"));
        skills.add(new Skill("Problem Solving"));
    }

    public List<Skill> getSkills() {
        return this.skills;
    }
}
