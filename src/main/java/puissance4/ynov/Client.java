package puissance4.ynov;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.net.InetAddress;
import java.nio.ByteBuffer;

public class Client {

    private SocketChannel socket = null;
    private int[][] grid;
    private Display display = new Display();
    private int id = 1;

    public Client() {
    }

    public Client(InetAddress ipServer) {
        try {
            System.out.println("Connexion au serveur...");
            socket = SocketChannel.open();
            socket.connect(new InetSocketAddress(ipServer, 4004));
            System.out.println("Connexion établie");
            GameOnline();
        } catch (IOException e) {
            System.err.println("Impossible de récupérer l'adresse IP");
        }
        close();
    }

    private int[] Listen() throws IOException {

        ByteBuffer bytes = ByteBuffer.allocate(1024);
        bytes.clear();
        try {
            int bytesRead = socket.read(bytes);
            if (bytesRead <= 0) {
                socket.close();
                return null;
            }
            String message = new String(bytes.array(), "UTF-16");
            message = message.trim();
            try {
                if (message.equals("Your turn") || message.equals("Your turn ")) {
                    return new int[] { 0, 0 };
                }
                return new int[] { ConvertSendToInt(message.split(" ")[1]), Integer.parseInt(message.split(" ")[2].split("n")[0]) };
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw e;
            }
        } catch (IOException e) {
            socket.close();
            return null;
        }

    }

    public void send(String message) throws IOException {
        ByteBuffer bytes = ByteBuffer.wrap(message.getBytes("UTF-16"));
        while (bytes.hasRemaining()) {
            socket.write(bytes);
        }
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println(e.toString());
        }

    }

    public void GameOnline() {
        
        int nbtPlayer = 2;
        // génére la grille en fonction du nombre de joueur
        int width;
        int height;
        System.out.println("en attente de joueur...");
        ByteBuffer bytes = ByteBuffer.allocate(1024);
        try{
            int bytesRead = socket.read(bytes);
            if (bytesRead <= 0) {
                socket.close();
                return;
            }
            String message = (new String(bytes.array(), "UTF-16")).trim();
            nbtPlayer = Integer.parseInt(message.split(" ")[1]);
            message = "";
            bytes.clear();
        }catch(Exception e){
            System.out.println(e.getMessage());
            bytes.clear();
        }
        System.out.println("Starting game...");
        switch (nbtPlayer) {
            case 2:
                height = 6;
                width = 8;
                break;
            case 3:
                height = 10;
                width = 12;
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + nbtPlayer);
        }
        
        grid = display.GenerateGrid(width, height);

        // répétition tant que la grille n'est pas remplie ou qu'un joueur n'a pas gagné
        display.DisplayGrid(grid);
        while (!GridVerif.IsFinish(grid)) {
            try {
                System.out.println("\033[H\033[2J");//delete the console
                int[] Listen = Listen();
                if (Listen != null) {
                    if (Listen[0] == 0 && Listen[1] == 0) {
                        OnlineUserPlay(width, id);
                    } else {
                        PlacePiece(Listen[1], Listen[0]);
                    }
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        try {
            display.DisplayGrid(grid);
            int winner = GridVerif.WhoWin(grid);
            switch (winner) {
                case 0:
                    System.out.println("Match nul");
                    break;
                default:
                    System.out.println("Le joueur n°" + winner + " a gagné");
                    break;
            }
        } catch (Exception e) {
            System.err.println("Fatal Error : " + e.getMessage());
        }
    }

    private void PlacePiece(int col,int idPlayer){
        for (int i = grid[col].length - 1; i >= 0; i--) {
            if (grid[col][i] == 0) {
                grid[col][i] = idPlayer;
                display.DisplayGrid(grid);
                return;
            }
        }
    }

    private void OnlineUserPlay(int widht, int turnPlayer) {
        int position = display.Input(widht);
        if (grid[position][0] == 0) {
            String message = new String("Turn "+position+" ");
            try {
                send(message);
                System.out.println("Waiting for Server...");
            } catch (IOException e) {
                System.err.println("Impossible d'envoyer le message");
            }
        } else {
            System.out.println("La colonne est pleine");
            OnlineUserPlay(widht, turnPlayer);
        }
    }

    

    private int ConvertSendToInt(String nbt) {
        switch (nbt) {
            case "X":
                return 1;
            case "O":
                return 2;
            case "V":
                return 3;
            default:
                return Integer.parseInt(nbt);
        }
    }
}