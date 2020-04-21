package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

import java.util.List;

/**
 * This class describes the rules while one of the players has Apollo god card that are different from standard case.
 * The player holding Apollo during his/her move may use the power of Apollo,
 * which forces opponent's worker to be placed in the current coordinate of forcing player's worker.
 * The thing to remember is if opponent's worker forced move up into the 3rd level from the 2nd or
 * from 3rd level to another the same level does not trigger a win.
 * @author CG51
 * @version 0.1
 */
public class Apollo extends Player {
    /**
     * Constructor to set that player from the parent class owns the Apollo's power
     *
     * @param playerID
     */
    public Apollo( String playerID) {
        super( playerID);
    }

    /**
     * Overriden method to define the behavior of FSM when player owns the god Apollo
     * @param model The model where set the new current State
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

    @Override
    public boolean makeMovement(Model model, Coordinate destination) {
        Coordinate from = model.getCurrentWorker();
        setValidCoordinate(new Checks(model,model.getCurrentWorker()).isNotDome().isRisible());
        if (containsInValidCoordinate(destination)) {
            Worker wrkDestination = model.getGrid().getTile(destination).getWorker();

            if(wrkDestination==null)
                moveWorker(model,destination);
            else if(!wrkDestination.getPlayer().equals(this)) {

                model.getGrid().getTile(model.getCurrentWorker()).swapWorker(model.getGrid().getTile(destination));
                model.setCurrentWorker(destination);

            }else return false;

            if (winCondition(model, from, destination)) model.setCurrentState(new Win());
            else
                nextPhase(model);
            return true;
        }else return false;
    }



    @Override
    public boolean makePower(Model model, Coordinate destination) {
        throw new IllegalStateException();

    }

}
