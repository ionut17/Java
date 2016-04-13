package lab7.controller;

import java.io.IOException;
import java.util.HashSet;

/**
 *
 * @author Ionut
 */
public class GameManager {

    public void start() throws IOException {
        LetterPack lp = new LetterPackGenerator().getScrabblePack();

        System.out.println(lp.toString());

        Dictionary d = Dictionary.getDictionary();
        HashSet<String> dt = d.getWords();

        Player p1 = new Player(lp, dt, "Ionut");
        Player p2 = new Player(lp, dt, "Anca");

        new Thread(p1).start();
        //new Thread(p2).start();
    }

}
