package lab7.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

/**
 *
 * @author Ionut
 */
public class ScoreObserver {

    List<Player> observedPlayers = new ArrayList<>();
    ObservableList<String> scoreboard;
    TextArea textArea;
    private boolean updatedWinner = false;

    public ScoreObserver(ObservableList<String> tscoreboard, TextArea t) {
        this.scoreboard = tscoreboard;
        this.textArea = t;
    }

    void updateScores() {
        String[] playerScores = new String[observedPlayers.size()];
        int k = 0;
        for (Player p : observedPlayers) {
            playerScores[k] = p.getPlayerName() + " - " + p.getScore() + "p";
            k++;
        }
        this.scoreboard.setAll(playerScores);
    }

    void updateWinner() {
        if (!updatedWinner) {
            updatedWinner = true;
            int maxScore=0;
            for (Player p : observedPlayers) {
                if (p.getScore()>maxScore){
                    maxScore = p.getScore();
                }
            }
            for (Player p : observedPlayers) {
                if (p.getScore()==maxScore){
                    String winner = p.getPlayerName() + " has won with " + p.getScore() + " points!\n";
                    textArea.appendText(winner);
                }
            }
        }
    }

    void observePlayer(Player p) {
        observedPlayers.add(p);
    }

}
