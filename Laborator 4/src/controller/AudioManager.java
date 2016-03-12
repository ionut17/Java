package controller;

import view.ManagerObserver;
import view.PrintObserver;
import controller.command.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import view.exception.InvalidCommandException;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class AudioManager {

    private String currentDirectory;
    private final List<ManagerObserver> observerList = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    public AudioManager() {
        observerList.add(new PrintObserver());
    }

    public void start() {
        int ok = 1;
        //Read commands until close
        while (ok == 1) {
            try {
//                notifyObservers();
                System.out.println("\nInsert command: ");
                String stringCommand = readCommand();
                Command targetCommand = parseCommand(stringCommand);
                targetCommand.execute();
            }
            catch (InvalidCommandException e){
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Reads from console a command line
     *
     * @return String with command
     */
    private String readCommand() {
        return sc.nextLine();
    }

    /**
     * Parses the string command and returns a command object
     *
     * @param stringCommand
     * @return Command object
     */
    private Command parseCommand(String stringCommand) throws InvalidCommandException {
        Command myCommand = null;
        String[] tokens = stringCommand.split(" ");
        switch (tokens[0]) {
            case "cd":
                break;
            case "list":
                myCommand = new ListCommand();
                break;
            default:
                throw new InvalidCommandException("Invalid command! Try again..");
        }
        myCommand.setAudioManager(this);
        myCommand.setArgs(Arrays.copyOfRange(tokens, 1, tokens.length));
        return myCommand;
    }

    /**
     * @return the currentDirectory
     */
    public String getCurrentDirectory() {
        return currentDirectory;
    }

    /**
     * @param currentDirectory the currentDirectory to set
     */
    public void setCurrentDirectory(String currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    private void notifyObservers() {
        for (ManagerObserver obs : observerList) {
            obs.update();
        }
    }

}
