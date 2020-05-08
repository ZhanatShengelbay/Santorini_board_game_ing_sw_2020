package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.Move;
import it.polimi.ingsw.model.State.Select;
import it.polimi.ingsw.utility.Coordinate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AphroditeTest {

    Model model;
    Player aphrodite;
    Player opponent;


    @Before
    public void testSetUpGrid() {
        model=new Model();
        aphrodite =new Aphrodite("playertest", model);
        aphrodite.addWorker();
        aphrodite.addWorker();
        opponent=new Pan("opponent"); //normal nextPhase
        opponent.addWorker();

        model.getGrid().getTile(new Coordinate(1, 0)).setWorker(aphrodite.getWorker(0));
        model.getGrid().getTile(new Coordinate(3, 2)).setWorker(aphrodite.getWorker(1));
        model.getGrid().getTile(new Coordinate(2,0)).setWorker(opponent.getWorker(0));
    }
    @Test
    public void TestPower(){
        model.setCurrentState(new Select());
        opponent.makeSelection(model,new Coordinate(2,0));
        Coordinate destination = new Coordinate(3,1);
        assertTrue(model.getCurrentState() instanceof Move);
        assertFalse("opponent cannot move because there isnt a aphordite worker near destination",
                    opponent.makeMovement(model, new Coordinate(3,0)));
        opponent.makeMovement(model, destination);
        assertEquals("opponent should move", opponent.getWorker(0), model.getGrid().getTile(destination).getWorker());


    }
    @Test
    public void differentTestPower(){
        //This test is made to check the apollo power when Aphrodite's ground effect is active
        Player apollo = new Apollo("Apollo");
        apollo.addWorker();
        model.getGrid().getTile(new Coordinate(2,0)).setWorker(apollo.getWorker(0));
        model.setCurrentState(new Select());
        apollo.makeSelection(model,new Coordinate(2,0));
        Coordinate destination = new Coordinate(1,0);
        apollo.makeMovement(model, destination);
        assertEquals("opponent should move", apollo.getWorker(0), model.getGrid().getTile(destination).getWorker());
        assertEquals("aphrodite change position", aphrodite.getWorker(0), model.getGrid().getTile(new Coordinate(2, 0)).getWorker());


    }
}
