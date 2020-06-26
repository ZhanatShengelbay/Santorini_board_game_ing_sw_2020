package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

/**
 *  The power of god Artemis is described in this class. Player holding this god may move 1 more additional time,
 *  but not back to its initial space
 *
 *  @author CG51
 *  @version 1.1
 */
public class Artemis extends Player {
    /**
     * Class attribute
     */
    private Coordinate from;


    /**
     * Class constructor to set the Player's ID
     * @param playerID of type String
     * @param model
     */
    public Artemis( String playerID, Model model) {
        super(playerID, model);
    }

    /**
     * Method is overridden in order to keep the value of initial space
     *
     * @param selection of type Coordinate
     * @return true or false
     */
    @Override
    public boolean makeSelection(Coordinate selection) {

        boolean result = super.makeSelection(selection);
        if (result) from = selection;
        return result;


    }

    /**
     * Method defines the behavior of the Artemis' turn
     */
    @Override
    public void nextPhase() {
        State currentState = model.getCurrentState();
        State nextState = null;
        if (currentState instanceof Select)
            nextState = new Move();

        else if (currentState instanceof Move)
            if (isActive()) {
                nextState = new Build();
                togglePower();
            } else nextState = new Power();
        else if (currentState instanceof Build){
            nextState = new Select();
            model.nextPlayer();
        }
        model.setCurrentState(nextState);
    }

    /**
     * In the method the description of power is defined, i.e. how power behaves while it's off and ongit
     *
     * @param destination of type Coordinate
     * @return true or false
     */
    @Override
    public boolean makePower(Coordinate destination) {

        if (isActive()) {
            model.setCurrentState(new Move());
            Coordinate from = model.getCurrentWorker();
            setValidCoordinate(new Checks(model, from).isNotWorker().isNotDome().isRisible().remove(this.from));
            if (containsInValidCoordinate(destination)) {

                moveWorker(destination);
                if (winCondition(from, destination)) model.setCurrentState(new Win(this.getPlayerID()));
                else {
                    nextPhase();

                }
                return true;
            } else{
                model.setCurrentState(new Power());
                togglePower();
                return false;
            }


        } else {
            model.setCurrentState(new Build());
            return makeBuild(destination);
        }

    }
}

