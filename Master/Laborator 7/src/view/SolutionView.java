package view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="solutionView")
@ViewScoped
public class SolutionView {

    public void generate(){
        System.out.println("Generating...");
    }

}

