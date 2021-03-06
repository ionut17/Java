package service;

import model.Item;
import model.ItemDto;
import model.Project;
import model.Student;

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

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public List<Student> getItems() {
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = databaseService.getConnection();
        String stm = "Select * from students";
        List<Student> students = new ArrayList<>();
        try {
            pst = con.prepareStatement(stm);
            pst.execute();
            rs = pst.getResultSet();
            while(rs.next()) {
                students.add(new Student(rs.getString(2), getSkills(rs.getInt(1), "student_skills"), getProjects(rs.getInt(1))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            for (String skill: item.getSkills()){
                stm = "INSERT INTO student_skills (student, skill) VALUES ("+item.getId()+","+skillService.getId(skill)+")";
                pst = con.prepareStatement(stm);
                pst.execute();
            }
            for (Project project: item.getProjects()){
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

}

