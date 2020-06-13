package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.Move;
import it.polimi.ingsw.model.State.PositionWorkers;
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
        model.createPlayer("aphrodite","player");
        aphrodite=model.getPlayer(0);

        opponent=new Pan("opponent"); //normal nextPhase
        opponent.addWorker();
        model.getPlayers().add(aphrodite);
        model.getPlayers().add(opponent);

        model.setCurrentPlayer(aphrodite);
        model.setCurrentState(new PositionWorkers());
        model.getCurrentState().handle(new Coordinate(1, 0),model);
        model.getCurrentState().handle(new Coordinate(3, 2),model);

        model.getGrid().getTile(new Coordinate(2,0)).setWorker(opponent.getWorker(0));
    }
    @Test
    public void TestPower(){
        assertTrue(aphrodite instanceof Aphrodite);
        model.setCurrentState(new Select());
        model.setCurrentPlayer(opponent);
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
