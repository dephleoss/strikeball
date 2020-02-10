package multi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    ServerSocket server;
    Socket client;
    BufferedReader inClient;
    DataOutputStream outClient;
    public Server(Socket s){
        this.client = s;
    }
    public Socket attendi(){
        System.out.println("SERVER partito...\n");
        try {
            server = new ServerSocket(2000);
            client = server.accept();
            server.close();
            inClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outClient = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;
    }
    public void comunica(){
        String stringaricevuta = null;
        try {
            stringaricevuta = inClient.readLine();
            System.out.println("Stringa ricevuta :"+stringaricevuta);
            String stringam = stringaricevuta.toUpperCase();
            System.out.println("invio stringa modificata");
            outClient.writeBytes(stringam + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
