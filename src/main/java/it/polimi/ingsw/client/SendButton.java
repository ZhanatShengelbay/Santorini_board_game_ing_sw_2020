package it.polimi.ingsw.client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendButton extends Button {

    GameGUI gameGui;
    ClientBackEnd model;

    public SendButton(GameGUI gameGui, ClientBackEnd model) {
        this.gameGui = gameGui;
        this.model=model;
        this.addActionListener(this);
        String resourceURI="resources/sendActive.png";
        this.setIcon(new ImageIcon(resourceURI));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
       model.sendChoice();

    }
}
