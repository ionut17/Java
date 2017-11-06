package view;

import model.Skill;
import model.Student;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="studentView")
@ViewScoped
public class StudentView extends ItemView<Student> {

    @PostConstruct
    public void init() {
        List<Skill> skills =  new ArrayList<>();
        skills.add(new Skill("Programming"));
        skills.add(new Skill("Design"));
        this.items.add(new Student("Ionut", skills));
    }

}

