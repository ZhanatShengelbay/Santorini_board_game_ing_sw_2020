package it.polimi.ingsw.model;


import it.polimi.ingsw.model.State.Build;
import it.polimi.ingsw.model.State.Move;
import it.polimi.ingsw.model.State.PositionWorkers;
import it.polimi.ingsw.model.State.Select;
import it.polimi.ingsw.utility.Coordinate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class LimusTest {

    Model model;
    Player limus;
    Player opponent;


    @Before
    public void testSetUpGrid() {
        model=new Model();
        model.createPlayer("limus","player");
        limus =model.getPlayer(0);

        opponent=new Pan("opponent", model); //normal nextPhase
        opponent.addWorker();
        model.getPlayers().add(limus);
        model.getPlayers().add(opponent);

        model.setCurrentPlayer(limus);
        model.setCurrentState(new PositionWorkers());
        model.getCurrentState().handle(new Coordinate(1, 0), model);
        model.getCurrentState().handle(new Coordinate(3, 2),model);

        model.getGrid().getTile(new Coordinate(2,0)).setWorker(opponent.getWorker(0));
    }
    @Test
    public void TestPower(){
        assertTrue(limus instanceof Limus);
        model.setCurrentState(new Select());
        model.setCurrentPlayer(opponent);
        opponent.makeSelection(new Coordinate(2,0));
        Coordinate destination = new Coordinate(1,1);
        assertTrue(model.getCurrentState() instanceof Move);
        opponent.makeMovement(destination);
        assertTrue(model.getCurrentState() instanceof Build);
        assertFalse(opponent.makeBuild(new Coordinate(2,1)));
        model.getGrid().getTile(2,1).levelUp().levelUp().levelUp();
        assertTrue(opponent.makeBuild(new Coordinate(2,1)));



    }


}
