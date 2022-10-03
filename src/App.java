public class App {

    public static void main(String[] args) throws Exception {
        Display display = new Display();
        int[][] grid = display.GenerateGrid(7, 5);
        display.DisplayGrid(grid);
        int input = display.Input(3);
        System.out.println(input);
    }
    
}
