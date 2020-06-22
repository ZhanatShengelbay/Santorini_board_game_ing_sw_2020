package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.End;
import it.polimi.ingsw.model.State.Select;
import it.polimi.ingsw.utility.Coordinate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AthenaTest {
    Model model;
    Player athena;
    Player opponent;


    @Before
    public void testSetUpGrid() {
        model=new Model();
        athena =new Athena("playertest", model);
        athena.addWorker();
        opponent=new Pan("opponent", model); //normal nextPhase
        opponent.addWorker();
        model.getPlayers().add(athena);
        model.getPlayers().add(opponent);
        model.getGrid().getTile(new Coordinate(1, 0)).setWorker(athena.getWorker(0));
        model.getGrid().getTile(new Coordinate(1, 1)).levelUp();
        model.getGrid().getTile(new Coordinate(1, 2)).levelUp();
        model.getGrid().getTile(new Coordinate(2, 1)).levelUp();
        model.getGrid().getTile(new Coordinate(3,1)).setWorker(opponent.getWorker(0));
    }

    @Test
    public void test(){
        model.setCurrentState(new Select());
        model.setCurrentPlayer(athena);
        assertFalse(model.getGroundEffects().contains(athena));
        athena.makeSelection(new Coordinate(1, 0));
        athena.makeMovement(new Coordinate(1, 1));
        assertTrue(model.getGroundEffects().contains(athena));
        athena.makeBuild(new Coordinate(1, 2));
        model.getCurrentState().handle(null,model);
        assertTrue(model.getCurrentState() instanceof Select);
        assertEquals(true, model.getCurrentPlayer().equals(opponent));

        opponent.makeSelection(new Coordinate(3, 1));
        assertFalse(opponent.makeMovement(new Coordinate(2, 1)));
        assertTrue(opponent.makeMovement(new Coordinate(4, 1)));
        //i can change the player because i don t use the current player
        model.setCurrentState(new Select());
        athena.makeSelection(new Coordinate(1, 1));
        assertTrue(athena.makeMovement(new Coordinate(1,2)));


    }

}
