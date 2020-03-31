package it.polimi.ingsw;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.utility.Coordinate;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class PlayerTest {

    private Model model;
    private Grid grid;
    private Player player;
    private Worker worker1;
    private Worker worker2;
    private List<Worker> workers = new ArrayList<Worker>();
    private Coordinate coordinate;
    private Coordinate tmpCoordinate;
    private Coordinate moveCoordinate;
    private Coordinate buildCoordinate;
    private int worker_index;
    private PositionWorkers posWorker;
    private Select select;
    private Move move;
    private Build build;

    @Before
    public void setUp(){
        worker_index = 0;
        worker1 = new Worker();
        worker2 = new Worker();
        workers.add(worker1);
        workers.add(worker2);
        grid = new Grid();
        model = new Model(grid);
        player = new Player(workers, "testplayer");
        coordinate = new Coordinate(2, 3);
        moveCoordinate = new Coordinate(1,3);
        tmpCoordinate = new Coordinate(0,0);
        buildCoordinate = new Coordinate(2,4);
        posWorker = new PositionWorkers(coordinate, worker_index);
        select = new Select(coordinate);
        move = new Move(moveCoordinate);
        build = new Build(buildCoordinate);
    }

    @Test
    public void testPositionWorker(){
        player.positionWorker(model, posWorker);
        assertEquals(grid.getTile(coordinate).getWorker(), player.getWorker(worker_index));
        for(int i = 0; i < 5 && i!=coordinate.getX(); i++){
            for(int j = 0; j < 5 && j!=coordinate.getY(); j++){
                tmpCoordinate.setX(i);
                tmpCoordinate.setY(j);
                assertNull(grid.getTile(tmpCoordinate).getWorker());
            }
        }
    }

    @Test
    public void testMakeSelection(){
        player.positionWorker(model, posWorker);
        player.makeSelection(model, select);
        assertEquals(grid.getTile(model.getCurrentWorker()).getWorker(), grid.getTile(coordinate).getWorker());
        assertEquals(model.getCurrentWorker(), select.getCoordinate());
    }

    @Test
    public void testMakeMovement(){
        player.positionWorker(model, posWorker);
        player.makeSelection(model, select);
        Coordinate oldPosition = model.getCurrentWorker();
        player.makeMovement(model, move);
        assertNull(grid.getTile(oldPosition).getWorker());
        assertEquals(grid.getTile(model.getCurrentWorker()).getWorker(), grid.getTile(moveCoordinate).getWorker());
        assertEquals(grid.getTile(move.getChoice()).getWorker(), grid.getTile(moveCoordinate).getWorker());
    }

    @Test
    public void testMakeBuild(){
        player.positionWorker(model, posWorker);
        player.makeSelection(model, select);
        player.makeBuild(model, build);
        assertEquals(TypeBlock.FIRST, grid.getTile(build.getChoice()).getHeight());
    }

    @Test
    public void testBuildMove(){
        player.positionWorker(model, posWorker);
        player.makeSelection(model, select);
        player.makeBuild(model, build);
        player.makeBuild(model, build);
        player.makeBuild(model, build);
        player.makeBuild(model, build);
        move = new Move(new Coordinate(2,4));
        Coordinate oldPosition = model.getCurrentWorker();
        player.makeMovement(model, move);
        assertEquals(grid.getTile(model.getCurrentWorker()).getWorker(), grid.getTile(oldPosition).getWorker());
        assertEquals(TypeBlock.DOME, grid.getTile(build.getChoice()).getHeight());
    }
}
