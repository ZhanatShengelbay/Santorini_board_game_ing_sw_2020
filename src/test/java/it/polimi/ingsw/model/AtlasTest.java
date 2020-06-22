package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class AtlasTest {
    Model model;
    Player atlas;


    @Before
    public void testSetUpGrid() {
        model=new Model();
        atlas =new Atlas("playertest", model);
        atlas.addWorker();

        model.getGrid().getTile(new Coordinate(2, 0)).setWorker(atlas.getWorker(0));

    }

    @Test
    public void TestPower(){
        model.setCurrentState(new Select());
        model.setCurrentPlayer(atlas);
        atlas.makeSelection(new Coordinate(2, 0));
        Coordinate destination = new Coordinate(2,1);
        assertTrue(model.getCurrentState() instanceof Move);
        atlas.makeMovement(new Coordinate(3, 0));
        assertTrue(model.getCurrentState() instanceof Power);
        atlas.togglePower();
        assertEquals(model.getGrid().getTile(destination).getHeight().ordinal(),TypeBlock.FLOOR.ordinal());
        atlas.makePower(destination);
        assertTrue(model.getCurrentState() instanceof End);
        assertEquals(model.getGrid().getTile(destination).getHeight().ordinal(),TypeBlock.DOME.ordinal());
        model.getCurrentState().handle(null,model);
        assertFalse(atlas.isActive());



    }

    @Test
    public void secondTest(){
        model.setCurrentState(new Select());
        model.setCurrentPlayer(atlas);
        atlas.makeSelection(new Coordinate(2, 0));
        Coordinate destination = new Coordinate(2,1);
        assertTrue(model.getCurrentState() instanceof Move);
        atlas.makeMovement(new Coordinate(3, 0));
        assertTrue(model.getCurrentState() instanceof Power);
        assertFalse(atlas.isActive());
        assertEquals(model.getGrid().getTile(destination).getHeight().ordinal(),TypeBlock.FLOOR.ordinal());
        atlas.makePower(destination);
        assertTrue(model.getCurrentState() instanceof End);
        assertEquals(model.getGrid().getTile(destination).getHeight().ordinal(),TypeBlock.FIRST.ordinal());


    }
}
