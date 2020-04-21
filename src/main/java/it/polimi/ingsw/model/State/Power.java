package it.polimi.ingsw.model.State;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.utility.Coordinate;

public class Power implements State{


    @Override
    public boolean handle(Coordinate choice, Model model) {
       return model.getCurrentPlayer().makePower(model,choice);

    }

    @Override
    public String questionMessage() {
        return null;
    }
}
