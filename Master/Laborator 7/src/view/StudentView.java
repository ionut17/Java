package view;

import model.*;
import service.ItemService;
import service.StudentService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="studentView")
@ViewScoped
public class StudentView extends ItemView<Student> {

    public List<Student> getItemsIncomplete() {
        return itemsIncomplete;
    }

    protected List<Student> itemsIncomplete;

    @ManagedProperty("#{studentService}")
    protected StudentService studentService;

    @PostConstruct
    public void init(){
        int x = 5;
        this.items = studentService.getItems();
        this.itemsIncomplete = studentService.getItemsIncomplete();
        int y = 4;
    }

    public void addItem(Student student){
        Student student2 = new Student();
//        student.setId(item.getId());
//        student.setName(item.getName());
//        List<Project> proj = new ArrayList<>();
//        for (Project project : item.getProjects()){
//            proj.add(new Project(Integer.valueOf(project), "ge", new ArrayList<>()));
//        }
//        student.setProjects(item);
        studentService.add(student);
    }

    public void setStudentService(StudentService itemService) {
        this.studentService = itemService;
    }

}

