package lab6.controller;

import java.awt.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static javafx.geometry.Pos.BASELINE_CENTER;
import static javafx.geometry.Pos.TOP_RIGHT;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
class MenuPane extends FlowPane {

    public MenuPane(int width, int height, Function myFunction, CanvasPane canvas) {
        super();
        this.setPrefSize(width, height);
        this.setId("menu");

        Label f_label = new Label("f(X): ");
        TextField functionField = new TextField();
        functionField.setPromptText("Enter a function.");
        functionField.setPrefColumnCount(15);

        Button graphicBtn = new Button();
        graphicBtn.setText("Graphic");
        graphicBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Graphic");
                if (functionField.getText() != null && !functionField.getText().isEmpty()) {
                    String function = functionField.getText();

                    System.out.println(function);
//                    functionField.clear();

                    myFunction.setFunction(function);
                    canvas.drawShapes();
                }
            }
        });

        Button saveBtn = new Button();
        saveBtn.setText("Save");
        saveBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Save");
            }
        });

        Button resetBtn = new Button();
        resetBtn.setText("Reset");
        resetBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Reset");
            }
        });

        Button loadBtn = new Button();
        loadBtn.setText("Load");
        loadBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Load");
            }
        });

        HBox hbox = new HBox(10);
//        hbox.setPadding(new Insets(0, 20, 10, 20));
        hbox.setAlignment(TOP_RIGHT);
        hbox.getChildren().addAll(f_label, functionField, graphicBtn, saveBtn, resetBtn, loadBtn);

        System.out.println(functionField.getText());
        this.getChildren().add(hbox);

    }

}
