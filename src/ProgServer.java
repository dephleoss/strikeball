package src;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ProgServer {
    public static void main(String Argv[]){
        Socket socket = null;
        String indirizzo=null;
        ServerSocket serverSocket= null;
        int tempo=5000;
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
                System.out.println("Connesso");
                indirizzo = InetAddress.getLocalHost().getHostAddress();
                System.out.println(indirizzo);
                System.out.print(serverSocket.toString());
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
