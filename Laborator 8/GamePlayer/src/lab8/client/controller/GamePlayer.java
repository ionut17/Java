package lab8.client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ionut
 */
public class GamePlayer {

    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1"; // The server's IP address
        int PORT = 8100; // The server's port
        try (
                //Try resources
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Send a join request to the server
            String request = "join";
            out.println(request);

            System.out.println("begin reading");
            // While the game is on play it
            String response = null;
            System.out.println("started playing");
            while (response == null || !response.equals("finish")) {
                try {
                    System.out.println("make an extract request");
                    //Do things here
                    request = "extract";
                    out.println(request);
                    System.out.println("extracting..");
                    Thread.sleep(100);
                    request = "word";
                    out.println(request);
                    System.out.println("making word..");
                    //Thread sleep and check the server
//                    Thread.sleep(1000);
                    System.out.println("waiting server response");
                    response = in.readLine();
                    System.out.println("got: "+response);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
            //After the game ended make an exit request
            request = "exit";
            out.println(request);
        }
    }
}
