package lab6.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static javafx.geometry.Pos.TOP_RIGHT;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

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

        final ToggleGroup group = new ToggleGroup();

        ToggleButton tb1 = new ToggleButton("");
        tb1.setToggleGroup(group);
        ToggleButton tb2 = new ToggleButton("");
        tb2.setToggleGroup(group);
        ToggleButton tb3 = new ToggleButton("");
        tb3.setToggleGroup(group);
        ToggleButton tb4 = new ToggleButton("");
        tb4.setToggleGroup(group);
        ToggleButton tb5 = new ToggleButton("");
        tb5.setToggleGroup(group);
        
        final ToggleGroup group2 = new ToggleGroup();

        ToggleButton tb6 = new ToggleButton("1");
        tb6.setToggleGroup(group2);
        tb6.setSelected(true);
        
        ToggleButton tb7 = new ToggleButton("2");
        tb7.setToggleGroup(group2);
        
        ToggleButton tb8 = new ToggleButton("3");
        tb8.setToggleGroup(group2);


        HBox hbox = new HBox();

//        hbox.setPadding(new Insets(0, 20, 10, 20));
        hbox.setAlignment(TOP_RIGHT);
        hbox.getChildren().addAll(f_label, functionField, tb1, tb2, tb3, tb4, tb5,tb6, tb7, tb8, graphicBtn, saveBtn, resetBtn, loadBtn);

        System.out.println(functionField.getText());
        this.getChildren().add(hbox);

    }

}
