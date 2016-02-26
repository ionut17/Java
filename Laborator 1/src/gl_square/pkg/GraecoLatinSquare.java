package gl_square.pkg;

public class GraecoLatinSquare {

    private int count = 0;

    public void printTable(int n, String[] S, String[] T) {
        int[][][] a = new int[2][n][n];

        System.out.format("Graeco-Latin square (length: %d)\n\n", n);
        if (n == 6 || n==0) {
            System.out.format("For n=%d there are no solutions\n",n);
        } else {
            if (n < 5) {
                //Backtracking with error checking for the both S and T matrixs
                generateTable(0, 0, a, S, T, 0);
            } else if (n % 2 == 1) {
                //Generating the S and T matrix for odd n with 2 shifts in opposite directions
                for (int row = 0; row < n; row++) {
                    for (int column = 0; column < n; column++) {
                        a[0][row][column] = (row + column) % n;
                        a[1][row][column] = (row * (n - 1) + column) % n;
                    }
                }
                System.out.println(toString(a, S, T));
            } else {
                generateTable(0, 0, a, S, T, 0);
            }

            //Printing results number after generating the table
            if (n < 5) {
                System.out.println("Results: " + count);
            } else {
                System.out.println("Results: a lot");
            }
        }
    }

    public void generateTable(int row, int col, int a[][][], String S[], String T[], int index) {
        int n = a[0][0].length;
        if (row == n) {
            if (index == 0) {
                generateTable(0, 0, a, S, T, 1);
            } else if (index == 1) {
                count++;
                if (count == 1) {
                    System.out.println(toString(a, S, T));
                }
            }
        } else {
            int currentLength = (index == 0 ? S.length : T.length);
            for (int i = 0; i < currentLength; i++) {
                a[index][row][col] = i;
                if (index == 0 || validatePair(a, row, col)) {
                    if (validatePosition(a, row, col, index)) {
                        if (col == n - 1) {
                            generateTable(row + 1, 0, a, S, T, index);
                        } else {
                            generateTable(row, col + 1, a, S, T, index);
                        }
                    }
                }
            }
        }
    }

    boolean validatePosition(int a[][][], int maxRow, int maxCol, int type) {
        int n = a[1][0].length;
        //Verify the matrix a only on the filled cells
        //Check the duplicates on rows
        for (int row = 0; row <= maxRow; row++) {
            //If the row is lower than the current row (parse all the row's columns)
            int lastCol = n;
            //If the row is equal parse only the columns lower than the current column
            if (row == maxRow) {
                lastCol = maxCol + 1;
            }
            for (int column = 0; column < lastCol; column++) {
                for (int otherColumn = 0; otherColumn < lastCol; otherColumn++) {
                    //Check if is not the same column
                    if (column != otherColumn) {
                        //Verifying if is the same value on two different columns
                        if (a[type][row][column] == a[type][row][otherColumn]) {
                            return false;
                        }
                    }
                }
            }
        }
        //Check the duplicates on columns
        for (int column = 0; column < n; column++) {
            for (int row = 0; row < maxRow; row++) {
                //Daca randul nu e ultimul rand facem comparatia (pentru ultimul rand nu mai are rost intrucat daca ar fi conflicte ar fi gasite mai repede)
                for (int otherRow = 0; otherRow <= maxRow; otherRow++) {
                    if (row != otherRow) {
                        //Daca se compara cu ultimul rand atunci avem grija sa comparam doar cu acele coloane completate
                        if (otherRow == maxRow && column < maxCol) {
                            if (a[type][row][column] == a[type][otherRow][column]) {
                                return false;
                            }
                        }//Else comparam cu toate coloanele
                        else if (otherRow != maxRow) {
                            if (a[type][row][column] == a[type][otherRow][column]) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    boolean validatePair(int a[][][], int maxRow, int maxCol) {
        int n = a[1][0].length;
        int[][] pairs = new int[n][n];
        //Verify the matrix a only on the filled cells
        //Check the duplicates on rows
        for (int row = 0; row <= maxRow; row++) {
            //If the row is lower than the current row (parse all the row's columns)
            int lastCol = n;
            //If the row is equal parse only the columns lower than the current column
            if (row == maxRow) {
                lastCol = maxCol + 1;
            }
            for (int column = 0; column < lastCol; column++) {
                pairs[a[0][row][column]][a[1][row][column]]++;
            }
        }
        for (int row = 0; row < n; row++) {
            for (int column = 0; column < n; column++) {
                if (pairs[row][column] > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public void showExample() {
        String[] S = {"A", "B", "C"};
        String[] T = {"\u03B1", "\u03B2", "\u03B3"};
        int[][][] a = {
            {{0, 1, 2}, {1, 2, 0}, {2, 0, 1}},
            {{0, 2, 1}, {1, 0, 2}, {2, 1, 0}}
        };

        System.out.println("Example");
        System.out.println(toString(a, S, T));
    }

    public String toString(int[][][] a, String[] S, String[] T) {
        int n = S.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(S[a[0][i][j]]).append(T[a[1][i][j]]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
