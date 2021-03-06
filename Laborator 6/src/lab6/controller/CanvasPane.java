package lab6.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm;

/**
 *
 * @author Ionut
 */
class CanvasPane extends Canvas {

    Function attachedFunction = new Function();
    private String attachedColor = "#000000";
    private int attachedWeight = 3;
    private final double gap = 0;

    private Map< String, Map<String, Integer>> drawingSet = new HashMap<>();
    private boolean isReset = false;

    private Image canvasImage = null;
    private int pointsSize = 2;
    private int globalScale = 1;

    private String drawnFunction = null;
    private String[] mouseLocation = new String[2];

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
            drawnFunction = attachedFunction.getFunction();
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
        gc.setFont(new Font("Montseratt Bold", 12));
        for (int i = (int) -(width / 2) / globalScale; i <= (width / 2) / globalScale; i++) {
            if (i * globalScale != 0 && i % (100 / globalScale) == 0) {
                gc.fillRect(width / 2 + i * globalScale, height / 2 - 5, 1, 10);
                gc.fillText(String.valueOf(i), width / 2 + i * globalScale - 8, height / 2 + 20);
            }
        }
        for (int i = (int) -(height / 2) / globalScale; i <= (height / 2) / globalScale; i++) {
            if (i * globalScale != 0 && i * globalScale != -1 && i % (100 / globalScale) == 0) {
                gc.fillRect(width / 2 - 5, height / 2 - i * globalScale, 10, 1);
                gc.fillText(String.valueOf(i), width / 2 - 35, height / 2 - i * globalScale + 5);
            }
        }
    }

    public void redraw() {
        GraphicsContext gc = this.getGraphicsContext2D();
        double width = gc.getCanvas().getWidth();
        double height = gc.getCanvas().getHeight();

        reset();

        if (drawnFunction != null) {
            gc.setFill(Paint.valueOf("#525252"));
            gc.fillText(drawnFunction, 20, height - 20);
        }

        if (canvasImage != null) {
            gc.drawImage(canvasImage, 0, 0, width, height);
        }

        drawCoord();

        for (Map.Entry< String, Map<String, Integer>> entry : drawingSet.entrySet()) {
            gc.setStroke(Paint.valueOf(entry.getValue().entrySet().iterator().next().getKey()));
            gc.setLineWidth(entry.getValue().entrySet().iterator().next().getValue());
            //Draw the function
            //Adding main points
            Map<Integer, Integer> pointSet = new HashMap<>();
            Function newFunction = new Function();
            newFunction.setFunction(entry.getKey());
            int scale = globalScale;
            for (int i = (int) -(width / 2) / scale; i <= (width / 2 - gap) / scale; i++) {
                int xCoord = (int) ((width / 2) + i * scale);
                if (newFunction.getValueOf(i) != null) {
                    int yCoord = (int) ((height / 2)) - Integer.valueOf(newFunction.getValueOf(i).toString()) * scale;
                    pointSet.put(xCoord, yCoord);
                    gc.fillRect(xCoord, yCoord, 1, 1);
                }
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

        Map<String, Integer> tooltipDisplay = new HashMap<>();
        for (Map.Entry< String, Map<String, Integer>> e : drawingSet.entrySet()) {
            tooltipDisplay.put(e.getKey(), 0);
        }
        this.setOnMouseMoved(event -> {
            double x = event.getX(), y = event.getY();
            mouseLocation[0] = String.valueOf((int) ((-width / 2) + x) / globalScale);
            mouseLocation[1] = String.valueOf((int) ((height / 2) - y) / globalScale);
            drawCoord();
            for (Map.Entry< String, Map<String, Integer>> e : drawingSet.entrySet()) {
                Function f = new Function();
                f.setFunction(e.getKey());
                Tooltip mousePositionToolTip = new Tooltip("");
                mousePositionToolTip.setText(f.getFunction());
//                System.out.println("* "+mouseLocation[1]+" = "+f.getValueOf(Integer.valueOf(mouseLocation[0])));
                if (mouseLocation[1].matches(f.getValueOf(Integer.valueOf(mouseLocation[0])).toString())) {
//                    if (tooltipDisplay.get(f.getFunction()) == 0) {
//                        Node node = (Node) event.getSource();
//                        mousePositionToolTip.show(node, event.getScreenX() + 5, event.getScreenY());
//                        mousePositionToolTip.hide();
//                        tooltipDisplay.replace(f.getFunction(), 1);
//                    }
                      drawnFunction = f.getFunction();
                      redraw();
                }
            }
        });

        //Draws a point where you click on the canvas
        Map<Double, Double> mouseSet = new HashMap<>();
        this.setOnMouseClicked(event -> {
            double x = event.getX(), y = event.getY();
            gc.setStroke(Paint.valueOf("878787"));
            gc.strokeOval(x, y, 2, 2);
            double computedX = (x / globalScale - (width / 2));
            double computedY = ((height / 2) - y / globalScale);
            System.out.println("compX: " + computedX / globalScale + " compY:" + computedY / globalScale);
            mouseSet.put(computedX, computedY);
            if (mouseSet.size() >= getPointsSize()) {
                double xValues[] = new double[mouseSet.size()];
                double yValues[] = new double[mouseSet.size()];
                int i = 0;
                for (Map.Entry<Double, Double> entry : mouseSet.entrySet()) {
                    System.out.println(entry.getKey() + " " + entry.getValue());
                    xValues[i] = entry.getKey();
                    yValues[i] = entry.getValue();
                    i++;
                }
                gc.fillRect(x, y, 1, 1);
                PolynomialFunctionLagrangeForm p = new PolynomialFunctionLagrangeForm(xValues, yValues);
                //p.computeCoefficients();
                double coefficients[] = p.getCoefficients();
                int degree = p.degree();
                String lagrangeFunction = "";
                for (int t = coefficients.length - 1; t >= 0; t--) {
                    if (degree != 0) {
                        lagrangeFunction += "math:pow(x," + String.valueOf(degree) + ")*" + ((Double) coefficients[t]).toString() + "+";
                    } else {
                        lagrangeFunction += ((Double) coefficients[t]).toString();
                    }
                    System.out.println("coef " + coefficients[t] + " ");
                    degree--;
                }
                System.out.println("lagrange function: " + lagrangeFunction);
                drawnFunction = lagrangeFunction;
                //Drawing the lagrange function
                Map<String, Integer> stylesMap = new HashMap<>();
                stylesMap.put(attachedColor, attachedWeight);
                drawingSet.put(lagrangeFunction, stylesMap);
                redraw();
            }
        });
    }

    public void drawImage(File target) {
        GraphicsContext gc = this.getGraphicsContext2D();
        double width = gc.getCanvas().getWidth();
        double height = gc.getCanvas().getHeight();

        drawingSet.clear();
        reset();

        canvasImage = new Image(target.toURI().toString());
        gc.drawImage(canvasImage, 0, 0, width, height);
    }

    public void drawCoord() {
        GraphicsContext gc = this.getGraphicsContext2D();
        double width = gc.getCanvas().getWidth();
        double height = gc.getCanvas().getHeight();
//        reset();
        gc.clearRect(20, 10, 100, 20);
        gc.setFill(Paint.valueOf("#525252"));
        String s = "x: " + mouseLocation[0] + " y: " + mouseLocation[1];
        gc.fillText(s, 20, 20);
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
            drawnFunction = null;
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

    /**
     * @return the pointsSize
     */
    public int getPointsSize() {
        return pointsSize;
    }

    /**
     * @param pointsSize the pointsSize to set
     */
    public void setPointsSize(int pointsSize) {
        this.pointsSize = pointsSize;
    }

    /**
     * @return the scale
     */
    public int getScale() {
        return globalScale;
    }

    /**
     * @param scale the scale to set
     */
    public void setScale(int scale) {
        this.globalScale = scale;
    }

}
