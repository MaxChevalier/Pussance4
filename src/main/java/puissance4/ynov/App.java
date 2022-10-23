package puissance4.ynov;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

/*
 *  create a plugin for the server and chose the number of players and option for the game
 */
public class App {
    /**
     * this method start the plugin and display the welcome message
     */
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
/** 
* this method allows you to choose the type of game you want to play
*/
    private static void OnligneSetup() { // lets create a server or connect to a server
        try {
            String input = "";
            while (true) {
                System.out.println("1 - Héberger une partie");
                System.out.println("2 - Rejoindre une partie");
                input = new BufferedReader(new InputStreamReader(System.in)).readLine();
                switch (input) {
                    case "1":
                        input = "0";
                        while (!(input.equals("2") || input.equals("3"))) {
                            System.out.println("Combien de joueur voulez vous ? (2 ou 3)");
                            input = (new BufferedReader(new InputStreamReader(System.in)).readLine()).trim();
                            switch (input) {
                                case "2":
                                    Server server = new Server(2);
                                    Thread thread = new Thread(server);
                                    thread.start();
                                    new Client(InetAddress.getByName("localhost"));
                                    server.stop();
                                    try{
                                        thread.interrupt();
                                    } catch (Exception e) {}
                                    
                                    break;
                                case "3":
                                server = new Server(3);
                                    thread = new Thread(server);
                                    thread.start();
                                    new Client(InetAddress.getByName("localhost"));
                                    server.stop();
                                    break;
                                default:
                                    System.out.println("Entrée non reconnu");
                                    break;
                            }
                        }
                        break;
                        
                    case "2":
                        System.out.println("Adresse IP de la partie : ");
                        input = new BufferedReader(new InputStreamReader(System.in)).readLine();
                        new Client(InetAddress.getByName(input.trim()));
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