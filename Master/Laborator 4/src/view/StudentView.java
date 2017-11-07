package view;

import model.*;
import service.ItemService;
import service.StudentService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="studentView")
@ViewScoped
public class StudentView extends ItemView<Student> {


    @ManagedProperty("#{studentService}")
    protected StudentService studentService;

    @PostConstruct
    public void init(){
        int x = 5;
        this.items = studentService.getItems();
        int y = 4;
    }

    public void addItem(StudentDto item){
        Student student = new Student();
        student.setId(item.getId());
        student.setName(item.getName());
        List<Project> proj = new ArrayList<>();
        for (String project : item.getProjects()){
            proj.add(new Project(Integer.valueOf(project), "ge", new ArrayList<String>()));
        }
        student.setProjects(proj);
        studentService.add(student);
    }

    public void setStudentService(StudentService itemService) {
        this.studentService = itemService;
    }

}

