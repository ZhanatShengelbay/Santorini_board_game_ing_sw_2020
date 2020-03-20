package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Coordinate;
import java.io.PrintStream;
import java.util.Scanner;

public class Select implements State {

    Coordinate coordinate;
    Scanner inStream;
    PrintStream outStream;

    public Select(Coordinate coordinate){
        this.coordinate = coordinate;
        inStream = new Scanner(System.in);
        outStream = new PrintStream(System.out);
    }

    @Override
    public String toString() {
        return "Select worker through coordinates";
    }

    public void getInput(){
        String inp;
        inp = inStream.next();
        coordinate.setX(Integer.parseInt(inp));
        inp = inStream.next();
        coordinate.setY(Integer.parseInt(inp));
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}