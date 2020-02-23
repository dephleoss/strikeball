package chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PannelloChatClient extends JPanel implements ActionListener{
    private List lista;
    private JTextField textNuovo;
    private ThreadGestioneServizioChat gestioneServizio;
    public PannelloChatClient(){
        super();
        this.setBackground(new Color(50,100,255));
        JPanel panLista = new JPanel(new BorderLayout(20,5));
        panLista.setBackground(new Color(50,100,255));
        lista = new List();
        lista.setBackground(Color.lightGray);
        lista.setSize(100,50);
        lista.setVisible(true);
        JLabel chat1= new JLabel(" Chat ",JLabel.CENTER);
        chat1.setForeground(new Color(200,100,100));
        JLabel chat2= new JLabel(" Chat ",JLabel.CENTER);
        chat2.setForeground(new Color(200,100,100));
        panLista.add(chat1,BorderLayout.WEST);
        panLista.add(lista,BorderLayout.CENTER);
        panLista.add(chat2,BorderLayout.EAST);

        JPanel nuovoMex = new JPanel(new BorderLayout(20,5));
        nuovoMex.setBackground(new Color(50,100,255));
        JLabel labNuovo = new JLabel("Nuovo Messggio ->  ",JLabel.CENTER);
        labNuovo.setForeground(new Color(255,255,0));
        textNuovo = new JTextField("");
        JButton buttonInvia = new JButton("Invia");
        buttonInvia.addActionListener(this);
        nuovoMex.add(labNuovo,BorderLayout.WEST);
        nuovoMex.add(textNuovo,BorderLayout.CENTER);
        nuovoMex.add(buttonInvia,BorderLayout.EAST);
        this.setLayout(new BorderLayout(0,5));
        add(panLista,BorderLayout.CENTER);
        add(nuovoMex,BorderLayout.SOUTH);
        connetti();



    }
    public void connetti(){
        gestioneServizio = new ThreadGestioneServizioChat(10,lista);
    }
    public void actionPerformed(ActionEvent e){
        String bottone = e.getActionCommand();
        if(bottone.equals("invia")){
            gestioneServizio.spedisciMessaggio(textNuovo.getText());
            textNuovo.setText("");
        }
    }

}
