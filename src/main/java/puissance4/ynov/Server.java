package puissance4.ynov;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Random;
import java.nio.ByteBuffer;

public class Server{

    ArrayList<SocketChannel> clients = new ArrayList<SocketChannel>();
    int turnPlayer = 1;
    ServerSocketChannel serverSocket = null;
    private SocketChannel socket = null;

    public void launch() {
        try {
            serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(4004));
            System.out.println("Adresse IP du serveur : " + InetAddress.getLocalHost());
            while (clients.size() < 2) {
                SocketChannel clientSocket = serverSocket.accept();
                clients.add(clientSocket);
            }
            System.out.println("Deux joueurs ont rejoint la partie");
            ByteBuffer bytes = ByteBuffer.wrap("Your turn".getBytes("UTF-16"));
            Random r = new Random();
            while (bytes.hasRemaining()) {
                clients.get(r.nextInt(clients.size()-1)+1).write(bytes);
            }
            // while(true){
            //     for (SocketChannel clientsocket : clients) {
            //         bytes.clear();
            //         clientsocket.read(bytes);
            //         String message = new String(bytes.array(), "UTF-16");
            //         System.out.println(message);
            //         broadcast(message);
            //     }
            // }
        } catch (IOException e) {
            System.err.println(e.toString());
            System.err.println("Server stopped due to unexpected exception");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String message) throws IOException {
        System.out.println("Broadcasting message to all clients");
        for (SocketChannel clientsocket : clients) {
            ByteBuffer bytes = ByteBuffer.wrap(message.getBytes("UTF-16"));
            while (bytes.hasRemaining()) {
                clientsocket.write(bytes);
            }
        }
        turnPlayer = (turnPlayer % 1) + 1;
        ByteBuffer bytes = ByteBuffer.wrap("Your turn".getBytes("UTF-16"));
        while (bytes.hasRemaining()) {
            clients.get(turnPlayer).write(bytes);
        }
    }
}
