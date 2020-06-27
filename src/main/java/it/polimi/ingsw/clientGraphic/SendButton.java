package it.polimi.ingsw.clientGraphic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SendButton extends Button {

    BoardGUI boardGui;
    BackEndGui model;


    public SendButton(BoardGUI boardGui, BackEndGui model) {
        this.boardGui = boardGui;
        this.model=model;

        this.addMouseListener(this);
        String URI="/sendActive.png";
        BufferedImage img = null;

        try {
            img = ImageIO.read(getClass().getResource(URI));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert img != null;
        Image dimg = img.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(dimg));
    }


    @Override
    public void actionPerformed() {
       model.sendChoice();

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        String URI="/sendActive_on.png";
        BufferedImage img = null;

        try {
            img = ImageIO.read(getClass().getResource(URI));
        } catch (IOException h ) {
            h.printStackTrace();
        }
        assert img != null;
        Image dimg = img.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(dimg));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        String URI="/sendActive.png";
        BufferedImage img = null;

        try {
            img = ImageIO.read(getClass().getResource(URI));
        } catch (IOException h) {
            h.printStackTrace();
        }
        assert img != null;
        Image dimg = img.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(dimg));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        String URI="/sendActive_active.png";
        BufferedImage img = null;

        try {
            img = ImageIO.read(getClass().getResource(URI));
        } catch (IOException h) {
            h.printStackTrace();
        }
        assert img != null;
        Image dimg = img.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(dimg));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        String URI="/sendActive.png";
        BufferedImage img = null;

        try {
            img = ImageIO.read(getClass().getResource(URI));
        } catch (IOException h) {
            h.printStackTrace();
        }
        assert img != null;
        Image dimg = img.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(dimg));
    }
}
