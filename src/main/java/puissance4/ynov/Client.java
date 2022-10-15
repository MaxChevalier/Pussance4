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
    private int id = 0;

    public Client() {
    }

    public Client(InetAddress ipServer) {
        try {
            System.out.println("Connexion au serveur...");
            socket = SocketChannel.open();
            socket.connect(new InetSocketAddress("localhost", 4004));
            System.out.println("Connexion établie");
            GameOnline();
        } catch (IOException e) {
            System.err.println("Impossible de récupérer l'adresse IP");
        }
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
            try {
                return new int[] { ConvertSendToInt(message.split(" ")[1]), Integer.parseInt(message.split(" ")[2]) };
            } catch (Exception e) {
                return new int[] { 0, 0 };
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
        System.out.println("Starting game...");
        int nbtPlayer = 2;
        // génére la grille en fonction du nombre de joueur
        int width;
        int height;
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
                int[] Listen = Listen();
                if (Listen != null) {
                    if (Listen[0] == 0 && Listen[1] == 0) {
                        OnlineUserPlay(width, id);
                    } else {
                        for (int i = grid[Listen[1]].length - 1; i >= 0; i--) {
                            if (grid[Listen[1]][i] == 0) {
                                grid[Listen[1]][i] = Listen[0];
                                display.DisplayGrid(grid);
                                return;
                            }
                        }
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

    private void OnlineUserPlay(int widht, int turnPlayer) {
        System.out.println("Your turn");
        int position = display.Input(widht);
        if (grid[position][0] == 0) {
            String message = new String("Turn " + ConvertNbPlayerToSend(turnPlayer) + " " + position);
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

    private String ConvertNbPlayerToSend(int nbt) {
        switch (nbt) {
            case 1:
                return "X";
            case 2:
                return "O";
            case 3:
                return "V";
            default:
                return Integer.toString(nbt);
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