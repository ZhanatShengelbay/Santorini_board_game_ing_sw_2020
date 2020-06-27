package it.polimi.ingsw.model.State;


import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.utility.Coordinate;

import java.io.Serializable;

/**
 * Class describes the Move state. it has the following methods:
 * @author CG51
 * @version 1.1
 */
public class Move implements State, Serializable {

    private static final long serialVersionUID = 8L;

    @Override
    public boolean handle(Coordinate choice, Model model){
       return model.getCurrentPlayer().makeMovement(choice);
    }

    @Override
    public String questionMessage() {
        return "Choose the coordinate where you want to move your worker";
    }

}
