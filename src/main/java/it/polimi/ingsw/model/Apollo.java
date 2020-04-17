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
     * @param workers worker list of the player
     * @param playerID
     */
    public Apollo(List<Worker> workers, String playerID) {
        super(workers, playerID);
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
            nextState=new Move(null);
        else if(currentState instanceof Move)
            nextState=new Build(null);
        else if(currentState instanceof Build)
            nextState=new End();
        model.setCurrentState(nextState);

    }

    @Override
    public void makeMovement(Model model, Move move) {
        Coordinate destination = move.getChoice();
        Coordinate from = model.getCurrentWorker();
        setValidCoordinate(new Checks(model,model.getCurrentWorker()).isNotDome().isRisible());
        if (containsValidCoordinate(destination)) {
            Worker wrkDestination = model.getGrid().getTile(destination).getWorker();

            if(wrkDestination==null)
                moveWorker(model,destination);
            else if(!wrkDestination.getPlayer().equals(this)) {

                Worker wrkFrom = model.getGrid().getTile(model.getCurrentWorker()).getWorker();
                model.getGrid().getTile(destination).setWorker(wrkFrom);
                model.getGrid().getTile(model.getCurrentWorker()).setWorker(wrkDestination);
                model.setCurrentWorker(destination);
                if (winCondition(model, from, destination)) model.setCurrentState(new Win());
                else
                    nextPhase(model);
            }else return;
        } else return;
    }



    @Override
    public void makePower(Model model, Choice choice) {
        throw new IllegalStateException();

    }
}
