package it.polimi.ingsw.model.State;

import it.polimi.ingsw.utility.Coordinate;

public class Build implements State {
    Coordinate choice;

    public Build(Coordinate coordinate){
        this.choice = coordinate;
    }

    public Coordinate getChoice() {
        return choice;
    }
}
