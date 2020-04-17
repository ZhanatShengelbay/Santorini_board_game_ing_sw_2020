package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.State;
import it.polimi.ingsw.utility.Coordinate;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * In this class the action of locating the worker is described. It has the following methods:
 */
public class PositionWorkers implements State {

    /**
     * Class attributes field. Coordinate, Scanner and PrintStream are used
     */
    Coordinate coordinate;
    Scanner inStream;
    PrintStream outStream;
    int num;

    /**
     * Getter method to get the chosen coordinate
     * @return coordinate of choice
     */
    @Override
    public Coordinate getChoice() {
        return coordinate;
    }

    /**
     * Constructor to set current(actual) values to the corresponding parameters
     * @param coordinate of choice
     * @param num index of worker, 1 or 2, is used to distinguish between 2 workers of the player
     */
    public PositionWorkers(Coordinate coordinate, int num){
        this.coordinate = coordinate;
        this.num = num;
        inStream = new Scanner(System.in);
        outStream = new PrintStream(System.out);
    }

    /**
     * Overriden toString method to display to the player that s/he should choose the place to locate her/his worker
     * @return text information
     */
    @Override
    public String toString() {
        return "Choose where to place your worker";
    }

    /**
     * Getter method to access the info from input
     */
    public void getInput(){
        String inp;
        inp = inStream.next();
        coordinate.setX(Integer.parseInt(inp));
        inp = inStream.next();
        coordinate.setY(Integer.parseInt(inp));
    }

    /**
     * Getter method to access the worker's index
     * @return
     */
    public int getNum(){
        return num;
    }
}
