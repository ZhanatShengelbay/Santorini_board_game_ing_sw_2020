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
        List<Worker> workerList = new ArrayList<>();
        Worker worker1 = new Worker();
        Worker worker2 = new Worker();
        workerList.add(worker1);
        workerList.add(worker2);
        demeter = new Demeter(workerList, "demeterTest");

        model = new Model(new Grid());
        model.getGrid().getTile(new Coordinate(1,1)).levelUp().setWorker(worker1);
        model.getGrid().getTile(new Coordinate(2,0)).levelUp().levelUp();
        model.getGrid().getTile(new Coordinate(2,1)).levelUp().levelUp();
        model.getGrid().getTile(new Coordinate(1,3)).levelUp().levelUp();
        model.getGrid().getTile(new Coordinate(3,2)).levelUp().setWorker(worker2);
        model.getGrid().getTile(new Coordinate(3,0)).levelUp().levelUp();
    }

    @Test
    public void twiceBuildingTest(){

        model.setCurrentState(new Select(null));
        Coordinate from = new Coordinate(1,1);
        demeter.makeSelection(model, new Select(from));
        Coordinate destination = new Coordinate(2,0);
        assertTrue(model.getCurrentState() instanceof Move);
        demeter.makeMovement(model, new Move(destination));
        assertEquals("demeter should move", demeter, model.getGrid().getTile(destination).getWorker().getPlayer());

        assertTrue(model.getCurrentState() instanceof Build);
        Coordinate bldPlace = new Coordinate(2,1);
        int bld = model.getGrid().getTile(bldPlace).getHeight().ordinal();
        demeter.makeBuild(model, new Build(bldPlace));
        assertEquals(bld+1, model.getGrid().getTile(bldPlace).getHeight().ordinal());

        assertTrue( model.getCurrentState() instanceof Choice);
        Choice newDest = new Choice();
        newDest.setState(new Build(new Coordinate(1,1)));
        demeter.makePower(model, newDest);
        int nbld = model.getGrid().getTile(new Coordinate(1,1)).getHeight().ordinal();
        demeter.makeBuild(model, new Build(new Coordinate(1,1)));
        assertEquals(nbld+1, model.getGrid().getTile(new Coordinate(1,1)).getHeight().ordinal());

// to check that building at the same place is not allowed:

//        assertTrue( model.getCurrentState() instanceof Choice);
//        Choice newDest = new Choice();
//        newDest.setState(new Build(new Coordinate(2,1)));
//        demeter.makePower(model, newDest);
//        int nbld = model.getGrid().getTile(new Coordinate(2,1)).getHeight().ordinal();
//        demeter.makeBuild(model, new Build(new Coordinate(2,1)));
//        assertEquals(nbld+1, model.getGrid().getTile(new Coordinate(2,1)).getHeight().ordinal());

        assertFalse(model.getCurrentState() instanceof Build);

        assertTrue(model.getCurrentState() instanceof End);
    }

}