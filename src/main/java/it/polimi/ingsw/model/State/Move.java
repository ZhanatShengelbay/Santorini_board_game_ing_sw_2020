package it.polimi.ingsw.model.State;


import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.utility.Coordinate;

/**
 * Class name is self-explained, it has the following also self-explained methods:
 */
public class Move implements State {


    @Override
    public boolean handle(Coordinate choice, Model model){
       return model.getCurrentPlayer().makeMovement(model,choice);
    }

    @Override
    public String questionMessage() {
        return null;
    }

}
