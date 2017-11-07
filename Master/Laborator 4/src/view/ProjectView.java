package view;

import model.Item;
import model.Project;
import model.Student;
import service.ProjectService;
import service.StudentService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="projectView")
@ViewScoped
public class ProjectView extends ItemView<Project> {

    @ManagedProperty("#{projectService}")
    protected ProjectService projectService;

    @PostConstruct
    public void init(){
        int x = 5;
        this.items = projectService.getItems();
        int y = 4;
    }

    public void addItem(Item item){
        projectService.add(new Project(item.getId(), item.getName(), item.getSkills()));
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

}

