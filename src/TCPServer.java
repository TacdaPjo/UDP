import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class TCPServer extends Thread {
    public static final int PORT = 6999;


    public static void main(String args[]) throws IOException {
        ServerSocket listenSocket = new ServerSocket(PORT);
        ArrayList<Connection> cons = new ArrayList<Connection>();
        Socket socket = null;

        while (true) {
            try {
                new Connection(listenSocket.accept()).start();
            } catch (IOException e) {
                System.out.println("IOexcp" + e);
            }
            Connection temp = new Connection(socket);
            cons.add(temp);
            for(Connection c : cons)
            {
                c.msg();
            }


        }

    }

}
