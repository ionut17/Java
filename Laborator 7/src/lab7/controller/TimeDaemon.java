package lab7.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 *
 * @author Ionut
 */
public class TimeDaemon implements Runnable {

    private final Label attachedTimeLabel;
    private final long tStart;

    public TimeDaemon(Label timeLabel) {
        this.attachedTimeLabel = timeLabel;
        this.tStart = System.currentTimeMillis();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                long tEnd = System.currentTimeMillis();
                long tDelta = tEnd - tStart;
                int elapsedSeconds = (int) (tDelta / 1000);

                int elapsedMinutes = elapsedSeconds / 60;

                StringBuilder sb = new StringBuilder();
                if (elapsedSeconds / 60 < 10) {
                    sb.append("0").append(elapsedSeconds / 60);
                } else {
                    sb.append(elapsedSeconds / 60);
                }
                sb.append(":");
                if (elapsedSeconds % 60 < 10) {
                    sb.append("0").append(elapsedSeconds % 60);
                } else {
                    sb.append(elapsedSeconds % 60);
                }

                Platform.runLater(new Runnable() {
                    public void run() {
                        attachedTimeLabel.setText(sb.toString());
                    }
                });
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}
