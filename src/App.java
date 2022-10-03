public class App {

    public static void main(String[] args) throws Exception {
        Display display = new Display();
        int[][] grid = display.GenerateGrid(7, 5);
        display.DisplayGrid(grid);
        int input = display.Input(grid.length);
        System.out.println(input);
    }
    
}
