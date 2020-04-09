package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.State.State;
import it.polimi.ingsw.utility.Coordinate;

/**
 * Interface class GroundEffect specifies the effect of God Athena's power, i.e. If the player possessing Athena moved up on of his/her worker, opponent can not
 * @author CG51
 * @version 0.1
 */

public interface GroundEffect {

     /**
      * It is called if one of the players possesses Athena's power
      * @param model
      * @param destination ending point of tile
      * @param from starting point of tile
      * @return
      */
     boolean respectEffect(Model model, Coordinate destination, Coordinate from);

     /**
      * adds affect to model
      * @param model
      */
     void addEffect(Model model);


}
