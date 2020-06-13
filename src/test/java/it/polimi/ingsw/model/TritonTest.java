package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TritonTest {
    Model model;
    Player triton;

    @Before
    public void setupGridTest(){

        triton = new Triton("tritonTest");
        triton.addWorker();
        triton.addWorker();

        model = new Model();
        model.getGrid().getTile(new Coordinate(1, 1)).levelUp().setWorker(triton.getWorker(0));
        model.getGrid().getTile(new Coordinate(0,1)).levelUp();
        model.getGrid().getTile(new Coordinate(0,2)).levelUp().levelUp();
        model.getGrid().getTile(new Coordinate(3,0)).levelUp();
        model.getGrid().getTile(new Coordinate(2,0)).levelUp().setWorker(triton.getWorker(1));
        model.getGrid().getTile(new Coordinate(2,1)).levelUp();
    }
    @Test
    public void TritonTest(){
        model.setCurrentState(new Select());
        model.setCurrentPlayer(triton);
        triton.makeSelection(model, new Coordinate(1, 1));
        Coordinate destination = new Coordinate(0,1);
        assertTrue(model.getCurrentState() instanceof Move);
        triton.makeMovement(model, destination);
        assertEquals(triton, model.getGrid().getTile(destination).getWorker().getPlayer(), "triton should move");
        int height=model.getGrid().getTile(destination).getHeight().ordinal();
        assertTrue(model.getCurrentState() instanceof Power);
        triton.togglePower();
        triton.makePower(model,new Coordinate(0,2));
        assertEquals(triton, model.getGrid().getTile(new Coordinate(0,2)).getWorker().getPlayer(), "triton should move");
        assertFalse(triton.isActive());
        assertTrue(model.getCurrentState() instanceof Power);
        triton.makePower(model,destination);
        assertEquals(height+1,model.getGrid().getTile(destination).getHeight().ordinal());

    }
}
