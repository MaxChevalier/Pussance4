class Display {

    public static void main(String[] args) throws Exception {
        int[][] grid = new int[][] { { 0, 1, 1 }, { 0, 0, 0 }, { 0, 1, 1 }, { 0, 1, 1 } };
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
                    if(grid[k][j] != 0){
                        String color ="";
                        switch (grid[k][j]) {
                            case 1:
                                color = "\u001B[95m";
                                break;
                            default:
                            color = "\u001B[0m";
                                break;
                        }
                        System.out.print(color + "■");
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
                System.out.print("─┘");
            }
        }

    }
}
