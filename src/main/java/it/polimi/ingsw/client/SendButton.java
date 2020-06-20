package it.polimi.ingsw.client;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SendButton extends Button {

    BoardGUI boardGui;
    ClientBackEnd model;

    public SendButton(BoardGUI boardGui, ClientBackEnd model) {
        this.boardGui = boardGui;
        this.model=model;
        this.addActionListener(this);
        String resourceURI="/sendActive.png";
        this.setIcon(new ImageIcon(getClass().getResource(resourceURI)));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
       model.sendChoice();

    }
}
