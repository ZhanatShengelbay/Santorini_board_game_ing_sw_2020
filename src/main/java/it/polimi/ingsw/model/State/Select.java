package it.polimi.ingsw.model.State;

import it.polimi.ingsw.utility.Coordinate;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * This class describes selection during the game
 *  @author CG51
 *  @version 0.1
 */
public class Select implements State {
    /**
     * class attributes necessary inside this class.
     */
    Coordinate coordinate;
    Scanner inStream;
    PrintStream outStream;


    public Select(Coordinate coordinate){
        this.coordinate = coordinate;
        inStream = new Scanner(System.in);
        outStream = new PrintStream(System.out);
    }

    /**
     * Overriden toString method to print the needed text message
     * @return wanted text message
     */
    @Override
    public String toString() {
        return "Select worker through coordinates";
    }

    /**
     * Getter method to access the input information of user (player) from scanner. Method sets the abscissa and the ordinate points based on input information
     */
    public void getInput(){
        String inp;
        inp = inStream.next();
        coordinate.setX(Integer.parseInt(inp));
        inp = inStream.next();
        coordinate.setY(Integer.parseInt(inp));
    }

    /**
     * Overriden getter method to access the coordinate
     * @return coordinate
     */
    @Override
    public Coordinate getChoice() {
        return coordinate;
    }
}