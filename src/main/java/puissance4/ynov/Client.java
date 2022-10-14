package main.java.puissance4.ynov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.net.InetAddress;

public class Client {
    public InetAddress ip;

    public Client(InetAddress ipServer) {
        startClient(ipServer);
        try {
            ip = InetAddress.getLocalHost();
        } catch(IOException e) {
            System.err.println("Impossible de récupérer l'adresse IP");
        }
        
    }

    public void startClient(InetAddress ipServer){


        try {
            ip = InetAddress.getLocalHost();
        }catch (IOException e){
            System.err.println("Impossible de récupérer l'adresse IP");
        }
    }

    public  void startClient(InetAddress ipServeur){
        
        try {
            ip = InetAddress.getLocalHost();
            SocketChannel socket = SocketChannel.open();
            socket.connect(new InetSocketAddress(ipServer, 8000));
            ClientHandler client = new ClientHandler(socket, null);
            Thread clientThread = new Thread(client);
            clientThread.start();
            while(true){
                String message = promptForString();
                try {
                    client.send(message);
                }
                catch(IOException e){
                    System.err.println(e.toString());
                }
                
            }

        } catch (IOException e){
            System.err.println(e.toString());
        }
    }

    public static String promptForString(){
        InputStreamReader bis = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(bis);
        try {
            return br.readLine();
        }
        catch(IOException e){
            System.err.println("Something went wrong : " + e.getMessage());
            System.err.println("Please retry : ");
            return promptForString();
        }
    }
}
