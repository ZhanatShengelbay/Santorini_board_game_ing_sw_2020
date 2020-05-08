package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.End;
import it.polimi.ingsw.model.State.Select;
import it.polimi.ingsw.utility.Coordinate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AthenaTest {
    Model model;
    Player athena;
    Player opponent;


    @Before
    public void testSetUpGrid() {
        model=new Model();
        athena =new Athena("playertest");
        athena.addWorker();
        opponent=new Pan("opponent"); //normal nextPhase
        opponent.addWorker();

        model.getGrid().getTile(new Coordinate(1, 0)).setWorker(athena.getWorker(0));
        model.getGrid().getTile(new Coordinate(1, 1)).levelUp();
        model.getGrid().getTile(new Coordinate(1, 2)).levelUp();
        model.getGrid().getTile(new Coordinate(2, 1)).levelUp();
        model.getGrid().getTile(new Coordinate(3,1)).setWorker(opponent.getWorker(0));
    }

    @Test
    public void test(){
        model.setCurrentState(new Select());
        assertFalse(model.getGroundEffects().contains(athena));
        athena.makeSelection(model,new Coordinate(1, 0));
        athena.makeMovement(model,new Coordinate(1, 1));
        assertTrue(model.getGroundEffects().contains(athena));
        athena.makeBuild(model,new Coordinate(1, 2));
        assertTrue(model.getCurrentState() instanceof End);
        model.setCurrentState(new Select());
        opponent.makeSelection(model,new Coordinate(3, 1));
        assertFalse(opponent.makeMovement(model,new Coordinate(2, 1)));
        //i can change the player because i don t use the current player
        model.setCurrentState(new Select());
        athena.makeSelection(model,new Coordinate(1, 1));
        assertTrue(athena.makeMovement(model, new Coordinate(1,2)));


    }

}
