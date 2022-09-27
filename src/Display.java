class Display {


    public static void DisplayGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print("|"+ grid[j][i] + "|");
            }
            System.out.println();
        }
    }
}
