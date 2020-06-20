package it.polimi.ingsw.client;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class PowerButton extends Button {

    BoardGUI boardGui;
    ClientBackEnd model;

    public PowerButton(BoardGUI boardGui, ClientBackEnd model) {
        this.boardGui = boardGui;
        this.model=model;
        this.addActionListener(this);
        this.setIcon(new ImageIcon(getClass().getResource("/heropower_inactive.png")));
    }
    @Override
    public void updateGraphic(boolean state){

        if(state) {
            this.setIcon(new ImageIcon(getClass().getResource("/heropower_active.png")));
        }
        else
            this.setIcon(new ImageIcon(getClass().getResource("/heropower_inactive.png")));


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        updateGraphic(model.togglePower());
    }
}
