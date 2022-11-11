import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) throws IOException {

        try {
            //Socket s = new Socket("192.168.232.207", TCPServer.PORT);
            Socket s = new Socket("localhost", TCPServer.PORT);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            Scanner sc = new Scanner(System.in);
            String message = "";


            while (true) {
                if (sc.hasNext()) {
                    message = sc.nextLine();
                    out.writeUTF(message);

                }
                String data = in.readUTF();
                System.out.println("Received: "+data);
            }


        }catch(IOException ioe){
            ioe.printStackTrace();

        }
    }
}
