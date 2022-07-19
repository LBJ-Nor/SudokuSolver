import java.util.Scanner;

public class Main {

    static int[][] board = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };

    public static void main(String[] args) {
        System.out.println("Solving...");
        solve();
        System.out.println("No more solutions!");
    }

    static boolean possible(int y, int x, int n) {
        // Check for n in row
        for (int i = 0; i < 9; i++) {
            if (board[y][i] == n) {
                return false;
            }
        }
        // Check for n in column
        for (int i = 0; i < 9; i++) {
            if (board[i][x] == n) {
                return false;
            }
        }
        // Check for n in box
        int xBox = (x / 3) * 3;
        int yBox = (y / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[yBox + i][xBox + j] == n) {
                    return false;
                }
            }
        }
        // true if n not found in row, column or box
        return true;
    }

    static void solve() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (board[y][x] == 0) {
                    for (int n = 1; n < 10; n++) {
                        if (possible(y, x, n)) {
                            board[y][x] = n; // Attempt possible number
                            solve(); // Recursion
                            board[y][x] = 0; // Backtrack
                        }
                    }
                    return;
                }
            }
        }
        displayBoard();
    }

    static void displayBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                System.out.printf("%2d", board[row][col]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Press enter for more: ");
        new Scanner(System.in).nextLine();
    }
}
