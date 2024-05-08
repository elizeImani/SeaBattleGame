import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Computer computer = new Computer();
        int steps = 0;

        System.out.println("Welcome to Battleship!");
        System.out.println("Here is the hidden board:");
        computer.printHiddenBoard();

        while (!computer.allShipsDestroyed()) {
            System.out.println("Your turn:");
            System.out.println("Enter coordinates to fire (e.g., A1, B3): ");
            String input = scanner.nextLine().toUpperCase();

            int x = input.charAt(0) - 'A';
            int y = Integer.parseInt(input.substring(1)) - 1;

            if (x < 0 || x >= Board.getBoardSize() || y < 0 || y >= Board.getBoardSize()) {
                System.out.println("Invalid coordinates. Try again.");
                continue;
            }

            computer.takeShot(x, y);
            computer.printHiddenBoard();
            steps++;
        }

        System.out.println("Congratulations! You destroyed all the opponent's ships in " + steps + " steps.");
    }
}
