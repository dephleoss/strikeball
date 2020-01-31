package src;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ProgServer {
    public static void main(String Argv[]){
        Socket socket = null;
        String indirizzo=null;
        ServerSocket serverSocket= null;
        int tempo=10000;
        try {
            serverSocket = new ServerSocket(2000);
        } catch (IOException e) {
            e.printStackTrace();
        }
            try {
                System.out.println("In attesa di connessione.....");
                Contasotto conta = new Contasotto(tempo);
                Thread t= new Thread(conta);
                t.start();
                serverSocket.setSoTimeout(tempo);
                socket=serverSocket.accept();
                BufferedReader inclient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                DataOutputStream out= new DataOutputStream(socket.getOutputStream());
                System.out.println("Connesso");
                String stringaricevuta = inclient.readLine();
                System.out.println("Stringa ricevuta :"+stringaricevuta);
                String stringam = stringaricevuta.toUpperCase();
                System.out.println("invio stringa modificata");
                out.writeBytes(stringam + '\n');
            }catch(SocketTimeoutException e){
                System.err.print("Server Chiuso!");
            }
            catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (socket!=null){
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
}
