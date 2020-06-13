package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.State.Win;
import it.polimi.ingsw.utility.Cardinal;
import it.polimi.ingsw.utility.Coordinate;

/**
 * Class describes the turn of the player who has the Minotaur's power, Bull-headed Monster.
 * The player holding Minotaur while moving, may move into the space of opponent's workers,
 * If the opponent's worker can be forced one space straight backwards to an unoccupied space at any level.
 * Class has the following methods:
 * @author CG51
 * @version 0.1
 */
public class Minotaur extends Player {
    /**
     * @param playerID
     */
    public Minotaur(String playerID) {
        super(playerID);
    }

    @Override
    public boolean makeMovement(Model model, Coordinate destination) {
        Worker wrkDestination = model.getGrid().getTile(destination).getWorker();
        if(wrkDestination==null || wrkDestination.getPlayer().equals(this))
            return super.makeMovement(model,destination);

        Coordinate from = model.getCurrentWorker();
        Coordinate opponentDestination= destination.shift(Cardinal.getDirection(from,destination));
        if(new Checks(model,destination,opponentDestination).isNotDome().isNotWorker().simpleGetResult())return false;
        //This block was made to avoid synergy problem with Aphrodite's power
        //Do first the movement and after check if this action is possible: if not, back to the initial condition

        model.getGrid().getTile(opponentDestination).setWorker(wrkDestination);

        setValidCoordinate(new Checks(model,model.getCurrentWorker()).isNotDome().isRisible());
        if (containsInValidCoordinate(destination)) {
            moveWorker(model, destination);
            if (winCondition(model, from, destination)) model.setCurrentState(new Win());
            else
                nextPhase(model);
            return true;
        }else {
            model.getGrid().getTile(destination).setWorker(wrkDestination);
            model.getGrid().getTile(opponentDestination).noneWorker();
            return false;
        }

    }

    @Override
    public boolean makePower(Model model, Coordinate destination) {
        throw new IllegalArgumentException();
    }
}
