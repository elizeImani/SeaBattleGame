import java.util.Random;

public class Computer {
    private Board computerBoard;

    public Computer() {
        computerBoard = new Board();
        placeShips();
    }

    public void printHiddenBoard() {
        System.out.println("Computer's Board (Hidden):");
        for (int i = 0; i < Board.getBoardSize(); i++) {
            for (int j = 0; j < Board.getBoardSize(); j++) {
                Cell cell = computerBoard.getCell(i, j);
                if (cell.isShot()) {
                    System.out.print(cell.getStatus() == CellStatus.HIT ? "X " : "O ");
                } else {
                    System.out.print("~ ");
                }
            }
            System.out.println();
        }
    }



    private void placeShips() {
        // Place ships according to the existing matrix board
        String[] boardMatrix = {
                "XXXXOOOX",
                "OOOOOXOX",
                "OXOOOOOX",
                "OXOOXXOO",
                "OOOOOOOO",
                "OOXOXXXO",
                "OOXOOOOO",
                "XOOOXOXO"
        };

        for (int i = 0; i < Board.getBoardSize(); i++) {
            for (int j = 0; j < Board.getBoardSize(); j++) {
                if (boardMatrix[i].charAt(j) == 'X') {
                    computerBoard.getCell(i, j).setStatus(CellStatus.SHIP);
                }
            }
        }
    }

    public boolean allShipsDestroyed() {
        return computerBoard.allShipsDestroyed();
    }


    public void takeShot(int x, int y) {
        Cell cell = computerBoard.getCell(x, y);
        if (cell.isShot()) {
            System.out.println("You already shot at this location.");
            return;
        }
        cell.setShot(true);
        if (cell.getStatus() == CellStatus.SHIP) {
            cell.setStatus(CellStatus.HIT);
            System.out.println("You hit a ship!");

            // Check if the ship is fully hit
            if (isShipFullyHit(x, y)) {
                markSurroundingCells(x, y);
            }
        } else {
            cell.setStatus(CellStatus.MISS);
            System.out.println("You missed!");
        }

        if (isShipFullyHit(x, y)) {
            markSurroundingCells(x, y);
        }
    }

    // Within the Computer class
    private ShipOrientation getShipOrientation(int x, int y) {
        // Check horizontally
        int leftX = x;
        while (leftX > 0 && computerBoard.getCell(leftX - 1, y).getStatus() == CellStatus.HIT) {
            leftX--;
        }
        int rightX = x;
        while (rightX < Board.getBoardSize() - 1 && computerBoard.getCell(rightX + 1, y).getStatus() == CellStatus.HIT) {
            rightX++;
        }
        if (rightX - leftX + 1 > 1) {
            return ShipOrientation.HORIZONTAL;
        }
        // Check vertically
        int topY = y;
        while (topY > 0 && computerBoard.getCell(x, topY - 1).getStatus() == CellStatus.HIT) {
            topY--;
        }
        int bottomY = y;
        while (bottomY < Board.getBoardSize() - 1 && computerBoard.getCell(x, bottomY + 1).getStatus() == CellStatus.HIT) {
            bottomY++;
        }
        if (bottomY - topY + 1 > 1) {
            return ShipOrientation.VERTICAL;
        }
        return null; // Ship is 1x1
    }

    // Within the Computer class
    private boolean isShipFullyHit(int x, int y) {
        // Get the ship's orientation
        ShipOrientation orientation = getShipOrientation(x, y);
        // Get the ship's starting position
        int startX = x;
        int startY = y;
        while (startX > 0 && computerBoard.getCell(startX - 1, y).getStatus() == CellStatus.HIT) {
            startX--;
        }
        while (startY > 0 && computerBoard.getCell(x, startY - 1).getStatus() == CellStatus.HIT) {
            startY--;
        }
        // Determine the ship's size
        int size = 0;
        if (orientation == ShipOrientation.HORIZONTAL) {
            while (startX + size < Board.getBoardSize() && computerBoard.getCell(startX + size, y).getStatus() == CellStatus.HIT) {
                size++;
            }
        } else {
            while (startY + size < Board.getBoardSize() && computerBoard.getCell(x, startY + size).getStatus() == CellStatus.HIT) {
                size++;
            }
        }
        // Check if all cells of the ship are hit
        for (int i = 0; i < size; i++) {
            if (orientation == ShipOrientation.HORIZONTAL) {
                if (computerBoard.getCell(startX + i, y).getStatus() != CellStatus.HIT) {
                    return false;
                }
            } else {
                if (computerBoard.getCell(x, startY + i).getStatus() != CellStatus.HIT) {
                    return false;
                }
            }
        }
        return true;
    }
    private void markSurroundingCells(int x, int y) {
        // Mark surrounding cells with "O"
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1}; // Directions for adjacent cells (left and right diagonals, left and right, up and down)
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1}; // Directions for adjacent cells (left and right diagonals, left and right, up and down)

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // Check if the cell is within the board boundaries
            if (nx >= 0 && nx < Board.getBoardSize() && ny >= 0 && ny < Board.getBoardSize()) {
                Cell cell = computerBoard.getCell(nx, ny);
                // Mark the cell as 'O' if it's empty
                if (cell.getStatus() == CellStatus.EMPTY) {
                    cell.setStatus(CellStatus.MISS);
                }
            }
        }
    }


}
