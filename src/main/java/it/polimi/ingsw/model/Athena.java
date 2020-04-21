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

public class Athena extends PlayerWithGroundEffect {

    private Coordinate from;

    /**
     * Constructor is used to initialize the object.
     *
     * @param playerID players name
     */
    public Athena( String playerID) {
        super(playerID);
    }

    /**
     * Method is overridden to keep info from where player moved
     * @param model
     * @param selection
     */
    @Override
    public boolean makeSelection(Model model, Coordinate selection) {
        boolean result = super.makeSelection(model, selection);
        if(result)from = selection;
        return result;

    }

    /**
     * Method is overridden to active the ground effect if player moved UP
     * @param destination The input choice
     * @param model The model where the movement happened
     * @return
     */
    @Override
    public boolean makeMovement(Model model, Coordinate destination) {
        boolean result = super.makeMovement(model, destination);
        if(result)
            if(model.getGrid().HeightDifference(from,destination)==1)
                addEffect(model);
        return result;
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
            nextState=new Move();
        else if(currentState instanceof Move)
            nextState=new Build();
        else if(currentState instanceof Build)
            nextState=new End();
        model.setCurrentState(nextState);

    }

    /**
     * Method throws exception according to the power of Athena, in case he opponent tries to move up
     * @param model
     * @param destination
     * @return
     */
    @Override
    public boolean makePower(Model model, Coordinate destination) {
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


}
