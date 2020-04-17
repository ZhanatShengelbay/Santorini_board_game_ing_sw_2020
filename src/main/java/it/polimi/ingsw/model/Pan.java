package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

import java.util.Collections;
import java.util.List;

/**
 * This class describes one of gods - Pan's power during the game;
 */
public class Pan extends Player {

    private boolean isActive;
    private Coordinate destination;
    private Coordinate from;


    /**
     * Constructor to set the player's info
     * @param workers
     * @param playerID
     */
    public Pan(List<Worker> workers, String playerID) {
        super(workers, playerID);
    }

    /**
     * Overridden in order to add Pan's power, i.e, Player that holds Pan wins if his worker moves down 2 or 3 levels together with ordinary case
     * @param model
     * @param from
     * @param destination
     * @return
     */
    @Override
    public boolean winCondition(Model model, Coordinate from, Coordinate destination) {
        int heightDiff = model.getGrid().HeightDifference(from, destination);
        if (heightDiff >= 2) {
            return true;
        }
        return super.winCondition(model, from, destination);
    }

    /**
     * Method defines how Pan's turn is carried out
     * @param model to set the new current state
     */
    @Override
    public void nextPhase(Model model) {
        State currentState = model.getCurrentState();
        State nextState = null;
        if (currentState instanceof Select)
            nextState = new Move(null);

        else if (currentState instanceof Move)
            nextState = new Build(null);

        else if (currentState instanceof Build)
            nextState = new End();
        model.setCurrentState(nextState);

    }

    /**
     * Power of Pan is used if it's active checks immediately if he wins, otherwise moves into the next phase of model
     * @param model
     * @param choice
     */
    @Override
    public void makePower(Model model, Choice choice) {
//        if (choice.getState() instanceof Build) {
//            model.setCurrentState(choice.getState());
//            makeBuild(model, (Build) choice.getState());
//        } else {
           if (isActive == true){
            if (winCondition(model, from, destination))
                model.setCurrentState(new Win());
            else {
                nextPhase(model);
            }
        }

    }
}