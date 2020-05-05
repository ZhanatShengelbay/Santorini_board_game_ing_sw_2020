package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Coordinate;

import java.util.List;

public abstract class PlayerWithGroundEffect extends Player{
    /**
     * @param playerID
     */
    public PlayerWithGroundEffect( String playerID) {
        super( playerID);
    }

    /**
     * adds affect to model
     * @param model
     */
    public void addEffect(Model model) {
        model.getGroundEffects().add(this);

    }
    /**
     * It is called if one of the players possesses a ground power
     * @param model
     * @param from starting point of tile
     * @param destination ending point of tile
     * @return
     */
    abstract  public boolean respectEffect(Model model, Coordinate destination, Coordinate from);
}
