package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Cardinal;
import it.polimi.ingsw.utility.Coordinate;

import javax.management.openmbean.TabularDataSupport;
import java.io.Serializable;
import java.util.*;
import java.util.jar.Attributes;

/**
 * This class describes a board of the game. There are methods which verifies that chosen coordinate is actually on the board
 *
 * @author CG51
 * @version 0.1
 */
public class Grid  implements Serializable {
    /**
     * This field holds information of the size of game board. The grid contains 5 rows and 5 columns. The operation place is defined by tile.
     */
    private static final long serialVersionUID = 2L;
    public static final int N_ROWS = 5;
    public static final int N_COLS = 5;
    private final Tile[][] grid = new Tile[5][5];

    /**
     * Initialization of the grid with the floor level, which means board is ready for the game.
     */
    public Grid() {
        for (int i = 0; i < N_ROWS; i++)
            for (int j = 0; j < N_COLS; j++)
                grid[i][j] = new Tile(TypeBlock.FLOOR, new Coordinate(i, j));
    }

    /**
     * Method checks the coordinate if it is the part of the grid
     *
     * @param coordinate
     * @return coordinate, if the wrong coordinate is entered returns null
     */
    public Tile getTile(Coordinate coordinate) {
        if (contains(coordinate))
            return grid[coordinate.getX()][coordinate.getY()];
        else return null;
    }

    /**
     * This method is used when coordinate is taken from input
     *
     * @param x abscissa of input, integer value
     * @param y ordinate of input, integer value
     * @return coordinate of input
     */
    //@Overloading
    public Tile getTile(int x, int y) {
        return this.getTile(new Coordinate(x, y));
    }

    /**
     * This method examines the possible coordinates around of the parting point "from", keeps obtained values in array
     *
     * @param from of type Coordinate, the starting point of the tile
     * @return array with values of possible coordinates
     */
    public List<Coordinate> validTileAround(Coordinate from) {
        if (!contains(from)) return null;
        List<Coordinate> result = new ArrayList<>();
        for (Cardinal c : Cardinal.values()) {
            Coordinate coordinate = from.shift(c);
            if (contains(coordinate))
                result.add(coordinate);
        }
        return result;

    }

    /**
     * Checks the destination whether it is reachable or not
     *
     * @param from        starting point of the tile
     * @param destination end point of the tile
     * @return set of reachable coordinates or false if there is no possible coordinates around starting point
     */
    public boolean checkDestination(Coordinate from, Coordinate destination) {
        List<Coordinate> validCoordinate = validTileAround(from);
        if (validCoordinate == null) return false;
        return validCoordinate.contains(destination);

    }

    /**
     * @param coordinate
     * @return
     */
    public boolean contains(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        return 0 <= x && x < N_ROWS && 0 <= y && y < N_COLS;
    }

    /**
     * Method calculates the level difference between starting and end points of the tile
     *
     * @param from        starting point
     * @param destination ending point
     * @return integer value of level difference
     */
    public int HeightDifference(Coordinate from, Coordinate destination) {
        return getTile(from).getHeight().ordinal() - getTile(destination).getHeight().ordinal();

    }

    public void print(){
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                if(getTile(i, j).getWorker() == null){
                    if(getTile(i, j).getHeight() == TypeBlock.FLOOR){
                        System.out.print("O");
                    }
                    else if(getTile(i, j).getHeight() == TypeBlock.FIRST){
                        System.out.print("1");
                    }
                    else if(getTile(i, j).getHeight() == TypeBlock.SECOND){
                        System.out.print("2");
                    }
                    else if(getTile(i, j).getHeight() == TypeBlock.THIRD){
                        System.out.print("3");
                    }
                    else if(getTile(i, j).getHeight() == TypeBlock.DOME){
                        System.out.print("D");
                    }
                }
                else System.out.print("+");
                if(j==4) System.out.print("\n");
            }
        }
    }


}
