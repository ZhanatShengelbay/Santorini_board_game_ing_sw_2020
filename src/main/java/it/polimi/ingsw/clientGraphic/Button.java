package it.polimi.ingsw.clientGraphic;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JLabel implements MouseListener {



    public void actionPerformed() {

    }

    public void updateGraphic(boolean state){}

    @Override
    public void mouseClicked(MouseEvent e) {
        actionPerformed();
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
