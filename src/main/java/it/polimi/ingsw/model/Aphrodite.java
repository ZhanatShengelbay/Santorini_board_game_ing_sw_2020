package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

import java.util.List;

public class Aphrodite extends PlayerWithGroundEffect {

    /**
     * @param playerID
     */

    public Aphrodite(String playerID, Model model) {
        super(playerID);
        addEffect(model);
    }

    @Override
    public boolean makeSelection(Model model, Coordinate selection) {
        model.getGroundEffects().remove(this);
        return super.makeSelection(model, selection);
    }

    @Override
    public boolean makeBuild(Model model, Coordinate destination) {
        boolean result= super.makeBuild(model, destination);
        if(result) addEffect(model);
        return result;
    }

    @Override
    public boolean respectEffect(Model model, Coordinate from, Coordinate destination) {
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
    public boolean makePower(Model model, Coordinate destination) {
        throw new IllegalArgumentException();
    }
}
