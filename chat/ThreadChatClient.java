package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class ThreadChatClient implements Runnable{
    private List lista;
    Thread me;
    private Socket client;
    private BufferedReader input=null;
    private PrintWriter output=null;
    public ThreadChatClient (List lista,String ipserver, int porta){
        this.lista=lista;
        try{
            client = new Socket(ipserver,porta);
            this.input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.output = new PrintWriter(client.getOutputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
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
                String mex=null;
                while ((mex=input.readLine())==null){

                }
                gestoreChat.spedisciMessaggio(mex);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void spedisciMessaggioChat(String messaggio){
        try{
            output.println(messaggio);
        }catch (Exception e){

        }
    }
}
