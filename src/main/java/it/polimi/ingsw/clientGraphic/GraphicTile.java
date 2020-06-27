package it.polimi.ingsw.clientGraphic;

import it.polimi.ingsw.model.TypeBlock;
import it.polimi.ingsw.utility.Coordinate;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GraphicTile extends JLabel  {

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

    public String getPlayerColor() {
        return playerColor;
    }

    public int getTileHeight() {
       return height; }

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
            String URI="/tileImage/"+TypeBlock.values()[this.height].toString().toLowerCase()+this.playerColor.toLowerCase() +".png";
            try {
                img = ImageIO.read(getClass().getResource(URI));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert img != null;
            Image dimg= img.getScaledInstance(125, 125, Image.SCALE_SMOOTH);
            this.setIcon(new ImageIcon(dimg));
            this.updateUI();

        }


    }


}
