package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.Build;
import it.polimi.ingsw.utility.Coordinate;

import java.util.List;

/**
 * This class describes the power of the Limus, Goddess of Famine. in opponent's turn:
 * Opponent worker can NOT build on spaces neighboring your Workers, unless building a dome to create a Complete Tower
 * @author CG51
 * @version 1.1
 * It has the following methods
 */
public class Limus extends PlayerWithGroundEffect {


    /**
     * Class constructor
     * @param playerID
     */
    public Limus(String playerID, Model model) {
        super(playerID, model);
    }

    /**
     * Overridden to add the ground effect
     * @param destination of type Coordinate
     * @return
     */
    @Override
    public boolean positionWorker(Coordinate destination) {
        //Add in the ground effect this player
        if(!model.getGroundEffects().contains(this))
            model.getGroundEffects().add(this);
        return super.positionWorker(destination);
    }

    /**
     * Overridden to
     * @param selection of type Coordinate
     * @return
     */
    @Override
    public boolean makeSelection(Coordinate selection) {
        model.getGroundEffects().remove(this);
        return super.makeSelection(selection);
    }

    /**
     * Overridden to implement the Limus' power
     * @param destination of type Coordinate
     * @return true or false
     */
    @Override
    public boolean makeBuild(Coordinate destination) {
        boolean result= super.makeBuild(destination);
        if(result) addEffect();
        return result;
    }

    /**
     * Method is used to check the Limus' affect
     * @param from starting point of tile
     * @param destination ending point of tile
     * @return true or false
     */
    @Override
    public boolean respectEffect(Coordinate from, Coordinate destination) {

        if(model.getCurrentState() instanceof Build) {
            if (model.getGrid().getTile(destination).getHeight() == TypeBlock.THIRD) return false;
            List<Coordinate> tileAroundDestination = model.getGrid().validTileAround(from);
            for (Coordinate c : tileAroundDestination) {
                Worker tmp = model.getGrid().getTile(c).getWorker();
                if (tmp!=null&&this.equals(tmp.getPlayer()))
                    return true;

            }
        }
        return false;
    }


    @Override
    public boolean makePower(Coordinate destination) {
        return false;
    }
}
