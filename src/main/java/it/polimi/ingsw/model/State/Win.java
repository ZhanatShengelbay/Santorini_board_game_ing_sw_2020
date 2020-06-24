package it.polimi.ingsw.model.State;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.utility.Coordinate;

import java.io.Serializable;

/**
 * This class describes what happens when the game is in "WIN" status.
 */
public class Win implements State, Serializable {

    private static final long serialVersionUID = 12L;
    private String winner;

    /**
     * Overriden getter method that does not allow the choice of coordinate while there is a winner in the game
     * @return null
     */

    public Win(String winner){
        this.winner = winner;
    }

    public String getWinner(){
        return winner;
    }

    @Override
    public boolean handle(Coordinate choice, Model model) {
        return false;
    }

    @Override
    public String questionMessage() {
        return null;
    }
}
