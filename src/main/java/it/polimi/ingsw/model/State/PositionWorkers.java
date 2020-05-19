package it.polimi.ingsw.model.State;

import it.polimi.ingsw.model.Checks;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.State.State;
import it.polimi.ingsw.utility.Coordinate;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * In this class the action of locating the worker is described. It has the following methods:
 */
public class PositionWorkers implements State {

    int numOfWorkerToBePlaced;

    /**
     * Class constructor indicating that each player has exactly 2 workers
     */
    public PositionWorkers() {
        this.numOfWorkerToBePlaced = 2;
    }

    /**
     *
     * @param choice
     * @param model
     * @return
     */
    @Override
    public boolean handle(Coordinate choice, Model model) {
        boolean result = model.getCurrentPlayer().makeMovement(model,choice);
        if(result)
            if(numOfWorkerToBePlaced>0)numOfWorkerToBePlaced--;
            else model.nextPlayer();
        return result;
    }

    @Override
    public String questionMessage() {
        return null;
    }
}
