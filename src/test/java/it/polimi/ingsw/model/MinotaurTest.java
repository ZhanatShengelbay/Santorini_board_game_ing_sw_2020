package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.Move;
import it.polimi.ingsw.model.State.Select;
import it.polimi.ingsw.utility.Coordinate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MinotaurTest {
    Model model;
    Player mino;
    Player opponent;

    @Before
    public void testSetUpGrid() {
        model=new Model();
        mino =new Minotaur("playertest", model);
        mino.addWorker();
        opponent=new AbstractPlayer("opponent");
        opponent.addWorker();
        model.getGrid().getTile(new Coordinate(2, 0)).setWorker(mino.getWorker(0));
        model.getGrid().getTile(new Coordinate(2,1)).setWorker(opponent.getWorker(0));
        model.getGrid().getTile(new Coordinate(2,2)).levelUp().levelUp().levelUp();
    }
    @Test
    public void SimpleTest(){
        model.setCurrentState(new Select());
        mino.makeSelection(new Coordinate(2,0));
        Coordinate destination=new Coordinate(2,1);
        mino.makeMovement(destination);
        assertEquals("mino should move", mino.getWorker(0), model.getGrid().getTile(destination).getWorker());
        assertEquals("mino should move", mino.getWorker(0), model.getGrid().getTile(model.getCurrentWorker()).getWorker());
        assertEquals("opponent shift", opponent.getWorker(0), model.getGrid().getTile(new Coordinate(2,2)).getWorker());



    }
    @Test
    public void secondTest(){
        model.setCurrentState(new Select());
        model.getGrid().getTile(new Coordinate(2,2)).levelUp();
        mino.makeSelection(new Coordinate(2,0));
        Coordinate destination=new Coordinate(2,1);
        assertFalse(mino.makeMovement(destination));
        assertEquals("mino shouldn t move", mino.getWorker(0), model.getGrid().getTile(new Coordinate(2,0)).getWorker());
        assertEquals("mino shouldn t move", mino.getWorker(0), model.getGrid().getTile(model.getCurrentWorker()).getWorker());

    }

    @Test

    public void  thirdTest(){
        mino.addWorker();
        model.getGrid().getTile(new Coordinate(2, 3)).setWorker(mino.getWorker(1));
        model.getGrid().getTile(new Coordinate(2,4)).setWorker(opponent.getWorker(0));
        model.setCurrentState(new Select());
        mino.makeSelection(new Coordinate(2,3));
        assertFalse(mino.makeMovement(new Coordinate(2,4)));

    }



}
