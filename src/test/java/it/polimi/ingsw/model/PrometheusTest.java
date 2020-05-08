package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.End;
import it.polimi.ingsw.model.State.Power;
import it.polimi.ingsw.model.State.Select;
import it.polimi.ingsw.utility.Coordinate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrometheusTest {
    Model model;
    Player prometheus;

    @Before
    public void setupGridTest(){

        prometheus = new Prometheus("playerTest");
        prometheus.addWorker();

        model = new Model();
        model.getGrid().getTile(new Coordinate(1, 0)).levelUp().setWorker(prometheus.getWorker(0));
        model.getGrid().getTile(new Coordinate(1,1)).levelUp();
    }
    @Test
    public void firstTest(){
        model.setCurrentState(new Select());
        prometheus.makeSelection(model,new Coordinate(1,0));
        assertTrue(model.getCurrentState() instanceof Power);
        prometheus.togglePower();
        int height= model.getGrid().getTile(1,1).getHeight().ordinal();
        prometheus.makePower(model,new Coordinate(1,1));
        assertEquals(model.getGrid().getTile(1,1).getHeight().ordinal(),height+1);
        assertFalse(prometheus.makeMovement(model,new Coordinate(1,1)));
        prometheus.makeMovement(model,new Coordinate(2,1));
        prometheus.makeBuild(model,new Coordinate(1,1));
        assertTrue(model.getCurrentState() instanceof End);

    }
    @Test
    public void secondTest(){
        model.setCurrentState(new Select());
        model.getGrid().getTile(new Coordinate(1,1)).levelUp();

        prometheus.makeSelection(model,new Coordinate(1,0));
        assertTrue(model.getCurrentState() instanceof Power);
        prometheus.makePower(model,new Coordinate(1,1));
        assertEquals(model.getGrid().getTile(1,1).getWorker(),prometheus.getWorker(0));
        prometheus.makeBuild(model,new Coordinate(2,1));
        assertTrue(model.getCurrentState() instanceof End);

    }

}
