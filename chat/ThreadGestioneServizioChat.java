package chat;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ThreadGestioneServizioChat implements Runnable{
    private int nrMaxConnessioni;
    private List lista;
    private ThreadChatConnessioni[] listaConnessioni;
    Thread me;
    private ServerSocket serverChat;
    public ThreadGestioneServizioChat(int numeroMaxConnessioni,List lista){
        this.nrMaxConnessioni=numeroMaxConnessioni;
        this.lista = lista;
        this.listaConnessioni=new ThreadChatConnessioni[this.nrMaxConnessioni];
        me = new Thread(this);
        me.start();
    }
    @Override
    public void run() {
        boolean continua = true;
        try{
            serverChat = new ServerSocket(6789);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Impossibile instanziare il server");
            e.printStackTrace();
        }
        if(continua){
           try {
               for (int xx=0;xx<this.nrMaxConnessioni;xx++){
                   Socket tempo=null;
                   tempo = serverChat.accept();
                   listaConnessioni[xx] = new ThreadGestioneServizioChat(this,tempo);
               }
           } catch (IOException e) {
               JOptionPane.showMessageDialog(null,"Impossibile instanziare server chat");
               e.printStackTrace();
           }
        }
    }
    public void spedisciMessaggio(String mex){
        lista.add(mex);
        lista.select(lista.getItemCount()-1);
        for (int xx=0;xx<this.nrMaxConnessioni;xx++){
            if (listaConnessioni[xx] != null){
                listaConnessioni[xx].spedisciMessaggioChat(mex);
            }

        }
    }
}
