package it.polimi.ingsw.clientGraphic;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PowerButton extends Button {

    BoardGUI boardGui;
    BackEndGui model;

    public PowerButton(BoardGUI boardGui, BackEndGui model) {
        this.boardGui = boardGui;
        this.model = model;
        this.addMouseListener(this);
        BufferedImage img = null;
        String URI = "/heropower_inactive.png";
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
    public void updateGraphic(boolean state) {
        String URI;
        if (state) {
            URI = "/heropower_active.png";
        } else
            URI = "/heropower_inactive.png";

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
        updateGraphic(model.togglePower());
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        if(!model.power) {
            BufferedImage img = null;
            String URI = "/heropower_inactive_on.png";
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

    @Override
    public void mouseExited(MouseEvent e) {
        if(!model.power) {
            BufferedImage img = null;
            String URI = "/heropower_inactive.png";
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


}
