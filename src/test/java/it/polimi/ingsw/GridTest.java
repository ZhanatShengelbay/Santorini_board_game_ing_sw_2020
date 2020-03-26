package it.polimi.ingsw;

import it.polimi.ingsw.model.Grid;
import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.utility.Coordinate;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class GridTest {

    private Grid grid;

    @Before
    public void gridSetUp(){
        this.grid = new Grid();
    }

    @Test
    public void testValidTile(){
        Coordinate c1 = new Coordinate(3,2);
        Coordinate c2 = new Coordinate(3,4);
        Coordinate c3 = new Coordinate(4,4);
        Coordinate c4 = new Coordinate(3,5);

        assertFalse(grid.checkDestination(c1,c2)); // no-near tile
        assertTrue(grid.checkDestination(c2,c3));  // near tile
        assertFalse(grid.checkDestination(c3,c4)); //near with a not valid tile in destination
        assertFalse(grid.checkDestination(c4,c2)); //near with a not valid tile in from



        }


}
