package tictactoe;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "_________";
        char[][] gameGrid = makeGameGrid(input);
        displayGame(gameGrid);
        while (!isWinner(gameGrid, 'X') & !isWinner(gameGrid, 'O')) {
            System.out.println("Enter the coordinates:");
            checkCoordinates(gameGrid, scanner.nextInt(), scanner.nextInt());
            if (isDraw(gameGrid)) {
                break;
            }
        }
        printStatus(gameGridtoString(gameGrid));
    }
    public static int counter = 0;
    public static void displayGame(char[][] input) {
        System.out.println("---------");
        for (int j = 0; j < 3; j++) {
        System.out.print("| ");
        for (int i = 0; i < 3; i++) {
            System.out.print(input[j][i]);
            System.out.print(" ");
        }
        System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }
    public static void checkCoordinates(char[][] gameGrid, int x, int y) {
        Scanner scanner = new Scanner(System.in);
        if (x <= 3 && x >= 1 && y <= 3 && y >= 1) {
            if (gameGrid[x-1][y-1] == '_') {
                counter++;
                if (counter % 2 == 0) {
                    enterCoordinatesO(gameGrid, x, y);
                    displayGame(gameGrid);
                } else {
                    enterCoordinatesX(gameGrid, x, y);
                    displayGame(gameGrid);
                }
            } else {
                System.out.println("This cell is occupied! Choose another one!");
                checkCoordinates(gameGrid, scanner.nextInt(), scanner.nextInt());
            }
        } else {
            System.out.println("Coordinates should be from 1 to 3!");
            checkCoordinates(gameGrid, scanner.nextInt(), scanner.nextInt());
        }
    }

    public static void enterCoordinatesX(char[][] input, int x, int y) {
        input[x-1][y-1] = 'X';
    }

    public static void enterCoordinatesO(char[][] input, int x, int y) {
        input[x-1][y-1] = 'O';
    }

    public static void printStatus(String input) {
        int Xs = getNumberOfChar(input, "X");
        int Os = getNumberOfChar(input, "O");
        int emptySpaces = getNumberOfChar(input, "_");

        if (isImpossible(Xs, Os) || isWinner(makeGameGrid(input), 'O') && isWinner(makeGameGrid(input), 'X')) {
            System.out.println("Impossible");
        } else if (isWinner(makeGameGrid(input), 'X')) {
                System.out.println("X wins");
        } else if (isWinner(makeGameGrid(input), 'O')) {
            System.out.println("O wins");
        } else if (Xs + Os == 9) {
            System.out.println("Draw");
        } else {
            System.out.println("Game not finished");
        }
    }

    public static char[][] makeGameGrid(String input) {
        char[][] gameGrid = new char[3][3];
        char[] inputArray = input.toCharArray();
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameGrid[i][j] = inputArray[counter];
                counter++;
            }
        }
        return gameGrid;
    }

    public static boolean isWinner(char[][] inputArray, char player) {
        int row1 = 0, row2 = 0, row3 = 0, col1 = 0, col2 = 0, col3 = 0, diag1 = 0, diag2 = 0;
        for (int i = 0; i < 3; i++) {
            row1 += inputArray[0][i];
            row2 += inputArray[1][i];
            row3 += inputArray[2][i];
            col1 += inputArray[i][0];
            col2 += inputArray[i][1];
            col3 += inputArray[i][2];
            diag1 += inputArray[i][i];
            diag2 += inputArray[i][2 - i];
        }
        return row1 == player * 3 || row2 == player * 3 || row3 == player * 3 || col1 == player * 3
                || col2 == player * 3 || col3 == player * 3  || diag1 == player * 3 || diag2 == player * 3;
    }

    public static boolean isImpossible(int x, int o) {
        return Math.abs(x - o) > 1;
    }

    public static boolean isDraw(char[][] gameGrid) {
        int Xs = 0;
        int Os = 0;
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (gameGrid[j][i] == 'X') {
                    Xs++;
                } else if (gameGrid[j][i] == 'O') {
                    Os++;
                }
            }
        }
        return Xs + Os == 9;
    }

    public static int getNumberOfChar(String input, String charToCount) {
        return input.length() - input.replace(charToCount, "").length();
    }

    public static String gameGridtoString(char[][] gameGrid) {
        StringBuilder str = new StringBuilder();
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                str.append(gameGrid[j][i]);
            }
        }
        return str.toString();
    }
}

