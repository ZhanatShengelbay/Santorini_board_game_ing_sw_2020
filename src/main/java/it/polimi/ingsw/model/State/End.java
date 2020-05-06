package it.polimi.ingsw.model.State;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.utility.Coordinate;

import java.io.Serializable;

/**
 * In this class the end of the game is described
 */
public class End implements State, Serializable {

    private static final long serialVersionUID = 6L;

    /**
     * Overriden getter method not to allow the choice of coordinate
     * @return null
     */


    @Override
    public boolean handle(Coordinate choice, Model model) {
        return false;
    }

    @Override
    public String questionMessage() {
        return null;
    }
}
