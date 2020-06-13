package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.Build;
import it.polimi.ingsw.utility.Coordinate;

import java.util.List;

public class Limus extends PlayerWithGroundEffect {


    /**
     * @param playerID
     */
    public Limus(String playerID) {
        super(playerID);
    }

    @Override
    public boolean positionWorker(Model model, Coordinate destination) {
        //Add in the ground effect this player
        if(!model.getGroundEffects().contains(this))
            model.getGroundEffects().add(this);
        return super.positionWorker(model, destination);
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
    public boolean makePower(Model model, Coordinate destination) {
        return false;
    }
}
