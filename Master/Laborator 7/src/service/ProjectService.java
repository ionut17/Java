package service;

import filters.Filter;
import model.Project;
import model.Skill;
import model.Student;
import repository.ProjectRepository;
import repository.StudentRepository;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "projectService", eager = true)
@ApplicationScoped
public class ProjectService extends ItemService<Project> {

    public ProjectRepository getProjectRepository() {
        return projectRepository;
    }

    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @ManagedProperty("#{projectRepository}")
    private ProjectRepository projectRepository;

    public List<Project> getItems() {
        List<Project> projects = this.projectRepository.getAll();
        if (projects == null) {
            System.out.println("No items found.");
        } else {
            for (Project item : projects) {
                System.out.println("Item name= " + item.getName());
            }
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
            for (Skill skill: item.getSkills()){
                stm = "INSERT INTO project_skills (project, skill) VALUES ("+item.getId()+","+skillService.getId(skill)+")";
                pst = con.prepareStatement(stm);
                pst.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Project> find(Filter filters){
        return this.projectRepository.find(filters);
    }

}

