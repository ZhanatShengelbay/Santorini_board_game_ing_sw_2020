package it.polimi.ingsw.client;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.ModelView;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GUITest {

    ClientBackEnd backEnd;
    Model model;
    ModelView message;



    @Before
    public void setup(){


    }

    @Test
    public void updateGrid(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                ClientBackEnd model=new ClientBackEnd(null);
                model.players=new ArrayList<>();
                model.players.add("mar");
                model.players.add("car");
                model.players.add("tar");
                HashMap<String,String> tmp=new HashMap<>();
                tmp.put("mar","minotaur");
                tmp.put("tar","zeus");
                tmp.put("car","ares");
                model.playersGods=tmp;
                model.playersColor.put("mar", Color.PINK);
                model.playersColor.put("car", Color.BLUE);
                model.playersColor.put("tar", Color.GRAY);
                model.getGui().playersPanel(model);
                model.getGui().createGrid(model);



            }
        });
        /*backEnd=new ClientBackEnd(null);
        model=new Model();
        model.getGrid().getTile(1,1).levelUp().levelUp();
        model.getGrid().getTile(2,2).levelUp();
        message=model.updateState();
        //backEnd.update(message);*/
    }
}
