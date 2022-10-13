public class GridVerif {
    public static void main(String[] args) {
        int[][] grid = 
        {{0,0,0,0,1},
        {0,0,0,1,0},
        {0,0,1,0,0},
        {0,1,0,0,0}};
        GridVerif gv = new GridVerif(grid);
        System.out.println(gv.CheckDiagonal());
    }
    public static boolean IsFinish(int[][] grid){
        // 
        for (int[] row : grid) {
            for (int cell : row) {
                if (cell == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] grid;

    private GridVerif(int[][] _grid){
        this.grid = _grid;
    }

    //private int CheckLine(){
        // TODO fonction qui vérifie si la ligne est gagné par un joueur et retourne l'identifiant du joueur gagnant (return 0 si aucun joueur n'a gagné)
    //}

    //private int CheckColumn(){
        // TODO fonction qui vérifie si la colonne est gagné par un joueur et retourne l'identifiant du joueur gagnant (return 0 si aucun joueur n'a gagné)
    //}

    private int CheckDiagonal(){
        //function that checks if the diagonal is won by a player and returns the id of the winning player (return 0 if no player has won)
        int idplayer = 0;
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0){
                    idplayer = grid[i][j];
                    count = 1;
                    for (int k = 1; k < 4;k++) {
                        if (i+k < grid.length && j+k < grid[i].length){
                            if (grid[i+k][j+k] == idplayer){
                                count++;
                            }
                            else{
                                break;
                            }
                        }
                        else{
                            break;
                        }
                    }
                    if (count == 4){
                        return idplayer;
                    }
                }
            }
        }
        return 0;
    }

    //  public static int WhoWin(int[][] grid){
    //     // fonction qui vérifie si un joueur a gagné et retourne l'identifiant du joueur gagnant (return 0 si aucun joueur n'a gagné)
    //         CheckDiagonal();
    //         CheckLine();
    //         CheckColumn(   );
    //         return 0;
    //     }
}