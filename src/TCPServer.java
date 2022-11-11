import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Queue;

public class TCPServer extends Thread {
    public static final int PORT = 6999;
    ArrayList<Connection> cons;
    ArrayList<Connection> priv;
    public TCPServer()
    {
        try{
            ServerSocket listenSocket = new ServerSocket(PORT);
            cons = new ArrayList<Connection>();
            priv = new ArrayList<Connection>();
            while (true) {
                Connection temp = new Connection(listenSocket.accept(),this);
                temp.out.writeUTF("WALLA SKICKA DIN ID");
                String id = temp.in.readUTF();
                temp.setId(id);
                send(temp.id+" has connected");
                cons.add(temp);
                temp.start();

                Thread checkDc = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true)
                        {
                            for(Connection c : cons)
                            {
                                if(c.clientSocket.isClosed() || !c.clientSocket.isConnected())
                                {
                                    try {
                                        cons.remove(c);
                                        c.clientSocket.close();
                                        c.out.close();
                                        c.in.close();
                                        send(c.id+" has disconnected");
                                    }catch(IOException e){}
                                }
                            }
                        }
                    }
                });
                checkDc.start();
                for(Connection c : cons)
                {
                    if(c.clientSocket.isClosed() || !c.clientSocket.isConnected())
                    {
                        cons.remove(c);
                        c.clientSocket.close();
                        c.out.close();
                        c.in.close();
                        send("User: "+c.clientSocket.getInetAddress()+" has disconnected");
                    }
                }
            }
        }catch(IOException ioe)
        {

        }

    }
    public void ban(String id)
    {
        for(Connection c : cons)
        {
            if(c.id.equals(id))
            {
                try {
                    c.out.writeUTF("You have been banned...");
                    c.clientSocket.close();
                    c.out.close();
                    c.in.close();
                }catch(IOException io){}
                cons.remove(c);
            }
        }
    }
    public void joinPriv(Connection c)
    {
        System.out.println(priv.size());
        priv.add(c);
    }
    public void leavePriv(Connection c)
    {
        priv.remove(c);
    }
    public void sendPriv(String msg)
    {
        try{
            for(Connection c : priv)
            {
                c.out.writeUTF(msg);
                c.out.flush();
            }
        }catch(IOException io)
        {

        }
    }
    public void send(String msg)
    {
        try{
            for(Connection c : cons)
            {
                c.out.writeUTF(msg);
                c.out.flush();

            }
        }catch(IOException io)
        {

        }
    }
}
