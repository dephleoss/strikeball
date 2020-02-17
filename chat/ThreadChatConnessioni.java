package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadChatConnessioni implements Runnable{
    private ThreadGestioneServizioChat gestoreChat;
    private Socket client = null;
    private BufferedReader input = null;
    private PrintWriter output = null;
    Thread me;
    public ThreadChatConnessioni(ThreadGestioneServizioChat gestoreChat,Socket client){
        this.gestoreChat = gestoreChat;
        this.client = client;
        try{
            this.input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.output = new PrintWriter(this.client.getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        me = new Thread(this);
        me.start();
    }
    @Override
    public void run() {
        while (true){
            try{
                String mex = null;
                while ((mex=input.readLine())==null){

                }
                gestoreChat.spedisciMessaggio(mex);
            } catch (IOException e) {
                e.printStackTrace();
            }
            output.println("Errore nella spedizione del messaggio a tutti");
        }
    }
    public void spedisciMessaggioChat(String messaggio){
        try {
            output.println(messaggio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
