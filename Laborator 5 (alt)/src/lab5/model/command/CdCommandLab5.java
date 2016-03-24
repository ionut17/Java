package lab5.model.command;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import lab4.view.exception.InvalidCommandException;
import lab5.controller.MyTree;
/**
 *
 * @author Anca
 */
public class CdCommandLab5 extends AbstractCommandLab5 {

    public CdCommandLab5() {
    }
    
    @Override
    public void execute() throws InvalidPathException, NullPointerException {
        if (args.length == 0) {
            throw new InvalidCommandException("cd: not enough arguments");
        }
        //Go to specified folder
            getMyTree().getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                System.out.println("**"+e.getPath().toString());
                String[] splitedStrings = e.getPath().toString().split(", ");
                String pathString = splitedStrings[splitedStrings.length - 1];
                Path path = Paths.get(pathString.substring(0, pathString.length() - 1));
                getMyTree().target.setLocation(path.toFile());
            }
        });
        
    }
    
    //parse args _ absolute path or not  

}

