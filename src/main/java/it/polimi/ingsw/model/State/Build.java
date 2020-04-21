package it.polimi.ingsw.model.State;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.utility.Coordinate;

/**
 * The class Build is responsible for build action of the game
 * @author CG51
 * @version 0.1
 */

public class Build implements State {

    @Override
    public boolean handle(Coordinate choice, Model model) {
       return model.getCurrentPlayer().makeBuild(model, choice);

    }

    @Override
    public String questionMessage() {
        return null;
    }
}
