package lab4.controller;

import lab4.view.exception.InvalidCommandException;
import lab4.view.exception.ExitCommandException;
import lab4.controller.command.Command;
import lab4.controller.command.ReportCommand;
import lab4.controller.command.FavCommand;
import lab4.controller.command.CdCommand;
import lab4.controller.command.InfoCommand;
import lab4.controller.command.ListCommand;
import lab4.controller.command.HelpCommand;
import lab4.controller.command.FindCommand;
import lab4.controller.command.PlayCommand;
import lab4.controller.command.RmFavCommand;
import lab4.view.ManagerObserver;
import lab4.view.PrintObserver;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class AudioManager {

    private Path currentDirectory = Paths.get("D:\\Dropbox\\Music");
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
            catch (InvalidPathException | NullPointerException e){
                System.err.println("Invalid path..");
//                e.printStackTrace();
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
            case "fav": 
                myCommand=new FavCommand();
                break;
            case "report":
                myCommand = new ReportCommand();
                break;
            case "rmfav":
                myCommand = new RmFavCommand();
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
