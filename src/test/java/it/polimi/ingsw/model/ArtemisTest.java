package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class ArtemisTest {
    Model model;
    Player artemis;

    @Before
    public void gridSetUp(){

        List<Worker> workers = new ArrayList<Worker>();
        Worker worker1 = new Worker();
        Worker worker2 = new Worker();
        workers.add(worker1);
        workers.add(worker2);
        artemis=new Artemis(workers, "playertest");


        model=new Model(new Grid());
        model.getGrid().getTile(new Coordinate(2,0)).levelUp();
        model.getGrid().getTile(new Coordinate(2,0)).setWorker(worker1);
        model.getGrid().getTile(new Coordinate(1,1)).levelUp().levelUp().levelUp().levelUp();
        model.getGrid().getTile(new Coordinate(2,1)).levelUp().levelUp();
        model.getGrid().getTile(new Coordinate(3,1)).levelUp().setWorker(worker2);
        model.getGrid().getTile(new Coordinate(3,0)).levelUp().levelUp();


    }

    @Test
    public void FirstMove(){
        model.setCurrentState(new Select(null));
        Coordinate from = new Coordinate(2,0);
        artemis.makeSelection(model, new Select(from));
        assertTrue(model.getCurrentState() instanceof Move);
        artemis.makeMovement(model,new Move(new Coordinate(3,0)));
        assertEquals(model.getGrid().getTile(new Coordinate(3, 0)).getWorker().getPlayer(), artemis);
        assertTrue(model.getCurrentState() instanceof Choice);
        Choice result = new Choice();
        result.setState(new Move(new Coordinate(2,1)));
        artemis.makePower(model,result);
        assertFalse(artemis.containsInValidCoordinate(from));
        assertEquals(model.getGrid().getTile(new Coordinate(2, 1)).getWorker().getPlayer(), artemis);
        assertTrue(model.getCurrentState() instanceof Build);
        int tmp= model.getGrid().getTile(new Coordinate(2,0)).getHeight().ordinal();
        artemis.makeBuild(model,new Build(new Coordinate(2,0)));
        assertEquals(model.getGrid().getTile(new Coordinate(2, 0)).getHeight().ordinal(), tmp+1);
        assertTrue(model.getCurrentState() instanceof End);






    }
}
