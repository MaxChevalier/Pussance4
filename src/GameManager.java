// import java.io.BufferedReader;
// import java.io.InputStreamReader;

// public class GameManager {

//     private Display display = new Display();
//     private int[][] grid;

//     public static void main(String[] args) throws Exception {
//         System.out.println(Const.WELCOME_MESSAGE);
//         GameManager gameManager = new GameManager();
//         boolean isWantToPlay = true;
//         while (isWantToPlay){ 
//             int nbtPlayer = 0;
//             System.out.println("Combien de joueur voulez vous ? (2 ou 3)");
//             BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));
//             try {
//                 nbtPlayer = Integer.parseInt(brInput.readLine());
//                 try{
//                     gameManager.Game(nbtPlayer);
//                 }
//                 catch (Exception e){
//                     System.err.println(e.getMessage());
//                 }
//             }
//             catch (Exception e){
//                 System.out.println("Une Erreur est survenue\nErreur : "+ e.getMessage());
//                 return;
//             }
//             System.out.println("Voulez vous rejouer ? (oui/non)");
//             switch (brInput.readLine()) {
//                 case "oui":
//                     isWantToPlay = true;
//                     break;
//                 case "non":
//                     isWantToPlay = false;
//                     break;
//                 default:
//                     System.out.println("Entrée non reconnu\nnous considérons que vous ne voulez pas rejouer");
//                     isWantToPlay = false;
//                     break;
//             }
//         }
//         System.out.println(Const.GOODBYE_MESSAGE);
        
//     }

//     private void Game(int nbtPlayer){
//         // génére la grille en fonction du nombre de joueur
//         switch (nbtPlayer) {
//             case 2:
//                 grid = display.GenerateGrid(8,6);
//                 break;
//             case 3:
//                 grid = display.GenerateGrid(12,10);
//                 break;
//             default:
//                 throw new IllegalArgumentException("Unexpected value: " + nbtPlayer);
//         }

//         int turnPlayer = 1; // représante lejoeur qui doit jouer

//         // répétition tant que la grille n'est pas remplie ou qu'un joueur n'a pas gagné
//         while (!IsFinish())
//         {
//             UserPlay(turnPlayer);
//             turnPlayer = (turnPlayer%nbtPlayer)+1; // passage au joueur suivant
//         }

//         int winner = WhoWin();

//         switch (winner){
//             case 0:
//                 System.out.println("Match nul");
//                 break;
//             default:
//                 System.out.println("Le joueur n°"+winner+" a gagné");
//                 break;
//         }
//     }

//     private void UserPlay(int turnPlayer){
//         // TODO créer la fonction pour permettre au joueur n°turnPlayer de jouer
//     }
    
// }
