import java.net.*;
import java.io.*;

public class TCPServer extends Thread {
    public static final int PORT = 6999;


    public static void main(String args[]) throws IOException {
        ServerSocket listenSocket = new ServerSocket(PORT);
        Socket socket = null;
        new Connection(listenSocket.accept()).start();

        while (true) {
            try{
                socket = listenSocket.accept();
            } catch (IOException e)
            {
                System.out.println("IOexcp" + e);
            }
            new Connection(socket).start();

        }

    }

}
