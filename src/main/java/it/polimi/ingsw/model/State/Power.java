package it.polimi.ingsw.model.State;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.utility.Coordinate;

import java.io.Serializable;

public class Power implements State, Serializable {

    private static final long serialVersionUID = 10L;

    @Override
    public boolean handle(Coordinate choice, Model model) {
       return model.getCurrentPlayer().makePower(model,choice);

    }

    @Override
    public String questionMessage() {
        return null;
    }
}
