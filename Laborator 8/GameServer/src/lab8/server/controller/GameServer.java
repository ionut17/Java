package lab8.server.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import static lab8.server.controller.GameManager.PORT;

/**
 *
 * @author Ionut
 */
class GameServer implements Runnable {
    
    public static final int PORT = 8100;
    
    GameManager gm;
    
    String[] players;
    LetterPack lp;
    ThreadPoolExecutor executor;

    ObservableList<String> scoreboard;
    ListView<String> listView;

    TextArea statusArea;
    TextArea packArea;
    Label timeLabel;
    
    //Observers
    ScoreObserver sc;

    public GameServer(GameManager tgm, String[] tplayers, LetterPack tlp, ThreadPoolExecutor texecutor, ObservableList<String> tscoreboard, ListView<String> tlistView, TextArea tstatusArea, TextArea tpackArea, Label ttimeLabel, ScoreObserver tsc) {
        this.gm = tgm;
        this.players = tplayers;
        this.lp = tlp;
        this.executor = texecutor;
        this.scoreboard = tscoreboard;
        this.listView = tlistView;
        this.statusArea = tstatusArea;
        this.packArea = tpackArea;
        this.timeLabel = ttimeLabel;
        this.sc = tsc;
    }

    @Override
    public void run() {
        try {
            Dictionary d = Dictionary.getDictionary();
            HashSet<String> dt = d.getWords();

            TimeDaemon tdm = new TimeDaemon(timeLabel);
            new Thread(tdm).start();

            //Starting 
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(PORT);
                int id = 0;
                while (true) {
                    System.out.println("Waiting for a player ...");
                    Socket socket = serverSocket.accept();
                    // Execute the client's request in a new thread
                    //Assign a new identifier
                    id++;
                    //Start a new thread
                    new PlayerThread(socket, id, gm, lp, dt, String.valueOf("Player "+id), statusArea, packArea, sc).start();
                }
            } catch (IOException e) {
                System.err.println("Ooops... " + e);
            } finally {
                serverSocket.close();
            }

//            //Creating players
//            List<Player> playerList = new ArrayList<>();
//            for (int i = 0; i < players.length; i++) {
//                Player p = new Player(gm, lp, dt, players[i], statusArea, packArea, sc);
//                sc.observePlayer(p);
//                playerList.add(p);
//                executor.execute(p);
//            }
//            executor.shutdown();

//            Turns
//            for (int i = 0; i < 5; i++) {
//                statusArea.appendText("Turn "+i+'\n');
//                for (Player p : playerList) {
//                    executor.getQueue().element().notify();
//                    try{
//                        Thread.sleep(1000);
//                    }
//                    catch (InterruptedException ex){
//                        ex.printStackTrace();
//                    }
//                }
//            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
