package puissance4.ynov;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.io.IOException;

public class ServerHandler implements Runnable {

    Server server = null;
    SocketChannel clientsocket = null;

    public ServerHandler(Server server, SocketChannel clientsocket) {
        this.server = server;
        this.clientsocket = clientsocket;
    }

    @Override
    public void run() {
        ByteBuffer bytes = ByteBuffer.allocate(1024);
        while (true){
            bytes.clear();
            try {
                int bytesRead = clientsocket.read(bytes);
                if (bytesRead <= 0) {
                    clientsocket.close();
                    return;
                }
                String message = new String(bytes.array(), "UTF-16");
                System.out.println(message);
                server.broadcast(message);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        }
        
    }
}
