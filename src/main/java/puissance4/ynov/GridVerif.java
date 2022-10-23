package puissance4.ynov;

public class GridVerif {
    public static boolean IsFinish(int[][] grid) {
        // function that checks if the grid is filled or if a player has won
        try {
            if (WhoWin(grid) != 0) {
                return true;
            }
            for (int j = 0; j < grid.length; j++) {
                if (grid[j][0] == 0) {
                    return false;
                }
            }
            return true;
        }catch (Exception e){
            return true;
        }
        
    }

    public static int WhoWin(int[][] grid) {
        // function that returns an integer to find out who won (return 0 if tied)
        try {
            GridVerif gridVerif = new GridVerif(grid);
            if (gridVerif.CheckLine() != 0) {
                return gridVerif.CheckLine();
            }
            if (gridVerif.CheckColumn() != 0) {
                return gridVerif.CheckColumn();
            }
            if (gridVerif.CheckDiagonal() != 0) {
                return gridVerif.CheckDiagonal();
            }
            return 0;
        } catch (Exception e) {
            throw e;
        }

    }

    private int[][] grid;

    /**
     * @param _grid the grid must have a min size of 4x4
     */
    private GridVerif(int[][] _grid) {
        if (_grid.length < 4 || _grid[0].length < 4) {
            throw new IllegalArgumentException("La taille de la grille n'est pas valide");
        }
        this.grid = _grid;
    }

    private int CheckLine() {
        // function that checks if the line is won by a player and returns the identifier of the winning player (return 0 if no player has won)
        for (int i = 0; i < this.grid.length - 3; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                if (this.grid[i][j] == this.grid[i + 1][j] && this.grid[i][j] == this.grid[i + 2][j]
                        && this.grid[i][j] == this.grid[i + 3][j] && this.grid[i][j] != 0) {
                    return this.grid[i][j];
                }
            }
        }
        return 0;
    }

    private int CheckColumn() {
        // function that checks if the column is won by a player and returns the id of the winning player (return 0 if no player has won)
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length - 3; j++) {
                if (this.grid[i][j] == this.grid[i][j + 1] && this.grid[i][j] == this.grid[i][j + 2]
                        && this.grid[i][j] == this.grid[i][j + 3] && this.grid[i][j] != 0) {
                    return this.grid[i][j];
                }
            }
        }
        return 0;
    }

    private int CheckDiagonal() {
        // function that checks if the diagonal is won by a player and returns the id of the winning player (return 0 if no player has won)
        for (int i = 0; i < this.grid.length - 3; i++) {
            for (int j = 0; j < this.grid[i].length - 3; j++) {
                if (this.grid[i][j] == this.grid[i + 1][j + 1] && this.grid[i][j] == this.grid[i + 2][j + 2]
                        && this.grid[i][j] == this.grid[i + 3][j + 3] && this.grid[i][j] != 0) {
                    return this.grid[i][j];
                }
            }
        }
        for (int i = 0; i < this.grid.length - 3; i++) {
            for (int j = 3; j < this.grid[i].length; j++) {
                if (this.grid[i][j] == this.grid[i + 1][j - 1] && this.grid[i][j] == this.grid[i + 2][j - 2]
                        && this.grid[i][j] == this.grid[i + 3][j - 3] && this.grid[i][j] != 0) {
                    return this.grid[i][j];
                }
            }
        }
        return 0;
    }
}