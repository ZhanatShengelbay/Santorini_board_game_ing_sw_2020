package it.polimi.ingsw.model.State;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.utility.Coordinate;

/**
 * Implementation of a particular state, a power phase during the end of a turn. This state need particular exception to be handled
 * @author CG51
 * @version 0.1
 */

public class PowerEnd extends Power {
    private static final long serialVersionUID = 10L;

    /**
     * Method is overridden to manage the specific input
     * @param choice
     * @param model
     * @return
     */
    @Override
    public boolean handle(Coordinate choice, Model model) {
        return model.getCurrentPlayer().makePower(choice);

    }
    /**
     * Method to send a message to View from Controller
     * @return
     */
    @Override
    public String questionMessage() {
        return super.questionMessage()+" If you just want to end just send a input";
    }
}
