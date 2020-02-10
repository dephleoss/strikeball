package multi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {
    public void start(){
        try{
            ServerSocket ss = new ServerSocket(2000);
            while (true){
                System.out.println("Server in attesa...");
                Socket socket = ss.accept();
                System.out.println("Server Socket:" + socket);
                Server sThread = new Server(socket);
                sThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
