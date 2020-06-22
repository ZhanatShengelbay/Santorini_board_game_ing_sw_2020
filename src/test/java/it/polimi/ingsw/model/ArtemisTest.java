package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArtemisTest {
    Model model;
    Player artemis;

    @Before
    public void gridSetUp(){

        model=new Model();
        artemis=new Artemis("playertest", model);
        artemis.addWorker();
        artemis.addWorker();


        model.getGrid().getTile(new Coordinate(2,0)).levelUp();
        model.getGrid().getTile(new Coordinate(2,0)).setWorker(artemis.getWorker(0));
        model.getGrid().getTile(new Coordinate(1,1)).levelUp().levelUp().levelUp().levelUp();
        model.getGrid().getTile(new Coordinate(2,1)).levelUp().levelUp();
        model.getGrid().getTile(new Coordinate(3,1)).levelUp().setWorker(artemis.getWorker(1));
        model.getGrid().getTile(new Coordinate(3,0)).levelUp().levelUp();


    }

    @Test
    public void FirstMove(){
        model.setCurrentState(new Select());
        model.setCurrentPlayer(artemis);
        Coordinate from = new Coordinate(2,0);
        artemis.makeSelection(from);
        assertTrue(model.getCurrentState() instanceof Move);
        artemis.makeMovement(new Coordinate(3,0));
        assertEquals(model.getGrid().getTile(new Coordinate(3, 0)).getWorker().getPlayer(), artemis);
        assertTrue(model.getCurrentState() instanceof Power);
        artemis.togglePower();
        assertFalse("cannot move back",artemis.makePower(from));
        artemis.makePower(new Coordinate(2,1));
        assertFalse(artemis.containsInValidCoordinate(from));
        assertEquals(model.getGrid().getTile(new Coordinate(2, 1)).getWorker().getPlayer(), artemis);
        assertTrue(model.getCurrentState() instanceof Build);
        int tmp= model.getGrid().getTile(new Coordinate(2,0)).getHeight().ordinal();
        artemis.makeBuild(new Coordinate(2,0));
        assertEquals(model.getGrid().getTile(new Coordinate(2, 0)).getHeight().ordinal(), tmp+1);
        assertTrue(model.getCurrentState() instanceof Select);






    }
}
