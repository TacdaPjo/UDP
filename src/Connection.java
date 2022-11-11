import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;


public class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    int id=0;

    public Connection(Socket aClientSocket, int id) throws IOException {
        this.clientSocket = aClientSocket;
        this.in = new DataInputStream(clientSocket.getInputStream());
        this.out = new DataOutputStream(clientSocket.getOutputStream());
        this.id=id;
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
