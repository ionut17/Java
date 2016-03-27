package lab6.controller;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Ionut
 */
public class GraphFX extends Application {

    final static int WIDTH = 1000;
    final static int MENU_HEIGHT = 100;
    final static int CANVAS_HEIGHT = 700;

    @Override
    public void start(Stage primaryStage) {
        MenuPane menu = new MenuPane(WIDTH, MENU_HEIGHT);
        CanvasPane canvas = new CanvasPane(WIDTH, CANVAS_HEIGHT);

        menu.setPrefSize(1000,100);
        
        BorderPane root = new BorderPane();
        root.setTop(menu);
        root.setCenter(canvas);


        Scene scene = new Scene(root, WIDTH, MENU_HEIGHT + CANVAS_HEIGHT);
        scene.getStylesheets().add("styles.css");

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
