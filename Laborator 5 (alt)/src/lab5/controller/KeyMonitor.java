package lab5.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import lab4.view.exception.InvalidCommandException;
import lab5.controller.content.DetailsPanel;
import lab5.model.command.CdCommandLab5;
import lab5.model.command.CommandLab5;
import lab5.model.command.FavCommandLab5;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Anca
 */
class KeyMonitor extends KeyAdapter {

    private MyTree myTree=new MyTree();
    private DetailsPanel dp=new DetailsPanel();
    private JTextArea commandLine;
    String commandRcvd;
//    private JTextArea resultArea;

    public KeyMonitor(JTextArea commandLine) {
        this.commandLine = commandLine;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (commandLine.getText().charAt(commandLine.getCaretPosition() - 2) == '>') {
                e.consume();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            int startPos = commandLine.getText().lastIndexOf("Command> ") + 9;
            commandRcvd = commandLine.getText().substring(startPos);
            commandLine.setText(commandLine.getText() + "\nCommand> ");
            String commandReceived = commandRcvd;
            if (commandReceived.isEmpty() == false) {
                CommandLab5 myCommand = parse(commandReceived);
                try {
                    myCommand.execute();
                } catch (Exception ex) {
                    Logger.getLogger(KeyMonitor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            e.consume();
        }
    }

    public CommandLab5 parse(String stringCommand) {
        CommandLab5 myCommand = null;
        String[] tokens = stringCommand.split(" ");
        switch (tokens[0]) {
            case "cd":
                myCommand = new CdCommandLab5();
                myCommand.setMyTree(getMyTree());
                myCommand.setDp(getDp());
                break;
//            case "list":
//                myCommand = new ListCommand();
//                break;
//            case "play":
//                myCommand = new PlayCommand();
//                break;
//            case "info":
//                myCommand = new InfoCommand();
//                break;
//            case "find":
//                myCommand = new FindCommand();
//                break;
//            case "help":
//                myCommand = new HelpCommand();
//                break;
            case "fav":
                myCommand = new FavCommandLab5();
                break;
//            case "report":
//                myCommand = new ReportCommand();
//                break;
//            case "rmfav":
//                myCommand = new RmFavCommand();
//                break;
            case "exit":
                System.exit(0);

            default:
                throw new InvalidCommandException("Invalid command! Try again..");
        }
        myCommand.setArgs(Arrays.copyOfRange(tokens, 1, tokens.length));
        return myCommand;
    }

    /**
     * @return the myTree
     */
    public MyTree getMyTree() {
        return myTree;
    }

    /**
     * @param myTree the myTree to set
     */
    public void setMyTree(MyTree myTree) {
        this.myTree = myTree;
    }

    /**
     * @return the dp
     */
    public DetailsPanel getDp() {
        return dp;
    }

    /**
     * @param dp the dp to set
     */
    public void setDp(DetailsPanel dp) {
        this.dp = dp;
    }

    /**
     * @return the resultArea
     */
//    public JTextArea getResultArea() {
//        return resultArea;
//    }
}
