package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * Class defines the power of Ares, the God of War, through the methods below.
 * Ares gives the power of an extra removing action, which takes place at the end of the Ares' turn
 * At the end Ares' worker may remove an unoccupied block (NOT DOME)
 * neighboring second, i.e unmoved, worker of Ares.
 */
public class AresDraft extends Player {


    private List<Worker> workerList;

    private Worker wrkNum;
    private Worker movedWrkNum;
    private Coordinate wrkPlace;
    private Coordinate prevDest;


    public AresDraft(String PlayerID) {
        super(PlayerID);
    }


    @Override
    public boolean positionWorker(Model model, Coordinate destination) {
        boolean result =  super.positionWorker(model, destination);
        if (result) {
            wrkPlace = destination;
            workerList.add(model.getGrid().getTile(destination).getWorker());
        }

        return result;
    }

//    @Override
//    public boolean makeSelection(Model model, Coordinate selection) {
//        boolean selectedWrk = super.makeSelection(model, selection);
//        if (selectedWrk) movedWrk = selection;
//        return selectedWrk;
//    }


    @Override
    public boolean makeMovement(Model model, Coordinate destination) {
        boolean movedWr = super.makeMovement(model, destination);
         if (movedWr) prevDest = destination;
        if (movedWr) movedWrkNum = model.getGrid().getTile(destination).getWorker();
        return movedWr;

    }

    @Override
    public void nextPhase(Model model) {
        State currentState = model.getCurrentState();
        State nextState = null;
        if (currentState instanceof Select)
            nextState = new Move();
        else if (currentState instanceof Move)
            nextState = new Build();
        else if (currentState instanceof Build) {
            if (isActive()) {
                nextState = new End();
                togglePower();
            } else nextState = new Power();
        } else
            nextState = new End();

        model.setCurrentState(nextState);

    }



    @Override
    public boolean makePower(Model model, Coordinate destination) {
       if (isActive()) {
           Coordinate removingPlace = model.getCurrentPlayer().;
           setValidCoordinate(new Checks(model, hgjhg).isNotWorker().isNotDome());
                if (containsInValidCoordinate(destination)) {
                    model.getGrid().getTile(destination).reduceBlock();
                    nextPhase(model);
                    return true;
                } else return false;
            }
        model.setCurrentState(new End());
        return true;
    }


}
