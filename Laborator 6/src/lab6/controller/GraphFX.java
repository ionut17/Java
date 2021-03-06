package lab6.controller;

import de.codecentric.centerdevice.javafxsvg.SvgImageLoaderFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Ionut
 */
public class GraphFX extends Application {

    final static int WIDTH = 1000;
    final static int MENU_HEIGHT = 100;
    final static int CANVAS_HEIGHT = 700;
    
    Function myFunction = new Function();

    @Override
    public void start(Stage primaryStage) {
        //JavaFX SVG init
        SvgImageLoaderFactory.install();
        //Main code
        CanvasPane canvas = new CanvasPane(WIDTH, CANVAS_HEIGHT, myFunction);
        MenuPane menu = new MenuPane(WIDTH, MENU_HEIGHT, myFunction, canvas, primaryStage);
        
        menu.setPrefSize(1000,100);
        
        BorderPane root = new BorderPane();
        root.setTop(menu);
        root.setCenter(canvas);


        Scene scene = new Scene(root, WIDTH, MENU_HEIGHT + CANVAS_HEIGHT);
        scene.getStylesheets().add("styles.css");

        primaryStage.setTitle("GraphFX");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

//TODO: afisare functie, afisare pozitie (x,y)
//OPT: slider pentru scale


//Add back functionality (removes last line)
//Resizable in any way including the menu
//Desenare in functie de numarul de puncte ales si totul e scalabil
//Importare svg