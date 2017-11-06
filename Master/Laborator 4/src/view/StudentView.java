package view;

import model.Student;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="studentView")
@ViewScoped
public class StudentView extends ItemView<Student> {

}

