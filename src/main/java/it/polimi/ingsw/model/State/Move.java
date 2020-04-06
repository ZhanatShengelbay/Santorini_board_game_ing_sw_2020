package it.polimi.ingsw.model.State;


import it.polimi.ingsw.utility.Coordinate;

public class Move implements State {

    Coordinate choice;

    public Move(Coordinate coordinate){
        this.choice = coordinate;
    }

    public Coordinate getChoice() {
        return choice;
    }

}
