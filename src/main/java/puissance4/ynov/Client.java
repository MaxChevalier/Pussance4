package puissance4.ynov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.net.InetAddress;

public class Client {
 
    public InetAddress ip;

    public static void main(String[] args) throws Exception{
        try {
            SocketChannel socket = SocketChannel.open();
            socket.connect(new InetSocketAddress(4004));
            ClientHandler client = new ClientHandler(socket, null);
            Thread clientThread = new Thread(client);
            clientThread.start();
            while(true){
                String message = promptForString();
                try {
                    GameManager gameManager = new GameManager();
                    gameManager.GameOnLine();
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

    
    
    
    private void Listen() throws IOException{
        ByteBuffer bytes = ByteBuffer.allocate(1024);
        while(true){

            bytes.clear();
            try {
                int bytesRead = socket.read(bytes);
                if(bytesRead <= 0){
                    socket.close();
                    return;
                }
                String message = new String(bytes.array(),"UTF-16");
                if(server != null){
                    server.broadcast(message,this);
                }
                else {
                    System.out.println(message);
                }
            }catch (IOException e){
                socket.close();
                return;
            }
            
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
    public void send(String message) throws IOException{
        ByteBuffer bytes = ByteBuffer.wrap(message.getBytes("UTF-16"));
        while(bytes.hasRemaining()){
            socket.write(bytes);
        }
    }

    public void close(){
        try {
            socket.close();
        }
        catch(IOException e){
            System.err.println(e.toString());
        }

    }
}
