package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Coordinate;

import java.io.Serializable;

/**
 * Interface class GroundEffect specifies the effect of God Athena's power, i.e. If the player possessing Athena moved up on of his/her worker, opponent can not
 * @author CG51
 * @version 0.1
 */

public interface GroundEffect {

     boolean respectEffect(Model model, Coordinate from, Coordinate destination);


     void addEffect(Model model);


}
