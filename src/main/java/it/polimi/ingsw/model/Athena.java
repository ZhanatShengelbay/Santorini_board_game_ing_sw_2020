package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

/**
 *  Class describes the power of Athena is described in this class. if player owns the Athena, if s/he moved up any of her/his workers,
 *  on opponent's turn opponent can NOT move up, it is carried out through an abstract class that is extended
 *  @author CG51
 *  @version 1.1
 */

public class Athena extends PlayerWithGroundEffect {
    /**
     * Class attribute
     */
    private Coordinate from;

    /**
     * Constructor is used to initialize the object.
     * @param playerID players name
     */
    public Athena(String playerID, Model model) {
        super(playerID, model);

    }

    /**
     * Method is overridden to keep info from where player moved
     * @param selection
     */
    @Override
    public boolean makeSelection(Coordinate selection) {
        model.getGroundEffects().remove(this);
        boolean result = super.makeSelection(selection);
        if(result)from = selection;
        return result;

    }

    /**
     * Method is overridden to activate the ground effect if player moved UP
     * @param destination The input choice of type Coordinate
     * @return true or false
     */
    @Override
    public boolean makeMovement(Coordinate destination) {
        boolean result = super.makeMovement(destination);
        if(result)
            if(model.getGrid().HeightDifference(from,destination)==1)
                addEffect();
        return result;
    }




    /**
     * Method throws exception according to the power of Athena when opponent tries to move up
     * @param destination of type Coordinate
     * @return
     */
    @Override
    public boolean makePower(Coordinate destination) {
        throw new IllegalStateException();

    }

    /**
     * The method defines that if Athena moved up, opponent should respect her power
     * @param from starting point of tile
     * @param destination ending point of tile
     *
     * @return true or false
     */
    @Override
    public boolean respectEffect(Coordinate from,Coordinate destination) {
        if(model.getCurrentState() instanceof Move)
            return model.getGrid().HeightDifference(from, destination) > 0;
        else return false;

    }


}
