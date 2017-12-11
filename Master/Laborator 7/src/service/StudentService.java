package service;

import model.*;
import repository.StudentRepository;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "studentService")
@ApplicationScoped
public class StudentService extends ItemService<Student> {

    public ProjectService getProjectService() {
        return projectService;
    }

    @ManagedProperty("#{projectService}")
    protected ProjectService projectService;

    @ManagedProperty("#{studentRepository}")
    private StudentRepository studentRepository;

    public List<Student> getItems() {
        List<Student> students = this.studentRepository.getAll();
        if (students == null) {
            System.out.println("No items found.");
        } else {
            for (Student item : students) {
                System.out.println("Item name= " + item.getName());
            }
        }
        return students;
    }

    public List<Student> getItemsIncomplete() {
        List<Student> students = this.studentRepository.getAllIncomplete();
        if (students == null) {
            System.out.println("No items found.");
        } else {
            for (Student item : students) {
                System.out.println("Item name= " + item.getName());
            }
        }
        return students;
    }

    public void add(Student item){
        PreparedStatement pst = null;
        Connection con = databaseService.getConnection();
        String stm = "INSERT INTO students (id, name) VALUES ("+item.getId()+",'"+item.getName()+"')";
        try {
            pst = con.prepareStatement(stm);
            pst.execute();
            for (Skill skill: item.getSkills()){
                stm = "INSERT INTO student_skills (student, skill) VALUES ("+item.getId()+","+skillService.getId(skill)+")";
                pst = con.prepareStatement(stm);
                pst.execute();
            }
            for (Project project: item .getProjects()){
                stm = "INSERT INTO student_preferences (student, project) VALUES ("+item.getId()+","+project.getId()+")";
                pst = con.prepareStatement(stm);
                pst.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private List<Project> getProjects(Integer id){
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = databaseService.getConnection();
        String stm = "Select * from student_preferences where student="+id;

        List<Project> records = new ArrayList<>();
        try {
            pst = con.prepareStatement(stm);
            pst.execute();
            rs = pst.getResultSet();
            while(rs.next()) {
                records.add(projectService.getById(rs.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

}

