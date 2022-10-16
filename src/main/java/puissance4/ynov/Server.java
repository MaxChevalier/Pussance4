package puissance4.ynov;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Random;
import java.nio.ByteBuffer;

public class Server implements Runnable {

    ArrayList<SocketChannel> clients = new ArrayList<SocketChannel>();
    int turnPlayer = 1;
    ServerSocketChannel serverSocket = null;
    int nbtPlayer = 0;
    boolean isWorking;

    public Server(int nbtPlayer){
        this.nbtPlayer = nbtPlayer;
    }

    @Override
    public void run(){
        launch();
    }

    public void stop(){
        isWorking = false;
    }

    public void launch() {
        isWorking = true;
        try {
            serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(4004));
            System.out.println("Adresse IP du serveur : " + InetAddress.getLocalHost());
            while (clients.size() < nbtPlayer) {
                SocketChannel clientSocket = serverSocket.accept();
                clients.add(clientSocket);
            }
            for (SocketChannel clientsocket : clients) {
                ByteBuffer bytes = ByteBuffer.wrap(("Players "+nbtPlayer).getBytes("UTF-16"));
                while (bytes.hasRemaining()) {
                    clientsocket.write(bytes);
                }
                bytes.clear();
            }
            ByteBuffer bytes = ByteBuffer.wrap("Your turn".getBytes("UTF-16"));
            Random r = new Random();
            int clientId = r.nextInt(clients.size());
            while (bytes.hasRemaining()) {
                clients.get(clientId).write(bytes);
            }
            turnPlayer = clientId;
            while (isWorking) {
                SocketChannel clientSocket = clients.get(turnPlayer);
                bytes.clear();
                try {
                    int bytesRead = clientSocket.read(bytes);
                    if (bytesRead <= 0) {
                        clientSocket.close();
                        return;
                    }
                    String message = new String(bytes.array(), "UTF-16");
                    message.trim();
                    message = "Turn " + ConvertNbPlayerToSend(turnPlayer+1)+ " " + message.split(" ")[1];
                    broadcast(message);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (IOException e) {
            System.err.println(e.toString());
            System.err.println("Server stopped due to unexpected exception");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String message) throws IOException {
        for (SocketChannel clientsocket : clients) {
            ByteBuffer bytes = ByteBuffer.wrap(message.getBytes("UTF-16"));
            while (bytes.hasRemaining()) {
                clientsocket.write(bytes);
            }
        }
        turnPlayer = ((turnPlayer + 1) % nbtPlayer);
        ByteBuffer bytes = ByteBuffer.wrap("Your turn".getBytes("UTF-16"));
        while (bytes.hasRemaining()) {
            clients.get(turnPlayer).write(bytes);
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
}
