import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient extends Thread{

    public static void main(String[] args) throws IOException {
        boolean sUser = true;
        String id="Alifarlig";

        try {
            //Socket s = new Socket("192.168.232.207", TCPServer.PORT);
            Socket s = new Socket("localhost", TCPServer.PORT);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            Scanner sc = new Scanner(System.in);
            String message = "";

            Thread rec = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true)
                    {
                        try{
                            String data = in.readUTF();
                            if(data.equals("WALLA SKICKA DIN ID"))
                            {
                                out.writeUTF(id);
                            }
                            else
                            {
                                System.out.println(data);
                            }

                        }catch(IOException io)
                        {}

                    }
                }
            });
            rec.start();

            while (true) {
                if (sc.hasNext()) {
                    message = sc.nextLine();
                    out.writeUTF(message);
                }
            }


        }catch(IOException ioe){
        }
    }
}
