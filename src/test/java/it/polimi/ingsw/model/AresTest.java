package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AresTest {
    Model model;
    Player ares;
    Player opponent;

    @Before
    public void setupGridTest(){

        ares = new Ares("aresTest");
        ares.addWorker();
        ares.addWorker();
        opponent = new Demeter("demeterOpponent");
        opponent.addWorker();

        model = new Model();
        model.getGrid().getTile(new Coordinate(1,0)).levelUp().setWorker(ares.getWorker(0));
        model.getGrid().getTile(new Coordinate(3,4)).levelUp().setWorker(ares.getWorker(1));
        model.getGrid().getTile(new Coordinate(2,4)).setWorker(opponent.getWorker(0));
        model.getGrid().getTile(new Coordinate(3,3)).levelUp().levelUp();



    }

    @Test
    public void usualAresTest(){
        model.setCurrentState(new Select());
        ares.makeSelection(model, new Coordinate(1,0));
        Coordinate destination = new Coordinate(2,1);
        assertTrue(model.getCurrentState() instanceof Move);
        ares.makeMovement(model, destination);
        assertEquals("ares should move", ares, model.getGrid().getTile(destination).getWorker().getPlayer());
        int beforeHeight = model.getGrid().getTile(new Coordinate(1,1)).getHeight().ordinal();

        assertTrue(model.getCurrentState() instanceof Build);
        ares.makeBuild(model, new Coordinate(1,1));
        assertEquals("ares should build",model.getGrid().getTile(new Coordinate(1,1)).getHeight().ordinal(), beforeHeight + 1);
        //assertTrue(model.getCurrentState() instanceof End);
        // ares.togglePower();
        assertTrue(model.getCurrentState() instanceof Power);
        ares.makePower(model, new Coordinate(2,4));
        assertEquals("ares should not reduce the place where opponent is located", opponent, model.getGrid().getTile(new Coordinate(2,4)).getWorker().getPlayer());
        assertTrue(model.getCurrentState() instanceof End);
    }

    @Test
    public void aresReduceTest(){
        model.setCurrentState(new Select());
        ares.makeSelection(model, new Coordinate(1,0));
        int movedWrkNum = model.getGrid().getTile(new Coordinate(1, 0)).getWorker().getNum();
        Coordinate destination = new Coordinate(2,1);

        assertTrue(model.getCurrentState() instanceof Move);
        ares.makeMovement(model, destination);
        int beforeHeight = model.getGrid().getTile(new Coordinate(2,0)).getHeight().ordinal();

        assertTrue(model.getCurrentState() instanceof Build);
        ares.makeBuild(model, new Coordinate(2,0));
        assertEquals("ares should build", beforeHeight + 1, model.getGrid().getTile(new Coordinate(2,0)).getHeight().ordinal());

        //assertTrue(model.getCurrentState() instanceof End);

        int bfrHght = model.getGrid().getTile(new Coordinate(3,3)).getHeight().ordinal();
        assertTrue(model.getCurrentState() instanceof Power);
        ares.togglePower();
        ares.makePower(model, new Coordinate(3,3));
        int unmovedWrkNum = model.getGrid().getTile(new Coordinate(3,4)).getWorker().getNum();
        assertNotEquals("workers' nums should be different", movedWrkNum, unmovedWrkNum );

        assertEquals("ares should remove", bfrHght - 1, model.getGrid().getTile(new Coordinate(3,3)).getHeight().ordinal());

        assertTrue(model.getCurrentState() instanceof End);

    }


}