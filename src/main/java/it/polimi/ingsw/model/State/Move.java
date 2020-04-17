package it.polimi.ingsw.model.State;


import it.polimi.ingsw.utility.Coordinate;

/**
 * in this class one of main actions of the game is described.
 */

public class Move implements State {
    /**
     * class attribute that represents chosen coordinate
     */
    Coordinate choice;

    /**
     * This constructor sets passed coordinate to choice
     * @param coordinate passed
     */
    public Move(Coordinate coordinate){
        this.choice = coordinate;
    }

    /**
     * getter mehod of type Coordinate to access the chosen coordinate
     * @return chosen coordinate
     */
    public Coordinate getChoice() {
        return choice;
    }

}
