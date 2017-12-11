package view;

import model.Student;
import service.StudentService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="solutionView")
@ViewScoped
public class SolutionView {


    @ManagedProperty("#{studentService}")
    protected StudentService studentService;

    public void generate(){
        System.out.println("Generating...");
        Student student = new Student();
        student.setId(11);
        student.setName("Ionut");
        studentService.add(student);
    }

    public void setStudentService(StudentService itemService) {
        this.studentService = itemService;
    }


}

