package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

/**
 * The power of god Hephaestus is described in this class.
 * The player who owns the god may build 1 additional block, but NOT DOME, on top of the first block
 * @author CG51
 * @version 0.1
 */
public class Hephaestus extends Player {

    private Coordinate prevDestination;

    /**
     * Constructor to keep the player's value, the one who owns this god
     *
     * @param playerID player's name
     */
    public Hephaestus(String playerID) {
        super(playerID);
    }

    /**
     * Overridden to check if the destination is the same as previous built place
     *
     * @param model
     * @param destination
     * @return true or false depending on the condition's result
     */
    @Override
    public boolean makeBuild(Model model, Coordinate destination) {
        boolean lastBuilt = super.makeBuild(model, destination);
        if (lastBuilt) prevDestination = destination;
        return lastBuilt;
    }

    /**
     * Overridden method to set the behavior of the State according to the God's power
     * while the turn of player starts current state's value is received from the model
     * and the assignment of next state's value depends on the current state
     *
     * @param model The model where set the new current State
     */
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

    /**
     * this method defines how the god's power should be used
     *
     * @param model
     * @param destination
     */
    @Override
    public boolean makePower(Model model, Coordinate destination) {
        if (isActive()) {
            model.setCurrentState(new Build());
            int lastHeight = model.getGrid().getTile(prevDestination).getHeight().ordinal();
            if (lastHeight < 3) {
                model.getGrid().getTile(prevDestination).levelUp();
                nextPhase(model);
                return true;
            } else {
                model.setCurrentState(new Power());
                return false;
            }
        } else {
            model.setCurrentState(new End());
            return true;
        }
    }
}
