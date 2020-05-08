package it.polimi.ingsw.model.State;

import it.polimi.ingsw.model.Checks;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.State.State;
import it.polimi.ingsw.model.playerChoice.GameChoice;
import it.polimi.ingsw.utility.Coordinate;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Scanner;

/**
 * In this class the action of locating the worker is described. It has the following methods:
 */
public class PositionWorkers implements State, Serializable {

    int numOfWorkerToBePlaced;
    private static final long serialVersionUID = 9L;

    public PositionWorkers() {
        this.numOfWorkerToBePlaced = 2;
    }

    @Override
    public boolean handle(Coordinate choice, Model model) {
        boolean result = model.getCurrentPlayer().positionWorker(model,choice);
        if(result)
            if(numOfWorkerToBePlaced>1)numOfWorkerToBePlaced--;
            else model.nextPlayer();

        return result;
    }

    @Override
    public String questionMessage() {
        return null;
    }
}
