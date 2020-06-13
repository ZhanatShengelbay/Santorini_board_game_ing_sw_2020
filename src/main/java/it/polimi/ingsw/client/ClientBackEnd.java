package it.polimi.ingsw.client;

import it.polimi.ingsw.controller.Event;
import it.polimi.ingsw.model.ModelView;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.utility.Coordinate;
import it.polimi.ingsw.utility.Observer;
import it.polimi.ingsw.view.Client;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClientBackEnd implements Observer<Object>,Graphic{

    Client client;
    boolean power;
    boolean readyToSend;
    Coordinate coordinate;
    GameGUI gui;
    Map<String,Color> playersColor= new HashMap<>();
    List<String> players;
    ModelView model;


    public ClientBackEnd(Client client)
    {
        this.client=client;
        this.gui= new GameGUI();
        ClientBackEnd tmp=this;
        SwingUtilities.invokeLater(() -> gui.initGUI(tmp));
    }



    public void sendChoice(){
        if(readyToSend) {
            StringBuilder message = new StringBuilder("");
            if (power) message.append("@ ");
            message.append(coordinate.getX()).append(" ").append(coordinate.getY());
            client.send(message.toString());
            power = false;
            coordinate = null;
            readyToSend=!readyToSend;
        }
    }


    public boolean togglePower(){
        power= !power;
        return power;
    }

    public void addInput(Coordinate coordinate){
        this.coordinate=coordinate;
        if(!readyToSend)readyToSend=true;
    }

    public Color getPlayerColor(String player){
        return playersColor.get(player);

    }

    public void updateGraphicGrid(ModelView model){
        SwingUtilities.invokeLater(() -> {
            for(int i=0;i<GameGUI.N_ROWS;i++) {
                for (int j = 0; j < GameGUI.N_COLS; j++) {
                    changeTile(model.getGrid().getTile(i,j),gui.getGrid()[i][j]);

                }
            }
        });

    }
    public void handle(Event event){
        switch (event){
            case SETUP:

                break;
            case GODCHOICE:
        }
    }

    public void changeTile(Tile t, GraphicTile g){
        int height= t.getHeight().ordinal();
        String color= getPlayerColor(t.getWorker().getPlayer().getPlayerID()).toString();
        g.updateGraphic(color,height);
    }
    @Override
    public void update(Object message) {
        if(message instanceof String){
            this.gui.printText((String)message);
        }
        else if(message instanceof ModelView){
            updateGraphicGrid((ModelView)message);
            this.model=(ModelView)message;
        }
        else if(message instanceof Event) handle((Event)message);
        }





}
