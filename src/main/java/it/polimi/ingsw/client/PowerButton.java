package it.polimi.ingsw.client;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PowerButton extends Button {

    GameGUI gameGui;
    ClientBackEnd model;

    public PowerButton(GameGUI gameGui, ClientBackEnd model) {
        this.gameGui = gameGui;
        this.model=model;
        this.addActionListener(this);
        this.setIcon(new ImageIcon("resources/heropower_inactive.png"));
    }
    @Override
    public void updateGraphic(boolean state){

        if(state) {
            this.setIcon(new ImageIcon("resources/heropower_active.png"));
        }
        else
            this.setIcon(new ImageIcon("resources/heropower_inactive.png"));


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        updateGraphic(model.togglePower());
    }
}
