package it.polimi.ingsw.clientGraphic;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.ModelView;
import it.polimi.ingsw.view.Client;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class GUITest {

    BackEndGui backEnd;
    Model model;



    @Before
    public void setup(){
        Client client=new Client("127.0.0.1",12345);
        model=new Model();
        model.createPlayer("ARES","playertest");
        model.getGrid().getTile(0,0).levelUp();
        backEnd=new BackEndGui(client);




    }

    @Test
    public void updateGrid(){

        backEnd.createBoardGui();
        assertTrue(backEnd.getGui().grid[0][0].getHeight()==0);
        backEnd.update(model.updateState());
        assertTrue(backEnd.getGui().grid[0][0].getHeight()==1);
    }
}
