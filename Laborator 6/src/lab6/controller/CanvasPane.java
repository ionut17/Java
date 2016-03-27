package lab6.controller;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author Ionut
 */
class CanvasPane extends FlowPane{
    
    public CanvasPane(){
        super();
        Label lb = new Label("Just a random label");
        this.getChildren().add(lb);
    }
    
}
