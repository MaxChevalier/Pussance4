import java.util.Arrays;

class Display {


    public static void DisplayGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print("|"+ grid[j][i] + "|");
            }
            System.out.println();
        }
    }
    
    public static void displayInputDemande(int gridLength) {
        String[] PossInput = {"a","z","e","r","t","y","u","i","o","p","q","s","d","f","g","h","j","k","l","m","w","x","c","v","b","n"};
        System.out.println(Arrays.copyOfRange(PossInput, 0, gridLength));
        System.out.println("ou joué vous ?");
    }


}
