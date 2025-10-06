import java.util.InputMismatchException;
import java.util.Scanner;

public class ConnectFourGame {
    private final Board board = new Board();
    private final char[] players = {'R', 'Y'}; // R = Red, Y = Yellow
    private int current = 0;

    public void start() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("=== Connect Four (2 Players) ===");
            System.out.println("Players: R (Red) goes first, then Y (Yellow).");
            System.out.println("Choose a column number (1-" + Board.COLS + ") to drop your disc.\n");

            while (true) {
                board.print();
                char disc = players[current];
                System.out.println("Player " + disc + ", it's your turn.");
                int col = promptForColumn(sc) - 1;

                int row = board.dropDisc(col, disc);
                if (row == -1) {
                    System.out.println("Invalid move (column full or out of range). Try again.\n");
                    continue;
                }

                if (board.hasConnectFourFrom(row, col, disc)) {
                    board.print();
                    System.out.println("Player " + disc + " WINS! 🎉");
                    break;
                }

                if (board.isFull()) {
                    board.print();
                    System.out.println("It's a TIE. No more moves available.");
                    break;
                }

                current = 1 - current; // switch
            }
        }
    }

    private int promptForColumn(Scanner sc) {
        while (true) {
            System.out.print("Column (1-" + Board.COLS + "): ");
            try {
                int value = sc.nextInt();
                if (value < 1 || value > Board.COLS) {
                    System.out.println("Please enter a number between 1 and " + Board.COLS + ".");
                } else {
                    return value;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a whole number.");
                sc.next();
            }
        }
    }
}
