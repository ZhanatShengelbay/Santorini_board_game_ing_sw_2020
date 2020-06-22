package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.Select;
import it.polimi.ingsw.utility.Coordinate;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DefeatTest {

    Model model;
    List<Player> players = new ArrayList<>();

    @Before
    public void defeatSetUp() {
        model = new Model();
        players.add(new Artemis("player1", model));
        players.add(new Pan("player2", model));
        players.add(new Atlas("player3", model));
        model.players = players;
        model.setCurrentPlayer(players.get(0));
        model.setCurrentState(new Select());
        players.get(0).positionWorker(new Coordinate(0,1));
        players.get(0).positionWorker(new Coordinate(1,0));
        players.get(1).positionWorker(new Coordinate(0,0));
        players.get(1).positionWorker(new Coordinate(4,4));
        players.get(2).positionWorker(new Coordinate(1,1));
        players.get(2).positionWorker(new Coordinate(4,3));
        model.getGrid().getTile(3,4).levelUp().levelUp().levelUp();
        model.getGrid().getTile(3,3).levelUp().levelUp().levelUp();
    }

    @Test
    public void defeatTest(){
        model.nextPlayer();
        assertEquals(players.get(2).getPlayerID(), model.getCurrentPlayer().getPlayerID());
        assertNull(model.getGrid().getTile(0,0).getWorker());
        assertNull(model.getGrid().getTile(4,4).getWorker());
        model.nextPlayer();
        assertEquals(players.get(0).getPlayerID(), model.getCurrentPlayer().getPlayerID());
        model.nextPlayer();
        assertEquals(players.get(2).getPlayerID(), model.getCurrentPlayer().getPlayerID());
    }
}
