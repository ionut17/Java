package lab6.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 *
 * @author Ionut
 */
class CanvasPane extends Canvas {

    Function attachedFunction = new Function();
    private String attachedColor = "#000000";
    private int attachedWeight = 3;
    private final double gap = 0;
    private boolean isReset = false;
    private Image canvasImage = null;

    private Map< String, Map<String, Integer> > drawingSet = new HashMap<>();

    public CanvasPane(int width, int height, Function target) {
        super(width, height);
        attachedFunction = target;

//        this.widthProperty().addListener(observable -> drawShapes());
//        this.heightProperty().addListener(observable -> drawShapes());
        this.reset();
        drawShapes(attachedColor);
    }

    public void drawShapes(String graphColor) {
        GraphicsContext gc = this.getGraphicsContext2D();
        double width = gc.getCanvas().getWidth();
        double height = gc.getCanvas().getHeight();

//        this.reset();
        //Drawing function
        Map<String, Integer> stylesMap = new HashMap<>();
        if (attachedFunction.getFunction() != null) {
            stylesMap.put(attachedColor, attachedWeight);
            drawingSet.put(attachedFunction.getFunction(), stylesMap);
            redraw();
//            attachedFunction.setFunction(null);
        }
    }

    public void reset() {
        GraphicsContext gc = this.getGraphicsContext2D();
        double width = gc.getCanvas().getWidth();
        double height = gc.getCanvas().getHeight();

        //Settings
        gc.clearRect(0, 0, width, height);
        gc.setFill(Paint.valueOf("#C7C7C7"));

        //Drawing background lines
        gc.fillRect(width / 2, gap, 1, height - 2 * gap);
        gc.fillRect(gap, height / 2, width - 2 * gap, 1);

        gc.setFont(new Font("Montseratt", 18));
        gc.fillText("O", width / 2 - 6, height / 2 + 7);
        gc.fillText("x", width - 20, height / 2 - 10);
        gc.fillText("y", width / 2 + 10, 20);

        //Drawing background counts
        int scale = 1;
        gc.setFont(new Font("Montseratt Bold", 12));
        for (int i = (int) -(width / 2); i <= width / 2; i += scale) {
            if (i != 0 && i%40==0) {
                gc.fillRect(width / 2 + i * scale, height / 2 - 5, 1, 10);
                gc.fillText(String.valueOf(i), width / 2 + i * scale - 8, height / 2 + 20);
            }
        }
        for (int i = (int) -(height / 2); i <= height / 2; i += scale) {
            if (i / scale != 0 && i / scale != -1 && i%40==0) {
                gc.fillRect(width / 2 - 5, height / 2 - i * scale, 10, 1);
                gc.fillText(String.valueOf(i), width / 2 - 35, height / 2 - i * scale + 5);
            }
        }
    }

    public void redraw() {
        GraphicsContext gc = this.getGraphicsContext2D();
        double width = gc.getCanvas().getWidth();
        double height = gc.getCanvas().getHeight();

        reset();
        
        if(canvasImage!=null){
            gc.drawImage(canvasImage, 0, 0, width, height);
        }

        for (Map.Entry< String, Map<String, Integer>> entry : drawingSet.entrySet()) {
            gc.setStroke(Paint.valueOf(entry.getValue().entrySet().iterator().next().getKey()));
            gc.setLineWidth(entry.getValue().entrySet().iterator().next().getValue());
            //Draw the function
            //Adding main points
            Map<Integer, Integer> pointSet = new HashMap<>();
            Function newFunction = new Function();
            newFunction.setFunction(entry.getKey());
            for (int i = (int) -(width / 2); i <= (width / 2 - gap); i++) {
                int xCoord = (int) ((width / 2) + i);
                int yCoord = (int) ((height / 2)) - Integer.valueOf(newFunction.getValueOf(i).toString());
                pointSet.put(xCoord, yCoord);
                gc.fillRect(xCoord, yCoord, 1, 1);
            }
            //Drawing line between points
            int xOld = (int) (width / 2);
            int yOld = (int) (height / 2);
            int count = 0;
            for (Map.Entry<Integer, Integer> entry2 : pointSet.entrySet()) {
                if (count == 0) {
                    xOld = entry2.getKey();
                    yOld = entry2.getValue();
                } else {
                    int xCoord = entry2.getKey();
                    int yCoord = entry2.getValue();
                    gc.strokeLine(xOld, yOld, xCoord, yCoord);
                    xOld = xCoord;
                    yOld = yCoord;
                }
                count++;
            }
        }
    }
    
    public void drawImage(File target){
        GraphicsContext gc = this.getGraphicsContext2D();
        double width = gc.getCanvas().getWidth();
        double height = gc.getCanvas().getHeight();
        
        drawingSet.clear();        
        reset();
        
        canvasImage = new Image(target.toURI().toString());
        gc.drawImage(canvasImage, 0, 0, width, height) ;
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
        if (isReset == false) {
            redraw();
        } else {
            drawingSet.clear();
            canvasImage = null;
            reset();
            isReset = false;
        }
    }

    /**
     * @return the attachedColor
     */
    public String getAttachedColor() {
        return attachedColor;
    }

    /**
     * @param attachedColor the attachedColor to set
     */
    public void setAttachedColor(String attachedColor) {
        this.attachedColor = attachedColor;
    }

    /**
     * @return the attachedWeight
     */
    public int getAttachedWeight() {
        return attachedWeight;
    }

    /**
     * @param attachedWeight the attachedWeight to set
     */
    public void setAttachedWeight(int attachedWeight) {
        this.attachedWeight = attachedWeight;
    }

    /**
     * @param isReset the isReset to set
     */
    public void setIsReset(boolean isReset) {
        this.isReset = isReset;
    }

}
