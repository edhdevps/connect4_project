public class Board {
    public static final int ROWS = 6;
    public static final int COLS = 7;
    private static final char EMPTY = ' ';
    private final char[][] grid = new char[ROWS][COLS];

    public Board() {
        clear();
    }

    public void clear() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                grid[r][c] = EMPTY;
            }
        }
    }

    public boolean isColumnInRange(int col) {
        return col >= 0 && col < COLS;
    }

    public boolean isColumnFull(int col) {
        return grid[0][col] != EMPTY;
    }

    /**
     * Drops a disc into the given column. Returns the row index where it landed, or -1 if move invalid.
     */
    public int dropDisc(int col, char disc) {
        if (!isColumnInRange(col) || isColumnFull(col)) return -1;
        for (int r = ROWS - 1; r >= 0; r--) {
            if (grid[r][col] == EMPTY) {
                grid[r][col] = disc;
                return r;
            }
        }
        return -1;
    }

    public boolean isFull() {
        for (int c = 0; c < COLS; c++) {
            if (!isColumnFull(c)) return false;
        }
        return true;
    }

    public char getCell(int r, int c) {
        return grid[r][c];
    }

    public void print() {
        System.out.println();
        System.out.print("   ");
        for (int c = 1; c <= COLS; c++) System.out.print(" " + c + "  ");
        System.out.println();
        for (int r = 0; r < ROWS; r++) {
            System.out.print(" | ");
            for (int c = 0; c < COLS; c++) {
                System.out.print(grid[r][c] == EMPTY ? "." : grid[r][c]);
                System.out.print(" | ");
            }
            System.out.println();
        }
        System.out.print(" +");
        for (int c = 0; c < COLS; c++) System.out.print("---+");
        System.out.println("\n");
    }

    public boolean hasConnectFourFrom(int row, int col, char disc) {
        int[][] directions = {
            {0, 1},  // horizontal ->
            {1, 0},  // vertical down
            {1, 1},  // diag down-right
            {1, -1}  // diag down-left
        };

        for (int[] d : directions) {
            int dr = d[0], dc = d[1];
            int count = 1;
            count += countDirection(row, col, dr, dc, disc);
            count += countDirection(row, col, -dr, -dc, disc);
            if (count >= 4) return true;
        }
        return false;
    }

    private int countDirection(int row, int col, int dr, int dc, char disc) {
        int r = row + dr, c = col + dc, count = 0;
        while (r >= 0 && r < ROWS && c >= 0 && c < COLS && grid[r][c] == disc) {
            count++;
            r += dr;
            c += dc;
        }
        return count;
    }
}
