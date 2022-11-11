import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;


public class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    ArrayList<Connection> subs;

    public Connection(Socket aClientSocket, ArrayList<Connection> cons) throws IOException {
        this.clientSocket = aClientSocket;
        this.subs=cons;
        this.in = new DataInputStream(clientSocket.getInputStream());
        this.out = new DataOutputStream(clientSocket.getOutputStream());
    }

    public void msg()
    {
        try {
            out.writeUTF("NEW MESSAGE");
        }catch(IOException ioe)
        {

        }
    }

    public void run() {
        try {
            while(true)
            {
                String msg = in.readUTF();
                for(Connection c: subs)
                {
                    c.out.writeUTF(msg);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
