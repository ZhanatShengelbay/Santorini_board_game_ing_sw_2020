package it.polimi.ingsw.clientGraphic;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.State.PositionWorkers;
import it.polimi.ingsw.utility.Coordinate;
import it.polimi.ingsw.view.Client;
import it.polimi.ingsw.view.Server;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoardGUITest {

    BackEndGui backEnd;
    Model model;

    Client client;
    @Before
    public void setup() {

        client=new Client("127.0.0.1", 12345);
        backEnd= new BackEndGui(client);

        client.addObserver(backEnd);

        model = new Model();
        model.createPlayer("ARES","playertest");
        //model.getGrid().getTile(0,0).levelUp();
        //backEnd=new BackEndGui(client);
        backEnd.createBoardGui();
        backEnd.initGraphic(model.updateState());

    }
    @Test
    public void tileModified() {

        model.getGrid().getTile(0,0).levelUp();

        assertTrue(backEnd.getGui().grid[0][0].getTileHeight() == 0);
        for(int i = 0; i< BoardGUI.N_ROWS; i++) {
            for (int j = 0; j < BoardGUI.N_COLS; j++) {
                backEnd.changeTile(model.updateState().getGrid().getTile(i,j),backEnd.getGui().getGrid()[i][j]);

            }
        }
        assertTrue(backEnd.getGui().grid[0][0].getTileHeight() == 1);
        model.setCurrentState(new PositionWorkers());
        model.getCurrentState().handle(new Coordinate(2,2),model);
        assertEquals(backEnd.getGui().grid[2][2].getPlayerColor(),"");
        for(int i = 0; i< BoardGUI.N_ROWS; i++) {
            for (int j = 0; j < BoardGUI.N_COLS; j++) {
                backEnd.changeTile(model.updateState().getGrid().getTile(i,j),backEnd.getGui().getGrid()[i][j]);

            }
        }
        String color = null;
        Color tmp=backEnd.getPlayerColor("playertest");
        if(tmp.equals(Color.BLUE))
            color="blue";
        if(tmp.equals(Color.PINK))
            color="pink";
        if(tmp.equals(Color.GRAY))
            color="gray";

        assertEquals(backEnd.getGui().grid[2][2].getPlayerColor(),color);

    }

    @Test
    public void textTest(){
        String tmp=backEnd.getGui().textBox.getText();
        backEnd.update("text");

        assertEquals(backEnd.getGui().textBox.getText(),tmp+" "+"text");
    }





}