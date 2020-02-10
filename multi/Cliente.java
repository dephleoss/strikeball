package multi;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.io.InputStream;

public class Cliente {
    private Socket s;
    private BufferedReader tastiera;
    private String stringaOut;
    private String stringaServer;
    private String stringaletta="";
    private DataOutputStream out;
    private BufferedReader in;
    public Socket connetti(){
        try {
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            s = new Socket("localhost",2000);
            out = new DataOutputStream(s.getOutputStream());
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
    public void comunica(){
        while (true)
            try {
                System.out.print("Inserisci la stringa da inviare al server:\n");
                stringaletta = tastiera.readLine();
                System.out.print("Invio stringa al client");
                out.writeBytes(stringaletta + '\n');
                String stringaricevuta = in.readLine();
                System.out.print("Stringa ricevuta: " + stringaricevuta);
                if (stringaletta.equals("FINE")) {
                    System.out.println("CLIENT in chiusura...");
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
}
