package it.polimi.ingsw.model.State;

import it.polimi.ingsw.model.State.State;
import it.polimi.ingsw.utility.Coordinate;

import java.io.PrintStream;
import java.util.Scanner;

public class PositionWorkers implements State {

    Coordinate coordinate;
    Scanner inStream;
    PrintStream outStream;
    int num;

    @Override
    public Coordinate getChoice() {
        return coordinate;
    }

    public PositionWorkers(Coordinate coordinate, int num){
        this.coordinate = coordinate;
        this.num = num;
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
