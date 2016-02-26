package main.pkg;

import gl_square.pkg.GraecoLatinSquare;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Main app = new Main();

        int n;
        String[] S, T;

        //If arguments are missing, generate n, S and T
        if (args.length < 3) {
            Random randomGen = new Random();
            n = randomGen.nextInt(7)+3;
            S = new String[n];
            T = new String[n];
            for (int i = 0; i < n; i++) {
                S[i] = String.valueOf((char) ('A' + i));
                T[i] = String.valueOf((char) ('\u03B1' + i));
            }
        } else {
            n = Integer.parseInt(args[0]);
            S = args[1].split("");
            T = args[2].split("");
        }

        GraecoLatinSquare square = new GraecoLatinSquare();
        square.showExample();
        square.printTable(n,S,T);
    }
}
