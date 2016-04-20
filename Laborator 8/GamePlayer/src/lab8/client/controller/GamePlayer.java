package lab8.client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Ionut
 */
public class GamePlayer {

    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1"; // The server's IP address
        int PORT = 8100; // The server's port
        try (
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            // Send a request to the server

            String request = "join";
            out.println(request);

            // Wait the response from the server
            String response = in.readLine();
            while (response == null || !response.equals("finish")) {
                response = in.readLine();
                if (response != null) {
                    System.out.println(response);
                }
            }
            
            request = "exit";
            out.println(request);
        }
    }
}
