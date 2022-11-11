import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient2 {

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
                    String data = in.readUTF();
                    System.out.println("Received: " + data);
                }
                if (message.equals("exit")) {
                    s.close();
                }
            }


        }catch(IOException ioe){
            ioe.printStackTrace();

        }
    }
}
