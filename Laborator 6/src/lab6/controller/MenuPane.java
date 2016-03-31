package lab6.controller;

import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static javafx.geometry.Pos.TOP_RIGHT;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
class MenuPane extends FlowPane {

    Stage myStage;

    public MenuPane(int width, int height, Function myFunction, CanvasPane canvas, Stage primaryStage) {
        super();
        this.setPrefSize(width, height);
        this.setId("menu");
        myStage = primaryStage;

        //Input-hbox
        HBox input = new HBox();
        input.setId("input-hbox");

        Label f_label = new Label("f(X): ");
        TextField functionField = new TextField();
        functionField.setPromptText("Enter a function.");
        functionField.setPrefColumnCount(15);
        input.getChildren().addAll(f_label, functionField);

        //Buttons-hbox
        HBox buttons = new HBox();
        buttons.setId("buttons-hbox");

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
                    canvas.drawShapes("#000000");
                }
            }
        });

        Button saveBtn = new Button();
        saveBtn.setText("Save");
        saveBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Save");
                WritableImage wim = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
                canvas.snapshot(null, wim);
                File file = new File("CanvasImage.png");
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);
                    System.out.println("File saved succesfully to " + file.getName());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        Button resetBtn = new Button();
        resetBtn.setText("Reset");
        resetBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                canvas.setIsReset(true);
                canvas.reset();
            }
        });

        Button loadBtn = new Button();
        loadBtn.setText("Load");
        loadBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Load");
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.jpg,*.png,*.gif,*.svg)", "*.jpg", "*.png", "*.gif", "*.svg");
                fileChooser.getExtensionFilters().add(extFilter);
                File selectedFile = fileChooser.showOpenDialog(myStage);
                canvas.drawImage(selectedFile);
            }
        });

        buttons.getChildren().addAll(graphicBtn, saveBtn, resetBtn, loadBtn);
        buttons.setSpacing(5);

        //Color toggles
        HBox colorToggles = new HBox();
        final ToggleGroup group = new ToggleGroup();
        Label label1 = new Label("Colors: ");

        ToggleButton tb0 = new ToggleButton("");
        tb0.setId("tb0");
        tb0.setToggleGroup(group);
        tb0.setSelected(true);
        tb0.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                canvas.setAttachedColor("#000000");
            }
        });
        ToggleButton tb1 = new ToggleButton("");
        tb1.setId("tb1");
        tb1.setToggleGroup(group);
        tb1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                canvas.setAttachedColor("#308AC7");
            }
        });
        ToggleButton tb2 = new ToggleButton("");
        tb2.setId("tb2");
        tb2.setToggleGroup(group);
        tb2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                canvas.setAttachedColor("#e74c3c");
            }
        });
        ToggleButton tb3 = new ToggleButton("");
        tb3.setId("tb3");
        tb3.setToggleGroup(group);
        tb3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                canvas.setAttachedColor("#f39c12");
            }
        });
        ToggleButton tb4 = new ToggleButton("");
        tb4.setId("tb4");
        tb4.setToggleGroup(group);
        tb4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                canvas.setAttachedColor("#27ae60");
            }
        });
        ToggleButton tb5 = new ToggleButton("");
        tb5.setId("tb5");
        tb5.setToggleGroup(group);
        tb5.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                canvas.setAttachedColor("#8e44ad");
            }
        });

        colorToggles.getChildren().addAll(label1, tb0, tb1, tb2, tb3, tb4, tb5);

        Label label2 = new Label("Weights: ");

        HBox weightToggles = new HBox();
        final ToggleGroup group2 = new ToggleGroup();

        ToggleButton tb6 = new ToggleButton("1");
        tb6.setId("tb6");
        tb6.setToggleGroup(group2);
        tb6.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                canvas.setAttachedWeight(1);
            }
        });

        ToggleButton tb7 = new ToggleButton("2");
        tb7.setId("tb7");
        tb7.setToggleGroup(group2);
        tb7.setSelected(true);
        tb7.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                canvas.setAttachedWeight(3);
            }
        });

        ToggleButton tb8 = new ToggleButton("3");
        tb8.setId("tb6");
        tb8.setToggleGroup(group2);
        tb8.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                canvas.setAttachedWeight(5);
            }
        });

        weightToggles.getChildren().addAll(label2, tb6, tb7, tb8);

        //drawSizes - Select how many clicks to draw a graph
        HBox drawSizes = new HBox();
        drawSizes.setId("drawSizes-hbox");

        Label label3 = new Label("Draw points: ");
        ObservableList<String> options = FXCollections.observableArrayList("2", "3", "4", "5", "6", "7", "8", "9", "10");
        ComboBox combo = new ComboBox(options);
        combo.setValue("2");
        combo.setVisibleRowCount(5);
        combo.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                canvas.setPointsSize(Integer.valueOf(combo.getValue().toString()));
                canvas.redraw();
            }
        });
        drawSizes.getChildren().addAll(label3, combo);

        //Main box
        FlowPane mainBox = new FlowPane();
        mainBox.prefWidthProperty().bind(myStage.widthProperty());
        mainBox.setId("mainBox");
        mainBox.setVgap(10);
        mainBox.setHgap(20);

        HBox box1 = new HBox();
        HBox box2 = new HBox();
        box1.setSpacing(5);
        box2.setSpacing(20);

        box1.getChildren().addAll(input, buttons);
        box2.getChildren().addAll(colorToggles, weightToggles, drawSizes);

        mainBox.getChildren().addAll(box1, box2);

        System.out.println(functionField.getText());
        this.getChildren().add(mainBox);
    }

}
