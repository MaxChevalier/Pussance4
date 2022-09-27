public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
    public class grille {
        int[][] grille = new int[6][7];
        public grille() {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    grille[i][j] = 0;
                }
            }
        }
        public void afficher() {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    System.out.print(grille[i][j]);
                }
                System.out.println();
            }
        }
    }
}
