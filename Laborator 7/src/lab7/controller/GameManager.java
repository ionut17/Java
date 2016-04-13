package lab7.controller;

/**
 *
 * @author Ionut
 */
public class GameManager {
    
    public void start(){
        LetterPack lp = new LetterPackGenerator().getScrabblePack();
        
        System.out.println(lp.toString());
        
        Player p1 = new Player(lp,"Ionut");
        Player p2 = new Player(lp,"Anca");
        
        new Thread(p1).start();
        new Thread(p2).start();
    }
    
}
