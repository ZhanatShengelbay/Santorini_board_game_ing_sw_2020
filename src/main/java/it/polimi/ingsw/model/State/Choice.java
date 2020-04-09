package it.polimi.ingsw.model.State;

import it.polimi.ingsw.utility.Coordinate;


public class Choice implements State{

    State state;
    Coordinate choice;


    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    @Override
    public Coordinate getChoice() {
        return choice;
    }
}
