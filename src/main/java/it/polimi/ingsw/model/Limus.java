package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.Build;
import it.polimi.ingsw.utility.Coordinate;

import java.util.List;

public class Limus extends PlayerWithGroundEffect {


    /**
     * @param playerID
     */
    public Limus(String playerID, Model model) {
        super(playerID, model);
    }

    @Override
    public boolean positionWorker(Coordinate destination) {
        //Add in the ground effect this player
        if(!model.getGroundEffects().contains(this))
            model.getGroundEffects().add(this);
        return super.positionWorker(destination);
    }
    
    @Override
    public boolean makeSelection(Coordinate selection) {
        model.getGroundEffects().remove(this);
        return super.makeSelection(selection);
    }

    @Override
    public boolean makeBuild(Coordinate destination) {
        boolean result= super.makeBuild(destination);
        if(result) addEffect();
        return result;
    }


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
