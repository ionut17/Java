package view;

import model.Project;
import model.Student;
import model.StudentDto;
import service.ProjectService;
import service.SolutionService;
import service.StudentService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean(name="solutionView")
@ViewScoped
public class SolutionView extends ItemView<Student> {


    @ManagedProperty("#{studentService}")
    protected StudentService studentService;

    public StudentService getStudentService() {
        return studentService;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @ManagedProperty("#{projectService}")
    protected ProjectService projectService;

    private SolutionService solutioner;

    public Map<Student, Project> solution;

    @PostConstruct
    public void init(){
        solutioner = new SolutionService(projectService.getItems(), studentService.getItems());
        this.solution = solutioner.solve();
    }


}

