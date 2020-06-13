package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.controller.SetUpController;
import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

public class HephaestusTest {
    Model model;
    Player hephaestus;

    @Before
    public void setupGridTest(){

        hephaestus = new Hephaestus("hephaestusTest");
        hephaestus.addWorker();
        hephaestus.addWorker();

        model = new Model();
        model.getGrid().getTile(new Coordinate(4,0)).levelUp().setWorker(hephaestus.getWorker(0));
        model.getGrid().getTile(new Coordinate(4,1)).levelUp();
        model.getGrid().getTile(new Coordinate(3,1)).levelUp().levelUp();
        model.getGrid().getTile(new Coordinate(3,0)).levelUp();
        model.getGrid().getTile(new Coordinate(2,0)).levelUp().setWorker(hephaestus.getWorker(1));
        model.getGrid().getTile(new Coordinate(2,1)).levelUp();
    }
    @Test
    public void NotDomeSecondBuildTest(){
        model.setCurrentState(new Select());
        model.setCurrentPlayer(hephaestus);
        hephaestus.makeSelection(model, new Coordinate(4,0));
        Coordinate destination = new Coordinate(3,1);
        assertTrue(model.getCurrentState() instanceof Move);
        hephaestus.makeMovement(model, destination);
        assertEquals(hephaestus, model.getGrid().getTile(destination).getWorker().getPlayer(), "hephaestus should move");

        assertTrue(model.getCurrentState() instanceof Build);
        final Coordinate bldDestionation = new Coordinate(4,1);
        int beforeHeight = model.getGrid().getTile(bldDestionation).getHeight().ordinal();
        hephaestus.makeBuild(model, bldDestionation);
        assertEquals(beforeHeight+1, model.getGrid().getTile(bldDestionation).getHeight().ordinal());

        assertTrue(model.getCurrentState() instanceof Power);
        hephaestus.makePower(model, bldDestionation);
        assertNotEquals(beforeHeight + 2, model.getGrid().getTile(bldDestionation).getHeight().ordinal());
        assertTrue(model.getCurrentState() instanceof End);
    }
    @Test
    public void hephaestusPowerTest(){
        model.setCurrentState(new Select());
        model.setCurrentPlayer(hephaestus);
        hephaestus.makeSelection(model, new Coordinate(2,0));
        Coordinate destination = new Coordinate(3,0);
        assertTrue(model.getCurrentState() instanceof Move);
        hephaestus.makeMovement(model, destination);
        assertEquals(hephaestus, model.getGrid().getTile(destination).getWorker().getPlayer(), "hephaestus should move");

        assertTrue(model.getCurrentState() instanceof Build);
        Coordinate bldDestionation = new Coordinate(2,1);
        int beforeHeight = model.getGrid().getTile(bldDestionation).getHeight().ordinal();
        hephaestus.makeBuild(model, bldDestionation);
        assertEquals(beforeHeight+1, model.getGrid().getTile(bldDestionation).getHeight().ordinal());

        assertTrue(model.getCurrentState() instanceof Power);
        hephaestus.togglePower();
        hephaestus.makePower(model, bldDestionation);
        assertEquals(beforeHeight + 2, model.getGrid().getTile(bldDestionation).getHeight().ordinal());
        assertTrue(model.getCurrentState() instanceof End);
    }
    @Test
    public void differentDestinationTest(){
        model.setCurrentState(new Select());
        model.setCurrentPlayer(hephaestus);
        hephaestus.makeSelection(model, new Coordinate(2,0));
        Coordinate destination = new Coordinate(3,0);
        assertTrue(model.getCurrentState() instanceof Move);
        hephaestus.makeMovement(model, destination);
        assertEquals(hephaestus, model.getGrid().getTile(destination).getWorker().getPlayer(), "hephaestus should move");

        assertTrue(model.getCurrentState() instanceof Build);
        Coordinate bldDestionation = new Coordinate(2,1);
        int beforeHeight = model.getGrid().getTile(bldDestionation).getHeight().ordinal();
        hephaestus.makeBuild(model, bldDestionation);
        assertEquals(beforeHeight+1, model.getGrid().getTile(bldDestionation).getHeight().ordinal());

        int bfHght = model.getGrid().getTile(new Coordinate(4,1)).getHeight().ordinal();
        assertTrue(model.getCurrentState() instanceof Power);
        hephaestus.togglePower();
        hephaestus.makePower(model, new Coordinate(4,1));
        assertEquals(bfHght, model.getGrid().getTile(new Coordinate(4,1)).getHeight().ordinal());
        assertTrue(model.getCurrentState() instanceof End);
    }

}