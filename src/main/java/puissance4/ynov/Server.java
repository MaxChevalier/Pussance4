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

    ArrayList<SocketChannel> clients = new ArrayList<SocketChannel>(); // list of clients
    int turnPlayer = 1;
    ServerSocketChannel serverSocket = null; // server socket

    public void launch() { // launch the server
        try {
            serverSocket = ServerSocketChannel.open();  // open the server socket
            serverSocket.bind(new InetSocketAddress(4004)); // bind the server socket to the port 4004
            System.out.println("Adresse IP du serveur : " + InetAddress.getLocalHost());// display the server IP
            while (clients.size() < 2) { // wait for 2 clients
                SocketChannel clientSocket = serverSocket.accept(); // accept the client
                clients.add(clientSocket); // add the client to the list
                ServerHandler serverHandler = new ServerHandler(this, clientSocket);
                Thread thread = new Thread(serverHandler);
                thread.start();// start the thread
            }
            System.out.println("Deux joueurs ont rejoint la partie");
            ByteBuffer bytes = ByteBuffer.wrap("Your turn".getBytes("UTF-16"));// send the message "Your turn" to the first client
            Random r = new Random(); // create a random object
            while (bytes.hasRemaining()) { // send the message
                clients.get(r.nextInt(clients.size()-1)+1).write(bytes); // send the message to a random client
            }
            
        } catch (IOException e) { // catch the exception
            System.err.println(e.toString());
            System.err.println("Server stopped due to unexpected exception");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String message) throws IOException { // broadcast a message to all clients
        System.out.println("Broadcasting message to all clients");
        for (SocketChannel clientsocket : clients) { // for each client
            ByteBuffer bytes = ByteBuffer.wrap(message.getBytes("UTF-16")); // convert the message to bytes
            while (bytes.hasRemaining()) { // while there are bytes to send
                clientsocket.write(bytes);
            }
        }
        turnPlayer = (turnPlayer % 1) + 1; // change the turn
        ByteBuffer bytes = ByteBuffer.wrap("Your turn".getBytes("UTF-16"));// convert the message to bytes
        while (bytes.hasRemaining()) {
            clients.get(turnPlayer).write(bytes);// send the message to the client
        }
    }
}