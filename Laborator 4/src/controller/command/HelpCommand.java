package controller.command;

import view.exception.InvalidCommandException;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class HelpCommand extends AbstractCommand {

    @Override
    public void execute() throws Exception {
        System.out.println("Helper");
        if (args.length==0){
            System.out.println("cd: setting the current directory");
            System.out.println("list: listing of the audio files in the current directory or in param directory");
            System.out.println("play: playback of a file");
            System.out.println("info: display the metadata of a specific file: songname, artist, album, year, etc");
            System.out.println("find: search for a song having a particular name, or artist, or album. etc");
            System.out.println("fav: add a specific file to the favorites list");
            System.out.println("showfav: shows the favorites list");
            System.out.println("report: create a report containing the favorite songs");
            System.out.println("close: close the application");
            System.out.println("help: get info about commands");
        }
        else {
            throw new InvalidCommandException("Invalid command! Try again..");
        }
    }
    
}
