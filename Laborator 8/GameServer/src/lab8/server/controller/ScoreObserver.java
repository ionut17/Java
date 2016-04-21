package lab8.server.controller;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            int maxScore = 0;
            for (Player p : observedPlayers) {
                if (p.getScore() > maxScore) {
                    maxScore = p.getScore();
                }
            }
            for (Player p : observedPlayers) {
                if (p.getScore() == maxScore) {
                    String winner = p.getPlayerName() + " has won with " + p.getScore() + " points!\n";
                    textArea.appendText(winner);
                }
            }
            //Saving to file
            String content = textArea.getText();
            String[] lines = content.split("\n");
            StringBuilder sb = new StringBuilder();
            sb.append("<html><body>");
            for (String line : lines) {
                sb.append(line).append("<br>");
            }
            sb.append("</body></html>");
            try {
                PrintWriter fileOut = new PrintWriter("game-report.html");
                fileOut.print(sb.toString());
                fileOut.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            //Upload file to sftp
            String SFTPHOST = "fenrir.info.uaic.ro";
            int SFTPPORT = 22;
            String SFTPUSER = "robert.iacob";
            String SFTPPASS = "";
            String SFTPWORKINGDIR = "html/projects/java";
            String FILETOTRANSFER = "game-report.html";

            Session session = null;
            Channel channel = null;
            ChannelSftp channelSftp = null;

            try {
                JSch jsch = new JSch();
                session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
                session.setPassword(SFTPPASS);
                java.util.Properties config = new java.util.Properties();
                config.put("StrictHostKeyChecking", "no");
                session.setConfig(config);
                session.connect();
                channel = session.openChannel("sftp");
                channel.connect();
                channelSftp = (ChannelSftp) channel;
                System.out.println(channelSftp.pwd());
                channelSftp.cd(SFTPWORKINGDIR);
                System.out.println(channelSftp.pwd());
                File f = new File(FILETOTRANSFER);
                channelSftp.put(new FileInputStream(f), f.getName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            textArea.appendText("\n###File game-report.html uploaded successfully!");
        }
    }

    void observePlayer(Player p) {
        observedPlayers.add(p);
    }

}
