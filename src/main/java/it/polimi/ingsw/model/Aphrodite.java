package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

import java.util.List;

/**
 * Class describes the power of Aphrodite, Goddess of love.
 * Player holding the Aphrodite has an effect on opponent's move.
 * During any move, if opponent's (any) worker starts its turn neighboring one of Aphrodite's workers,
 * its (opponent's) last move must to be a space neighboring one of Aphrodite's workers
 * Class has the following method to exploit described above power
 * @author CG51
 * @version 0.1
 */
public class Aphrodite extends PlayerWithGroundEffect {

    /**
     * Constructor to initialize Aphrodite object
     * @param playerID possessing Aphrodite
     * @param model to be added an effect
     */

    public Aphrodite(String playerID, Model model) {
        super(playerID, model);

    }



    @Override
    public boolean positionWorker(Coordinate destination) {
        //Add in the ground effect this player
        if(!model.getGroundEffects().contains(this))
            model.getGroundEffects().add(this);
        return super.positionWorker(destination);
    }

    /**
     * Overridden to
     * @param selection
     * @return
     */
    @Override
    public boolean makeSelection(Coordinate selection) {
        model.getGroundEffects().remove(this);
        return super.makeSelection(selection);
    }

    /**
     *
     * @param destination
     * @return
     */

    @Override
    public boolean makeBuild(Coordinate destination) {
        boolean result= super.makeBuild(destination);
        if(result) addEffect();
        return result;
    }

    /**
     *
     * @param from starting point of tile
     * @param destination ending point of tile
     * @return
     */
    @Override
    public boolean respectEffect(Coordinate from, Coordinate destination) {
        if (model.getCurrentState() instanceof Move) {
            List<Coordinate> aroundTile = model.getGrid().validTileAround(from);
            for (Coordinate c : aroundTile) {
                Worker wrkNearFrom = model.getGrid().getTile(c).getWorker();
                //this trigger the search of an aphrodite worker near the destination tile
                if (wrkNearFrom != null && wrkNearFrom.getPlayer().equals(this)) {
                    List<Coordinate> aroundDestination = model.getGrid().validTileAround(destination);
                    for (Coordinate d : aroundDestination) {
                        Worker wrkNearDestination = model.getGrid().getTile(d).getWorker();
                        if (wrkNearDestination != null && wrkNearDestination.getPlayer().equals(this))
                            return false;
                    }
                    return true;
                }

            }
        }
        return false;

    }






    @Override
    public boolean makePower(Coordinate destination) {
        throw new IllegalArgumentException();
    }
}
