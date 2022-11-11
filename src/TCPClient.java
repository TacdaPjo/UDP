import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient {

    public static void main(String args[]) throws IOException {
        users a = new users();
        if (a.userID == 3) {
            System.out.println("Welcome");
        }
        //int port = 7896;
        while(true) {
        Socket s = new Socket("localhost", TCPServer.PORT);
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

            System.out.print("Enter your message here: ");
            Scanner sc = new Scanner(System.in);
            String message = sc.nextLine();
            out.writeUTF(message + " " + new java.util.Date());
            String data = in.readUTF();
            System.out.println("Received: " + data);
        }
        //if (s != null) s.close();


    }
}
