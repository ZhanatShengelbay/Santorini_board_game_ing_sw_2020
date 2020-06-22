package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.Build;
import it.polimi.ingsw.model.State.End;
import it.polimi.ingsw.model.State.Move;
import it.polimi.ingsw.model.State.Select;
import it.polimi.ingsw.utility.Coordinate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ZeusTest {
    Model model;
    Player zeus;

    @Before
    public void setupGridTest(){
        zeus = new Zeus("zeustest", model);
        zeus.addWorker();
        zeus.addWorker();
        model = new Model();
        model.getGrid().getTile(new Coordinate(1, 0)).levelUp().setWorker(zeus.getWorker(0));
        model.getGrid().getTile(new Coordinate(1, 1)).levelUp();
        model.getGrid().getTile(new Coordinate(2, 0)).levelUp();
        model.getGrid().getTile(new Coordinate(3, 0)).levelUp().levelUp().levelUp().setWorker(zeus.getWorker(1));
        model.getGrid().getTile(new Coordinate(4, 0)).levelUp().levelUp().levelUp();

    }

    @Test
    public void zeusSelfPlaceBuildTest(){
        model.setCurrentState(new Select());
        zeus.makeSelection(new Coordinate(1,0));
        Coordinate destination = new Coordinate(1,1);
        assertTrue(model.getCurrentState() instanceof Move);
        zeus.makeMovement(destination);
        assertEquals("zeus should move", model.getGrid().getTile(destination).getWorker(), zeus.getWorker(0));

        int beforeHeight = model.getGrid().getTile(destination).getHeight().ordinal();
        assertTrue(model.getCurrentState() instanceof Build);
        zeus.makeBuild(destination);
        assertEquals("zeus should build", beforeHeight + 1, model.getGrid().getTile(destination).getHeight().ordinal());
        assertTrue(model.getCurrentState() instanceof End);
    }

    @Test
    public void zeusUsualTest(){
        model.setCurrentState(new Select());
        zeus.makeSelection(new Coordinate(1,0));
        Coordinate destination = new Coordinate(1,1);
        assertTrue(model.getCurrentState() instanceof Move);
        zeus.makeMovement(destination);
        assertEquals("zeus should move", model.getGrid().getTile(destination).getWorker(), zeus.getWorker(0));

        int beforeHeight = model.getGrid().getTile(new Coordinate(2,1)).getHeight().ordinal();
        assertTrue(model.getCurrentState() instanceof Build);
        zeus.makeBuild(new Coordinate(2,1));
        assertEquals("zeus should build", beforeHeight + 1, model.getGrid().getTile(new Coordinate(2,1)).getHeight().ordinal());
        assertTrue(model.getCurrentState() instanceof End);
    }

    @Test
    public void zeusDomeTest(){
        model.setCurrentState(new Select());
        zeus.makeSelection(new Coordinate(3,0));

        Coordinate destination = new Coordinate(4,0);
        assertTrue(model.getCurrentState() instanceof Move);
        zeus.makeMovement(destination);
        assertEquals("zeus should move", zeus.getWorker(1), model.getGrid().getTile(destination).getWorker());

        int bfHt = model.getGrid().getTile(new Coordinate(3,1)).getHeight().ordinal();
        assertTrue(model.getCurrentState() instanceof Build);
        assertEquals("zeus shouldnt build since next block to be build would be DOME",false, zeus.makeBuild(destination));
        zeus.makeBuild(new Coordinate(3,1));
        assertEquals("zeus should build in different place", bfHt + 1, model.getGrid().getTile(3,1).getHeight().ordinal());

        assertTrue(model.getCurrentState() instanceof End);
    }


}