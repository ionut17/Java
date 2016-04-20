package lab8.server.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

/**
 *
 * @author Ionut
 */
class PlayerThread extends Thread {

    private Socket socket = null;
    private TextArea statusArea;
    private final int id;

    public PlayerThread(Socket socket, TextArea tstatusArea, int tid) {
        this.socket = socket;
        this.statusArea = tstatusArea;
        this.id = tid;
    }

    public void run() {
        try {
            // Get the request from the input stream: client → server
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            String request = in.readLine();
            switch(request){
                case "join":
                    System.out.println("join");
                    statusArea.appendText("Player "+id+" has joined...\n");
//                    Platform.runLater(new Runnable() {
//                        public void run() {
//                            statusArea.appendText("Player "+pcount+" has joined...\n");
//                        }
//                    });
                    break;
                default:
                    System.out.println("default");
            }
            PlayerThread.sleep(5);
            // Send the response to the oputput stream: server → client
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            String raspuns = "finish";
            out.println(raspuns);
            out.flush();
            
            request = in.readLine();
            if (request.equals("exit")){
                statusArea.appendText("Player "+id+" exited...\n");
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            try {
                socket.close(); // or use try-with-resources
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
