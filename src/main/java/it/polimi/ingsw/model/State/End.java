package it.polimi.ingsw.model.State;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.utility.Coordinate;

/**
 * In this class the end of the game is described
 */
public class End implements State {

    /**
     * Overriden getter method not to allow the choice of coordinate
     * @return null
     */


    @Override
    public boolean handle(Coordinate choice, Model model) {
        if (model.getCurrentPlayer().isActive())
            model.getCurrentPlayer().togglePower();
        model.nextPlayer();
        return true;
    }

    @Override
    public String questionMessage() {
        return null;
    }
}
