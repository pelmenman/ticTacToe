import java.sql.SQLOutput;
import java.util.*;

public class ticTacToeGame {
    private final String X = " X ";
    private final String O = " O ";
    String[][] table = new String[3][3];
    Scanner cin = new Scanner(System.in);
    Random random = new Random();
    public void start() {
        boolean winner = false;
        prepare();



        while(true) {
            getHumanStep();
            if (somebodyWin(X)) {
                System.out.println("You win");
                return;
            }

            if (!tableIsFull()) {
                System.out.println("Table is full! Game over");
                return;
            }

            getComputerStep();

            if (somebodyWin(O)) {
                System.out.println("Computer win");
                return;
            }
            if (!tableIsFull()) {
                System.out.println("Table is full! Game over");
                return;
            }
        }


    }

    private void prepare() {
        fillTable();
        printTable();
    }

    private void fillTable() {
        for(int i = 0; i < table.length; i++) {
            for(int j = 0; j < table.length; j++) {
                table[i][j] = "   ";
            }
        }
    }
    public void printTable() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                printCharacterAt(i, j);
            }
            if (i != 2) System.out.println("------------");
        }

        System.out.println();
    }

    private void printCharacterAt(int i, int j){
        switch (j) {
            case (0) -> System.out.print(table[i][j] + "|");
            case (2) -> System.out.print("|" + table[i][j] + "\n");
            default -> System.out.print(table[i][j]);
        }
    }

    private void getHumanStep() {
        int x, y;
        do {
            x = cin.nextInt() - 1;
            y = cin.nextInt() - 1;
        } while(coordsAreValid(x, y));

        table[x][y] = X;
        printTable();
    }
    private boolean coordsAreValid(int x, int y) {
        if (x < 0 || y < 0 || x > 3 || y > 3) {
            System.out.println("Incorrect coordinates. Choose another one");
            return true;
        } else {
            return !table[x][y].equals("   ");
        }
    }

    private void getComputerStep() {
        int x, y;
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while(!table[x][y].equals("   "));

        table[x][y] = O;
        printTable();
    }

    private boolean tableIsFull() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (table[i][j].equals("   ")) return true;
            }
        }
        return false;
    }

    private boolean somebodyWin(String sign) {
        for (int i = 0; i < table.length; i++) {

            if (table[i][0].equals(sign) && table[i][1].equals(sign) && table[i][2].equals(sign))
                return true;

            if (table[0][i].equals(sign) && table[1][i].equals(sign) && table[2][i].equals(sign))
                return true;

        }

        if (table[0][0].equals(sign) && table[1][1].equals(sign) && table[2][2].equals(sign))
            return true;

        if (table[0][2].equals(sign) && table[1][1].equals(sign) && table[2][0].equals(sign))
            return true;

        return false;

    }
    }
