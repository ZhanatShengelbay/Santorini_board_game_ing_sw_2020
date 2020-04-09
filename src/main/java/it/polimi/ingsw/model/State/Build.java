package it.polimi.ingsw.model.State;

import it.polimi.ingsw.utility.Coordinate;

/**
 * The class Build is responsible for build action of the game
 * @author CG51
 * @version 0.1
 */

public class Build implements State {
   /**
    * The class attribute field where choice values of type Coordinate are stored
    */
    Coordinate choice;

    /**
     * this constructor sets the current choice to the coordinate of worker that we are using
     * @param coordinate value to be passed
     */
    public Build(Coordinate coordinate){
        this.choice = coordinate;
    }

    /**
     *method returns the Coordinate value that was chosen
     * @return choice that was set Build
     */
    public Coordinate getChoice() {
        return choice;
    }
}
