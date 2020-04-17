package it.polimi.ingsw.model.State;

import it.polimi.ingsw.utility.Coordinate;

/**
 * The class Choice implements S
 * @author CG51
 * @version 0.1
 */

public class Choice implements State{
    /**
     * class attribute field to store instance variables state and chosen coordinate
     */
    State state;
    Coordinate choice;

    /**
     * setter method to set passed state value
     * @param state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * getter method to access the state
     * @return state
     */
    public State getState() {
        return state;
    }

    /**
     * overriden getter method to access to chosen coordinate
     * @return chosen coordinate
     */
    @Override
    public Coordinate getChoice() {
        return choice;
    }
}
