package it.polimi.ingsw.model.State;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.utility.Coordinate;

/**
 * State interface to implement the states of the model. It has the following methods:
 * @author CG51
 * @version 1.1
 */
public interface State {


    boolean handle(Coordinate choice, Model model);

    String questionMessage();










    
}
