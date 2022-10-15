package puissance4.ynov;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.io.IOException;

public class ServerHandler implements Runnable {

    Server server = null;
    SocketChannel clientsocket = null;

    public ServerHandler(Server server, SocketChannel clientsocket) { // constructor
        this.server = server;
        this.clientsocket = clientsocket;
    }

    @Override
    public void run() { // run the thread
        ByteBuffer bytes = ByteBuffer.allocate(1024); // create a buffer
        while (true){
            bytes.clear(); // clear the buffer
            try {
                int bytesRead = clientsocket.read(bytes);
                if (bytesRead <= 0) { // if the client disconnect
                    clientsocket.close(); // close the socket
                    return;
                }
                String message = new String(bytes.array(), "UTF-16");
                System.out.println(message);
                server.broadcast(message); // broadcast the message to all the clients
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        }
    }
}
