package view;

import filters.Filter;
import model.Project;
import model.Student;
import service.ProjectService;
import service.StudentService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean(name="solutionView")
@ViewScoped
public class SolutionView {


    @ManagedProperty("#{studentService}")
    protected StudentService studentService;

    private List<Project> filteredProjects;

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @ManagedProperty("#{projectService}")
    protected ProjectService projectService;

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

    public void find(Filter filters){
        this.setFilteredProjects(this.projectService.find(filters));
    }

    public List<Project> getFilteredProjects() {
        return filteredProjects;
    }

    public void setFilteredProjects(List<Project> filteredProjects) {
        this.filteredProjects = filteredProjects;
    }
}

