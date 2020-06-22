package it.polimi.ingsw.clientGraphic;

import it.polimi.ingsw.controller.Event;
import it.polimi.ingsw.model.ModelView;
import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.utility.Coordinate;
import it.polimi.ingsw.utility.Observer;
import it.polimi.ingsw.view.Client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BackEndGui implements Observer<Object> {

    private final Client client;
    boolean power;
    boolean readyToSend;
    private Coordinate coordinate;
    private BoardGUI gui;
    private GodSetupUI setupGui;
     Map<String,Color> playersColor= new HashMap<>();
    Map<String,String> playersGods;
    List<String> players;
    ModelView model;
    boolean init;
    boolean godChoice;

    public BackEndGui(Client client)
    {
        this.client=client;
        //this.gui= new GameGUI();
        this.players=new ArrayList<>();
        init=true;
        setup();
    }

    public BoardGUI getGui() {
        return gui;
    }

    public void setSetupGui(GodSetupUI setupGui) {
        this.setupGui = setupGui;
    }

    public void createBoardGui() {
        this.gui=new BoardGUI();
        gui.initBoard(this);
    }

    public void setup(){
        new MenuGUI(this);
    }

    public void sendChoice(){
           if(coordinate!=null) {
                StringBuilder message = new StringBuilder("");
                if (power) message.append("@ ");
                message.append(coordinate.getX()).append(" ").append(coordinate.getY());
                client.send(message.toString());
                power = false;
                gui.power.updateGraphic(false);
            }

    }
    public void sendMessage(String message){
        client.send(message.toLowerCase());
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

    public Map<String, String> getPlayersGods() {
        return playersGods;
    }

    public void updateGraphicGrid(ModelView model){
        SwingUtilities.invokeLater(() -> {
            for(int i = 0; i< BoardGUI.N_ROWS; i++) {
                for (int j = 0; j < BoardGUI.N_COLS; j++) {
                    changeTile(model.getGrid().getTile(i,j),gui.getGrid()[i][j]);

                }
            }
            gui.revalidate();
        });
        /*
        for(int i=0;i<GameGUI.N_ROWS;i++) {
            for (int j = 0; j < GameGUI.N_COLS; j++) {
                changeTile(model.getGrid().getTile(i,j),gui.getGrid()[i][j]);

            }
        }*/

    }
    public void handle(Event event){
        switch (event){
            case SETUP:
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setupGui.createGodSetup();
                    }
                });



                break;
            case GODCHOICE:
                godChoice=true;
                break;
        }
    }

    public void changeTile(Tile t, GraphicTile g){
        int height= t.getHeight().ordinal();
        String color=null;
        if(t.getWorker()!=null) {
            Color tmp;
            tmp = getPlayerColor(t.getWorker().getPlayer().getPlayerID());
            if(tmp.equals(Color.BLUE))
                color="blue";
            if(tmp.equals(Color.PINK))
                color="pink";
            if(tmp.equals(Color.GRAY))
                color="gray";
        }
        String finalColor = color;
        g.updateGraphic(finalColor, height);
        g.updateUI();


    }



    @Override
    public void update(Object message) {
        if(message instanceof String){
            try {
                this.gui.printText((String)message);
            }catch (NullPointerException e){
                System.out.println((String)message);
            }
        }
        else if(message instanceof ModelView){
            if(init)
                initGraphic((ModelView)message);
            updateGraphicGrid((ModelView)message);
            this.model=(ModelView)message;
        }
        else if(message instanceof Event) handle((Event)message);

        else if(message instanceof String[]&&godChoice){
            SwingUtilities.invokeLater(() -> setupGui.createGodChoice((String[])message));
            godChoice=false;
        }

    }


    private void initGraphic(ModelView message) {
        for(int i=0;i<message.sizePlayers();i++){
            this.players.add(message.getPlayer(i));
            switch (i){
                case 0:
                    playersColor.put(players.get(i),Color.PINK);
                    break;
                case 1:
                    playersColor.put(players.get(i),Color.BLUE);
                    break;
                case 2:
                    playersColor.put(players.get(i),Color.GRAY);
                    break;
            }

        }
        this.playersGods=message.getGodsPlayer();
        gui.playersPanel(this);
        init=false;

    }


}
