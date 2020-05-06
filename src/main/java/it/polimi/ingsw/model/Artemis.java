package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

import java.util.List;
/**
 *  The power of god Artemis is described in this class. Player holding this god may move 1 more additional time,
 *  but not back to its initial space
 *  @author CG51
 *  @version 0.1
 */
public class Artemis extends Player {
    /**
     * Class attributes
     */
    private Coordinate from;


    /**
     * Constructor of the class to initialize player's  values
     *
     *
     * @param playerID name of the player
     */
    public Artemis( String playerID) {
        super(playerID);
    }

    /**
     * Method is overridden in order to keep the value of initial space
     *
     * @param selection
     * @param model
     * @return
     */
    @Override
    public boolean makeSelection(Model model, Coordinate selection) {

        boolean result = super.makeSelection(model, selection);
        if (result) from = selection;
        return result;


    }

    /**
     * Method defines the behavior of the Artemis' turn
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
            if (isActive()) {
                nextState = new Build();
                togglePower();
            } else nextState = new Power();
        else if (currentState instanceof Build)
            nextState = new End();
        model.setCurrentState(nextState);
    }

    /**
     * In the method the description of power is defined, i.e. how power behaves while it's off and ongit
     *
     * @param model
     * @param destination
     * @return
     */
    @Override
    public boolean makePower(Model model, Coordinate destination) {

        if (isActive()) {
            model.setCurrentState(new Move());
            Coordinate from = model.getCurrentWorker();
            setValidCoordinate(new Checks(model, from).isNotWorker().isNotDome().isRisible().remove(this.from));
            if (containsInValidCoordinate(destination)) {

                moveWorker(model, destination);
                if (winCondition(model, from, destination)) model.setCurrentState(new Win());
                else {
                    nextPhase(model);

                }
                return true;
            } else return false;


        } else {
            model.setCurrentState(new Move());
            return makeBuild(model, destination);
        }

    }
}

