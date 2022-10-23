package puissance4.ynov;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GameManager {

    private Display display = new Display();
    private int[][] grid;

    public static void GameInitialisater() throws Exception { // initialize the game
        GameManager gameManager = new GameManager();
        boolean isWantToPlay = true;
        while (isWantToPlay) {
            int nbtPlayer = 0;
            System.out.println("Combien de joueur voulez vous ? (2 ou 3)"); // ask the number of player
            BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));
            try {
                nbtPlayer = Integer.parseInt(brInput.readLine());
                try {
                    gameManager.Game(nbtPlayer); // start the game
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            } catch (Exception e) {
                System.out.println("Fatal error : " + e.getMessage()); // if the input is not a number
                return;
            }
            System.out.println("Voulez vous rejouer ? (oui/non)"); // ask if the player want to play again
            switch (brInput.readLine()) {
                case "oui":
                    isWantToPlay = true;
                    break;
                case "non":
                    isWantToPlay = false;
                    break;
                default:
                    System.out.println("Entrée non reconnu\nnous considérons que vous ne voulez pas rejouer"); // if the input is not oui or non
                    isWantToPlay = false;
                    break;
            }
        }
        System.out.println(Const.GOODBYE_MESSAGE);

    }




    private void Game(int nbtPlayer) { // start the game
        int width;
        int height;
        switch (nbtPlayer) { // generate the grid according to the number of players
            case 2:
                height = 6;
                width = 8;
                break;
            case 3:
                height = 10;
                width = 12;
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + nbtPlayer);
        }
        grid = display.GenerateGrid(width, height);

        int turnPlayer = 1; // representing the player who must play


        // repetition as long as the grid is not filled or a player has not won
        while (!GridVerif.IsFinish(grid)){
            try{
                System.out.println("\033[H\033[2J");//delete the console
                UserPlay(width, turnPlayer);
                turnPlayer = (turnPlayer % nbtPlayer) + 1; // change the player who must play
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        
        try{
            display.DisplayGrid(grid);
            int winner = GridVerif.WhoWin(grid); // get the winner
            switch (winner){
                case 0:
                    System.out.println("Match nul"); // if there is no winner
                    break;
                default:
                    System.out.println("Le joueur n°"+winner+" a gagné"); // display the winner
                    break;
                }
        }catch (Exception e){
            System.err.println("Fatal Error : "+e.getMessage());
        }
        
    }

    private void UserPlay(int widht, int turnPlayer) { // ask the player where he wants to play
        System.out.println("C'est au tour du joueur n°" + turnPlayer + " de jouer");
        display.DisplayGrid(grid);
        int position = display.Input(widht); // get the position where the player want to play
        for (int i = grid[position].length - 1; i >= 0; i--) {
            if (grid[position][i] == 0) {
                grid[position][i] = turnPlayer;
                return;
            }
        }
        throw new IllegalArgumentException("La colonne est pleine"); // if the column is full
    }
}