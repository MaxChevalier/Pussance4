package puissance4.ynov;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientHandler implements Runnable {

    private SocketChannel socket = null;
    private Server server = null;

    public ClientHandler(SocketChannel socket, Server server){
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            Listen();
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
