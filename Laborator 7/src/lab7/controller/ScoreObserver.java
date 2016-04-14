package lab7.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Ionut
 */
public class ScoreObserver {

    List<Player> observedPlayers = new ArrayList<>();
    ObservableList<String> scoreboard;

    public ScoreObserver(ObservableList<String> tscoreboard) {
        this.scoreboard = tscoreboard;
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

    void observePlayer(Player p) {
        observedPlayers.add(p);
    }

}
