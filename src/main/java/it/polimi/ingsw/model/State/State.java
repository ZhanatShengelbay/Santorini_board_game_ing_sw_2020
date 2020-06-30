package it.polimi.ingsw.model.State;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.utility.Coordinate;

/**
 * Manages, checks the input.
 * @author CG51
 * @version 1.1
 */
public interface State {


    boolean handle(Coordinate choice, Model model);

    String questionMessage();










    
}
