package service;

import model.Project;
import model.Student;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "projectService", eager = true)
@ApplicationScoped
public class ProjectService extends ItemService<Project> {

    public List<Project> getItems() {
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = databaseService.getConnection();
        String stm = "Select * from projects";
        List<Project> projects = new ArrayList<>();
        try {
            pst = con.prepareStatement(stm);
            pst.execute();
            rs = pst.getResultSet();
            while(rs.next()) {
                projects.add(new Project(rs.getString(2), getSkills(rs.getInt(1), "project_skills")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }

    public Project getById(Integer id) {
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = databaseService.getConnection();
        String stm = "Select * from projects where id="+id.toString();
        Project project = null;
        try {
            pst = con.prepareStatement(stm);
            pst.execute();
            rs = pst.getResultSet();
            while(rs.next()) {
                project = new Project(rs.getInt(1), rs.getString(2), new ArrayList<>());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return project;
    }

    public void add(Project item){
        PreparedStatement pst = null;
        Connection con = databaseService.getConnection();
        String stm = "INSERT INTO projects (id, name) VALUES ("+item.getId()+",'"+item.getName()+"')";
        try {
            pst = con.prepareStatement(stm);
            pst.execute();
            for (String skill: item.getSkills()){
                stm = "INSERT INTO project_skills (project, skill) VALUES ("+item.getId()+","+skillService.getId(skill)+")";
                pst = con.prepareStatement(stm);
                pst.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

