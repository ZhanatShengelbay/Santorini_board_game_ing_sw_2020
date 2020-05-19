package it.polimi.ingsw.model.State;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.utility.Coordinate;

/**
 * In this class handle() and questionMessage() methods are overridden
 * to perform with accordance with Select's needs
 */
public class Select implements State {

    @Override
    public boolean handle(Coordinate choice, Model model) {
        return model.getCurrentPlayer().makeSelection(model,choice);

    }

    /**
     * Method is used to send a message from Controller to View, this way the View knows whether it is his/her turn or not
     * @return
     */
    @Override
    public String questionMessage() {
        return null;
    }
}