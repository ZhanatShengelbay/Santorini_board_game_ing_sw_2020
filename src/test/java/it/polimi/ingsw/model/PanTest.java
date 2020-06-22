package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.Move;
import it.polimi.ingsw.model.State.Select;
import it.polimi.ingsw.model.State.Win;
import it.polimi.ingsw.utility.Coordinate;
import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PanTest {
    Model model;
    Player pan;


    @Test
    public void usePowerToWinTest(){

        model = new Model();
        pan = new Pan( "panTest", model);
        pan.addWorker();
        pan.addWorker();

        model.getGrid().getTile(new Coordinate(1,1)).levelUp().levelUp().levelUp().setWorker(pan.getWorker(0));
        model.getGrid().getTile(new Coordinate(2, 0)).levelUp();
        model.getGrid().getTile(new Coordinate(2,3)).levelUp().levelUp().setWorker(pan.getWorker(1));

        model.setCurrentState(new Select());
        Coordinate from = new Coordinate(1,1);
        pan.makeSelection(from);
        assertTrue(model.getCurrentState() instanceof Move);
        Coordinate destination = new Coordinate(2,0);
        pan.makeMovement(destination);
        assertEquals("pan should be moved", pan, model.getGrid().getTile(destination).getWorker().getPlayer());
        assertTrue("pan should win", model.getCurrentState() instanceof Win);

    }

}