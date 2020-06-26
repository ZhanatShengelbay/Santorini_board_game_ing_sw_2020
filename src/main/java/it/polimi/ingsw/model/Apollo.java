package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

/**
 * This class describes the rules while one of the players has Apollo god card that are different from standard case.
 * The player holding Apollo during his/her move may use the power of Apollo,
 * which forces opponent's worker to be placed in the current coordinate of forcing player's worker.
 * The thing to remember is if opponent's worker forced move up into the 3rd level from the 2nd or
 * from 3rd level to another the same level does not trigger a win.
 * @author CG51
 * @version 1.1
 */
public class Apollo extends Player {
    /**
     * Constructor to set that player from the parent class owns the Apollo's power
     * @param playerID
     * @param model
     */
    public Apollo( String playerID, Model model) {
        super( playerID, model);
    }

    /**
     * Overriden method to define the behavior of FSM when player owns the god Apollo
     */
    @Override
    public void nextPhase() {
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
     * To modify the Move event in accordance with the Apollo's power
     * Along with the standard move condition this method allows to Apollo to swap by forcing the opponent's worker
     * @param destination The input choice
     * @return
     */
    @Override
    public boolean makeMovement(Coordinate destination) {
        Worker wrkDestination = model.getGrid().getTile(destination).getWorker();
        if(wrkDestination==null || wrkDestination.getPlayer().equals(this))
            return super.makeMovement(destination);
        //This block was made to avoid synergy problem with Aphrodite's power
        //Do first the movement and after check if this action is possible: if not, back to the initial condition
        Coordinate from = model.getCurrentWorker();
        Worker wrkFrom = model.getGrid().getTile(model.getCurrentWorker()).getWorker();
        model.getGrid().getTile(from).setWorker(wrkDestination);

        setValidCoordinate(new Checks(model,model.getCurrentWorker()).isNotDome().isRisible());
        if (containsInValidCoordinate(destination)) {
                model.getGrid().getTile(destination).setWorker(wrkFrom);
                model.setCurrentWorker(destination);
            if (winCondition(from, destination)) model.setCurrentState(new Win(this.getPlayerID()));
            else
                nextPhase();
            return true;
        }else {
            model.getGrid().getTile(destination).setWorker(wrkDestination);
            model.getGrid().getTile(from).setWorker(wrkFrom);
            return false;
        }

    }




    @Override
    public boolean makePower(Coordinate destination) {
        throw new IllegalStateException();

    }

}
