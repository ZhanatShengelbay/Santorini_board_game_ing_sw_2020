package it.polimi.ingsw.model.State;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.utility.Coordinate;
import java.io.Serializable;

/**
 * Class contains 2 methods: handle() to manage the particular input and
 * the second one, is used to send a message from Controller to View. This way View knows if it is her/his turn or not
 * @author CG51
 * @version 0.1
 */

public class Power implements State, Serializable {

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
        return "You can active your power and select the coordinate";
    }
}
