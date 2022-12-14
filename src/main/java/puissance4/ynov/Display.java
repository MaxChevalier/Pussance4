package puissance4.ynov;

import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * this class display all things for the user
 */
class Display implements User_Interface {

    /**
     * display the grid
     * 
     * @param grid the grid to display
     */
    public void DisplayGrid(int[][] grid) { // display the grid
        // ┌─┬─┬─┐
        // │1│4│7│
        // ├─┼─┼─┤
        // │2│5│8│
        // ├─┼─┼─┤
        // │3│6│9│
        // └─┴─┴─┘
        int col = grid.length;
        int row = grid[0].length;
        for (int i = 0; i <= col; i++) {
            if (i == 0) {
                System.out.print("┌");
            } else if (i == col) {
                System.out.print("─┐\n");
            } else {
                System.out.print("─┬");
            }
        }

        for (int j = 0; j < row; j++) {
            System.out.print("│");
            for (int k = 0; k < col; k++) {
                if (grid[k][j] != 0) {
                    String color = "";
                    switch (grid[k][j]) {
                        case 1:
                            color = "\u001B[95m";
                            break;
                        case 2:
                            color = "\u001B[34m";
                            break;
                        case 3:
                            color = "\u001B[32m";
                            break;
                        default:
                            color = "\u001B[0m";
                            break;
                    }
                    try {
                        System.out.print(color + "■");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                } else {
                    System.out.print(" ");
                }
                System.out.print("\u001B[0m│");

            }
            System.out.print("\n");
        }
        for (int i = 0; i <= col; i++) { 
            if (i == 0) {
                System.out.print("└");
            } else if (i != col) {
                System.out.print("─┴");
            } else {
                System.out.print("─┘\n");
            }
        }

    }

    /**
     * display all possible placement for the player to choose in function of the grid length
     * @param gridLength the grid lenght where the player can play
     */
    public void displayInputDemande(int gridLength) {
        String[] PossInput = { "a", "z", "e", "r", "t", "y", "u", "i", "o", "p", "q", "s", "d", "f", "g", "h", "j", "k",
                "l", "m", "w", "x", "c", "v", "b", "n" };
        System.out.print(" ");
        for (int i = 0; i < gridLength; i++) {
            System.out.print(PossInput[i] + " ");
        }
        System.out.println("\nou jouez vous ?");
    }

    /**
     * request the user where he want to play
     * 
     * @param gridLength the grid length where the player can play
     * 
     * @return the column where the user have played
     */
    public int Input(int gridLength) {
        String[] PossInput = { "a", "z", "e", "r", "t", "y", "u", "i", "o", "p", "q", "s", "d", "f", "g", "h", "j", "k",
                "l", "m", "w", "x", "c", "v", "b", "n", " " };
        String input = " ";
        while (Arrays.asList(PossInput).indexOf(input) >= gridLength) {
            this.displayInputDemande(gridLength);
            BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));
            try {
                input = brInput.readLine();
            } catch (Exception e) {
                System.out.println("Une Erreur est survenue\nErreur : " + e.getMessage() + "\n merci de ressayer");
                return Input(gridLength);
            }
        }
        return Arrays.asList(PossInput).indexOf(input);
    }


    /**
     * Generate a Grid in function of the grid length
     * @param width Grid width
     * @param height Grid height
     * 
     * @return the grid
     */
    public int[][] GenerateGrid(int width, int height) {
        int[][] grid = new int[width][height];
        return grid;
    }
}
