/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7.controller;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import javafx.application.Application;
import java.io.IOException;
import java.util.HashSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Ionut
 */
public class GameManager extends Application {

    LetterPack lp = new LetterPackGenerator().getScrabblePack();
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(6);
    String[] players = {"Ionut", "Anca", "Stefan", "Eveline", "Dan"};

    TextArea statusArea = new TextArea();
    TextArea packArea = new TextArea(lp.toString());
    Label timeLabel = new Label("00:00");

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
        Dictionary d = Dictionary.getDictionary();
        HashSet<String> dt = d.getWords();

        Player p1 = new Player(lp, dt, "Ionut");
        Player p2 = new Player(lp, dt, "Anca");

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        //Timekeeper
        Label textLabel = new Label("Time elapsed: ");
        HBox timeCounter = new HBox();
        timeCounter.setId("timeCounter");
        timeCounter.getChildren().addAll(textLabel, timeLabel);

        //Players Pane
        ObservableList<String> names = FXCollections.observableArrayList(players);
        ListView<String> listView = new ListView<String>(names);
        
        listView.setPrefHeight(200);
        packArea.setPrefWidth(200);
        packArea.setPrefHeight(400);
        
        BorderPane left = new BorderPane();
        left.setTop(listView);
        left.setCenter(packArea);

        this.play();

        //Status Pane
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

        primaryStage.setTitle("Hello World!");
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

        TimeDaemon tdm = new TimeDaemon(timeLabel);
        new Thread(tdm).start();
        
        //Creating players
        for (int i = 0; i < 5; i++) {
            Player p = new Player(lp, players[i % 5], statusArea, packArea);
            executor.execute(p);
        }

    }

}
