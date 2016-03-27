package lab6.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Ionut
 */
public class GraphFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        MenuPane menu = new MenuPane();
        CanvasPane canvas = new CanvasPane();
        
        BorderPane root = new BorderPane();
        root.setTop(menu);
        root.setCenter(canvas);
        
        Scene scene = new Scene(root, 1000, 600);
        
        primaryStage.setTitle("GraphFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
