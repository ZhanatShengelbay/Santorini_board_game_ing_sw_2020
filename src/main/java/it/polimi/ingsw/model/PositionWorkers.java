package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Coordinate;

import java.io.PrintStream;
import java.util.Scanner;

public class PositionWorkers implements State{

    Coordinate coordinate;
    Scanner inStream;
    PrintStream outStream;
    int num;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public PositionWorkers(Coordinate coordinate){
        this.coordinate = coordinate;
        inStream = new Scanner(System.in);
        outStream = new PrintStream(System.out);
    }

    @Override
    public String toString() {
        return "Choose where to place your worker";
    }

    public void getInput(){
        String inp;
        inp = inStream.next();
        coordinate.setX(Integer.parseInt(inp));
        inp = inStream.next();
        coordinate.setY(Integer.parseInt(inp));
    }

    public int getNum(){
        return num;
    }
}