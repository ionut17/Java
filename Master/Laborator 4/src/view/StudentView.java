package view;

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
        this.items = new ArrayList<>();
        List<String> skills =  skillService.getSkills();
        Student student = new Student("Ionut", skills);
        this.items.add(student);
    }

}

