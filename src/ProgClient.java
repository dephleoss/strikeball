package src;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ProgClient {
    public static void main(String Argv[]){
        Socket socket=null;
        String indirizzo=null;
        try {
            BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
            socket = new Socket("localhost",2000);
            indirizzo = InetAddress.getLocalHost().getHostAddress();
            System.out.println(indirizzo);
            System.out.println(socket.toString());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            System.out.print("Inserisci la stringa da inviare al server:");
            String stringa = tastiera.readLine();
            System.out.print("Invio stringa al client");
            out.writeBytes(stringa+'\n');
            String stringaricevuta = in.readLine();
            System.out.print("Stringa ricevuta: "+stringaricevuta);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
