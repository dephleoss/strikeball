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
    @Override
    public void run() {
        try{
            comunica();
        }
        catch (Exception e){
            e.printStackTrace(System.out);
        }
    }
    public void comunica(){
        try {
            inClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outClient = new DataOutputStream(client.getOutputStream());
            while (true){
                String stringaricevuta = inClient.readLine();
                if (stringaricevuta==null || stringaricevuta.equals("FINE")){
                    System.out.println("Stringa ricevuta :"+stringaricevuta);
                    String stringam = stringaricevuta.toUpperCase();
                    outClient.writeBytes("Server in Chisura -->"+stringam + '\n');
                    System.out.println("Server In Chiusura...");
                    break;
                }
                else {
                    System.out.println("Stringa ricevuta :"+stringaricevuta);
                    String stringam = stringaricevuta.toUpperCase();
                    System.out.println("invio stringa modificata");
                    outClient.writeBytes(stringam + '\n');
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                inClient.close();
                outClient.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
