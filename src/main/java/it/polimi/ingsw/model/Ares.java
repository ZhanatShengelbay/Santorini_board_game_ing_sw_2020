package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Coordinate;

public class Ares extends Player {

    private Coordinate wrkPosition;

    /**
     * @param playerID
     */
    public Ares(String playerID) {
        super(playerID);
    }


    @Override
    public void nextPhase(Model model) {

    }

    @Override
    public boolean makePower(Model model, Coordinate destination) {
        return false;
    }
}
