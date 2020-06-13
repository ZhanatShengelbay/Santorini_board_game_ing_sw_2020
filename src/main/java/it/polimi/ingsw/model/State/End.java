package it.polimi.ingsw.model.State;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.utility.Coordinate;

import java.io.Serializable;

/**
 * In this class the end of the game is described
 */
public class End implements State, Serializable {

    private static final long serialVersionUID = 6L;

    @Override
    public boolean handle(Coordinate choice, Model model) {
        if (model.getCurrentPlayer().isActive())
            model.getCurrentPlayer().togglePower();
        model.nextPlayer();
        model.setCurrentState(new Select());
        return true;
    }

    @Override
    public String questionMessage() {
        return "End of your turn";
    }
}
