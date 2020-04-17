package it.polimi.ingsw.model.State;

import it.polimi.ingsw.utility.Coordinate;

/**
 * The Interface class State configures Controller's behaviour and are communicated to the View either from the Model or Controller
 *  State interface is used for:
 *  <li></li> register the Choice of type Coordinate made by player</li>
 *  <li> enable the Controller to know the actual(current) phase at the moment, so that the Controller calls the right method of the Player (depending on the type of State, it might be Select, Move, Build) </li>
 */

public interface State {









    
}
