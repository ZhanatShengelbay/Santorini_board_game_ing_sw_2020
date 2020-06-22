package it.polimi.ingsw.clientGraphic;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SendButton extends Button {

    BoardGUI boardGui;
    BackEndGui model;

    public SendButton(BoardGUI boardGui, BackEndGui model) {
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
