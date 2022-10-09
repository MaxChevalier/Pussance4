import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GameManager {

    private Display display = new Display();
    private int[][] grid;

    public static void main(String[] args) throws Exception {
        System.out.println(Const.WELCOME_MESSAGE);
        GameManager gameManager = new GameManager();
        boolean isWantToPlay = true;
        while (isWantToPlay) {
            int nbtPlayer = 0;
            System.out.println("Combien de joueur voulez vous ? (2 ou 3)");
            BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));
            try {
                nbtPlayer = Integer.parseInt(brInput.readLine());
                try {
                    gameManager.Game(nbtPlayer);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            } catch (Exception e) {
                System.out.println("Une Erreur est survenue\nErreur : " + e.getMessage());
                return;
            }
            System.out.println("Voulez vous rejouer ? (oui/non)");
            switch (brInput.readLine()) {
                case "oui":
                    isWantToPlay = true;
                    break;
                case "non":
                    isWantToPlay = false;
                    break;
                default:
                    System.out.println("Entrée non reconnu\nnous considérons que vous ne voulez pas rejouer");
                    isWantToPlay = false;
                    break;
            }
        }
        System.out.println(Const.GOODBYE_MESSAGE);

    }

    private void Game(int nbtPlayer) {
        // génére la grille en fonction du nombre de joueur
        int width;
        int height;
        switch (nbtPlayer) {
            case 2:
                height = 6;
                width = 8;
                break;
            case 3:
                height = 10;
                width = 10;
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + nbtPlayer);
        }
        grid = display.GenerateGrid(8, 6);

        int turnPlayer = 1; // représante lejoeur qui doit jouer

        // répétition tant que la grille n'est pas remplie ou qu'un joueur n'a pas gagné
        // while (!IsFinish())
        while (true) {
            UserPlay(width, turnPlayer);
            turnPlayer = (turnPlayer % nbtPlayer) + 1; // passage au joueur suivant
        }

        // int winner = WhoWin();

        // switch (winner){
        // case 0:
        // System.out.println("Match nul");
        // break;
        // default:
        // System.out.println("Le joueur n°"+winner+" a gagné");
        // break;
        // }
    }

    private void UserPlay(int widht,int turnPlayer) {
        System.out.println("C'est au tour du joueur n°" + turnPlayer + " de jouer");
        display.DisplayGrid(grid);
        int position = display.Input(widht);
        for (int i = grid[position].length - 1; i >= 0; i--) {
            if (grid[position][i] == 0) {
                grid[position][i] = turnPlayer;
                break;
            }
        }
    }

}
