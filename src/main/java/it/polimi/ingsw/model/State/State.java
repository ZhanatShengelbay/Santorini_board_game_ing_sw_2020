package it.polimi.ingsw.model.State;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.utility.Coordinate;

public interface State {


    boolean handle(Coordinate choice, Model model);

    String questionMessage();










    
}
