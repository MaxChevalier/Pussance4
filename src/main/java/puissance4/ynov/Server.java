package main.java.puissance4.ynov;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class Server {

    ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();

    public static void main(String[] args){
        Server server = new Server();
        server.launch();
        
    }

    public void launch(){
        try {
            ServerSocketChannel serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(8000));
            while(true){
                SocketChannel clientSocket = serverSocket.accept();
                System.out.println("Client connected");
                ClientHandler client = new ClientHandler(clientSocket,this);
                clients.add(client);
                Thread clientThread = new Thread(client);
                clientThread.start();
            }
        }catch (IOException e){
            System.err.println(e.toString());
            System.err.println("Server stopped due to unexpected exception");
        }
    }

    public void broadcast(String message, ClientHandler handler) throws IOException{
        for (ClientHandler client : clients) {
            if(client != handler){
                client.send(message);
            }
        }
    }
}
