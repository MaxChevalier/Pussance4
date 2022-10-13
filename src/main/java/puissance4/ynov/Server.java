package main.java.puissance4.ynov;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class Server {

    ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
    public static int joueur = 0;
    public static void main(String[] args) throws Exception{
        Server server = new Server();
        server.launch();
    }
    
    public void launch(){
         int joueur = 0;
        try {
            ServerSocketChannel serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(8000));
            while(true){
                 SocketChannel clientSocket = serverSocket.accept();
                joueur++;
                System.out.println("Client connected");
                ClientHandler client = new ClientHandler(clientSocket,this);
                clients.add(client);
                Thread clientThread = new Thread(client);
                System.out.println(joueur);
                clientThread.start();
                if (joueur <= 2){
                    System.out.println("Le jeux commence");
                    GameManager.GameInitialisater();
                }
                else {
                    System.out.println("Le serveur est plein");
                }
            }
        }catch (IOException e){
            System.err.println(e.toString());
            System.err.println("Server stopped due to unexpected exception");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
