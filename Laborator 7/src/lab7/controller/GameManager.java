package lab7.controller;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import javafx.application.Application;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Ionut
 */
public class GameManager extends Application {

    String[] players = {"Ionut","Anca", "Stefan", "Eveline"}; //, "Anca", "Stefan", "Eveline", "Dan"

    //Contents
    LetterPack lp = new LetterPackGenerator().getScrabblePack();
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(6);

    //Players Pane
    ObservableList<String> scoreboard = FXCollections.observableArrayList("");
    ListView<String> listView = new ListView<String>(scoreboard);

    TextArea statusArea = new TextArea();
    TextArea packArea = new TextArea(lp.toString());
    Label timeLabel = new Label("00:00");
    
    //Observers
    ScoreObserver sc = new ScoreObserver(scoreboard, statusArea);
    private int turn = 0;

    @Override
    public void start(Stage primaryStage) {
        //Timekeeper
        Label textLabel = new Label("Time elapsed: ");
        HBox timeCounter = new HBox();
        timeCounter.setId("timeCounter");
        timeCounter.getChildren().addAll(textLabel, timeLabel);

        listView.setPrefHeight(200);
        listView.setId("listView");
        packArea.setPrefWidth(200);
        packArea.setPrefHeight(400);
        packArea.setId("packArea");

        BorderPane left = new BorderPane();
        left.setTop(listView);
        left.setCenter(packArea);

        this.play();
        sc.updateScores();

        //Status Pane
        statusArea.setId("statusArea");
        statusArea.setEditable(false);
        statusArea.setPrefRowCount(10);
        statusArea.setWrapText(true);

        //Root pane
        BorderPane root = new BorderPane();
        root.setTop(timeCounter);
        root.setCenter(statusArea);
        root.setLeft(left);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("styles.css");

        primaryStage.setTitle("GameManager");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        //Code
    }

    public void play() {

        try {
            Dictionary d = Dictionary.getDictionary();
            HashSet<String> dt = d.getWords();

            TimeDaemon tdm = new TimeDaemon(timeLabel);
            new Thread(tdm).start();

            //Creating players
            List<Player> playerList = new ArrayList<>();
            for (int i = 0; i < players.length; i++) {
                Player p = new Player(this, lp, dt, players[i], statusArea, packArea, sc);
                sc.observePlayer(p);
                playerList.add(p);
                executor.execute(p);
            }
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
