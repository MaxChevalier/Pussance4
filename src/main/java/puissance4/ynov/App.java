package puissance4.ynov;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class App {
    public static void main(String[] args) {
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
                        GameManager.GameInitialisater();
                        return;
                    case "2":
                        OnlineSetup();
                        break;
                    default:
                        System.out.println("Entrée non reconnu");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void OnlineSetup() {
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
                        System.out.println("Entrée non reconnu");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
