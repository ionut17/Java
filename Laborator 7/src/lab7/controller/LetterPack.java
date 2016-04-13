package lab7.controller;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Ionut
 */
public class LetterPack {

    private final int MAX_SIZE = 100;
    private Map<Tile, Integer> pack = new ConcurrentHashMap<>();
    private boolean available = false;

    /**
     * @return the MAX_SIZE
     */
    public int getMAX_SIZE() {
        return MAX_SIZE;
    }

    /**
     * @return the pack
     */
    public Map<Tile, Integer> getPack() {
        return pack;
    }

    /**
     * @param pack the pack to set
     */
    public void setPack(Map<Tile, Integer> pack) {
        this.pack = pack;
    }

    public int getPackSize() {
        int size = 0;
        for (Map.Entry<Tile, Integer> entry : pack.entrySet()) {
            size += entry.getValue();
        }
        return size;
    }

    public synchronized Tile extractLetter() {
//        while (!available) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        if (getPackSize() > 0) {
            Random rd = new Random();
            int ok = 0, index, k;
            while (ok == 0) {
                index = rd.nextInt(pack.size());
                k = 0;
                for (Map.Entry<Tile, Integer> entry : pack.entrySet()) {
                    if (k == index && entry.getValue() > 0) {
                        ok = 1;
                        entry.setValue(entry.getValue() - 1);
                        return entry.getKey();
                    }
                    k++;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Tile, Integer> entry : pack.entrySet()) {
            sb.append(entry.getValue()+"x ").append(entry.getKey().getLetter()).append("\n"); //.append(" (" + entry.getKey().getValue() + ")")
        }
        return sb.toString();
    }

}
