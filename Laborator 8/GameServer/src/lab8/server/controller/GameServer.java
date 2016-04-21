package lab8.server.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

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
    Button startBtn;

    //Observers
    ScoreObserver sc;

    public GameServer(GameManager tgm, String[] tplayers, LetterPack tlp, ThreadPoolExecutor texecutor, ObservableList<String> tscoreboard, ListView<String> tlistView, TextArea tstatusArea, TextArea tpackArea, Label ttimeLabel, ScoreObserver tsc, Button tstartBtn) {
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
        this.startBtn = tstartBtn;
    }

    @Override
    public void run() {
        try {
            Dictionary d = Dictionary.getDictionary();
            HashSet<String> dt = d.getWords();

            TimeDaemon tdm = new TimeDaemon(timeLabel);
            new Thread(tdm).start();

            //Local players
            List<Player> playerList = new ArrayList<>();
            for (int i = 0; i < players.length; i++) {
                Player p = new Player(gm, lp, dt, players[i], statusArea, packArea, sc);
                sc.observePlayer(p);
                playerList.add(p);
                executor.execute(p);
                sc.updateScores();
            }
            executor.shutdown();
            startBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    statusArea.appendText("\n###Game started###\n\n");
                    for (int i = 0; i < playerList.size(); i++) {
                        synchronized (playerList.get(i)) {
                            playerList.get(i).notify();
                        }
                    }
                }
            });

            List<PlayerThread> remoteList = new ArrayList<>();
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
                    PlayerThread pt = new PlayerThread(socket, id, gm, lp, String.valueOf("Player " + id), statusArea, packArea, sc);
                    remoteList.add(pt);
                    statusArea.appendText("Player " + id + " has joined...\n");
                    startBtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            statusArea.appendText("\n###Game started###\n\n");
                            for (int i = 0; i < playerList.size(); i++) {
                                synchronized (playerList.get(i)) {
                                    playerList.get(i).notify();
                                }
                            }
                            for (int i = 0; i < remoteList.size(); i++) {
                                remoteList.get(i).start();
                            }
                        }
                    });
                }
            } catch (IOException e) {
                System.err.println("Ooops... " + e);
            } finally {
                serverSocket.close();
            }

            //Creating players
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
