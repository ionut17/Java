package lab8.server.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Ionut
 */
public class LetterPackGenerator {
    
    int[] distribution = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
    int[] value = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};

    public LetterPack getScrabblePack() {
        LetterPack lp = new LetterPack();

        Map<Tile, Integer> pack = new ConcurrentHashMap<>();
        
//        int[] value = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
//        int[] distribution = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};

        int k = 0;
//        pack.put(new Tile('@',0),2);
        for (char c = 'A'; c <= 'Z'; c++, k++) {
            pack.put(new Tile(c,value[k]), distribution[k]);
        }

        lp.setPack(pack);
        return lp;
    }
    
    public int valueOf(char c){
        return value[(int)(c-'A')];
    }

}
