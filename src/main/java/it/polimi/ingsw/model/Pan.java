package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

import java.util.List;

/**
 * This class describes one of gods - Pan's power during the game;
 * Its power makes the player holding Pan to be able to win, in addition to
 * the normal win condition - moving up from the 2nd level to 3rd,
 * also if one of the Pan's worker moves down 2 or more levels
 * @author CG51
 * @version 0.1
 */
public class Pan extends Player {


    /**
     * Constructor to set the player's info
     * @param playerID
     */
    public Pan( String playerID) {
        super( playerID);
    }

    /**
     * Overridden in order to add Pan's power, i.e, Player that holds Pan wins if his worker moves down 2 or 3 levels together with ordinary case
     * @param model
     * @param from
     * @param destination
     * @return
     */
    @Override
    public boolean winCondition(Model model, Coordinate from, Coordinate destination) {
        int heightDiff = model.getGrid().HeightDifference(from, destination);
        if (heightDiff <= -2) {
            return true;
        }
        return super.winCondition(model, from, destination);
    }



    /**
     * Power of Pan is used if it's active checks immediately if he wins, otherwise moves into the next phase of model
     * @param model
     * @param destination
     */
    @Override
    public boolean makePower(Model model,Coordinate destination) {
        throw new IllegalArgumentException();

    }
}