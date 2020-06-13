package it.polimi.ingsw.client;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GraphicListener implements MouseListener {

    GameGUI gameGui;
    ClientBackEnd model;

    public GraphicListener(GameGUI gameGui, ClientBackEnd model) {
        this.gameGui = gameGui;
        this.model=model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GraphicTile button = (GraphicTile)e.getSource();
        gameGui.selectionTile(button);
        model.addInput(button.getCoordinate());

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {


    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
