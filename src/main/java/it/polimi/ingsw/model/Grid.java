package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Cardinal;
import it.polimi.ingsw.utility.Coordinate;

import javax.management.openmbean.TabularDataSupport;
import java.util.*;
import java.util.jar.Attributes;

public class Grid {

    public static final int N_ROWS = 5;
    public static final int N_COLS = 5;
    private final Tile[][] grid= new Tile[5][5];

    public Grid() {
        for(int i=0;i<N_ROWS;i++)
            for(int j=0;j<N_COLS;j++)
                grid[i][j]= new Tile(TypeBlock.FLOOR,new Coordinate(i,j));
    }

    public Tile getTile(Coordinate coordinate){
        if(contains(coordinate))
            return grid[coordinate.getX()][coordinate.getY()];
        else return null;
    }

    //@Overloading
    public Tile getTile(int x, int y){
       return this.getTile(new Coordinate(x,y));
    }

    public List<Coordinate> validTileAround(Coordinate from) {
        if(!contains(from))return null;
        List<Coordinate> result= new ArrayList<>();
        for (Cardinal c : Cardinal.values()) {
            Coordinate coordinate = from.shift(c);
            if (contains(coordinate))
                result.add(coordinate);
        }
        return result;

    }

    public boolean checkDestination(Coordinate from,Coordinate destination){
        List<Coordinate> validCoordinate = validTileAround(from);
        if(validCoordinate==null) return false;
        return validCoordinate.contains(destination);

    }

    public boolean contains (Coordinate coordinate){
        int x= coordinate.getX();
        int y= coordinate.getY();
        return 0 <= x && x < N_ROWS && 0 <= y && y < N_COLS;
    }

    public int HeightDifference(Coordinate from, Coordinate destination){
      return getTile(from).getHeight().ordinal() - getTile(destination).getHeight().ordinal();

    }




}
