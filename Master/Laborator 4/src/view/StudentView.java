package view;

import model.ItemDto;
import model.Skill;
import model.Student;
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

    public void addItem(Student item){
        studentService.add(item);
    }

    public void setStudentService(StudentService itemService) {
        this.studentService = itemService;
    }

}

