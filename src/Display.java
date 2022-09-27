class Display {

    public static void main(String[] args) throws Exception {
        int[][] grid = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
        Display.DisplayGrid(grid);
    }

    public static void DisplayGrid(int[][] grid) {
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
                    System.out.print(grid[k][j]+"│");
                } 
            System.out.print("\n");
        }

        // for (int i = 0; i <= row ; i++){
        // if(i %2 == 1){ //impaire
        // for (int j = 0; j <= col*2; j++) {
        // if(j == 0){
        // System.out.print("│");
        // }
        // else if (j != col){
        // System.out.print(grid[j/2][i]+"│");
        // } else {
        // System.out.print(grid[j/2][i]+"│\n");
        // }
        // }
        // } else {
        // for (int j = 0; j <= col; j++) {
        // if(j == 0){
        // System.out.print("├");
        // }
        // else if (j != col){
        // System.out.print("─┼");
        // } else {
        // System.out.print("─┤\n");
        // }
        // }
        // }
        // }
        for (int i = 0; i <= col; i++) {
            if (i == 0) {
                System.out.print("└");
            } else if (i != col) {
                System.out.print("─┴");
            } else {
                System.out.print("─┘");
            }
        }

    }
}
