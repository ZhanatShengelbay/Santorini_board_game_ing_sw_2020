package it.polimi.ingsw.model.State;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.utility.Coordinate;

/**
 * This class describes what happens when the game is in "WIN" status.
 */
public class Win implements State {

    /**
     * Overriden getter method that does not allow the choice of coordinate while there is a winner in the game
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
