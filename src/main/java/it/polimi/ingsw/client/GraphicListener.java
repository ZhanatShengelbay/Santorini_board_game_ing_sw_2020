package it.polimi.ingsw.client;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GraphicListener implements MouseListener {

    BoardGUI boardGui;
    ClientBackEnd model;

    public GraphicListener(BoardGUI boardGui, ClientBackEnd model) {
        this.boardGui = boardGui;
        this.model=model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GraphicTile tmp=(GraphicTile)e.getSource();
        GraphicTile button = boardGui.getTile(tmp.getCoordinate());

        boardGui.selectionTile(button);
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
