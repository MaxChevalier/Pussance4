public class GridVerif {
    // public static boolean IsFinish(int[][] grid){
    //     // TODO fonction qui vérifie si la grille est remplie ou si un joueur a gagné
    // }

    // public static int WhoWin(int[][] grid){
    //     // TODO fonction qui retourne un entier pour savoir qui a gagné (return 0 si égalité)

    // }

    private int[][] grid;

    private GridVerif(int[][] _grid){
        this.grid = _grid;
    }

    public static void test(int[][] _grid){
        GridVerif gridVerif = new GridVerif(_grid);
        System.out.println(gridVerif.CheckLine());
        System.out.println(gridVerif.CheckColumn());
    }

    public static void main(String[] args){
        int[][] grid = {{1,1,1,1},{0,0,1,0},{0,0,1,0},{0,0,2,0},{0,0,1,0}};
        GridVerif.test(grid);
    }

    private int CheckLine(){
        int[] infocheck = {0,0}; // player, nbt a la suite
        // TODO fonction qui vérifie si la ligne est gagné par un joueur et retourne l'identifiant du joueur gagnant (return 0 si aucun joueur n'a gagné)
        for(int i = 0 ; i < this.grid.length ; i++){
            for(int j = 0 ; j < this.grid[i].length ; j++){
                if(this.grid[i][j] == infocheck[0]){
                    if (infocheck[1] == 3){
                        return infocheck[0];
                    }else{
                        infocheck[1]++;
                    }
                }
                else{
                    infocheck[0] = this.grid[i][j];
                    infocheck[1] = 1;
                }
            }
           
        }
        return 0;

    }

    private int CheckColumn(){
        // TODO fonction qui vérifie si la colonne est gagné par un joueur et retourne l'identifiant du joueur gagnant (return 0 si aucun joueur n'a gagné)
        int[] infocheck = {0,0}; // player, nbt a la suite
        for(int i = 0 ; i < this.grid.length ; i++){
            for(int j = 0 ; j < this.grid[i].length ; j++){
                if(this.grid[j][i] == infocheck[0]){
                    if (infocheck[1] == 3){
                        return infocheck[0];
                    }else{
                        infocheck[1]++;
                    }
                }
                else{
                    infocheck[0] = this.grid[j][i];
                    infocheck[1] = 1;
                }
            }
           
        }
        return 0;
    }

    // private int CheckDiagonal(){
    //     // TODO fonction qui vérifie si la diagonale est gagné par un joueur et retourne l'identifiant du joueur gagnant (return 0 si aucun joueur n'a gagné)
    // }
}
