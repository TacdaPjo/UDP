import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient3 {

    public static void main(String[] args) throws IOException {
        final Socket clientSocket;
        final Scanner sc = new Scanner(System.in);
        final DataInputStream in;
        final DataOutputStream out;
        try {
            clientSocket=new Socket("localhost",TCPServer.PORT);
            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new DataInputStream(clientSocket.getInputStream());
            Thread sender = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                    while(true){
                        msg = sc.nextLine();
                        try {
                            out.writeUTF(msg);
                            out.flush();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
            sender.start();
            Thread reciever = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                    try {
                        msg=in.readUTF();
                        while(msg!=null){
                            System.out.println("Server: " + msg);
                            msg = in.readUTF();
                        }

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });reciever.start();
        }catch (IOException ioe){}

    }
}
