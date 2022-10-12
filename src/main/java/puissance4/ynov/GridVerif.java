package puissance4.ynov;

public class GridVerif {
    public static boolean IsFinish(int[][] grid){
        // TODO fonction qui vérifie si la grille est remplie ou si un joueur a gagné
        if (WhoWin(grid)!=0){
            return true;
        }
        System.out.println("bonjour");
        for (int j = 0; j < grid.length; j++) {
            if (grid[j][0]==0){
                return false;
            }
        }
        return true;
    }

    public static int WhoWin(int[][] grid){
        // TODO fonction qui retourne un entier pour savoir qui a gagné (return 0 si égalité)
        GridVerif gridVerif = new GridVerif(grid);
        System.out.println(gridVerif.CheckLine());
        System.out.println(gridVerif.CheckColumn());
        System.out.println(gridVerif.CheckDiagonal());
        if (gridVerif.CheckLine()!=0){
            return gridVerif.CheckLine();
        }
        if (gridVerif.CheckColumn()!=0){
            return gridVerif.CheckColumn();
        }
        if (gridVerif.CheckDiagonal()!=0){
            return gridVerif.CheckDiagonal();
        }
        return 0;
    }

    private int[][] grid;

    public GridVerif(int[][] _grid){
        this.grid = _grid;
    }

    public int CheckLine(){
        // TODO fonction qui vérifie si la ligne est gagné par un joueur et retourne l'identifiant du joueur gagnant (return 0 si aucun joueur n'a gagné)
        for(int i = 0 ; i < this.grid.length ; i++){
            for(int j = 0 ; j < this.grid[i].length ; j++){
                if(this.grid[i][j] == this.grid[i+1][j] && this.grid[i][j] == this.grid[i+2][j] && this.grid[i][j] == this.grid[i+3][j]){
                    return this.grid[i][j];
                }     
            }
        }
        return 0;
    }

    public int CheckColumn(){
        // TODO fonction qui vérifie si la colonne est gagné par un joueur et retourne l'identifiant du joueur gagnant (return 0 si aucun joueur n'a gagné)
        for(int i = 0 ; i < this.grid.length ; i++){
            for(int j = 0 ; j < this.grid[i].length ; j++){
                if(this.grid[i][j] == this.grid[i][j+1] && this.grid[i][j] == this.grid[i][j+2] && this.grid[i][j] == this.grid[i][j+3]){
                    return this.grid[i][j];
                }     
            }
        }
        return 0;
    }

    public int CheckDiagonal(){
        for (int i = 0 ; i < this.grid.length-3 ; i++){
            for (int j = 0 ; j < this.grid[i].length-3 ; j++){
                if (this.grid[i][j] == this.grid[i+1][j+1] && this.grid[i][j] == this.grid[i+2][j+2] && this.grid[i][j] == this.grid[i+3][j+3]){
                    return this.grid[i][j];
                }
                if (this.grid[i][j+3] == this.grid[i+1][j+2] && this.grid[i][j+3] == this.grid[i+2][j+1] && this.grid[i][j+3] == this.grid[i+3][j]){
                    return this.grid[i][j+3];
                }
            }
        }
        return 0;
    }
}
