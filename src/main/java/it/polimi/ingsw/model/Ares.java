package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Coordinate;

public class Ares extends Player {

    private Coordinate wrkPosition;

    @Override
    public boolean positionWorker(Model model, Coordinate destination) {
        return super.positionWorker(model, destination);
        wrkPosition = super.positionWorker(destination;
    }

    @Override
    public void nextPhase(Model model) {

    }

    @Override
    public boolean makePower(Model model, Coordinate destination) {
        return false;
    }
}
