package view;

import model.Project;
import model.Student;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="projectView")
@ViewScoped
public class ProjectView extends ItemView<Project> {

}

