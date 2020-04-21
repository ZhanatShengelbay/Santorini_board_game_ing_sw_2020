package it.polimi.ingsw.model;

import java.util.List;

public abstract class PlayerWithGroundEffect extends Player implements GroundEffect{
    /**
     * @param playerID
     */
    public PlayerWithGroundEffect( String playerID) {
        super( playerID);
    }

    @Override
    public void addEffect(Model model) {
        model.getGroundEffects().add(this);

    }
}
