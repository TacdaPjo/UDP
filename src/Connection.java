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
    TCPServer master;
    String id;

    public Connection(Socket aClientSocket, TCPServer master) throws IOException {
        this.clientSocket = aClientSocket;
        this.master=master;
        this.in = new DataInputStream(clientSocket.getInputStream());
        this.out = new DataOutputStream(clientSocket.getOutputStream());
    }

    public void setId(String id)
    {
        this.id=id;
    }

    public void run() {
        try {
            while(true) {

                String msg = in.readUTF();

                if(msg.contains("!joinPriv") || msg.contains("!sendPriv") || msg.contains("!quitPriv") || msg.contains("!ban") ) {
                    System.out.println("JAG GÅR IN HIT");

                    if(msg.contains("!ban"))
                    {
                        master.ban(msg.substring(5));
                        System.out.println(msg.substring(5));
                    }

                    if (msg.contains("!joinPriv")) {
                        master.joinPriv(this);
                    }
                    if (msg.contains("!quitPriv")) {
                        master.leavePriv(this);
                    }
                    if (msg.contains("!sendPriv ")) {
                        master.sendPriv(msg);
                    }
                }else
                {
                    master.send(id+": "+msg);
                }


            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
