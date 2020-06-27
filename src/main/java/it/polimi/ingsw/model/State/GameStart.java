package it.polimi.ingsw.model.State;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.controller.SetUpController;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.utility.Coordinate;

import java.io.Serializable;

/**
 * Class describes the GameStart state
 * @author CG51
 * @version 1.1
 */
public class GameStart implements State, Serializable {

    private static final long serialVersionUID = 7L;

    @Override
    public boolean handle(Coordinate choice, Model model) {
        return false;
    }

    @Override
    public String questionMessage() {
        return null;
    }
}
