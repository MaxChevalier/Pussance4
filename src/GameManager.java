import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class GameManager{
    static void main(String[] args){

    }

    public static int Input(int gridLength){
        String[] PossInput = {"a","z","e","r","t","y","u","i","o","p","q","s","d","f","g","h","j","k","l","m","w","x","c","v","b","n"};
        String input = "";
        Display.displayInputDemande(gridLength);
        BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));
        try{
            input = brInput.readLine();
        }
        catch (Exception e){
            System.out.println("Une Erreur est survenue\nErreur : "+ e.getMessage() +"\n merci de ressayer");
            return Input(gridLength);
        }
        return Arrays.asList(PossInput).indexOf(input);
    }
}