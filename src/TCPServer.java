import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TCPServer extends Thread {
    public static final int PORT = 6999;
    static DataInputStream in;
    static DataOutputStream out;
    static Socket clientSocket;
    static ArrayList<Connection> clientS = new ArrayList<Connection>();
    static int id=0;

    public static void main(String args[]) throws IOException {
         ServerSocket serverSocket;
        while (true) {
            try {

                    serverSocket = new ServerSocket(PORT);
                        Connection testCon = new Connection(serverSocket.accept(),id);
                        id++;
                        clientS.add(testCon);
                        clientSocket = testCon.clientSocket;
                        if(clientSocket.isConnected()){
                            for(Connection c : clientS) {
                                c.out.writeUTF(String.valueOf(c.id)+ " has connected");
                            }
                        }

                        out = new DataOutputStream(clientSocket.getOutputStream());
                        in = new DataInputStream(clientSocket.getInputStream());


                        Thread sender = new Thread(new Runnable() {
                            String msg;

                            @Override
                            public void run() {
                                while (true) {
                                    try {
                                        msg = in.readUTF();
                                    } catch (IOException e) {
                                        System.out.println("KUK1");
                                    }
                                    try {
                                        for(Connection c : clientS) {
                                            c.out.writeUTF(msg);
                                            //c.out.flush();
                                        }
                                    } catch (IOException e) {
                                        System.out.println("KUK2");
                                    }
                                }
                            }

                        });
                        sender.start();
                        Thread recieve = new Thread(new Runnable() {
                            String msg;

                            @Override
                            public void run() {
                                try {
                                    msg = in.readUTF();
                                    if (msg != null) {
                                        //System.out.println("Client: " + clientSocket.getInetAddress() + " " + msg);
                                        for(Connection c : clientS) {
                                            c.out.writeUTF(msg);
                                            //c.out.flush();
                                        }

                                    }
                                } catch (IOException e) {
                                    System.out.println("KUK3");
                                }
                            }
                        });
                        recieve.start();

                } catch(IOException ioe){
                System.out.println("KUK4");
                }

            }
    }
}
