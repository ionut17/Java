package controller.command;

import controller.AudioManager;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class ListCommand extends AbstractCommand {

    @Override
    public void execute() {
        System.out.println("Listing...");
        System.out.println("args count: " + args.length);
    }

}
