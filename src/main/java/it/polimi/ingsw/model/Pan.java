package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.Choice;

import java.util.List;

public class Pan extends Player{


    /**
     * @param workers
     * @param playerID
     */
    public Pan(List<Worker> workers, String playerID) {
        super(workers, playerID);
    }

    @Override
    public void nextPhase(Model model) {

    }

    @Override
    public void makePower(Model model, Choice choice) {

    }
}
