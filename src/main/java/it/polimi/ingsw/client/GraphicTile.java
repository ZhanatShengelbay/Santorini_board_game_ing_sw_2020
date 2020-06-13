package it.polimi.ingsw.client;

import it.polimi.ingsw.model.TypeBlock;
import it.polimi.ingsw.utility.Coordinate;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphicTile extends JLabel {

    private int height;
    private String playerColor;
    private final Coordinate coordinate;

    public GraphicTile(Coordinate coordinate) {
        super();
        height=0;
        playerColor ="";
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void updateGraphic(String playerColor, int height){
        boolean change=false;
        if(playerColor ==null)
            playerColor ="";
        if(!this.playerColor.equals(playerColor)){
            change=true;
            this.playerColor = playerColor;
        }
        if(this.height!=height){
            change=true;
            this.height=height;
        }
        if(change){
            BufferedImage img = null;
            String URI="resources/"+TypeBlock.values()[this.height].toString().toLowerCase()+this.playerColor +".png";
            try {
                img = ImageIO.read(new File(URI));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image dimg= img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            this.setIcon(new ImageIcon(dimg));
        }
    }
}
