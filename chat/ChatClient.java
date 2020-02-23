package chat;

import javax.swing.*;
import java.awt.*;

public class ChatClient extends JFrame {
    public ChatClient(){
        super("Chat  Client");
        this.setSize(new Dimension(500,300));
        this.setLocationRelativeTo(null);
        this.setEnabled(true);
        this.setBackground(Color.green);
        PannelloChatClient pan = new PannelloChatClient();
        this.getContentPane().add(pan);
        this.setVisible(true);
    }

}

