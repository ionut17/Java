package lab5.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Ionut
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        MainFrame spotify = new MainFrame(1000,800);
    }
    
}

/*
    iconite la melodii
    command parser bottom
    attach handler to exit button
    favorites things
    organize code to mvc
    make xml/contextual menu
*/


/*
    http://geosoft.no/software/filesystem/FileSystemModel.java.html
    http://stackoverflow.com/questions/15149565/how-does-jtree-display-file-name
    http://www.codejava.net/java-se/swing/jtree-basic-tutorial-and-examples
    https://docs.oracle.com/javase/tutorial/uiswing/components/tree.html
*/