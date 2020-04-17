package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

import java.util.List;

/**
 *  power of Athena is described in this class. if player owns the Athena, if s/he moved up any of her/his workers,
 *  on opponent's turn opponent can NOT move up
 *  @author CG51
 *  @version 0.1
 */

public class Athena extends Player implements GroundEffect {

    private Coordinate from;

    /**
     * Constructor is used to initialize the object.
     * @param workers list of workers
     * @param playerID players name
     */
    public Athena(List<Worker> workers, String playerID) {
        super(workers, playerID);
    }

    /**
     * Method is overridden to keep info from where player moved
     * @param model
     * @param select
     */
    @Override
    public void makeSelection(Model model, Select select) {
        super.makeSelection(model, select);
        from = model.getCurrentWorker();

    }

    /**
     * Method is overridden to active the ground effect if player moved UP
     * @param model The model where the movement happened
     * @param move The Move state that contains input choice
     */
    @Override
    public void makeMovement(Model model, Move move) {
        super.makeMovement(model, move);
        if(model.getGrid().HeightDifference(from,move.getChoice())==1)
                addEffect(model);
    }

    /**
     * method defines the behavior of Athena
     * @param model sets new current State
     */
    @Override
    public void nextPhase(Model model) {
        State currentState=model.getCurrentState();
        State nextState=null;
        if(currentState instanceof Select)
            nextState=new Move(null);
        else if(currentState instanceof Move)
            nextState=new Build(null);
        else if(currentState instanceof Build)
            nextState=new End();
        model.setCurrentState(nextState);

    }

    /**
     * Method throws exception according to the power of Athena, in case he opponent tries to move up
     * @param model
     * @param choice
     */
    @Override
    public void makePower(Model model, Choice choice) {
        throw new IllegalStateException();

    }

    /**
     * The method defines that if Athena moved up, opponent should respect her power
     * @param model
     * @param from starting point of tile
     * @param destination ending point of tile
     * @return
     */
    @Override
    public boolean respectEffect(Model model,Coordinate from, Coordinate destination) {
        if(model.getCurrentState() instanceof Move)
            return model.getGrid().HeightDifference(from, destination) > 0;
        else return false;

    }

    /**
     * Adds the effect into model
     * @param model
     */
    @Override
    public void addEffect(Model model) {
        model.getGroundEffects().add(this);

    }
}
