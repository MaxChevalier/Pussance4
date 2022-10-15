package puissance4.ynov;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class App {
    public static void main(String[] args) { // allows you to choose in which mode to launch the game
        try {
            System.out.println(Const.WELCOME_MESSAGE);
            String input = "";
            while (true) {
                System.out.println("Voulez-vous jouer en local ou en ligne ?");
                System.out.println("1 - Local");
                System.out.println("2 - En ligne");
                input = new BufferedReader(new InputStreamReader(System.in)).readLine();
                switch (input) {
                    case "1":
                        GameManager.GameInitialisater(); // start local game
                        return;
                    case "2":
                        OnligneSetup();// start online game
                        break;
                    default:
                        System.out.println("Entrée non reconnu"); // if input is not 1 or 2
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void OnligneSetup() { // lets create a server or connect to a server
        try {
            String input = "";
            while (true) {
                System.out.println("1 - Héberger une partie");
                System.out.println("2 - Rejoindre une partie");
                input = new BufferedReader(new InputStreamReader(System.in)).readLine();
                switch (input) {
                    case "1":
                        Server server = new Server();
                        server.launch();
                        return;
                    case "2":
                        // System.out.println("Adresse IP de la partie : ");
                        // input = new BufferedReader(new InputStreamReader(System.in)).readLine();
                        input = "";
                        new Client(InetAddress.getByName(input));
                        break;
                    default:
                        System.out.println("Entrée non2 reconnu");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}