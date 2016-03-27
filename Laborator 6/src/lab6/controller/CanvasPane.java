package lab6.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 *
 * @author Ionut
 */
class CanvasPane extends Canvas {

    Function attachedFunction = new Function();

    public CanvasPane(int width, int height, Function target) {
        super(width, height);
        attachedFunction = target;
//        drawShapes();

        this.widthProperty().addListener(observable -> drawShapes());
        this.heightProperty().addListener(observable -> drawShapes());

    }

    public void drawShapes() {
        GraphicsContext gc = this.getGraphicsContext2D();
        double width = gc.getCanvas().getWidth();
        double height = gc.getCanvas().getHeight();
        double gap = 0;

        //Settings
        gc.clearRect(0, 0, width, height);
        gc.setFill(Paint.valueOf("#C7C7C7"));

        //Drawing background lines
        gc.fillRect(width / 2, gap, 1, height - 2 * gap);
        gc.fillRect(gap, height / 2, width - 2 * gap, 1);

        //Draw the function
        gc.setFill(Paint.valueOf("#3498db"));
        //Adding main points
        Map<Integer, Integer> pointSet = new HashMap<>();
        for (int i = (int) -(width / 2); i <= width / 2 - gap; i++) {
            int xCoord = (int) (width / 2 + i);
            int yCoord = (int) ((height / 2)) - Integer.valueOf(attachedFunction.getValueOf(i).toString());
            pointSet.put(xCoord, yCoord);
            gc.fillRect(xCoord, yCoord, 1, 1);
        }
        //Drawing line between points
        int xOld = (int) (width / 2);
        int yOld = (int) (height / 2);
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : pointSet.entrySet()) {
            if (count == 0) {
                xOld = entry.getKey();
                yOld = entry.getValue();
            } else {
                int xCoord = entry.getKey();
                int yCoord = entry.getValue();
                gc.strokeLine(xOld, yOld, xCoord, yCoord);
                xOld = xCoord;
                yOld = yCoord;
            }
            count++;
        }
    }

    @Override
    public double minHeight(double width) {
        return 64;
    }

    @Override
    public double maxHeight(double width) {
        return 10000;
    }

    @Override
    public double prefHeight(double width) {
        return minHeight(width);
    }

    @Override
    public double minWidth(double height) {
        return 0;
    }

    @Override
    public double maxWidth(double height) {
        return 10000;
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public void resize(double width, double height) {
        super.setWidth(width);
        super.setHeight(height);
//        drawShapes();
    }
}
