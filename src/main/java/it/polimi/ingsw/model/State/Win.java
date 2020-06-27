package it.polimi.ingsw.model.State;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.utility.Coordinate;

import java.io.Serializable;

/**
 * This class describes what happens when the game is in "WIN" status. It has the following methods:
 * @author CG51
 * @version 1.1
 */
public class Win implements State, Serializable {

    /**
     * Class attributes
     */
    private static final long serialVersionUID = 12L;
    private String winner;


    /**
     * Constructor to make a reference to the current winner
     * @param winner
     */
    public Win(String winner){
        this.winner = winner;
    }

    /**
     * getter to access the winner
     * @return
     */
    public String getWinner(){
        return winner;
    }



    @Override
    public boolean handle(Coordinate choice, Model model) {

        return true;
    }

    @Override
    public String questionMessage() {
        return null;
    }

    /**
     * setter to set the winner
     * @param model
     * @param winner
     * @return
     */
    public Win setWinner(Model model,String winner)
    {
        model.setWinner(winner);
        return this;
    }
}
