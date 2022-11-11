import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;


public class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    public Connection(Socket aClientSocket) throws IOException {
        this.clientSocket = aClientSocket;
        this.in = new DataInputStream(clientSocket.getInputStream());
        this.out = new DataOutputStream(clientSocket.getOutputStream());
    }

    public void run() {
        try {
            //if(!in.readUTF().equals("Hej"))
            //out.writeUTF(in.readUTF());
            out.writeUTF("Hej ni lyckades komma in på mina server, grattis");

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
