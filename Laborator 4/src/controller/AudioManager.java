package controller;

import view.ManagerObserver;
import view.PrintObserver;
import controller.command.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;
import view.exception.*;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class AudioManager {

    private Path currentDirectory = Paths.get("C:\\");
    private final List<ManagerObserver> observerList = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    public AudioManager() {
        ManagerObserver print = new PrintObserver();
        print.setAttachedManager(this);
        observerList.add(print);
    }

    public void start() {
        int ok = 1;
        //Read commands until close
        while (ok == 1) {
            try {
                notifyObservers();
                String stringCommand = readCommand();
                Command targetCommand = parseCommand(stringCommand);
                targetCommand.execute();
            } catch (ExitCommandException e) {
                System.err.println("Closing app..");
                break;
            } catch (InvalidCommandException e) {
                System.err.println(e.getMessage());
            } catch (FileNotFoundException e) {
                System.err.println("File not found..");
            } catch (IOException e) {
                System.err.println("I/O Error..");
            } catch (SAXException | TikaException e){
                System.err.println("SAX|Tika Error..");
            }
            catch (Exception e) {
                e.printStackTrace();
                System.err.println("Something went wrong..");
            } finally {
                System.out.println();
            }
        }
    }

    /**
     * Reads from console a command line
     *
     * @return String with command
     */
    private String readCommand() throws InvalidCommandException, IOException {
        String nextLine = sc.nextLine();
        if (nextLine.isEmpty()) {
            throw new InvalidCommandException("Empty command! Try again..");
        }
        return nextLine;
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
                myCommand = new CdCommand();
                break;
            case "list":
                myCommand = new ListCommand();
                break;
            case "play":
                myCommand = new PlayCommand();
                break;
            case "info":
                myCommand = new InfoCommand();
                break;
            case "find":
                myCommand = new FindCommand();
                break;
            case "help":
                myCommand = new HelpCommand();
                break;
            case "close":
                throw new ExitCommandException();
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
    public Path getCurrentDirectory() {
        return currentDirectory;
    }

    /**
     * @param currentDirectory the currentDirectory to set
     */
    public void setCurrentDirectory(Path currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    private void notifyObservers() {
        for (ManagerObserver obs : observerList) {
            obs.update();
        }
    }
}
