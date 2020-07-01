package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Coordinate;

import java.util.List;

/**
 * Class is used for players if their God has an effect
 * such as not allowing opponent to move up or should move to neighboring space (Athena, Aphrodite).
 * This class inherits all features of the Player and adds specified effects, these effects are exploited in appropriate God classes
 * @author CG51
 * @version 0.1
 */
public abstract class PlayerWithGroundEffect extends Player{

    /**
     * Constructor for the players owning groud effect
     * @param playerID
     */
    public PlayerWithGroundEffect(String playerID, Model model) {
        super( playerID, model);
    }

    /**
     * adds effect to the model
     */
    public void addEffect() {
        model.getGroundEffects().add(this);

    }
    /**
     * It is called if one of the players possesses a ground power
     * @param from starting point of tile
     * @param destination ending point of tile
     * @return true or false
     */
    abstract  public boolean respectEffect(Coordinate from, Coordinate destination);
    /**
     * If the player lose, remove the groundEffect from the list in model
     */

    @Override
    protected void defeatHandler() {
        model.getGroundEffects().remove(this);
        super.defeatHandler();
    }
}
