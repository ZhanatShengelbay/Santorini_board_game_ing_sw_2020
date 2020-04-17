package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.Move;
import it.polimi.ingsw.model.State.Select;
import it.polimi.ingsw.model.State.Win;
import it.polimi.ingsw.utility.Coordinate;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PanTest {
    Model model;
    Player pan;


    @Test
    public void usePowerToWinTest(){

        List<Worker> workerList = new ArrayList<>();
        Worker worker1 = new Worker();
        Worker worker2 = new Worker();
        workerList.add(worker1);
        workerList.add(worker2);
        pan = new Pan(workerList, "panTest");
        boolean isActive = true;

        model = new Model(new Grid());
        model.getGrid().getTile(new Coordinate(1,1)).levelUp().levelUp().levelUp().setWorker(worker1);
        model.getGrid().getTile(new Coordinate(2, 0)).levelUp();
        model.getGrid().getTile(new Coordinate(2,3)).levelUp().levelUp().setWorker(worker2);

        model.setCurrentState(new Select(null));
        Coordinate from = new Coordinate(1,1);
        pan.makeSelection(model, new Select(from));
        assertTrue(model.getCurrentState() instanceof Move);
        Coordinate destination = new Coordinate(2,0);
        pan.makeMovement(model, new Move(destination));
        assertEquals("pan should be moved", pan, model.getGrid().getTile(destination).getWorker().getPlayer());
        assertEquals("pan should win",null, model.getCurrentState().getChoice());

    }

}