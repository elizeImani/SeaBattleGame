public class Board {
    private static final int BOARD_SIZE = 8;
    private Cell[][] cells;

    public Board() {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public boolean allShipsDestroyed() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (cells[i][j].getStatus() == CellStatus.SHIP && !cells[i][j].isShot()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void display(boolean showShips) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (showShips || cells[i][j].getStatus() != CellStatus.SHIP) {
                    System.out.print(cells[i][j].getStatus().name().charAt(0) + " ");
                } else {
                    System.out.print("~ ");
                }
            }
            System.out.println();
        }
    }

    public static int getBoardSize() {
        return BOARD_SIZE;
    }
}
