package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DemeterTest {
    Model model;
    Player demeter;

    @Before
    public void setupGridTest(){

        demeter = new Demeter( "demeterTest");
        demeter.addWorker();
        demeter.addWorker();

        model = new Model();
        model.getGrid().getTile(new Coordinate(1,1)).levelUp().setWorker(demeter.getWorker(0));
        model.getGrid().getTile(new Coordinate(2,0)).levelUp().levelUp();
        model.getGrid().getTile(new Coordinate(2,1)).levelUp().levelUp();
        model.getGrid().getTile(new Coordinate(1,3)).levelUp().levelUp();
        model.getGrid().getTile(new Coordinate(3,2)).levelUp().setWorker(demeter.getWorker(1));
        model.getGrid().getTile(new Coordinate(3,0)).levelUp().levelUp();
    }

    @Test
    public void twiceBuildingTest(){

        model.setCurrentState(new Select());
        demeter.makeSelection(model,new Coordinate(1,1));
        Coordinate destination = new Coordinate(2,0);
        assertTrue(model.getCurrentState() instanceof Move);
        demeter.makeMovement(model, destination);
        assertEquals("demeter should move", demeter, model.getGrid().getTile(destination).getWorker().getPlayer());

        assertTrue(model.getCurrentState() instanceof Build);
        Coordinate bldPlace = new Coordinate(2,1);
        int bld = model.getGrid().getTile(bldPlace).getHeight().ordinal();
        demeter.makeBuild(model,bldPlace);
        assertEquals(bld+1, model.getGrid().getTile(bldPlace).getHeight().ordinal());
        assertTrue( model.getCurrentState() instanceof Power);
        int newBld = model.getGrid().getTile(new Coordinate(1,1)).getHeight().ordinal();
        demeter.togglePower();
        Coordinate newDest=(new Coordinate(2,1));
        boolean result = demeter.makePower(model, newDest);
        assertFalse(result);
        assertEquals(newBld, model.getGrid().getTile(new Coordinate(1,1)).getHeight().ordinal());
        assertTrue(model.getCurrentState() instanceof Power);
        newDest=(new Coordinate(1,1));
        demeter.makePower(model, newDest);
        assertEquals(newBld+1, model.getGrid().getTile(new Coordinate(1,1)).getHeight().ordinal());



//        assertTrue( model.getCurrentState() instanceof Choice);
//        Choice newDest = new Choice();
//        newDest.setState(new Build(new Coordinate(2,1)));
//        demeter.makePower(model, newDest);
//        int nbld = model.getGrid().getTile(new Coordinate(2,1)).getHeight().ordinal();
//        demeter.makeBuild(model, new Build(new Coordinate(2,1)));
//        assertEquals(nbld+1, model.getGrid().getTile(new Coordinate(2,1)).getHeight().ordinal());

        assertTrue(model.getCurrentState() instanceof End);
    }

}